package engine.role;

import entity.Staff;
import org.snaker.engine.entity.TaskActor;
import org.snaker.engine.impl.GeneralAccessStrategy;
import service.StaffService;
import util.StaticFactory;

import java.util.List;

/**
 * DATE:2015/1/27
 * TIME:14:59
 * Created by guofan on 2015/1/27
 */
public class AccessStrategy extends GeneralAccessStrategy {

    //todo 权限控制
    @Override
    public boolean isAllowed(String operator, List<TaskActor> actors) {
        for (TaskActor s : actors) {
            if (s.getActorId().equals(operator)) {
                return true;
            }
        }
        StaffService staffService = (StaffService) StaticFactory.getBean(StaffService.class);
        Staff staff = staffService.getById(operator);
        if ("2".equals(staff.getUser().getPrivilege())) {
            for (TaskActor s : actors) {
                if (s.getActorId().equals(staff.getCol().getId())) {
                    return true;
                }
            }
        }
        if ("3".equals(staff.getUser().getPrivilege())) {
            return true;
        }
        return false;
    }
}
