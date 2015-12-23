package org.benjob.smartmailbox.rest;

import org.benjob.smartmailbox.bo.AddressBo;
import org.benjob.smartmailbox.model.Address;
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
@RequestMapping("/address")
public class AddressService {
	
	@Autowired
    AddressBo addressBo;
	
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Address addPerson(@RequestBody Address person) {
        try {
            addressBo.create(person);
        } catch (Exception e) {
        	throw new InternalErrorException();
        }
        
        return person;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Address getAddress(@PathVariable("id") long id) {
    	Address address = null;
        try {
        	address = addressBo.getById(id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
        
        if ( address == null ) {
        	throw new ResourceNotFoundException();
        }
        
        return address;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
        try {
            addressBo.delete(id);
            return new Status(1, "Address deleted Successfully!");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }

    }
}
