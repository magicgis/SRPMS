package service.imp;

import dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guofan on 2015/5/6.
 */
@Service
public abstract class BaseServiceImp<T> implements BaseService<T> {
    private BaseDao<T> baseDao;

    BaseDao<T> getBaseDao() {
        return baseDao;
    }

    @Autowired
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }


    public boolean delete(T entity) {
        return getBaseDao().delete(entity);
    }


    public boolean update(T entity) {
        return getBaseDao().update(entity);
    }

    public List<T> getAll() {
        return getBaseDao().getAll();
    }

    public boolean save(T entity) {
        return getBaseDao().save(entity);
    }

    public Serializable add(T entity) {
        return getBaseDao().add(entity);
    }


    public List<T> getAll(String sort, String order) {
        return getBaseDao().getAll(sort, order);
    }

    public T getById(Serializable id) {
        return getBaseDao().getById(id);
    }

    public boolean deleteById(String id) {
        return delete(getBaseDao().getById(id));
    }
}
