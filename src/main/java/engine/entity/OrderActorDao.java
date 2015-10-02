package engine.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * DATE:2015/3/20
 * TIME:20:03
 * Created by guofan on 2015/3/20
 */
@Repository
@SuppressWarnings({"unused", "unchecked"})
public class OrderActorDao {

    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(OrderActor orderActor) {
        this.getCurrentSession().save(orderActor);
    }

    public boolean areTheyAlreadyIn(String order, String actor) {
        return (get(order, actor) != null);
    }

    OrderActor get(String order, String actor) {
        OrderActor ans = null;
        String hql = "from OrderActor where actor = '" + actor + "' and order = '" + order + "' and status = 1";
        List<OrderActor> list = this.getCurrentSession().createQuery(hql).list();
        if (list != null && list.size() != 0) {
            ans = list.get(0);
        }
        return ans;
    }

    public void save(String order, String actor, int role, String type) {
        OrderActor OA = new OrderActor();
        OA.setActor(actor);
        OA.setOrder(order);
        OA.setIdoa(actor + "-" + order);
        OA.setRole(role);
        OA.setType(type);
        OA.setStatus(1);
        save(OA);
    }

    public void delete(String order, String actor) {
        OrderActor orderActor = get(order, actor);
        this.getCurrentSession().delete(orderActor);
    }

    public void deleteAllOrder(String order) {
        List<OrderActor> list = getByOrder(order);
        for (OrderActor aList : list) {
            this.getCurrentSession().delete(aList);
        }
    }

    public List<OrderActor> getByOrder(String order) {
        String hql = "from OrderActor where order = '" + order + "' and status = 1";
        return this.getCurrentSession().createQuery(hql).list();
    }

    public List<OrderActor> getByActor(String actor) {
        String hql = "from OrderActor where actor = '" + actor + "' and status = 1";
        return this.getCurrentSession().createQuery(hql).list();
    }

    public List<OrderActor> getByActorAndType(String actor, String type) {
        String hql = "from OrderActor where actor = '" + actor + "' and  type = ''" + type + "' and status = 1";
        return this.getCurrentSession().createQuery(hql).list();
    }

    public List<OrderActor> getByAll(String actor, String type, Integer role) {
        String hql = "from OrderActor";
        List<String> where = new ArrayList<String>();
        if (actor != null && !actor.trim().equals("")) {
            where.add("actor = '" + actor + "'");
        }
        if (type != null && !type.trim().equals("")) {
            where.add("type = '" + type + "'");
        }
        if (role != null) {
            where.add("role = " + role);
        }
        if (where.size() > 0) {
            hql = hql + " where ";
            for (String s : where) {
                hql = hql + s + " and ";
            }
            hql = hql.substring(0, hql.length() - 5);
            hql += " and status = 1";
        }
        return this.getCurrentSession().createQuery(hql).list();
    }

    public String getMajorActorByOrder(String order) {
        String hql = "from OrderActor where order = '" + order + "' and role = 1 and status = 1";
        List<OrderActor> list = this.getCurrentSession().createQuery(hql).list();
        return list.get(0).getActor();
    }


    public List<OrderActor> getOrderByActorAndPole(String actor, Integer role) {
        if (role == null) {
            return getByActor(actor);
        }
        String hql = "from OrderActor where actor = '" + actor + "' and role = " + Integer.toString(role) + " and status = 1";
        return this.getCurrentSession().createQuery(hql).list();
    }

    public void update(OrderActor orderActor) {
        getCurrentSession().update(orderActor);
    }
}
