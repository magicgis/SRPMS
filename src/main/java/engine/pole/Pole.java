package engine.pole;

import org.snaker.engine.Assignment;
import org.snaker.engine.core.Execution;
import org.snaker.engine.model.TaskModel;

/**
 * DATE:2015/1/24
 * TIME:19:01
 * Created by guofan on 2015/1/24
 */
public class Pole extends Assignment {
    @Override
    public Object assign(TaskModel model, Execution execution) {
        /* model 相当于预期的 task node */
        /* execution 则相当于实际执行时的 task entity */
//        System.out.println(model.getAssignee());
//        System.out.println(execution.ge());
//        System.out.println(execution.)
        String taskName = model.getName();
        if (taskName.equals("ApprovalByCol") || taskName.equals("SubmitByCol")) {
            /*TODO 根据order里的变量来分配任务*/
            return "col";
        } else if (model.getName().equals("ApprovalByDep")) {
            /*TODO 根据order里的变量来分配任务*/
            return "dep";
        }else{
            /*一般情况下，我们都将任务分配给order的创造者*/
            return execution.getOrder().getCreator();
        }
//        return execution.getOperator();
    }
}
