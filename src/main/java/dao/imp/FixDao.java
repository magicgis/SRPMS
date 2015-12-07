package dao.imp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guofan on 2015/12/7.
 */
@Repository
public class FixDao {
    private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(FixDao.class);

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<String> getTheWrongOrderList() {
        String sql = "SELECT orderId FROM Task GROUP BY orderId HAVING COUNT(orderId) > 1";
        return getCurrentSession().createQuery(sql).list();
    }

    public void removeTask(String taskId) {
        try {
            String hql = "DELETE Task WHERE id = '" + taskId + "'";
            String hql2 = "DELETE TaskActor WHERE taskId = '" + taskId + "'";
            getCurrentSession().createQuery(hql).executeUpdate();
            getCurrentSession().createQuery(hql2).executeUpdate();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
