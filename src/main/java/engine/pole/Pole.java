package engine.pole;

import org.snaker.engine.Assignment;
import org.snaker.engine.AssignmentHandler;
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
        if(model.getName().equals("Submission")||model.getName().equals("Confirm")) {
            return "teacher";
        }else if (model.getName().equals("ApprovalByCol")){
            return "college";
        }else if(model.getName().equals("ApprovalBySch")){
            return "school";
        }else{
            return null;
        }
//        return execution.getOperator();
    }
}
