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
	
	public List<Address> getAddresses(long person_id) {
		return personAddressDao.getAddresses(person_id);
    }
	
    public PersonAddress assignAddress(long person_id, long address_id) {
    	Person person = mainDao.load(person_id);
    	Address address = addressDao.load(address_id);
    	
    	Date now = new Date();
    	PersonAddress personAddress = new PersonAddress(person, address, true, now, null);
    	
    	personAddressDao.persist( personAddress );
    	
    	return personAddress;
    }

}
