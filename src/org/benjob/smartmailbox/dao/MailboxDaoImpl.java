package org.benjob.smartmailbox.dao;

import org.benjob.smartmailbox.model.Mailbox;

public class MailboxDaoImpl extends BaseDaoImpl<Mailbox> implements MailboxDao {
    public MailboxDaoImpl() {
        super( Mailbox.class );
    }
}