package engine.utils;

import engine.entity.OrderActorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guofan on 2015/5/11.
 */
@Service
public class AOService {

    static OrderActorDao orderActorDao;


    public static OrderActorDao getDao() {
        return orderActorDao;
    }

    public void setOrderActorDao(OrderActorDao orderActorDao) {
        this.orderActorDao = orderActorDao;
    }

    public OrderActorDao getOrderActorDao() {
        return orderActorDao;
    }
}
