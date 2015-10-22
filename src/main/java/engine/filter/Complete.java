package engine.filter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import entity.Paper;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import service.MagService;
import service.PaperService;
import util.StaticFactory;

import java.io.Serializable;
import java.util.ArrayList;
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

        MagService magService = (MagService) StaticFactory.getBean(MagService.class);
//        ConferService conferService = (ConferService) StaticFactory.getBean(ConferService.class);
        PaperService paperService = (PaperService) StaticFactory.getBean(PaperService.class);

        String orderId = execution.getOrder().getId();


        Map<String, Object> args = execution.getOrder().getVariableMap();

        ArrayList userInfo;

        String type = (String) args.get("WF_Type");
        switch (type) {
            case "paper":
                Paper paper = new Paper();
                Map<String, Object> info = (Map<String, Object>) engine.utils.Tool.getLatestArgs(args);
                util.Trans.putMapOnObj(paper, info);
                System.out.println(info.toString());
                System.out.println(paper.toString());
                paper.setAttachment((String) info.get("filesData"));
                JsonFactory factory = new JsonFactory();
                ObjectMapper mapper = new ObjectMapper(factory);
                userInfo = (ArrayList) info.get("actors");

                if ("magPaper".equals(info.get("type"))) {
                    paper.setMag(magService.getById((Serializable) ((HashMap) info.get("mag")).get("id")));
                }
                else if ("conferPaper".equals(info.get("type"))) {
//                    paper.setConfer(conferService.getById((Serializable) info.get("confer.id")));
                    //todo 这儿情况复杂，可能需要先存储一个会议记录，但是感觉没必要
                }
                else if ("newsPaper".equals(info.get("type"))) {
//                    paper.setConfer(conferService.getById((Serializable) info.get("news.id")));
                    //todo 这儿也需要特殊处理，我艹
                }
                Serializable id = paperService.add(paper);

                break;

        }

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
