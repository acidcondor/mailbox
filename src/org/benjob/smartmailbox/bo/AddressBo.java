package org.benjob.smartmailbox.bo;

import java.util.List;

import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.Mailbox;

public interface AddressBo extends BaseBo<Address> {

    public List<Mailbox> getMailboxes(long address_id);
}
