package org.benjob.smartmailbox.model;
// Generated Dec 29, 2015 10:41:30 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * PersonMailbox generated by hbm2java
 */
public class PersonMailbox implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    private long id;
    private Person Person;
    private Mailbox Mailbox;
    private boolean Owner;
    private Date StartDate;
    private Date EndDate;

    public PersonMailbox() {
    }

    public PersonMailbox(Person Person, Mailbox Mailbox, boolean Owner, Date StartDate, Date EndDate) {
        this.Person = Person;
        this.Mailbox = Mailbox;
        this.Owner = Owner;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return this.Person;
    }

    public void setPerson(Person Person) {
        this.Person = Person;
    }

    public Mailbox getMailbox() {
        return this.Mailbox;
    }

    public void setMailbox(Mailbox Mailbox) {
        this.Mailbox = Mailbox;
    }

    public boolean isOwner() {
        return this.Owner;
    }

    public void setOwner(boolean Owner) {
        this.Owner = Owner;
    }

    public Date getStartDate() {
        return this.StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return this.EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

}
