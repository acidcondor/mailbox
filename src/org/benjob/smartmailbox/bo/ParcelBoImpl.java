package org.benjob.smartmailbox.bo;

import org.benjob.smartmailbox.dao.ParcelDao;
import org.benjob.smartmailbox.dao.PersonDao;
import org.benjob.smartmailbox.model.Parcel;
import org.benjob.smartmailbox.model.Person;

public class ParcelBoImpl extends BaseBoImpl<Parcel, ParcelDao> implements ParcelBo {
    
    private PersonDao personDao;
    
    public void setPersonDao( PersonDao personDao ) {
        this.personDao = personDao;
    }
    
    public void create(Parcel parcel) {
        throw new RuntimeException( "Unsupported operation, you need to provide the person id for the recipient of this parcel");
    }

    public Parcel create(Parcel parcel, Long person_id) {
        Person person = personDao.findById(person_id);
        
        if ( person == null ) {
            throw new RuntimeException( "Person doesn't exist" );
        }
        
        parcel.setRecipient(person);
        
        mainDao.persist( parcel );
        
        return parcel;
    }
    
}
