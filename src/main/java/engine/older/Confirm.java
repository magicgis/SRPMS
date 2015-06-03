package engine.older;

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
 * DATE:2015/3/20
 * TIME:23:36
 * Created by guofan on 2015/3/20
 * 这是点击确认的拦截器
 * 负责把actor与order绑定
 * 只有用户确认之后才会绑定！
 */
    public class Confirm implements SnakerInterceptor {

    @Override
    public void intercept(Execution execution) {
        /*
        * 不知为何，使用自动注入会失败
        * 而在ApplicationContext.xml中配置会自动成为全局拦截器
        * 唯有手动getBean才能正常注入
        * PS：注入用多了，都不会普通调用了！
        * 再次更新：为什么要用注入？
        * 所以本次更新去掉注入，直接调用DAO
        * 再再更新：艹，不能new
        * */
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml",
                "classpath:/applicationContext-snaker.xml");
        OrderActorDao orderActorDao =(OrderActorDao) factory.getBean("orderActorDao");
//        OrderActorDao orderActorDao = new OrderActorDao();
        String actor = execution.getOperator();
        String order = execution.getOrder().getId();
        String creator = execution.getOrder().getCreator();
        String type = orderActorDao.getByOrder(order).get(0).getType();
        if(!orderActorDao.areTheyAlreadyIn(order,actor)){
            /*负责人为1，参与者为0*/
            if(creator.equals(actor)) {
                orderActorDao.save(order, actor, 1,type);
            }else{
                orderActorDao.save(order,actor,0,type);
            }
        }
        /*获取任务完成之后产生的后续任务*/
        List<Task> tasks = execution.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(order));
        /*如果任务是递交学院审批，那么就可以把学院与管理部门与order绑定了*/
        if(tasks.size()==1&&tasks.get(0).getTaskName().equals("Submit")){
////            System.out.println("FXXK");
//            //TODO 将学院与管理部门与order绑定！
            Map<String,Object> args = new HashMap<String, Object>();
            args.put("Status","WaitForSubmit");
            execution.getEngine().order().addVariable(order, args);
//            orderActorDao.save(order,"col",2,type);
        }
    }
}