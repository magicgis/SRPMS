package engine.role;

import org.snaker.engine.entity.TaskActor;
import org.snaker.engine.impl.GeneralAccessStrategy;

import java.util.List;

/**
 * DATE:2015/1/27
 * TIME:14:59
 * Created by guofan on 2015/1/27
 */
public class AccessStrategy extends GeneralAccessStrategy {
    @Override
    public boolean isAllowed(String operator, List<TaskActor> actors) {
//        System.out.println(operator);
        for(TaskActor s:actors){
//            System.out.println("getActorId():"+s.getActorId());
        }
        return true;
    }
}
