package engine;

import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;
import org.snaker.engine.helper.StreamHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * DATE:2015/1/22
 * TIME:23:04
 * Created by guofan on 2015/1/22
 */
@Component
public class SnakerEngineUtils implements Engine {
    @Autowired
    private SnakerEngine snakerEngine;

    public String initFlows(){
        return snakerEngine.process().deploy(StreamHelper.getStreamFromClasspath("workflow/easy.snaker"));
    }

    public SnakerEngine getSnakerEngine(){
        return snakerEngine;
    }

    @Override
    public List<String> getAllProcessName(){
        /*查找所有的工作流*/
        List<Process> list = snakerEngine.process().getProcesss(new QueryFilter());
        List<String> names = new ArrayList<String>();
        /*每一个进程 process*/
        for(Process entity:list){
            if(names.contains(entity.getName())){
                continue;
            }else{
                names.add(entity.getName());
            }
        }
        return names;
    }

    @Override
    public List<String> getAllProcessId(){
        /*查找所有的工作流*/
        List<Process> list = snakerEngine.process().getProcesss(new QueryFilter());
        List<String> ids = new ArrayList<String>();
        /*每一个进程 process*/
        for(Process entity:list){
            if(ids.contains(entity.getId())){
                continue;
            }else{
                ids.add(entity.getId());
            }
        }
        return ids;
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
    public List<Order> getOrderByActor(String actor) {
        return snakerEngine.query().getActiveOrders(new QueryFilter().setOperator(actor));
    }

    @Override
    public List<HistoryOrder> getAllOrderByActor(String actor) {
        return snakerEngine.query().getHistoryOrders(new QueryFilter().setOperator(actor));
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
        /*启动*/
        Order order = snakerEngine.startInstanceById(processId,operator,args);
        /*获取第一个任务*/
        List<Task> startTask = snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
        /*分配参与者*/
        snakerEngine.task().addTaskActor(startTask.get(0).getId(),operator);
        /*移除多余的参与者*/
        snakerEngine.task().removeTaskActor(startTask.get(0).getId(), "");
        return order;
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
        Map<String,Object> beforeMap = task.getVariableMap();
        /*把上一轮的map放入*/
//        beforeMap.remove("S-ACTOR");
//        beforeMap.remove("DecByCol");
//        beforeMap.remove("DecByDep");
//        NowMap.putAll(beforeMap);
        int flowOrder = 0;
        for(String key:beforeMap.keySet()){
//            args.put(key,formParams.getFirst(key));
            if(beforeMap.get(key)instanceof Map){
                flowOrder++;
                NowMap.put(key,beforeMap.get(key));
            }
        }
//        Map<String,Object>tempMap = new HashMap<String,Object>;
//        args.put("WF-TaskName",);
        String flowOderStr = "WF-"+Integer.toString(flowOrder)+"-"+task.getTaskName();
        /*把此轮的参数放入*/
        NowMap.put(flowOderStr,args);
        /*决策参数需要单独处理*/
        if(args.containsKey("DecByCol")){
            NowMap.put("DecByCol",args.get("DecByCol"));
        }
        if(args.containsKey("DecByDep")){
            NowMap.put("DecByDep",args.get("DecByDep"));
        }
        /*把操作者作为参与者*/
        NowMap.put("S-ACTOR", operator);

        /*执行任务（不一定会产生新任务）*/
        List<Task> tasks =  snakerEngine.executeTask(taskId, operator, NowMap);
        if(tasks.isEmpty()){
            return null;
        }

        /*获取下一任务参与者*/
        String actorText =(String)args.get("WF-NextTaskActor");
        String[] actorString = actorText.split(",");
        List<String> actors = new ArrayList<String>();
        for(String u:actorString){
            if (!u.equals(" "))
                if (!u.equals("")) {
                    actors.add(u);
                }
        }
        for(int i = 1;i < actors.size();i++){
            snakerEngine.task().addTaskActor(tasks.get(0).getId(),1,"");
        }
        tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(orderId));
        for(Task u:tasks){
            snakerEngine.task().addTaskActor(u.getId(),actors.get(0));
            snakerEngine.task().removeTaskActor(u.getId(),"");
            actors.remove(0);
        }
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

    @Override
    public List<Task> getNewTasks(Order ord){
        return snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(ord.getId()));
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
}
