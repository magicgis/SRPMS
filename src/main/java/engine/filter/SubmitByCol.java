package engine.filter;

import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;

import java.util.HashMap;
import java.util.Map;

import static util.StaticFactory.getBean;

/**
 * Created by guofan on 2015/6/24.
 */
public class SubmitByCol implements SnakerInterceptor {
    @Override
    public void intercept(Execution execution) {
        OrderActorDao orderActorDao = (OrderActorDao) getBean("orderActorDao");
        String order = execution.getOrder().getId();
        String type = orderActorDao.getByOrder(order).get(0).getType();
        if (execution.getTasks().get(0).getTaskName().equals("ApprovalByDep")) {
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("Status", "WaitForDep");
            execution.getEngine().order().addVariable(order, args);
            orderActorDao.save(order, "10003", 3, type);
        }
    }
}
