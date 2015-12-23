package org.benjob.smartmailbox.bo;

import org.benjob.smartmailbox.dao.PersonDao;
import org.benjob.smartmailbox.model.Person;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PersonBoImpl extends BaseBoImpl<Person, PersonDao> implements PersonBo {

    public PersonBoImpl() {
        // TODO Auto-generated constructor stub
    }

}
