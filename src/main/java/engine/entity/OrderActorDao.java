package engine.entity;

import engine.entity.OrderActor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DATE:2015/3/20
 * TIME:20:03
 * Created by guofan on 2015/3/20
 */
@Repository
public class OrderActorDao {

    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(OrderActor orderActor){
        this.getCurrentSession().save(orderActor);
    }

    public boolean areTheyAlreadyIn(String order,String actor){
        return (get(order,actor)!=null);
    }

    OrderActor get(String order,String actor){
        OrderActor ans = null;
        String hql = "from OrderActor where actor = '"+actor+"' and order = '"+order+"'";
        List<OrderActor> list = this.getCurrentSession().createQuery(hql).list();
        if(list!=null && list.size()!=0){
            ans = list.get(0);
        }
        return ans;
    }

    public void save(String order,String actor,int role){
        OrderActor OA = new OrderActor();
        OA.setActor(actor);
        OA.setOrder(order);
        OA.setIdoa(actor + "-" + order);
        OA.setRole(role);
        save(OA);
    }

    public void delete(String order,String actor){
        OrderActor orderActor = get(order,actor);
        this.getCurrentSession().delete(orderActor);
    }

    public void deleteAllOrder(String order){
        List<OrderActor> list = getByOrder(order);
        for (OrderActor aList : list) {
            this.getCurrentSession().delete(aList);
        }
    }

    public List<OrderActor> getByOrder(String order){
        String hql = "from OrderActor where order = '"+order+"'";
        List<OrderActor> list = this.getCurrentSession().createQuery(hql).list();
        return list;
    }

    public List<OrderActor> getByActor(String actor){
        String hql = "from OrderActor where actor = '"+actor+"'";
        List<OrderActor> list = this.getCurrentSession().createQuery(hql).list();
        return list;
    }
}
