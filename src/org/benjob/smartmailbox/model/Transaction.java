package org.benjob.smartmailbox.model;
// Generated Dec 29, 2015 10:31:07 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * Transaction generated by hbm2java
 */
public class Transaction implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    private long id;
    private Parcel Parcel;
    private Mailbox Mailbox;
    private Date Date;

    public Transaction() {
    }

    public Transaction(Parcel Parcel, Mailbox Mailbox, Date Date) {
        this.Parcel = Parcel;
        this.Mailbox = Mailbox;
        this.Date = Date;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Parcel getParcel() {
        return this.Parcel;
    }

    public void setParcel(Parcel Parcel) {
        this.Parcel = Parcel;
    }

    public Mailbox getMailbox() {
        return this.Mailbox;
    }

    public void setMailbox(Mailbox Mailbox) {
        this.Mailbox = Mailbox;
    }

    public Date getDate() {
        return this.Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

}
