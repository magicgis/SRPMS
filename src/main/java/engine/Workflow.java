package engine;

import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;

import java.util.List;
import java.util.Map;

/**
 * DATE:2015/2/12
 * TIME:18:23
 * Created by guofan on 2015/2/12
 */
public interface Workflow {
    /**
     * 获取当前系统启用的工作流程名
     * @return String list
     */
    List<String> getProcessName();

    /**
     * 获取当前系统启动的工作流程的id
     * @return String list
     */
    List<String> getProcessId();

    /**
     * 根据id启动一个新的工作流实例
     * @param processId 工作流id
     * @param operator 操作人，一般为staffId
     * @param args 完成此步骤操作所需要，所产生的数据（表单，决策之类的）
     * @return 流程 order
     */
    Order startInstanceById(String processId,String operator,Map<String ,Object> args);

    /**
     * 根据流程id启动流程实体并执行第一个任务列表
     * @param processId 工作流id
     * @param operator 操作人 staffId
     * @param args 参数
     * @return 启动的流程实体
     */
    Order startAndExecute(String processId, String operator, Map<String, Object> args);

    /**
     * 执行任务
     * @param taskId 任务实例id
     * @param operator 操作人 staffId
     * @param args 完成此步骤操作所需要，所产生的数据（表单，决策之类的）
     * @return 流程的下一步骤所有任务
     */
    List<Task> execute(String taskId, String operator, Map<String, Object> args);

    /**
     * 根据任务主键ID，操作人ID，参数列表执行任务，并且根据nodeName跳转到任意节点
     * 1、nodeName为null时，则跳转至上一步处理 2、nodeName不为null时，则任意跳转，即动态创建转移
     * @param taskId 任务id
     * @param operator 操作者 staffId
     * @param args 完成此步骤操作所需要，所产生的数据（表单，决策之类的）
     * @param nodeName 节点
     * @return 流程的下一步骤所有任务
     */
    List<Task> executeAndJump(String taskId, String operator, Map<String, Object> args,String nodeName);

    /**
     * 跳转上一步
     * @param taskId 任务id
     * @param operator 操作者 staffId
     * @param args 完成此步骤操作所需要，所产生的数据（表单，决策之类的）
     * @return 流程的下一步骤所有任务
     */
    List<Task> refuse(String taskId, String operator, Map<String, Object> args);

    /**
     * 获取order的最新活跃任务
     * @param order orderId
     * @return List<Task>
     */
    List<Task> getNewTasks(String order);

    /**
     * 添加成员到某一任务（一般用于确认提交）
     * @param task 任务实例
     * @param members staffId[]
     * @return 所产生的新任务
     */
    List<Task> addMemberToTask(Task task,String[] members);

    /**
     * 查找员工已完成的所有流程实例（主办或者参与）
     * @param staffId staffId
     * @return HistoryOrder List
     */
    List<HistoryOrder> getHistoryOrder(String staffId);

    /**
     * 查找员工未完成的流程实例（主办或者参与）
     * @param staffId staffId
     * @return Order List
     */
    List<Order> getOrder(String staffId);

    /**
     * 查找员工当前待办任务
     * @param StaffId staffId
     * @return Task List
     */
    List<Task> getTask(String StaffId);

    /**
     * 查找员工已完成任务
     * @param StaffId staffId
     * @return HistoryTask List
     */
    List<HistoryTask> getHistoryTask(String StaffId);
}
