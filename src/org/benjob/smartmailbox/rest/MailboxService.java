package org.benjob.smartmailbox.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.benjob.smartmailbox.bo.MailboxBo;
import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.Mailbox;
import org.benjob.smartmailbox.model.Status;
import org.benjob.smartmailbox.rest.exceptions.InternalErrorException;
import org.benjob.smartmailbox.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailbox")
public class MailboxService {

    @Autowired
    MailboxBo mailboxBo;
    
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Mailbox addMailbox(@RequestBody Mailbox mailbox, @PathVariable("id") long address_id) {
        try {
            mailboxBo.create(mailbox, address_id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
        
        return mailbox;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Mailbox getMailbox(@PathVariable("id") long id) {
        Mailbox mailbox = null;
        try {
            mailbox = mailboxBo.getById(id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
        
        if ( mailbox == null ) {
            throw new ResourceNotFoundException();
        }
        
        return mailbox;
    }
    
    @RequestMapping(value = "/lock/{phoneNumber}", method = RequestMethod.GET)
    public void lockMailbox(@PathVariable("phoneNumber") long phoneNumber) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://192.168.2.1:8080/omnipi/http/monitor/mailbox/" + phoneNumber + "/locked");
    
            httpClient.execute(request);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/unlock/{phoneNumber}", method = RequestMethod.GET)
    public void unlockMailbox(@PathVariable("phoneNumber") long phoneNumber) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://192.168.2.1:8080/omnipi/http/monitor/mailbox/" + phoneNumber + "/unlocked");
    
            httpClient.execute(request);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}/address", method = RequestMethod.GET)
    public @ResponseBody Address getAddress(@PathVariable("id") long mailbox_id) {
        Address address = null;
        try {
            address = mailboxBo.getMailboxAddress(mailbox_id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
        
        if ( address == null ) {
            throw new ResourceNotFoundException();
        }
        
        return address;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Status deleteMailbox(@PathVariable("id") long id) {
        try {
            mailboxBo.delete(id);
            return new Status(1, "Mailbox deleted Successfully!");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }

    }

}
