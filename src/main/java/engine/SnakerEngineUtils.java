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
        return snakerEngine.startInstanceById(processId,operator,args);
    }

    @Override
    public List<Task> execute(String taskId, String operator, Map<String, Object> args) {
        /*把操作者作为参与者*/
        args.put("S-ACTOR",operator);
        /*执行任务（不一定会产生新任务）*/
        List<Task> tasks =  snakerEngine.executeTask(taskId, operator, args);
        if(tasks.isEmpty()){
            return null;
        }
        String actorText =(String)args.get("X-NextTaskActor");
        String[] actorString = actorText.split(",");
        List<String> actors = new ArrayList<String>();
        for(String u:actorString){
            if (!u.equals(" "))
                if (!u.equals("")) {
                    actors.add(u);
                }
        }
        List<Task> Newtasks = new ArrayList<Task>();
        Newtasks.addAll(tasks);
        if(actors.size() == 1){
            snakerEngine.task().addTaskActor(tasks.get(0).getId(),actors.get(0));
        }else{
            actors.remove(operator);
            for(String u:actors){
                Newtasks.add(createUserTask(tasks.get(0).getId(), u));
            }
            snakerEngine.task().addTaskActor(tasks.get(0).getId(),operator);
        }
        return Newtasks;
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
    public Task createUserTask(String taskId,String user){
        List<Task> tasks = snakerEngine.task().createNewTask(taskId,0,user);
        snakerEngine.task().addTaskActor(tasks.get(0).getId(),user);
//        tasks.get(0).setVariable("X-taskType:takeIn");
//        snakerEngine.task().updateTask(tasks.get(0));
        return tasks.get(0);
    }

}
