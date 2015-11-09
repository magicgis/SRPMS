package engine.filter;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.StaticFactory.getBean;

/**
 * DATE:2015/3/21
 * TIME:3:07
 * Created by guofan on 2015/3/21
 * 这是填写表单完毕之后提交的拦截器
 * 把order与actor绑定在一起
 */
public class Submission implements SnakerInterceptor {
    @Override
    public void intercept(Execution execution) {
        OrderActorDao orderActorDao = (OrderActorDao) getBean("orderActorDao");
        String actor = execution.getOperator();
        String order = execution.getOrder().getId();
        String creator = execution.getOrder().getCreator();
        List<OrderActor> relationship = orderActorDao.getByOrder(order);
        String type = orderActorDao.getByOrder(order).get(0).getType();
        if (relationship != null && relationship.size() > 1) {
            /*之前已经绑定过，说明这是被驳回的，全部取消绑定*/
            orderActorDao.deleteAllOrder(order);
            /*把负责人的绑定加上*/
            if (actor.equals(creator)) {
                orderActorDao.save(order, actor, 1, type);
            }
        }
        Map<String, Object> args = new HashMap<>();
        /*如果表单填写完毕,就加上部分全局变量*/
        String status = (String) execution.getArgs().get("IsComplete");
        if (Boolean.valueOf(status).equals(true)) {
            args.put("Status", "Complete");
        } else {
            args.put("Status", "Uncomplete");
        }
        execution.getEngine().order().addVariable(order, args);

    }
}
