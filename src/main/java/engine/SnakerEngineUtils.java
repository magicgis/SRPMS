package engine;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.krb5.Asn1Exception;

import java.util.*;

/**
 * DATE:2015/1/22
 * TIME:23:04
 * Created by guofan on 2015/1/22
 */
@Component
public class SnakerEngineUtils {
    @Autowired
    private SnakerEngine engine;

    public String initFlows(){
        return engine.process().deploy(StreamHelper.getStreamFromClasspath("workflow/easy.snaker"));
    }

    public SnakerEngine getEngine(){
        return engine;
    }

    /**
     * 返回所有的工作流的name
     * @return String List
     */
    public List<String> getAllProcessNames(){
        /*查找所有的工作流*/
        List<Process> list = engine.process().getProcesss(new QueryFilter());
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

    /**
     * 返回所有的工作流的ID
     * @return String List
     */
    public List<String> getAllProcessId(){
        /*查找所有的工作流*/
        List<Process> list = engine.process().getProcesss(new QueryFilter());
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

    /**
     * 根据流程定义ID，操作人ID，参数列表启动流程实例
     * @param processId 流程id
     * @param operator 操作者
     * @param args 参数列表
     * @return 流程工作单实体类
     */
    public Order startInstanceById(String processId,String operator,Map<String ,Object> args){
        return engine.startInstanceById(processId,operator,args);
    }


    /**
     * 根据流程id启动流程实体
     * @param processId 流程id
     * @param operator 操作人
     * @param args 参数
     * @return 启动的流程实体
     */
    public List<Task> startAndExecute(String processId, String operator, Map<String, Object> args) {
        Order order = engine.startInstanceById(processId, operator);
        List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
        engine.task().addTaskActor(tasks.get(0).getId(), operator);
        return tasks;

    }

    /**
     * 执行任务,并生成下一任务
     * @param taskId 任务id
     * @param operator 操作者
     * @param args 参数
     * @return List<Task>
     */
    public List<Task> execute(String taskId, String operator, Map<String, Object> args) {
        args.put("S-ACTOR",operator);
        /*执行任务（不一定会产生新任务）*/
        List<Task> tasks =  engine.executeTask(taskId, operator, args);
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
            engine.task().addTaskActor(tasks.get(0).getId(),actors.get(0));
        }else{
            actors.remove(operator);
            for(String u:actors){
                Newtasks.add(createUserTask(tasks.get(0).getId(), u));
            }
            engine.task().addTaskActor(tasks.get(0).getId(),operator);
        }
        return Newtasks;
    }

    /**
     * 根据任务主键ID，操作人ID，参数列表执行任务，并且根据nodeName跳转到任意节点
     * 1、nodeName为null时，则跳转至上一步处理 2、nodeName不为null时，则任意跳转，即动态创建转移
     * @param taskId 任务id
     * @param operator 操作者
     * @param args 参数
     * @param nodeName 节点
     * @return
     */
    public List<Task> executeAndJump(String taskId, String operator, Map<String, Object> args,String nodeName) {
        return engine.executeAndJumpTask(taskId, operator, args, nodeName);
    }

    /**
     * 跳转上一步
     * @param taskId
     * @param operator
     * @param args
     * @return
     */
    public List<Task> refuse(String taskId, String operator, Map<String, Object> args) {
        return engine.executeAndJumpTask(taskId, operator, args, null);
    }

    /**
     * 获取order的最新活跃任务
     * @param ord order
     * @return List<Task>
     */
    public List<Task> getNewTasks(Order ord){
        return engine.query().getActiveTasks(new QueryFilter().setOrderId(ord.getId()));
    }

    /**
     * 添加成员到任务,创建新任务
     * @param taskId 原有任务ID
     * @param user 添加的用户
     * @return 产生的新任务
     */
    public Task createUserTask(String taskId,String user){
        List<Task> tasks = engine.task().createNewTask(taskId,0,user);
        engine.task().addTaskActor(tasks.get(0).getId(),user);
//        tasks.get(0).setVariable("X-taskType:takeIn");
//        engine.task().updateTask(tasks.get(0));
        return tasks.get(0);
    }

    /**
     * 获取用户的活跃任务
     * @param user 用户
     * @return 任务
     */
    public List<Task> getUserActiveTask(String user){
        return engine.query().getActiveTasks(new QueryFilter().setOperator(user));
    }

}
