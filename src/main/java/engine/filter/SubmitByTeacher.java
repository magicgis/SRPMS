package engine.filter;

import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.StaticFactory.getBean;

/**
 * DATE:2015/4/8
 * TIME:10:26
 * Created by guofan on 2015/4/8
 */
public class SubmitByTeacher implements SnakerInterceptor {
    @Override
    public void intercept(Execution execution) {
        OrderActorDao orderActorDao = (OrderActorDao) getBean("orderActorDao");
        String order = execution.getOrder().getId();
        String type = orderActorDao.getByOrder(order).get(0).getType();
        List<Task> tasks = execution.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(order));
        if (tasks.size() == 1 && tasks.get(0).getTaskName().equals("ApprovalByCol")) {
            // 将学院与order绑定！
            Map<String, Object> args = new HashMap<>();
            args.put("Status", "WaitForCol");
            execution.getEngine().order().addVariable(order, args);
            orderActorDao.save(order, (String) execution.getArgs().get("WF_Col_Id"), 2, type);
        }
    }
}
