package org.benjob.smartmailbox.dao;

import java.util.List;

import org.benjob.smartmailbox.model.PersonAddress;

public interface PersonAddressDao extends BaseDao<PersonAddress> {
	public List<PersonAddress> getAddresses(Long person_id);
	
	public List<PersonAddress> getPersons(Long address_id);
}
