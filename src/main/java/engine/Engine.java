package engine;

import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;
import org.snaker.engine.helper.StreamHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/2/12
 * TIME:18:23
 * Created by guofan on 2015/2/12
 */
public interface Engine {

    /**
     * 初始化，返回流程ID
     * @return process ID
     */
    public String initFlows();

    /**
     * 返回所有的工作流的名称
     * @return String List
     */
    public List<String> getAllProcessName();

    /**
     * 返回所有的工作流的ID
     * @return String List
     */
    public List<String> getAllProcessId();

    /**
     * 根据流程定义ID，操作人ID，参数列表启动流程实例,并完成第一个任务！
     * @param processId 流程id
     * @param operator 操作者
     * @param args 参数列表
     * @return 流程工作单实体类
     */
    public Order startInstanceById(String processId,String operator,Map<String ,Object> args);

    /**
     * 根据orderId获取活跃任务
     * @param orderId orderId
     * @return 该order的所有活跃任务
     */
    public List<Task> getTaskByOrder(String orderId);

    /**
     * 根据orderId获取历史任务
     * @param orderId orderId
     * @return 该order的所有历史任务
     */
    public List<HistoryTask> getHisTaskByOrder(String orderId);

    /**
     * 根据actor获取参与order(未完成)
     * @param actor 参与者
     * @return order List
     */
    public List<Order> getOrderByActor (String actor);

    /**
     * 根据actor获取所参与order(已完成和未完成)
     * @param actor 参与者
     * @return order List
     */
    public List<HistoryOrder> getAllOrderByActor(String actor);

    /**
     * 获取用户当前任务
     * @param actor 参与者
     * @return task list
     */
    public List<Task> getTaskByActor(String actor);

    /**
     * 获取用户已完成任务
     * @param actor 参与者
     * @return task list
     */
    public List<HistoryTask> getHisTaskByActor(String actor);

    /**
     * 执行任务,并生成下一任务
     * @param taskId 任务id
     * @param operator 操作者
     * @param args 参数
     * @return List<Task>
     */
    public List<Task> execute(String taskId, String operator, Map<String, Object> args) ;

    /**
     * 根据任务主键ID，操作人ID，参数列表执行任务，并且根据nodeName跳转到任意节点
     * 1、nodeName为null时，则跳转至上一步处理 2、nodeName不为null时，则任意跳转，即动态创建转移
     * @param taskId 任务id
     * @param operator 操作者
     * @param args 参数
     * @param nodeName 节点
     * @return
     */
    public List<Task> executeAndJump(String taskId, String operator, Map<String, Object> args,String nodeName) ;

    /**
     * 跳转上一步
     * @param taskId
     * @param operator
     * @param args
     * @return
     */
    public List<Task> refuse(String taskId, String operator, Map<String, Object> args) ;

    /**
     * 获取order的最新活跃任务
     * @param ord order
     * @return List<Task>
     */
    public List<Task> getNewTasks(Order ord);

    /**
     * 添加成员到任务,创建新任务
     * @param taskId 原有任务ID
     * @param user 添加的用户
     * @return 产生的新任务
     */
    public Task createUserTask(String taskId,String user);

}
