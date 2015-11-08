package engine.filter;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import entity.Paper;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import service.*;
import util.StaticFactory;

import java.io.Serializable;
import java.util.*;

import static util.Trans.getFileData;
import static util.Trans.moveMap;

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
        BaseInfoService baseInfoService = (BaseInfoService) StaticFactory.getBean(BaseInfoService.class);
        StaRefService staRefService = (StaRefService) StaticFactory.getBean(StaRefService.class);

        String orderId = execution.getOrder().getId();


        Map<String, Object> args = execution.getOrder().getVariableMap();

        ArrayList userInfo;

        String type = (String) args.get("WF_Type");
        switch (type) {
            case "paper":
                Paper paper = new Paper();
                Map<String, Object> info = (Map<String, Object>) args.get("WF_0_Submission");
                //todo 临时措施
                info.put("score", info.get("sum"));
                util.Trans.putMapOnObj(paper, info);
                //处理附件信息
                paper.setAttachment(getFileData(info.get("filesData")));
                //处理部门信息
                String deptId = (String) ((Map) info.get("dept")).get("id");
                paper.setDept(baseInfoService.getById(deptId));
                //那些不好显示的部分
                Map argMap = new HashMap();
                moveMap(info, argMap, Arrays.asList("actors", "filesData", "dept", "mag", "confer", "newspaper"));
                paper.setArgMap(argMap);
                //子类进行处理，holy shit
                if ("magPaper".equals(info.get("type"))) {
                    paper.setMag(magService.getById((Serializable) ((HashMap) info.get("mag")).get("id")));
                }
                else if ("conferPaper".equals(info.get("type"))) {
//                    paper.setConfer(conferService.getById((Serializable) info.get("confer.id")));
                    //todo 这儿情况复杂，可能需要先存储一个会议记录，但是感觉没必要
                }
                else if ("newsPaper".equals(info.get("type"))) {
//                    paper.setConfer(conferService.getById((Serializable) info.get("news.id")));
                    //todo 这儿也需要特殊处理，
                }

                Serializable id = paperService.add(paper);

                userInfo = (ArrayList) info.get("actors");
                staRefService.insertRelation((String) id, "paper", userInfo);

                break;
            case "book":
                break;
            case "patent":
                PatentService patentService = (PatentService) StaticFactory.getBean(PatentService.class);
//                Patent patent = patentService.getById()
            default:
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
