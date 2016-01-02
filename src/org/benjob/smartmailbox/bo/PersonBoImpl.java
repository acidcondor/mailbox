package org.benjob.smartmailbox.bo;

import java.util.Date;
import java.util.List;

import org.benjob.smartmailbox.dao.MailboxDao;
import org.benjob.smartmailbox.dao.PersonMailboxDao;
import org.benjob.smartmailbox.dao.PersonDao;
import org.benjob.smartmailbox.model.Person;
import org.benjob.smartmailbox.model.PersonMailbox;
import org.benjob.smartmailbox.model.Mailbox;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PersonBoImpl extends BaseBoImpl<Person, PersonDao> implements PersonBo {

	MailboxDao mailboxDao;
	PersonMailboxDao personMailboxDao;
	
	public void setMailboxDao( MailboxDao mailboxDao ) {
        this.mailboxDao = mailboxDao;
    }
	
	public void setPersonMailboxDao( PersonMailboxDao personMailboxDao ) {
		this.personMailboxDao = personMailboxDao;
	}
	
	public List<PersonMailbox> getMailboxes(long person_id) {
		return personMailboxDao.getAddresses(person_id);
    }
	
	public List<PersonMailbox> getPersons(long mailbox_id) {
        return personMailboxDao.getPersons(mailbox_id);
    }
	
	public PersonMailbox assignPrimaryMailbox(long person_id, long mailbox_id) {
	    return assignMailbox( person_id, mailbox_id, Boolean.TRUE);
	}
	
	public PersonMailbox assignSecondaryMailbox(long person_id, long mailbox_id) {
        return assignMailbox( person_id, mailbox_id, Boolean.FALSE);
    }
	
    public PersonMailbox assignMailbox(long person_id, long mailbox_id, Boolean isOwner) {
        if ( isOwner == null ) {
            isOwner = Boolean.FALSE;
        }
        
    	List<PersonMailbox> persons = personMailboxDao.getPersons(mailbox_id);
    	
    	if ( persons != null && !persons.isEmpty() ) {
            for ( PersonMailbox _person : persons ) {
                if ( _person.getPerson().getId() == person_id ) {
                    throw new RuntimeException("This mailbox is already assigned to this person!");
                }
                
                if ( isOwner && _person.isOwner() ) {
                    throw new RuntimeException("This mailbox already has an owner!");
                }
            }
        }
    	
    	Person person = mainDao.load(person_id);
    	Mailbox mailbox = mailboxDao.load(mailbox_id);
        
    	Date now = new Date();
    	PersonMailbox personAddress = new PersonMailbox(person, mailbox, isOwner, now, null);
    	
    	personMailboxDao.persist( personAddress );
    	
    	return personAddress;
    }
    
    public void unassignMailbox(long person_id, long mailbox_id) {
        List<PersonMailbox> pa = getPersons(mailbox_id);
        
        PersonMailbox relationToDelete = null;
        if ( pa != null && !pa.isEmpty() ) {
            for ( PersonMailbox person : pa ) {
                if ( person.getPerson().getId() == person_id ) {
                    relationToDelete = person;
                    break;
                }
            }
        }
        
        if ( relationToDelete != null ) {
            personMailboxDao.delete(relationToDelete);
        } else {
            throw new RuntimeException("This address is not assigned to this person");
        }
    }

}
