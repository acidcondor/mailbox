package org.benjob.smartmailbox.model;
// Generated Dec 29, 2015 10:31:07 PM by Hibernate Tools 4.3.1.Final

/**
 * Address generated by hbm2java
 */
public class Address implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    private long id;
    private String Street;
    private String City;
    private String State;
    private Integer ZipCode;
    private String Unit;
    private String Country;
    private String MailBoxAddress;

    public Address() {
    }

    public Address(String Street, String City, String State, Integer ZipCode, String Unit, String Country, String MailBoxAddress) {
        this.Street = Street;
        this.City = City;
        this.State = State;
        this.ZipCode = ZipCode;
        this.Unit = Unit;
        this.Country = Country;
        this.MailBoxAddress = MailBoxAddress;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return this.Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return this.State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public Integer getZipCode() {
        return this.ZipCode;
    }

    public void setZipCode(Integer ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getUnit() {
        return this.Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public String getCountry() {
        return this.Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getMailBoxAddress() {
        return this.MailBoxAddress;
    }

    public void setMailBoxAddress(String MailBoxAddress) {
        this.MailBoxAddress = MailBoxAddress;
    }

}
