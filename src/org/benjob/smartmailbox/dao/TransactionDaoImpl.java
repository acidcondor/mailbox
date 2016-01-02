package org.benjob.smartmailbox.dao;

import org.benjob.smartmailbox.model.Transaction;

public class TransactionDaoImpl extends BaseDaoImpl<Transaction> implements TransactionDao {

    public TransactionDaoImpl() {
        super( Transaction.class );
    }

}
