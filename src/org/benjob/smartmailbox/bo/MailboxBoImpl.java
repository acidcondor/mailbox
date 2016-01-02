package org.benjob.smartmailbox.bo;

import java.util.HashSet;
import java.util.Set;

import org.benjob.smartmailbox.dao.AddressDao;
import org.benjob.smartmailbox.dao.MailboxDao;
import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.Mailbox;

public class MailboxBoImpl extends BaseBoImpl<Mailbox, MailboxDao> implements MailboxBo {

    private AddressDao addressDao;
    
    public void setAddressDao( AddressDao addressDao ) {
        this.addressDao = addressDao;
    }
    
    public Mailbox create(Mailbox mailbox, long address_id) {
        Address address = addressDao.load(address_id);
        mailbox.setAddress( address );
        
        create(mailbox);
        
        return mailbox;
    }
    
    public Address getMailboxAddress(long mailbox_id) {
        Set<String> includedCollections = new HashSet<String>();
        includedCollections.add("Address");
        Mailbox mailbox = mainDao.findById(mailbox_id, includedCollections);
        
        if ( mailbox != null ) {
            return mailbox.getAddress();
        } else {
            return null;
        }
    }

}
