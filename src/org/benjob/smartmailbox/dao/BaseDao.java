package org.benjob.smartmailbox.dao;

import java.util.List;
import java.util.Set;

public interface BaseDao<T> {

    public void persist(T transientInstance);

    public void attachDirty(T instance);

    public void attachClean(T instance);

    public void delete(T persistentInstance);

    public T merge(T detachedInstance);

    public T load(long id);

    public T findById(long id);

    public T findById(long id, Set<String> includedCollections);

    public List<T> findByIds(Set<Long> ids, Set<String> includedCollections);

    public List<T> findByExample(T instance);

    public List<T> findByExample(T instance, Set<String> includedCollections);

    public List<T> findByExample(T instance, Set<String> excludedProperties, Set<String> includedCollections);

    public List<T> findByExample(T instance, int fromRecord, int toRecord);

    public List<T> findByExample(T instance, Set<String> includedCollections, int fromRecord, int toRecord);

    public List<T> findByExample(T instance, Set<String> excludedProperties, Set<String> includedCollections, int fromRecord, int toRecord);

    public void initializeCollection(T instance, String collectionName);
    
    public void evict(T instance);
}
