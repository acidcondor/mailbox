package org.benjob.smartmailbox.rest;

import java.io.IOException;

import org.benjob.smartmailbox.model.SMSMessage;
import org.benjob.smartmailbox.model.Status;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SMSService {

    @RequestMapping(value = "/{phone}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public @ResponseBody Status sendSMS(@PathVariable("phone") String phone, @RequestBody SMSMessage message) {
        try {
            Long phoneNumber = null;
            
            if ( phone != null ) {
                try {
                    phoneNumber = new Long(phone);
                } catch ( NumberFormatException e ) {
                   return new Status(0, "invalid phone number");
                }
            }
            
            //We need to send a SMS notification
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(new String[]{"/bin/bash","/Users/bjob/send_sms.sh", phoneNumber.toString(), message.getContent() });

            try {
                pr.waitFor();
                
                return new Status(1, "Message sent Successfully!");
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new Status(0, "Error sending message.");
    }

}
