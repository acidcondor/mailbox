package org.benjob.smartmailbox.bo;

import java.util.List;

import org.benjob.smartmailbox.dao.AddressDao;
import org.benjob.smartmailbox.dao.MailboxDao;
import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.Mailbox;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AddressBoImpl extends BaseBoImpl<Address, AddressDao> implements AddressBo {
    
    private MailboxDao mailboxDao;

    public void setMailboxDao( MailboxDao mailboxDao ) {
        this.mailboxDao = mailboxDao;
    }
    
    public List<Mailbox> getMailboxes(long address_id) {
        Address address = mainDao.load(address_id);
        
        Mailbox mailbox = new Mailbox();
        mailbox.setAddress(address);
        return mailboxDao.findByExample(mailbox);
    }

}
