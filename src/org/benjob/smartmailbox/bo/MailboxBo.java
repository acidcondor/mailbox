package org.benjob.smartmailbox.bo;

import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.Mailbox;

public interface MailboxBo extends BaseBo<Mailbox> {

    public Mailbox create(Mailbox mailbox, long address_id);
    
    public Address getMailboxAddress(long mailbox_id);
}
