package dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Guofan on 4/26/2015.
 */
public interface BaseDao<T> {
    /**
     * 添加
     *
     * @param obj 实例对象
     * @return boolean
     */
    boolean save(T obj);

    Serializable add(T obj);

    /**
     * 删除
     *
     * @param obj 实例对象
     * @return boolean
     */
    boolean delete(T obj);

    /**
     * 更新
     *
     * @param obj 实例对象
     * @return boolean
     */
    boolean update(T obj);

    /**
     * 根据主键查找
     *
     * @param id 主键id
     * @return obj
     */
    T getById(Serializable id);

    /**
     * 无条件查找所有对象
     *
     * @return obj list
     */
    List<T> getAll();

    List<T> getAll(String sort, String order);

    /**
     * 无条件查找所有对象
     *
     * @param startRow 起始行
     * @param num      行数
     * @return HQL
     */
    List<T> getAll(int startRow, int num);

    /**
     * 表里到底有多少内容
     *
     * @return long
     */
    Long getAllCount();

    /**
     * 精确查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return list
     */
    List<T> findByPropertyA(String propertyName, Object value);

    /**
     * 模糊查找
     *
     * @param propertyName 属性名
     * @param value        属性值
     * @return list
     */
    List<T> findByPropertyF(String propertyName, String value);

    /**
     * 精确查找
     *
     * @param args HASHMAP
     * @return list
     */
    List<T> findByMapAcc(HashMap<String, Object> args);

    List<T> findByArrayFuz(List<String> keys, String keyword, String sort, String order);

    /**
     * 精确查找，带分页
     *
     * @param args  HASHMAP
     * @param start 起始行
     * @param num   数量
     * @return list
     */
    List<T> findByMapAcc(HashMap<String, Object> args, int start, int num);

    /**
     * 模糊查找
     *
     * @param args HASHMAP
     * @return list
     */
    List<T> findByMapFuz(HashMap<String, Object> args);

    /**
     * 模糊查找，带分页
     *
     * @param args  HASHMAP
     * @param start 起始行
     * @param num   数量
     * @return list
     */
    List<T> findByMapFuz(HashMap<String, Object> args, int start, int num);

}
