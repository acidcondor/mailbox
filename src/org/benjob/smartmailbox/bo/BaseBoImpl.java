package org.benjob.smartmailbox.bo;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.benjob.smartmailbox.dao.BaseDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseBoImpl<T, U extends BaseDao<T>> implements BaseBo<T> {

    protected U mainDao;

    public BaseBoImpl() {
        // TODO Auto-generated constructor stub
    }

    public void setMainDao(U mainDao) {
        this.mainDao = mainDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void create(T instance) {
        if (instance != null) {
            mainDao.persist(instance);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(T instance) {
        mainDao.attachDirty(instance);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(T instance) {
        mainDao.delete(instance);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(long id) {
        if (id != 0L) {
            T instance = mainDao.load(id);
            mainDao.delete(instance);
        }
    }

    public T getById(long id) {
        return mainDao.findById(id);
    }

    public T getById(long id, Set<String> includedCollections) {
        return mainDao.findById(id, includedCollections);
    }

    public Set<T> getByIds(Set<Long> ids) {
        List<T> listResult = getByIds(ids, null);

        // we get a list back, but we know this is actually a Set
        // convert...
        Set<T> result = new HashSet<T>();
        for (T currentItem : listResult) {
            result.add(currentItem);
        }

        return result;
    }

    public List<T> getByIds(Set<Long> ids, Set<String> includedCollections) {
        return mainDao.findByIds(ids, includedCollections);
    }

    public void initializeCollection(T object, String collectionName) {
        mainDao.initializeCollection(object, collectionName);
    }
}
