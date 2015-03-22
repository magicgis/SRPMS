package engine.filter;

import engine.entity.OrderActorDao;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * DATE:2015/3/21
 * TIME:3:07
 * Created by guofan on 2015/3/21
 * 这是填写表单完毕之后提交的拦截器
 * 作用有：给order分类（到底是哪个学院，哪个类别）
 * 把order与actor绑定在一起
 */
public class SubmitFilter implements SnakerInterceptor {
    @Override
    public void intercept(Execution execution) {
        /*手工获取DAO*/
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        OrderActorDao orderActorDao =(OrderActorDao) factory.getBean("orderActorDao");
        String actor = execution.getOperator();
        String order = execution.getOrder().getId();
        String creator = execution.getOrder().getCreator();
        /*如果之前已经绑定过，则全部取消绑定*/
        if(orderActorDao.areTheyAlreadyIn(order,actor)){
            orderActorDao.deleteAllOrder(order);
        }
        /*把负责人的绑定加上*/
        if(actor.equals(creator)){
            orderActorDao.save(order,actor,1);
        }
        /*-----------分割线-----------*/
        /*所产生的新任务的arg*/
        Map<String,Object> taskArgs = execution.getArgs();
        /*想法设法从其中取出最新提交的表单MAP*/
        int latestNum = 0;
        for(String key:taskArgs.keySet()){
            if(key.matches("WF-\\d+-Submission")){
                int n = Integer.valueOf(key.substring(key.indexOf('-') + 1, key.lastIndexOf('-')));
                if(latestNum<n){
                    latestNum=n;
                }
            }
        }
        String latestKey = "WF-"+latestNum+"-Submission";
        Map<String,Object> subArg =(Map) taskArgs.get(latestKey);
        /*移除该order的部分全局变量*/
        Order oldOrder = execution.getOrder();
        Map<String,Object> oldArgs = oldOrder.getVariableMap();
        oldArgs.remove("WF-Type");
        oldArgs.remove("WF-Col");
        oldOrder.setVariable(oldArgs.toString());
        execution.getEngine().order().updateOrder(oldOrder);
        /*如果表单填写完毕,就加上部分全局变量*/
        if(execution.getArgs().get("IsComplete").equals(true)){
            Map<String,Object> args = new HashMap<String,Object>();
            args.putAll(oldArgs);
            args.put("WF-Type",subArg.get("WF-Type"));
            args.put("WF-Col",subArg.get("WF-Col"));
            oldOrder.setVariable(args.toString());
            execution.getEngine().order().updateOrder(oldOrder);
        }
    }
}
