package org.benjob.smartmailbox.dao;
import java.util.ArrayList;
import java.util.List;

// Generated Dec 22, 2015 12:11:09 PM by Hibernate Tools 4.3.1.Final
import org.benjob.smartmailbox.model.Address;
import org.benjob.smartmailbox.model.PersonAddress;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Home object for domain model class PersonAddresses.
 * @see org.benjob.smartmailbox.model.PersonAddress
 * @author Hibernate Tools
 */
public class PersonAddressDaoImpl extends BaseDaoImpl<PersonAddress> implements PersonAddressDao {

    public PersonAddressDaoImpl() {
        super( PersonAddress.class );
    }
    
    @SuppressWarnings("unchecked")
	public List<Address> getAddresses(Long person_id) {
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PersonAddress.class);
    	
    	criteria.add(Restrictions.eq("Person.id", person_id));
    	criteria.setFetchMode("Address", FetchMode.JOIN);
    	
    	List<PersonAddress> allPersonAddresses = criteria.list();
    	
    	List<Address> result = null;
    	
    	if ( allPersonAddresses != null ) {
    		result = new ArrayList<Address>(allPersonAddresses.size());
    		
    		for ( PersonAddress personAddress : allPersonAddresses ) {
    			result.add(personAddress.getAddress());
    		}
    	}
    	
    	return result;
    }
}
