package engine;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StreamHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SnakerEngineUtils implements Engine {

    @Autowired
    private OrderActorDao orderActorDao;
    @Autowired
    private SnakerEngine snakerEngine;

    public String initFlows(){
        return snakerEngine.process().deploy(StreamHelper.getStreamFromClasspath("workflow/easy.snaker"));
    }

    public SnakerEngine getSnakerEngine(){
        return snakerEngine;
    }

    @Override
    public List<Process> getAllProcess() {
        return snakerEngine.process().getProcesss(new QueryFilter());
    }

    @Override
    public Process getProcessByName(String name) {
        return snakerEngine.process().getProcesss(new QueryFilter().setName(name)).get(0);
    }

    @Override
    public List<Task> getTaskByOrder(String orderId) {
        return snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(orderId));
    }

    @Override
    public List<HistoryTask> getHisTaskByOrder(String orderId) {
        return snakerEngine.query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
    }

    @Override
    public List<OrderActor> getAllOrderByActor(String actor) {
        return orderActorDao.getByActor(actor);
    }

    @Override
    public List<Order> getOrderByActor(String actor) {
        return snakerEngine.query().getActiveOrders(new QueryFilter().setOperator(actor));
    }


    @Override
    public List<Task> getTaskByActor(String actor) {
        return snakerEngine.query().getActiveTasks(new QueryFilter().setOperator(actor));
    }

    @Override
    public List<HistoryTask> getHisTaskByActor(String actor) {
        return snakerEngine.query().getHistoryTasks(new QueryFilter().setOperator(actor));
    }

    @Override
    public Order startInstanceById(String processId,String operator,Map<String ,Object> args){
        return  snakerEngine.startInstanceById(processId, operator, args);
    }

    @Override
    public List<Task> execute(String taskId, String operator, Map<String, Object> args) {
        /*获取当前任务*/
        Task task = snakerEngine.query().getTask(taskId);
        /*获取任务所属order*/
        String orderId = task.getOrderId();
        /*设置参与者*/
        args.put("S-ACTOR",operator);
        Map<String,Object> NowMap = new HashMap<String, Object>();
        /*获取前N轮任务所填写Map*/
        Map<String,Object> beforeMap = task.getVariableMap();
        /*把上N轮的map放入*/
        int flowOrder = 0;
        if(beforeMap.containsKey("Details")){
            beforeMap.remove("Details");
        }
        for(String key:beforeMap.keySet()){
            if(beforeMap.get(key)instanceof Map){
                flowOrder++;
                NowMap.put(key,beforeMap.get(key));
            }
        }
        String flowOderStr = "WF_"+Integer.toString(flowOrder)+"_"+task.getTaskName();
        /*把此轮的参数放入*/
        NowMap.put(flowOderStr,args);
        NowMap.put("S-ACTOR",operator);
        /*决策参数需要单独处理*/
        if(args.containsKey("DecByCol")){
            NowMap.put("DecByCol",args.get("DecByCol"));
        }
        if(args.containsKey("DecByDep")){
            NowMap.put("DecByDep",args.get("DecByDep"));
        }
        if(args.containsKey("IsComplete")){
            NowMap.put("IsComplete",args.get("IsComplete"));
        }

        /*执行任务（不一定会产生新任务）*/
        List<Task> tasks =  snakerEngine.executeTask(taskId, operator,NowMap);
        if(tasks.isEmpty()){
            return null;
        }
         /*如果存在下一任务参与者*/
        if(args.containsKey("WF_Actor")&&args.containsKey("IsComplete")){
            if(!Boolean.valueOf((String) args.get("IsComplete")))
                return tasks;
            String[] actorString = args.get("WF_Actor").toString().split(",");
            List<String> actors = new ArrayList<String>();
            for(String u:actorString){
                if (!u.equals(" ")&&!u.equals(""))
                    actors.add(u);
            }
            for(int i = 1;i < actors.size();i++){
                snakerEngine.task().addTaskActor(tasks.get(0).getId(), 1, "");
            }
            tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(orderId));
            for(Task u:tasks){
                if(u.getActorIds()[0].equals(actors.get(0))){
                    continue;
                }
                snakerEngine.task().addTaskActor(u.getId(),actors.get(0));
                snakerEngine.task().removeTaskActor(u.getId(),"");
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

    @Override
    public List<Task> executeAndJump(String taskId, String operator, Map<String, Object> args,String nodeName) {
        return snakerEngine.executeAndJumpTask(taskId, operator, args, nodeName);
    }

    @Override
    public List<Task> refuse(String taskId, String operator, Map<String, Object> args) {
        return snakerEngine.executeAndJumpTask(taskId, operator, args, null);
    }

    public boolean setOrderRestart(String orderId,String actor){
        boolean ans = false;
        String taskId = "";
        List<HistoryTask> hisTask = snakerEngine.query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
        String time = "";
        long realTime = 0;
        for (HistoryTask his : hisTask) {
            if(his.getTaskName().equals("Submission")){
                System.out.println(his.getCreateTime());
                time = his.getCreateTime().replace("-","").replace(" ", "").replace(":","");
                if(realTime<Long.valueOf(time)){
                    realTime = Long.valueOf(time);
                    taskId = his.getId();
                }
            }
        }
        if(taskId!=null){
            snakerEngine.task().withdrawTask(taskId,actor);
            ans = true;
        }
        return ans;
    }

    @Override
    public void stopOrder(String orderId) {
        snakerEngine.order().cascadeRemove(orderId);
    }

    @Override
    public List<Object> getChildrenTask(String TaskId) {
//        TODO
        return null;
    }

    @Override
    public Order getOrder(String id) {
        return snakerEngine.query().getOrder(id);
    }

    @Override
    public Task getTask(String id) {
        return snakerEngine.query().getTask(id);
    }

    @Override
    public List<Task> getConfirmTask(String actor) {
        List<Task> allTask = getTaskByActor(actor);
        List<Task> ans = new ArrayList<Task>();
        if (allTask != null && allTask.size() != 0) {
            for (Task task : allTask) {
                if(task.getTaskName().equals("Confirm")) {
                    ans.add(task);
                }
            }
        }
        return ans;
    }

    public Boolean isYourTask(String task,String actor){
        List<Task>tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOperator(actor));
        for (Task task1 : tasks) {
            if(task1.getId().equals(task))
                return true;
        }
        return false;
    }
}
