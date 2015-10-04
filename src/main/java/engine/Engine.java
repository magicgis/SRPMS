package engine;

import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;

import java.util.List;
import java.util.Map;

/**
 * DATE:2015/2/12
 * TIME:18:23
 * Created by guofan on 2015/2/12
 */
public interface Engine {

    /**
     * 初始化
     * Only For Test!!!
     *
     * @return process ID
     */
    void initFlows();

    /**
     * 获取所有工作流
     *
     * @return List<Process>
     */
    List<Process> getAllProcess();

    /**
     * 根据流程名获取流程
     *
     * @param name 名称
     * @return Process
     */
    Process getProcessByName(String name);

    /**
     * 根据流程定义ID，操作人ID，参数列表启动流程实例！
     *
     * @param processId 流程id
     * @param operator  操作者
     * @param args      参数列表
     * @return 流程工作单实体类
     */
    Order startInstanceById(String processId, String operator, Map<String, Object> args);

    Order startInstanceById(String processId, String operator, String type);

    /**
     * 根据orderId获取活跃任务
     *
     * @param orderId orderId
     * @return 该order的所有活跃任务
     */
    List<Task> getTaskByOrder(String orderId);

    /**
     * 根据orderId获取历史任务
     *
     * @param orderId orderId
     * @return 该order的所有历史任务
     */
    List<HistoryTask> getHisTaskByOrder(String orderId);

    /**
     * 根据actor获取所有order
     * 仅限于各种科研项目！
     *
     * @param actor actor
     * @return Order List
     */
    List<Order> getAllOrderByActor(String actor);

    /**
     * 根据actor获取主导order
     * 仅限于各种科研项目！
     *
     * @param actor actor
     * @return order List
     */
    List<Order> getMainOrderByActor(String actor);

    /**
     * 根据actor获取参与order
     * 仅限于各种科研项目！
     *
     * @param actor actor
     * @return order List
     */
    List<Order> getSecOrderByActor(String actor);

    /**
     * 根据用户和角色来获取order
     * 仅限于各种科研项目！
     *
     * @param actor actor
     * @param role  角色
     * @return
     */
    List<Order> getOrderByActorAndRole(String actor, Integer role);

    /**
     * 获取用户当前任务
     *
     * @param actor 参与者
     * @return task list
     */
    List<Task> getTaskByActor(String actor);

    /**
     * 获取用户已完成任务
     *
     * @param actor 参与者
     * @return task list
     */
    List<HistoryTask> getHisTaskByActor(String actor);

    /**
     * 执行任务
     *
     * @param taskId   任务id
     * @param operator 操作者
     * @param args     参数
     * @return List<Task>
     */
    List<Task> execute(String taskId, String operator, Map<String, Object> args);

    /**
     * 跳转上一步
     *
     * @param taskId
     * @param operator
     * @param args
     * @return
     */
    List<Task> refuse(String taskId, String operator, Map<String, Object> args);

    /**
     * 撤回任务
     *
     * @param orderId 撤回的orderId
     * @param actor   操作人
     * @return boolean
     */
    boolean setOrderRestart(String orderId, String actor);

    /**
     * 终止order
     *
     * @param orderId
     */
    void stopOrder(String orderId);


    /**
     * 顾名思义
     *
     * @param id id
     * @return Order
     */
    Order getOrder(String id);

    /**
     * 顾名思义
     *
     * @param id id
     * @return Task
     */
    Task getTask(String id);

    /**
     * 获取待确认的任务
     *
     * @param actor actor
     * @return List<Task>
     */
    List<Task> getConfirmTask(String actor);

    /**
     * 判断该任务是否属于某用户
     *
     * @param task  任务
     * @param actor 用户
     * @return T/F
     */
    Boolean isYourTask(String task, String actor);

    /**
     * 获取特定process的所有Order
     *
     * @param processName process名称
     * @return Order List
     */
    List<Order> getOrderByProcee(String processName);


}
