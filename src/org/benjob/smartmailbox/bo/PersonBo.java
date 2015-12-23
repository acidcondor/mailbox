package org.benjob.smartmailbox.bo;

import java.util.List;

import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.Person;
import org.benjob.smartmailbox.model.PersonAddress;

public interface PersonBo extends BaseBo<Person> {
	public List<Address> getAddresses(long person_id);
	
	public PersonAddress assignAddress(long person_id, long address_id);
}
