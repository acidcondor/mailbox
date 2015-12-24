package org.benjob.smartmailbox.bo;

import java.util.Date;
import java.util.List;

import org.benjob.smartmailbox.dao.AddressDao;
import org.benjob.smartmailbox.dao.PersonAddressDao;
import org.benjob.smartmailbox.dao.PersonDao;
import org.benjob.smartmailbox.model.Person;
import org.benjob.smartmailbox.model.PersonAddress;
import org.benjob.smartmailbox.model.Address;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PersonBoImpl extends BaseBoImpl<Person, PersonDao> implements PersonBo {

	AddressDao addressDao;
	
	PersonAddressDao personAddressDao;
	
	public void setAddressDao( AddressDao addressDao ) {
		this.addressDao = addressDao;
	}
	
	public void setPersonAddressDao( PersonAddressDao personAddressDao ) {
		this.personAddressDao = personAddressDao;
	}
	
	public List<PersonAddress> getAddresses(long person_id) {
		return personAddressDao.getAddresses(person_id);
    }
	
	public List<PersonAddress> getPersons(long address_id) {
	    return personAddressDao.getPersons(address_id);
	}
	
	public PersonAddress assignAddress(long person_id, long address_id) {
	    return assignAddress( person_id, address_id, Boolean.FALSE);
	}
	
    public PersonAddress assignAddress(long person_id, long address_id, Boolean isOwner) {
        if ( isOwner == null ) {
            isOwner = Boolean.FALSE;
        }
        
    	List<PersonAddress> persons = personAddressDao.getPersons(address_id);
    	
    	if ( persons != null && !persons.isEmpty() ) {
            for ( PersonAddress _person : persons ) {
                if ( _person.getPerson().getId() == person_id ) {
                    throw new RuntimeException("This address is already assigned to this person!");
                }
                
                if ( isOwner && _person.isOwner() ) {
                    throw new RuntimeException("This address already has an owner!");
                }
            }
        }
    	
    	Person person = mainDao.load(person_id);
        Address address = addressDao.load(address_id);
    	
    	Date now = new Date();
    	PersonAddress personAddress = new PersonAddress(person, address, isOwner, now, null);
    	
    	personAddressDao.persist( personAddress );
    	
    	return personAddress;
    }
    
    public void unassignAddress(long person_id, long address_id) {
        List<PersonAddress> pa = getPersons(address_id);
        
        PersonAddress relationToDelete = null;
        if ( pa != null && !pa.isEmpty() ) {
            for ( PersonAddress person : pa ) {
                if ( person.getPerson().getId() == person_id ) {
                    relationToDelete = person;
                    break;
                }
            }
        }
        
        if ( relationToDelete != null ) {
            personAddressDao.delete(relationToDelete);
        } else {
            throw new RuntimeException("This address is not assigned to this person");
        }
    }

}
