package engine.filter;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import entity.Paper;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/3/22
 * TIME:16:21
 * Created by guofan on 2015/3/22
 * 任务完成的拦截器
 * 负责把所有确认信息入库
 */
public class Complete implements SnakerInterceptor {

    @Override
    public void intercept(Execution execution) {

        String orderId = execution.getOrder().getId();


        Map<String, Object> args = execution.getOrder().getVariableMap();
        Paper paper = new Paper();
        Map<String, Object> info = (Map<String, Object>) engine.utils.Tool.getLatestArgs(args);
        util.Trans.putMapOnObj(paper, info);
//        paper.setBgPage(info.get("bgPage"));
        System.out.println(info.toString());
        System.out.println(paper.toString());
        paper.setAttachment((String) info.get("filesData"));
        OrderActorDao orderActorDao = (OrderActorDao) util.StaticFactory.getBean(OrderActorDao.class);
        List<OrderActor> x = orderActorDao.getByOrder(orderId);
        for (OrderActor orderActor : x) {
            orderActor.setStatus(0);
            orderActorDao.update(orderActor);
        }


        HashMap<String, Object> fxxk = new HashMap<>();
        fxxk.put("Status", "AllCompelete");
        execution.getEngine().order().addVariable(orderId, fxxk);


//        Order order = execution.getOrder();
//        HistoryOrder hisOrder = execution.getEngine().query().getHistOrder(order.getId());
//        hisOrder.setVariable(order.getVariable());
//        execution.getEngine().order().
//        execution.getEngine().

//        System.out.println();
    }
}
