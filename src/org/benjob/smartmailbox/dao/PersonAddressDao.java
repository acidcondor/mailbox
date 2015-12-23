package org.benjob.smartmailbox.dao;

import java.util.List;

import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.PersonAddress;

public interface PersonAddressDao extends BaseDao<PersonAddress> {
	public List<Address> getAddresses(Long person_id);
}
