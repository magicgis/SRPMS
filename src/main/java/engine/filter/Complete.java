package engine.filter;

import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;

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
//        Order order = execution.getOrder();
//        HistoryOrder hisOrder = execution.getEngine().query().getHistOrder(order.getId());
//        hisOrder.setVariable(order.getVariable());
//        execution.getEngine().order().
//        execution.getEngine().

//        System.out.println();
    }
}
