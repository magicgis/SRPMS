package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * DATE:2015/2/13
 * TIME:15:24
 * Created by guofan on 2015/2/13
 */
@Repository
public class BaseDao<T> implements BaseInterface<T> {

    public Class entityClass;
    private SessionFactory sessionFactory;
    private static final Log log = LogFactory.getLog(BaseDao.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public BaseDao(){
        entityClass = ReflectUtils.getClassGenericType(getClass());
    }

    @Override
    public boolean save(T obj) {
        log.debug("BaseDAO Save " + entityClass.getName());
        boolean result = true;
        try {
            this.getCurrentSession().save(obj);
        } catch (HibernateException hex) {
            log.error("Save "+entityClass.getName()+" Failed",hex);
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(T obj) {
        log.debug("BaseDAO Delete " + entityClass.getName());
        boolean result = true;
        try {
            this.getCurrentSession().delete(obj);
        } catch (HibernateException hex) {
            log.error("Delete "+entityClass.getName()+" Failed",hex);
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(T obj) {
        log.debug("BaseDAO Update " + entityClass.getName());
        boolean result = true;
        try {
            this.getCurrentSession().update(obj);
        } catch (HibernateException hex) {
            log.error("Update "+entityClass.getName()+" Failed",hex);
            result = false;
        }
        return result;
    }

    @Override
    public T getById(Serializable id) {
        log.debug("BaseDAO Find "+entityClass.getName()+" By Id "+id);
        T res;
        try {
            res = (T)this.getCurrentSession().get(entityClass, id);
        } catch (HibernateException hex) {
            log.debug("BaseDAO Find " + entityClass.getName() + " By Id " + id + " Failed",hex);
            res = null;
        }
        return res;
    }

    @Override
    public List<T> getAll() {
        log.debug("BaseDAO Get All "+entityClass.getName());
        String hql = "from " + getEntityClass().getName();
        List<T> res = null;
        try{
            res = this.getCurrentSession().createQuery(hql).list();
        }catch (HibernateException hex){
            log.error("BaseDAO Get All "+entityClass.getName()+" Failed",hex);
        }
        return res;
    }

    @Override
    public List<T> getAll(int startRow, int num) {
        log.debug("BaseDAO Get All "+entityClass.getName()+" LIMIT " + startRow + "," + num);
        String hql = "from " + getEntityClass().getName()+" LIMIT "+ startRow + "," + num;
        List<T> res = null;
        try{
            res = this.getCurrentSession().createQuery(hql).list();
        }catch (HibernateException hex){
            log.error("BaseDAO Get All "+entityClass.getName()+" Failed",hex);
        }
        return res;
    }

//    @Override
//    public List<T> findByPropertyA(String propertyName, String value) {
//        log.debug("BaseDAO Find(A) "+propertyName+" From "+entityClass.getName());
//        List<T> res;
//        String hql = "from " + entityClass.getName() + " where " + propertyName + " = '" + value + "'";
//        try {
//            res = this.getCurrentSession().createQuery(hql).list();
//        }catch (HibernateException hex){
//            log.error("BaseDAO Find(A) "+propertyName+" From "+entityClass.getName()+" Failed",hex);
//            res = null;
//        }
//        return res;
//    }
//
//    @Override
//    public List<T> findByPropertyF(String propertyName, String value) {
//        log.debug("BaseDAO Find(F) "+propertyName+" From "+entityClass.getName());
//        List<T> res;
//        String hql = "from " + entityClass.getName() + " where " + propertyName + " LIKE '%" + value + "%'";
//        try {
//            res = this.getCurrentSession().createQuery(hql).list();
//        }catch (HibernateException hex){
//            log.error("BaseDAO Find(F) "+propertyName+" From "+entityClass.getName()+" Failed",hex);
//            res = null;
//        }
//        return res;
//    }
//
//    @Override
//    public List<T> findByMap(HashMap<String, Object> params) {
//        return null;
//    }


    /**
     * TODO
     * 生成无条件查找所有对象的语句
     * @return HQL
     */
    protected String findAllHql() {
        return null;
    }

    /**
     * TODO
     * 生成无条件查找所有对象的语句
     * @param page 第几页
     * @param row 每页行数
     * @return HQL
     */
    protected String findAllHql(int page, int row) {
        return null;
    }

    /**
     * TODO
     * 根据属性名和属性值生成组合<bold>精确</bold>查询语句
     * @param params key-value
     * @return HQL
     */
    protected String findByMapA(HashMap<String, Object> params) {
        return null;
    }

    /**
     * TODO
     * 根据属性名和属性值生成组合<bold>精确</bold>查询语句
     * @param params key-value
     * @param page 第几页
     * @param row 每页行数
     * @return HQL
     */
    protected String findByMapA(HashMap<String, Object> params, int page, int row) {
        return null;
    }

    /**
     * TODO
     * 根据属性名和属性值生成组合<bold>模糊</bold>查询语句
     * @param params key-value
     * @return HQL
     */
    protected String findByMapF(HashMap<String, Object> params) {
        return null;
    }

    /**
     * TODO
     * 根据属性名和属性值生成组合<bold>模糊</bold>查询语句
     * @param params key-value
     * @param page 第几页
     * @param row 每页行数
     * @return HQL
     */
    protected String findByMapF(HashMap<String, Object> params, int page, int row) {
        return null;
    }

    /**
     * TODO
     * 执行sql语句
     * @return
     */
    protected List<T> executeSql(String HQL) {
        return null;
    }

    /**
     * TODO
     * 计算语句查询总量
     * @param HQL
     * @return
     */
    protected Long countBySql(String HQL) {
        return null;
    }

    /**
     * DATE:2014/12/6
     * TIME:16:28
     * Created by guofan on 2014/12/6
     */
    public static class ReflectUtils {
        /**
         * 获得超类的参数类型，取第一个参数类型
         * @param <T> 类型参数
         * @param clazz 超类类型
         */

        public static <T> Class<T> getClassGenericType(final Class clazz) {
            return getClassGenericType(clazz, 0);
        }

        /**
         * 根据索引获得超类的参数类型
         * @param clazz 超类类型
         * @param index 索引
         */
        public static Class getClassGenericType(final Class clazz, final int index) {
            Type genType = clazz.getGenericSuperclass();
            if (!(genType instanceof ParameterizedType)) {
                return Object.class;
            }
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index >= params.length || index < 0) {
                return Object.class;
            }
            if (!(params[index] instanceof Class)) {
                return Object.class;
            }
            return (Class) params[index];
        }
    }
}