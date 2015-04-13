package engine.filter;

import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/4/8
 * TIME:12:19
 * Created by guofan on 2015/4/8
 */
public class Decision implements SnakerInterceptor {
    @Override
    public void intercept(Execution execution) {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/application*.xml");
        OrderActorDao orderActorDao =(OrderActorDao) factory.getBean("orderActorDao");
        String actor = execution.getOperator();
        String order = execution.getOrder().getId();
        String creator = execution.getOrder().getCreator();
        String type = orderActorDao.getByOrder(order).get(0).getType();
        Map<String,Object> args = new HashMap<String, Object>();
        List<Task> tasks = execution.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(order));
        Map<String,Object> dec = execution.getArgs();
        boolean flag = false;
        if(dec.containsKey("DecByCol")){
            if(dec.get("DecByCol") instanceof String) {
                flag = Boolean.valueOf(dec.get("DecByCol").toString());
            }else{
                flag = (Boolean) dec.get("DecByCol");
            }
            if(flag){
                orderActorDao.save(order,"dep",3,type);
                args.put("Status", "WaitForDep");
            }else{
                args.put("Status","RefuseByCol");
            }

        }else if(dec.containsKey("DecByDep")){
            if(dec.get("DecByDep") instanceof String) {
                flag = Boolean.valueOf(dec.get("DecByDep").toString());
            }else{
                flag = (Boolean) dec.get("DecByDep");
            }
            if(flag){
                args.put("Status", "AllCompelete");
            }else{
                args.put("Status","RefuseByDep");
            }
        }
//        if(tasks.size()==1&&tasks.get(0).getTaskName().equals("Submission")){
//            args.put("Status", "RefuseByCol");
//        }else{
//            args.put("Status", "WaitForSch");
//        }
        execution.getEngine().order().addVariable(order, args);
    }
}
