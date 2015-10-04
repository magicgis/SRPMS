package engine.filter;

import engine.entity.OrderActorDao;
import entity.BaseInfo;
import entity.Staff;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import service.StaffService;

import java.util.HashMap;
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
        Order order = execution.getOrder();
        String type = (String) execution.getArgs().get("WF_Type");

        //以员工表为准
        StaffService staffService = (StaffService) getBean(StaffService.class);

        Map<String, Object> args = new HashMap<String, Object>();
        Staff staff;
        BaseInfo col;
        //员工发起与学校发起的区别在于负责人。
        if (!execution.getArgs().containsKey("Main-Actor")) {
            String actor = execution.getOperator();
            staff = staffService.getById(actor);
        }
        else {
            staff = staffService.getById((String) execution.getArgs().get("Main-Actor"));
        }
        col = staff.getCol();
        orderActorDao.save(order.getId(), staff.getId(), 1, type);
        args.put("WF_Col", col.getValue());
        args.put("WF_Col_Id", col.getId());
        args.put("WF_Type", type);
        args.put("Status", "Blank");

        execution.getEngine().order().addVariable(order.getId(), args);
    }
}
