package org.benjob.smartmailbox.bo;

import java.util.List;
import java.util.Set;

public interface BaseBo<T> {
    public void create(T instance);

    public void update(T instance);

    public void delete(T instance);
    
    public void delete(long id);

    public T getById(long id);

    public T getById(long id, Set<String> includedCollections);

    public Set<T> getByIds(Set<Long> ids);

    public List<T> getByIds(Set<Long> ids, Set<String> includedCollections);

    public void initializeCollection(T object, String collectionName);
}
