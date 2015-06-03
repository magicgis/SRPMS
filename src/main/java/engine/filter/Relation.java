package engine.filter;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guofan on 2015/6/2.
 */
@Component
public class Relation implements SnakerInterceptor {

    @Autowired
    OrderActorDao orderActorDao;


    @Override
    public void intercept(Execution execution) {
        List<Task> nextTasks = execution.getTasks();
        Task nowTask = execution.getTask();
        if (nextTasks != null && nextTasks.size() != 0) {
            String flag, nextFlag;
            if (nowTask != null) {
                flag = nowTask.getTaskName();
            } else {
                flag = "";
            }
            nextFlag = nextTasks.get(0).getTaskName();

            /*启动*/
            if (flag.equals("") && nextFlag.equals("Submission")) {
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
                return;
            }

            /*填表*/
            if (flag.equals("Submission")) {
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
                Map<String, Object> args = new HashMap<String, Object>();
                /*如果表单填写完毕,就加上部分全局变量*/
                String status = (String) execution.getArgs().get("IsComplete");
                if (Boolean.valueOf(status).equals(true)) {
                    args.put("Status", "Complete");
                } else {
                    args.put("Status", "Uncomplete");
                }
                execution.getEngine().order().addVariable(order, args);
                return;
            }

            /*确认*/
            if (flag.equals("Confirm")) {
                String actor = execution.getOperator();
                String order = execution.getOrder().getId();
                String creator = execution.getOrder().getCreator();
                String type = orderActorDao.getByOrder(order).get(0).getType();
                if (!orderActorDao.areTheyAlreadyIn(order, actor)) {
                    /*负责人为1，参与者为0*/
                    if (creator.equals(actor)) {
                        orderActorDao.save(order, actor, 1, type);
                    } else {
                        orderActorDao.save(order, actor, 0, type);
                    }
                }
                if (nextFlag.equals("SubmitByTeacher")) {
                    Map<String, Object> args = new HashMap<String, Object>();
                    args.put("Status", "WaitForSubmit");
                    execution.getEngine().order().addVariable(order, args);
                }
                return;
            }

            /*统一提交*/
            if (flag.equals("SubmitByTeacher")) {
                String order = execution.getOrder().getId();
                String type = orderActorDao.getByOrder(order).get(0).getType();
                if (nextFlag.equals("ApprovalByCol")) {
                    Map<String, Object> args = new HashMap<String, Object>();
                    args.put("Status", "WaitForCol");
                    execution.getEngine().order().addVariable(order, args);
                    orderActorDao.save(order, "col", 2, type);
                }
                return;
            }

            /*院系审核*/
            if (flag.equals("ApprovalByCol")) {
                String order = execution.getOrder().getId();
                Map<String, Object> args = new HashMap<String, Object>();
                if (nextFlag.equals("SubmitByCol")) {
                    args.put("Status", "ApprovedByCol");
                } else {
                    args.put("Status", "RefuseByCol");
                }
                execution.getEngine().order().addVariable(order, args);
                return;
            }

            /*院系统一提交*/
            if (flag.equals("SubmitByCol")) {
                String order = execution.getOrder().getId();
                String type = orderActorDao.getByOrder(order).get(0).getType();
                if (nextFlag.equals("ApprovalByDep")) {
                    Map<String, Object> args = new HashMap<String, Object>();
                    args.put("Status", "WaitForDep");
                    execution.getEngine().order().addVariable(order, args);
                    orderActorDao.save(order, "dep", 3, type);
                }
                return;
            }

            /*学校审核*/
            if (flag.equals("ApprovalByDep")) {
                String order = execution.getOrder().getId();
                Map<String, Object> args = new HashMap<String, Object>();
                if (nextFlag.equals("ApprovalByCol")) {
                    args.put("Status", "RefuseByDep");
                } else {
                    args.put("Status", "AllCompelete");
                }
                execution.getEngine().order().addVariable(order, args);
                return;
            }
        }
    }
}
