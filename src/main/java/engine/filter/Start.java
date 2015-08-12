package engine.filter;

import engine.entity.OrderActorDao;
import entity.BaseInfo;
import entity.Staff;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import service.StaffService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.StaticFactory.getBean;

/**
 * DATE:2015/3/22
 * TIME:17:28
 * Created by guofan on 2015/3/22
 * 用户新增order的拦截器
 */
public class Start implements SnakerInterceptor {


    @Override
    public void intercept(Execution execution) {
        OrderActorDao orderActorDao = (OrderActorDao) getBean("orderActorDao");
        String actor = execution.getOperator();
        Order order = execution.getOrder();
        String type = (String) execution.getArgs().get("WF_Type");
        StaffService staffService = (StaffService) getBean(StaffService.class);
        List<Staff> x = staffService.getAll();
        Staff staff = staffService.getById(actor);
        BaseInfo col = staff.getCol();
        orderActorDao.save(order.getId(), actor, 1, type);
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("WF_Type", type);
        args.put("WF_Col", col.getValue());
        args.put("WF_Col_Id", col.getId());
        args.put("Status", "Blank");
        execution.getEngine().order().addVariable(order.getId(), args);
    }
}
