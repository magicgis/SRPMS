package dao;

import java.io.Serializable;
import java.util.List;

/**
 * DATE:2015/2/13
 * TIME:16:20
 * Created by guofan on 2015/2/13
 */
public interface BaseInterface<T> {

    /**
     * 添加
     * @param obj 实例对象
     * @return boolean
     */
    boolean save(T obj);

    /**
     * 删除
     * @param obj 实例对象
     * @return boolean
     */
    boolean delete(T obj);

    /**
     * 更新
     * @param obj 实例对象
     * @return boolean
     */
    abstract boolean update(T obj);

    /**
     * 根据主键查找
     * @param id 主键id
     * @return  obj
     */
    abstract T getById(Serializable id);

    /**
     * 无条件查找所有对象
     * @return obj list
     */
    abstract List<T> getAll();

    /**
     * TODO
     * 无条件查找所有对象
     * @param startRow 第几页
     * @param num 每页行数
     * @return HQL
     */
    abstract List<T> getAll(int startRow, int num);

}
