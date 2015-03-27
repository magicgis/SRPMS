package engine.filter;

import engine.Engine;
import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * DATE:2015/3/22
 * TIME:17:28
 * Created by guofan on 2015/3/22
 */
public class Start implements SnakerInterceptor {

    @Override
    public void intercept(Execution execution) {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        OrderActorDao orderActorDao =(OrderActorDao) factory.getBean("orderActorDao");
//        SnakerEngine engine = factory.getBean("snakerEngine");
        String actor = execution.getOperator();
        String creator = execution.getOrder().getCreator();
        String order = execution.getOrder().getId();
        if(actor.equals(creator)){
            if(!orderActorDao.areTheyAlreadyIn(order,actor)){
                String type = (String)execution.getArgs().get("WF-Type");
                String col = (String)execution.getArgs().get("WF-Col");
                orderActorDao.save(order,actor,1,type);
                Order oldOrder = execution.getOrder();
                Map<String,Object> args = new HashMap<String,Object>();
                args.put("WF-Type",type);
                args.put("WF-Col",col);
                execution.getEngine().order().addVariable(oldOrder.getId(),args);
            }
        }
    }
}