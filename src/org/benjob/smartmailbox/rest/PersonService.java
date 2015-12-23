package org.benjob.smartmailbox.rest;

import org.benjob.smartmailbox.bo.PersonBo;
import org.benjob.smartmailbox.model.Person;
import org.benjob.smartmailbox.model.Status;
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
    public @ResponseBody Status addPerson(@RequestBody Person person) {
        try {
            personBo.create(person);
            return new Status(1, "Employee added Successfully !");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Person getPerson(@PathVariable("id") long id) {
        Person person = null;
        try {
            person = personBo.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
        try {
            personBo.delete(id);
            return new Status(1, "Employee deleted Successfully !");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }

    }
}