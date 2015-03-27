package engine.filter;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/3/21
 * TIME:3:07
 * Created by guofan on 2015/3/21
 * 这是填写表单完毕之后提交的拦截器
 * 把order与actor绑定在一起
 */
public class Submit implements SnakerInterceptor {
    @Override
    public void intercept(Execution execution) {
        /*手工获取DAO*/
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        OrderActorDao orderActorDao =(OrderActorDao) factory.getBean("orderActorDao");
        String actor = execution.getOperator();
        String order = execution.getOrder().getId();
        String creator = execution.getOrder().getCreator();
        List<OrderActor> relationship = orderActorDao.getByOrder(order);
        String type = orderActorDao.getByOrder(order).get(0).getType();
        if(relationship!=null&&relationship.size()>1){
            /*之前已经绑定过，说明这是被驳回的，全部取消绑定*/
            orderActorDao.deleteAllOrder(order);
            /*把负责人的绑定加上*/
            if(actor.equals(creator)){
                orderActorDao.save(order,actor,1,type);
            }
        }

        /*-----------分割线-----------*/
        /*所产生的新任务的arg*/
//        Map<String,Object> taskArgs = execution.getArgs();
//        int latestNum = 0;
//        for(String key:taskArgs.keySet()){
//            if(key.matches("WF\\d+Submission")){
//                int n = Integer.valueOf(key.substring(2, key.lastIndexOf('S')));
//                if(latestNum<n){
//                    latestNum=n;
//                }
//            }
//        }
//        String latestKey = "WF"+latestNum+"Submission";
//        Map<String,Object> subArg =(Map) taskArgs.get(latestKey);
//        /*移除该order的部分全局变量*/
//        Order oldOrder = execution.getOrder();
//        Map<String,Object> oldArgs = oldOrder.getVariableMap();
//        oldArgs.remove("WF-Col");
//        oldOrder.setVariable(oldArgs.toString());
//        execution.getEngine().order().updateOrder(oldOrder);
//        /*如果表单填写完毕,就加上部分全局变量*/
//        if(execution.getArgs().get("IsComplete").equals(true)){
//            Map<String,Object> args = new HashMap<String,Object>();
//            args.putAll(oldArgs);
//            args.put("WF-Col",subArg.get("WF-Col"));
//            oldOrder.setVariable(args.toString());
//            execution.getEngine().order().updateOrder(oldOrder);
//        }
    }
}
