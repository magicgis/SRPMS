package engine.older;

import engine.entity.OrderActorDao;
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
 * 用户新增order的拦截器
 */
public class Start implements SnakerInterceptor {


    @Override
    public void intercept(Execution execution) {
//        Long startTime = System.currentTimeMillis();
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml",
        "classpath:/applicationContext-snaker.xml");
        OrderActorDao orderActorDao =(OrderActorDao) factory.getBean("orderActorDao");
//        OrderActorDao orderActorDao = AOService.getDao();
//        Long endTime = System.currentTimeMillis();
//        System.out.println(endTime-startTime);
//        SnakerEngine engine = factory.getBean("snakerEngine");
        String actor = execution.getOperator();
        Order order = execution.getOrder();
        String type = (String) execution.getArgs().get("WF_Type");
        String col = (String) execution.getArgs().get("WF_Col");
        orderActorDao.save(order.getId(), actor, 1, type);
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("WF_Type", type);
        args.put("WF_Col", col);
        args.put("Status", "Blank");
        execution.getEngine().order().addVariable(order.getId(), args);
    }
}
