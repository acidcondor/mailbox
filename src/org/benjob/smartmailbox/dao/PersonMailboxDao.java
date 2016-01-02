package org.benjob.smartmailbox.dao;

import java.util.List;

import org.benjob.smartmailbox.model.PersonMailbox;

public interface PersonMailboxDao extends BaseDao<PersonMailbox> {
	public List<PersonMailbox> getAddresses(Long person_id);
	
	public List<PersonMailbox> getPersons(Long address_id);
}
