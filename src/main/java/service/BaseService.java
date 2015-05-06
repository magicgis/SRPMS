package service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guofan on 2015/5/6.
 */
public interface BaseService<T> {

    boolean save(T entity);

    Serializable add(T entity);

    boolean delete(T entity);

    boolean deleteById(String id);

    boolean update(T entity);

    List<T> getAll();

    List<T> getAll(String sort, String order);

    T getById(Serializable id);

//    List<T> search(String keyword, String sort, String order);
}