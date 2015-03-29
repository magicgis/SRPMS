package dao.imp;

import dao.BaseInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/2/13
 * TIME:15:24
 * Created by guofan on 2015/2/13
 */
@Repository
@SuppressWarnings({"unused","unchecked"})
public abstract class BaseDao<T> implements BaseInterface<T> {

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
            log.error("Update " + entityClass.getName() + " Failed", hex);
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
            log.debug("BaseDAO Find " + entityClass.getName() + " By Id " + id + " Failed", hex);
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
        log.debug("BaseDAO Get All "+entityClass.getName());
        String hql = "from " + getEntityClass().getName();
        List<T> res = null;
        try{
            Query sql = this.getCurrentSession().createQuery(hql);
            sql.setFirstResult(startRow);
            sql.setMaxResults(num);
            res = sql.list();
        }catch (HibernateException hex){
            log.error("BaseDAO Get All "+entityClass.getName()+" Failed",hex);
        }
        return res;
    }

    public Long getAllCount(){
        String hql = "select count(*) " + getEntityClass().getName();
        return  ((Number) this.getCurrentSession().createQuery(hql).uniqueResult()).longValue();
    }

    public List<T> findByPropertyA(String propertyName, Object value) {
        log.debug("BaseDAO Find(A) "+propertyName+" From "+entityClass.getName());
        List<T> res;
        String hql;
        if(value instanceof String) {
            hql = "from " + entityClass.getName() + " where " + propertyName + " = '" + value + "'";
        } else{
            hql = "from " + entityClass.getName() + " where " + propertyName + " = " + value;
        }
        try {
            res = this.getCurrentSession().createQuery(hql).list();
        }catch (HibernateException hex){
            log.error("BaseDAO Find(A) "+propertyName+" From "+entityClass.getName()+" Failed",hex);
            res = null;
        }
        return res;
    }

    public List<T> findByPropertyF(String propertyName, String value) {
        log.debug("BaseDAO Find(F) "+propertyName+" From "+entityClass.getName());
        List<T> res;
        String hql = "from " + entityClass.getName() + " where " + propertyName + " LIKE '%" + value + "%'";
        try {
            res = this.getCurrentSession().createQuery(hql).list();
        }catch (HibernateException hex){
            log.error("BaseDAO Find(F) "+propertyName+" From "+entityClass.getName()+" Failed",hex);
            res = null;
        }
        return res;
    }

    @Override
    public  List<T> findByMapAcc(HashMap<String, Object> args){
        String hql = findByMapA(args);
        return this.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public  List<T> findByMapAcc(HashMap<String, Object> args,int start,int num){
        String hql = findByMapA(args);
        Query ans = this.getCurrentSession().createQuery(hql);
        ans.setFirstResult(start);
        ans.setMaxResults(num);
        return ans.list();
    }

    @Override
    public  List<T> findByMapFuz(HashMap<String, Object> args){
        String hql = findByMapF(args);
        return this.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public  List<T> findByMapFuz(HashMap<String, Object> args,int start,int num){
        String hql = findByMapF(args);
        Query ans = this.getCurrentSession().createQuery(hql);
        ans.setFirstResult(start);
        ans.setMaxResults(num);
        return ans.list();
    }

    /**
     * 忘记是干嘛的了
     * @param hql hql语句
     * @param params 参数
     * @return list
     */
    public List<T> getByHql(String hql, Map<String, Object> params) {
        Query query = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.list();
    }

    /**
     * 生成无条件查找所有对象的语句
     * @return HQL
     */
    protected String findAllHql() {
        return "from "+entityClass.getName();
    }

    /**
     * 根据属性名和属性值生成组合<bold>精确</bold>查询语句
     * @param params key-value
     * @return HQL
     */
    protected String findByMapA(HashMap<String, Object> params) {
        String hql = "from "+entityClass.getName();
        String where = "";
        for (String s : params.keySet()) {
            if(params.get(s) instanceof String){
                where = where + s + " = " + "'" + params.get(s) + "' and ";
            }else{
                where = where + s + " = " + params.get(s) + " and ";
            }
        }
        if(where.length()>0){
            hql = hql + " where " + where.substring(0,where.length()-5);
        }
        return hql;
    }


    /**
     * 根据属性名和属性值生成组合<bold>模糊</bold>查询语句
     * @param params key-value
     * @return HQL
     */
    protected String findByMapF(HashMap<String, Object> params) {
        String hql = "from "+entityClass.getName();
        String where = "";
        for (String s : params.keySet()) {
            if(params.get(s) instanceof String){
                where = where + s + " LIKE " + "'%" + params.get(s) + "%' and ";
            }
        }
        if(where.length()>0){
            hql = hql + " where " + where.substring(0,where.length()-5);
        }
        return hql;
    }



    /**
     * 执行sql语句
     * @return list
     */
    protected List<T> executeSql(String hql) {
        return this.getCurrentSession().createQuery(hql).list();
    }

    /**
     * TODO
     * 计算语句查询总量
     * @param HQL hql 语句
     * @return list
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