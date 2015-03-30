package engine;

import engine.entity.OrderActor;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;

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
     * Only For Test!!!
     * @return process ID
     */
    String initFlows();

    /**
     * 获取所有工作流
     * @return List<Process>
     */
    List<Process> getAllProcess();

    /**
     * 根据流程名获取流程
     * @param name 名称
     * @return Process
     */
    Process getProcessByName(String name);

    /**
     * 根据流程定义ID，操作人ID，参数列表启动流程实例！
     * @param processId 流程id
     * @param operator 操作者
     * @param args 参数列表
     * @return 流程工作单实体类
     */
    Order startInstanceById(String processId, String operator, Map<String, Object> args);

    /**
     * 根据orderId获取活跃任务
     * @param orderId orderId
     * @return 该order的所有活跃任务
     */
    List<Task> getTaskByOrder(String orderId);

    /**
     * 根据orderId获取历史任务
     * @param orderId orderId
     * @return 该order的所有历史任务
     */
    List<HistoryTask> getHisTaskByOrder(String orderId);

    /**
     * 根据actor获取参与order关系
     * @param actor 参与者
     * @return OrderActor List
     */
    List<OrderActor> getAllOrderByActor(String actor);

    /**
     * 根据actor获取主导order
     * @param actor 参与者
     * @return order List
     */
    List<Order> getOrderByActor(String actor);

    /**
     * 获取用户当前任务
     * @param actor 参与者
     * @return task list
     */
    List<Task> getTaskByActor(String actor);

    /**
     * 获取用户已完成任务
     * @param actor 参与者
     * @return task list
     */
    List<HistoryTask> getHisTaskByActor(String actor);

    /**
     * 执行任务
     * @param taskId 任务id
     * @param operator 操作者
     * @param args 参数
     * @return List<Task>
     */
    List<Task> execute(String taskId, String operator, Map<String, Object> args) ;

    /**
     * 根据任务主键ID，操作人ID，参数列表执行任务，并且根据nodeName跳转到任意节点
     * 1、nodeName为null时，则跳转至上一步处理 2、nodeName不为null时，则任意跳转，即动态创建转移
     * @param taskId 任务id
     * @param operator 操作者
     * @param args 参数
     * @param nodeName 节点
     * @return
     */
    List<Task> executeAndJump(String taskId, String operator, Map<String, Object> args, String nodeName) ;

    /**
     * 跳转上一步
     * @param taskId
     * @param operator
     * @param args
     * @return
     */
    List<Task> refuse(String taskId, String operator, Map<String, Object> args) ;

    /**
     * 撤回任务
     * @param taskId 撤回到的任务ID
     * @param actor 操作人
     * @return boolean
     */
    boolean setOrderRestart(String taskId, String actor);

    /**
     * 终止order
     * @param orderId
     */
    void stopOrder(String orderId);


    List<Object> getChildrenTask(String TaskId);

    /**
     * 顾名思义
     * @param id id
     * @return Order
     */
    Order getOrder(String id);

    /**
     * 顾名思义
     * @param id id
     * @return Task
     */
    Task getTask(String id);

    List<Task> getConfirmTask(String actor);


}
