package engine.filter;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import engine.utils.Tool;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
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
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/application*.xml");
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
        /*取出原有数据*/
        Map<String,Object> args = new HashMap<String, Object>();
        /*清空原有数据*/
//        execution.getOrder().setVariable("{}");
//        execution.getEngine().order().updateOrder(execution.getOrder());

//        Map<String,Object> params = execution.getArgs();
//        String latestKey = Tool.getLatestArgs(params);
//        if(args.containsKey("Details")){
//            args.remove("Details");
//        }
//        args.put("Details", params.get(latestKey));

        /*如果表单填写完毕,就加上部分全局变量*/
        String status = (String) execution.getArgs().get("IsComplete");
        if(Boolean.valueOf(status).equals(true)){
            args.put("Status","Complete");
        }else{
            args.put("Status", "Uncomplete");
        }
        execution.getEngine().order().addVariable(order, args);

    }
}
