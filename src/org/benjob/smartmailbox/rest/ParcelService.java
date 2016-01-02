package org.benjob.smartmailbox.rest;

import org.benjob.smartmailbox.bo.ParcelBo;
import org.benjob.smartmailbox.bo.PersonBo;
import org.benjob.smartmailbox.model.Parcel;
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
@RequestMapping("/parcel")
public class ParcelService {
    
    @Autowired
    ParcelBo parcelBo;
    
    @Autowired 
    PersonBo personBo;

    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Parcel createParcel(@PathVariable("id") long person_id, @RequestBody Parcel parcel) {
        try {
            parcelBo.create(parcel, person_id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
        
        return parcel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Parcel getParcel(@PathVariable("id") long id) {
        Parcel parcel = null;
        try {
            parcel = parcelBo.getById(id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
        
        if ( parcel == null ) {
            throw new ResourceNotFoundException();
        }
        
        return parcel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Status deleteParcel(@PathVariable("id") long id) {
        try {
            parcelBo.delete(id);
            return new Status(1, "Parcel deleted Successfully!");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }

    }

}
