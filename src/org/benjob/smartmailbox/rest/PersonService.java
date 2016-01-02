package org.benjob.smartmailbox.rest;

import java.util.List;

import org.benjob.smartmailbox.bo.PersonBo;
import org.benjob.smartmailbox.model.Person;
import org.benjob.smartmailbox.model.PersonMailbox;
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
@RequestMapping("/person")
public class PersonService {

    @Autowired
    PersonBo personBo;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Person createPerson(@RequestBody Person person) {
        try {
            personBo.create(person);
        } catch (Exception e) {
        	throw new InternalErrorException();
        }
        
        return person;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Person getPerson(@PathVariable("id") long id) {
        Person person = null;
        try {
            person = personBo.getById(id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
        
        if ( person == null ) {
        	throw new ResourceNotFoundException();
        }
        
        return person;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
        try {
            personBo.delete(id);
            return new Status(1, "Person deleted Successfully!");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }
    }
    
    @RequestMapping(value = "/{id}/assignPrimaryMailbox/{mailbox_id}", method = RequestMethod.GET)
    public @ResponseBody PersonMailbox assignPrimaryMailbox(@PathVariable("id") long id, @PathVariable("mailbox_id") long mailbox_id) {
        try {
            return personBo.assignPrimaryMailbox(id, mailbox_id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }
    
    @RequestMapping(value = "/{id}/assignSecondaryMailbox/{mailbox_id}", method = RequestMethod.GET)
    public @ResponseBody PersonMailbox assignSecondaryMailbox(@PathVariable("id") long id, @PathVariable("mailbox_id") long mailbox_id) {
        try {
            return personBo.assignSecondaryMailbox(id, mailbox_id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }
    
    @RequestMapping(value = "/{id}/unassignMailbox/{mailbox_id}", method = RequestMethod.GET)
    public @ResponseBody Status unassignMailbox(@PathVariable("id") long id, @PathVariable("mailbox_id") long mailbox_id) {
        try {
            personBo.unassignMailbox(id, mailbox_id);
            return new Status(1, "Address unassigned Successfully!");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }
    }
    
    @RequestMapping(value = "/{id}/mailboxes", method = RequestMethod.GET)
    public @ResponseBody List<PersonMailbox> getMailboxes(@PathVariable("id") long id) {
        try {
            return personBo.getMailboxes(id);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }
}