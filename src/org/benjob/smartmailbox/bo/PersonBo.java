package org.benjob.smartmailbox.bo;

import java.util.List;

import org.benjob.smartmailbox.model.Person;
import org.benjob.smartmailbox.model.PersonMailbox;

public interface PersonBo extends BaseBo<Person> {
	public List<PersonMailbox> getMailboxes(long person_id);
	
	public List<PersonMailbox> getPersons(long mailbox_id);
	
	public PersonMailbox assignPrimaryMailbox(long person_id, long mailbox_id);
	
	public PersonMailbox assignSecondaryMailbox(long person_id, long mailbox_id);
	
	public PersonMailbox assignMailbox(long person_id, long mailbox_id, Boolean isOwner);
	
	public void unassignMailbox(long person_id, long mailbox_id);
}
