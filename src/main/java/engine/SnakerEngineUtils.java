package engine;

import dao.*;
import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StreamHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import util.Args;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/1/22
 * TIME:23:04
 * Created by guofan on 2015/1/22
 */
@Service
@SuppressWarnings("unchecked")
public class SnakerEngineUtils implements Engine {

    private static final Logger log = LogManager.getLogger(SnakerEngineUtils.class);

    @Autowired
    private OrderActorDao orderActorDao;
    @Autowired
    private SnakerEngine snakerEngine;
    @Autowired
    private StaffDao staffDao;

    @Autowired
    ApplicationContext applicationContext;

    public void initFlows() {
        snakerEngine.process().deploy(StreamHelper.getStreamFromClasspath("workflow/easy.snaker"));
        snakerEngine.process().deploy(StreamHelper.getStreamFromClasspath("workflow/mag.snaker"));
    }

    public List<Process> getAllProcess() {
        return snakerEngine.process().getProcesss(new QueryFilter());
    }

    public Process getProcessByName(String name) {
        return snakerEngine.process().getProcesss(new QueryFilter().setName(name)).get(0);
    }

    public List<Task> getTaskByOrder(String orderId) {
        return snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(orderId));
    }

    public List<HistoryTask> getHisTaskByOrder(String orderId) {
        return snakerEngine.query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
    }

    public List<Order> getAllOrderByActor(String actor) {
        return getOrderByActorAndRole(actor, null);
    }

    public List<Order> getMainOrderByActor(String actor) {
        return getOrderByActorAndRole(actor, 1);
    }

    public List<Order> getSecOrderByActor(String actor) {
        return getOrderByActorAndRole(actor, 0);
    }

    public List<Task> getTaskByActor(String actor) {
        return snakerEngine.query().getActiveTasks(new QueryFilter().setOperator(actor));
    }

    public List<Order> getOrderByActorAndRole(String actor, Integer role) {
        List<OrderActor> temp = orderActorDao.getOrderByActorAndPole(actor, role);
        List<Order> ans = new ArrayList<>();
        for (OrderActor orderActor : temp) {
            ans.add(getOrder(orderActor.getOrder()));
        }
        return ans;
    }

    public List<HistoryTask> getHisTaskByActor(String actor) {
        return snakerEngine.query().getHistoryTasks(new QueryFilter().setOperator(actor));
    }

    public Order startInstanceById(String processId, String operator, Map<String, Object> args) {
        return snakerEngine.startInstanceById(processId, operator, args);
    }

    @Override
    public Order startInstanceById(String processId, String operator, String type) {
        HashMap args = new HashMap();
        args.put("WF_Type", type);
        return snakerEngine.startInstanceById(processId, operator, args);
    }

    @Override
    public Order startInstanceByEntity(String entityId, String type) {
        HashMap<String, Object> args;
        HashMap entityInfo;
        Staff staff;
        String processId = getProcessByName("basicProcess_Beta").getId();
        Order order = null;
        BaseDao baseDao = (BaseDao) applicationContext.getBean(Args.DAOS.get(type));
        VirtualEntity virtualEntity = (VirtualEntity) baseDao.getById(entityId);
        args = (HashMap<String, Object>) virtualEntity.getArgMap();
        entityInfo = (HashMap) args.clone();
        staff = staffDao.getById((Serializable) args.get("Main-Actor"));
        args.put("WF_Type", type);
        args.put("WF_Entity", entityId);
        args.put("name", virtualEntity.getName());
        order = startInstanceById(processId, staff.getId(), args);
        entityInfo.put("WF_OrderId", order.getId());
        virtualEntity.setArgMap(entityInfo);
        virtualEntity.setProcess("1");
        baseDao.update(virtualEntity);

        return order;
    }

    @Override
    public boolean resetEntityProcess(String orderId) {
        Order order;
        String type, entityId;
        try {
            order = getOrder(orderId);
            type = (String) order.getVariableMap().get("WF_Type");
            entityId = (String) order.getVariableMap().get("WF_Entity");
            snakerEngine.order().cascadeRemove(orderId);
            orderActorDao.deleteAllOrder(orderId);
            HashMap args;
            BaseDao baseDao = (BaseDao) applicationContext.getBean(Args.DAOS.get(type));
            VirtualEntity virtualEntity = (VirtualEntity) baseDao.getById(entityId);
            args = (HashMap<String, Object>) virtualEntity.getArgMap();
            args.remove("WF_OrderId");
            virtualEntity.setArgMap(args);
            virtualEntity.setProcess("0");
            baseDao.update(virtualEntity);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            log.error("传入的OrderId有误，或者不是实体工作流程,Id:" + orderId);
            return false;
        }
        return true;
    }

    public boolean resetEntityProcess(String type, String entityId) {
        BaseDao baseDao = (BaseDao) applicationContext.getBean(Args.DAOS.get(type));
        VirtualEntity virtualEntity = (VirtualEntity) baseDao.getById(entityId);
        HashMap args = (HashMap) virtualEntity.getArgMap();
        String orderId = (String) args.remove("WF_OrderId");
        try {
            if (orderId != null) {
                snakerEngine.order().cascadeRemove(orderId);
                orderActorDao.deleteAllOrder(orderId);
            }
            args.remove("WF_OrderId");
            virtualEntity.setArgMap(args);
            virtualEntity.setProcess("0");
            baseDao.update(virtualEntity);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            log.error("传入的Entity有误，或者不是实体工作流程,Id:" + entityId);
            return false;
        }
        return true;
    }

    public List<Task> execute(String taskId, String operator, Map<String, Object> args) {
        /*获取当前任务*/
        Task task = snakerEngine.query().getTask(taskId);
        /*获取任务所属order*/
        String orderId = task.getOrderId();
        /*设置参与者*/
        args.put("S-ACTOR", operator);
        /*获取order的arg*/
        Map<String, Object> orderArg = snakerEngine.query().getOrder(orderId).getVariableMap();
        /*获取Order里面的顺序*/
        int flowOrder = 0;
        String flowOderStr = "WF_" + Integer.toString(flowOrder) + "_" + task.getTaskName();
        Map<String, Object> temp = new HashMap<>();
        if (task.getTaskName().equals("Submission")) {
            flowOderStr = "WF_0_Submission";
            temp.put("WF_Latest", args);
        }
        temp.put(flowOderStr, args);

        /*把此轮的参数放入Order*/
        snakerEngine.order().addVariable(orderId, temp);
        Map fxxkMyself = new HashMap();

        if (args.containsKey("IsComplete")) {
            fxxkMyself.put("IsComplete", args.get("IsComplete"));
        }
        if (args.containsKey("WF_Actor")) {
            fxxkMyself.put("WF_Actor", args.get("WF_Actor"));
        }
        if (args.containsKey("DecByCol")) {
            fxxkMyself.put("DecByCol", args.get("DecByCol"));
        }
        if (args.containsKey("DecByDep")) {
            fxxkMyself.put("DecByDep", args.get("DecByDep"));

        }

        /*执行任务（不一定会产生新任务）*/
        List<Task> tasks = snakerEngine.executeTask(taskId, operator, fxxkMyself);
        if (tasks.isEmpty()) {
            return null;
        }

         /*如果存在下一任务参与者*/
        if (args.containsKey("WF_Actor") && args.containsKey("IsComplete")) {
            if (!Boolean.valueOf((String) args.get("IsComplete")))
                return tasks;
            String[] actorString = args.get("WF_Actor").toString().split(",");
            List<String> actors = new ArrayList<>();
            for (String u : actorString) {
                if (!u.equals(" ") && !u.equals(""))
                    actors.add(u);
            }
            for (int i = 1; i < actors.size(); i++) {
                snakerEngine.task().addTaskActor(tasks.get(0).getId(), 1, "");
            }
            tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(orderId));
            for (Task u : tasks) {
                if (actors.get(0).equals(u.getActorIds()[0])) {
                    continue;
                }
                snakerEngine.task().addTaskActor(u.getId(), actors.get(0));
                snakerEngine.task().removeTaskActor(u.getId(), "");
                actors.remove(0);
            }
        }

        /**
         * 不知道为什么，在此处执行任务不会记录到已完成中。
         * 所以放在更上一层进行自动执行。
         */
//        for (Task u : tasks) {
//            if(u.getTaskName().equals("Confirm")&&isYourTask(u.getId(), operator)){
//                execute(u.getId(),operator,new HashMap<String, Object>());
//                tasks.remove(u);
//            }
//        }

        return tasks;
    }


    public List<Task> refuse(String taskId, String operator, Map<String, Object> args) {
        return snakerEngine.executeAndJumpTask(taskId, operator, args, null);
    }

    public boolean setOrderRestart(String orderId, String actor) {
        boolean ans = false;
        String taskId = "";
        List<HistoryTask> hisTask = snakerEngine.query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
        Task nowTask = snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(orderId)).get(0);
        String time = "";
        long realTime = 0;
        /**
         * 这儿有个特殊情况，就是都确认了，
         * 需要先撤回到确认任务
         * 再撤回到填表任务
         */
        if (nowTask.getTaskName().equals("SubmitByTeacher")) {
            for (HistoryTask his : hisTask) {
                if (his.getTaskName().equals("Confirm")) {
//                    System.out.println(his.getCreateTime());
                    time = his.getCreateTime().replace("-", "").replace(" ", "").replace(":", "");
                    if (realTime < Long.valueOf(time)) {
                        realTime = Long.valueOf(time);
                        taskId = his.getId();
                    }
                }
            }
            snakerEngine.task().withdrawTask(taskId, actor);
        }
        time = "";
        realTime = 0;
        for (HistoryTask his : hisTask) {
            if (his.getTaskName().equals("Submission")) {
//                System.out.println(his.getCreateTime());
                time = his.getCreateTime().replace("-", "").replace(" ", "").replace(":", "");
                if (realTime < Long.valueOf(time)) {
                    realTime = Long.valueOf(time);
                    taskId = his.getId();
                }
            }
        }
        if (taskId != null) {
            snakerEngine.task().withdrawTask(taskId, actor);
            HashMap<String, Object> status = new HashMap<>();
            status.put("Status", "Uncomplete");
            snakerEngine.order().addVariable(orderId, status);
            ans = true;
        }
        return ans;
    }

    public void stopOrder(String orderId) {
        snakerEngine.order().cascadeRemove(orderId);
        orderActorDao.deleteAllOrder(orderId);
    }

    public Order getOrder(String id) {
        return snakerEngine.query().getOrder(id);
    }

    public Task getTask(String id) {
        return snakerEngine.query().getTask(id);
    }

    public List<Task> getConfirmTask(String actor) {
        List<Task> allTask = getTaskByActor(actor);
        List<Task> ans = new ArrayList<>();
        if (allTask != null && allTask.size() != 0) {
            for (Task task : allTask) {
                if (task.getTaskName().equals("Confirm")) {
                    ans.add(task);
                }
            }
        }
        return ans;
    }

    public Boolean isYourTask(String task, String actor) {
        List<Task> tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOperator(actor));
        for (Task task1 : tasks) {
            if (task1.getId().equals(task))
                return true;
        }
        return false;
    }

    public List<Order> getOrderByProcee(String processName) {
        String id = getProcessByName(processName).getId();
        return snakerEngine.query().getActiveOrders(new QueryFilter().setProcessId(id));
    }
}
