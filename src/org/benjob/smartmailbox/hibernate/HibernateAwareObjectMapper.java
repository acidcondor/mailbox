package org.benjob.smartmailbox.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public HibernateAwareObjectMapper() {
        Hibernate4Module mod = new Hibernate4Module();
        mod.enable(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        
        registerModule(mod);
    }

}
