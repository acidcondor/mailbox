package org.benjob.smartmailbox.dao;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;

import static org.hibernate.criterion.Example.create;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    protected Class<? extends T> clazz;
    protected SessionFactory sessionFactory;

    public BaseDaoImpl(Class<? extends T> theClass) {
        this.clazz = theClass;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void persist(T transientInstance) {
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public void attachDirty(T instance) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public void attachClean(T instance) {
        try {
            sessionFactory.getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public void delete(T persistentInstance) {
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T merge(T detachedInstance) {
        try {
            T result = (T) sessionFactory.getCurrentSession().merge(detachedInstance);
            return result;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T load(long id) {
        return (T) sessionFactory.getCurrentSession().load(this.clazz, id);
    }

    public T findById(long id) {
        return findById(id, null);
    }

    public T findById(long id, Set<String> includedCollections) {
        Set<Long> ids = new HashSet<Long>();

        ids.add(id);

        List<T> result = findByIds(ids, includedCollections);

        if (result.size() > 1) {
            // I do not believe we can ever get here...
            return null;
        } else {
            if (result.size() == 0) {
                return null;
            } else {
                return result.get(0);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByIds(Set<Long> ids, Set<String> includedCollections) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz.getCanonicalName());
            
            // Set the fetch mode for the collections to include
            if (includedCollections != null && includedCollections.size() != 0) {
                for (String includedCollection : includedCollections) {
                    criteria.setFetchMode(includedCollection, FetchMode.JOIN);
                }
            }

            // Only get distinct Root entities
            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

            List<T> results = (List<T>) criteria.add(Restrictions.in("id", ids)).list();

            return results;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public List<T> findByExample(T instance) {
        return findByExample(instance, null, null);
    }

    public List<T> findByExample(T instance, Set<String> includedCollections) {
        return findByExample(instance, null, includedCollections);
    }

    public List<T> findByExampleExcludingProperties(T instance, Set<String> excludedProperties) {
        return findByExample(instance, excludedProperties, null);
    }

    public List<T> findByExample(T instance, Set<String> excludedProperties, Set<String> includedCollections) {
        return findByExample(instance, excludedProperties, includedCollections, -1, -1);
    }

    public List<T> findByExample(T instance, int fromRecord, int toRecord) {
        return findByExample(instance, null, null, fromRecord, toRecord);
    }

    public List<T> findByExample(T instance, Set<String> includedCollections, int fromRecord, int toRecord) {
        return findByExample(instance, null, includedCollections, fromRecord, toRecord);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T instance, Set<String> excludedProperties, Set<String> includedCollections, int fromRecord, int toRecord) {
        try {
            Example exampleInstance = create(instance);

            // Exclude the properties
            if (excludedProperties != null) {
                for (String excludedProperty : excludedProperties) {
                    exampleInstance = exampleInstance.excludeProperty(excludedProperty);
                }
            }

            Criteria myCriteria = sessionFactory.getCurrentSession().createCriteria(clazz.getCanonicalName());

            if (includedCollections != null) {
                // Include the collections
                for (String includedCollection : includedCollections) {
                    myCriteria.setFetchMode(includedCollection, FetchMode.JOIN);
                }
            }

            // set the LIMIT
            if (fromRecord >= 0 && toRecord >= 0) {
                if (fromRecord < toRecord) {
                    myCriteria.setFirstResult(fromRecord);
                    myCriteria.setMaxResults(toRecord - fromRecord);
                } else {
                    myCriteria.setFirstResult(toRecord);
                    myCriteria.setMaxResults(fromRecord - toRecord);
                }
            }

            // Only get distinct Root entities
            myCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

            // get the results
            List<T> results = (List<T>) myCriteria.add(exampleInstance).list();
            return results;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public void initializeCollection(T instance, String collectionName) {
        ClassMetadata classMetadata = sessionFactory.getClassMetadata(instance.getClass());
        Object collection = classMetadata.getPropertyValue(instance, collectionName);

        if (!Hibernate.isInitialized(collection)) {
            attachClean(instance);
            Hibernate.initialize(collection);
        }
    }
    
    public void evict(T transientInstance) {
        sessionFactory.getCurrentSession().evict(transientInstance);
    }
}
