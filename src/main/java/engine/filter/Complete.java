package engine.filter;

import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import entity.*;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import service.*;
import util.StaticFactory;

import java.io.Serializable;
import java.util.*;

import static util.Trans.moveMap;
import static util.Trans.putMapOnObj;

/**
 * DATE:2015/3/22
 * TIME:16:21
 * Created by guofan on 2015/3/22
 * 任务完成的拦截器
 * 负责把所有确认信息入库
 */
public class Complete implements SnakerInterceptor {

    /**
     * without any test
     *
     * @param execution
     */
    @Override
    public void intercept(Execution execution) {

        BaseInfoService baseInfoService = (BaseInfoService) StaticFactory.getBean(BaseInfoService.class);
        RelationService relationService = (RelationService) StaticFactory.getBean(RelationService.class);
        StandardInfoService standardInfoService = (StandardInfoService) StaticFactory.getBean(StandardInfoService.class);

        String orderId = execution.getOrder().getId();
        Map<String, Object> args = execution.getOrder().getVariableMap();


        Map<String, Object> info = (Map<String, Object>) args.get("WF_0_Submission");

        Serializable id = null;
        Map init;

        String type = (String) args.get("WF_Type");
        switch (type) {
            case "paper":
                PaperService paperService = (PaperService) StaticFactory.getBean(PaperService.class);
                Paper paper = new Paper();
                util.Trans.putMapOnObj(paper, info);
                //处理部门信息
                String deptId = (String) ((Map) info.get("dept")).get("id");
                paper.setDept(baseInfoService.getById(deptId));
                //那些不好显示的部分
                Map argMap = new HashMap();
                moveMap(info, argMap, Arrays.asList("actors", "filesData", "dept", "mag", "confer", "newspaper"));
                paper.setArgMap(argMap);
                //子类进行处理，holy shit
                if ("magPaper".equals(info.get("type"))) {
                    MagService magService = (MagService) StaticFactory.getBean(MagService.class);
                    paper.setMag(magService.getById((Serializable) ((HashMap) info.get("mag")).get("id")));
                }
                else if ("conferPaper".equals(info.get("type"))) {
                    ConferService conferService = (ConferService) StaticFactory.getBean(ConferService.class);
                    Map cf = (Map) info.get("confer");
                    Confer confer = new Confer();
                    putMapOnObj(confer, cf);
                    try {
                        confer.setStandard(standardInfoService.getById((Serializable) ((Map) cf.get("standard")).get("id")));
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                    conferService.save(confer);
                    paper.setConfer(confer);
                }
                else if ("newsPaper".equals(info.get("type"))) {
                    NewspaperService newspaperService = (NewspaperService) StaticFactory.getBean(NewspaperService.class);
                    Map np = (Map) info.get("newspaper");
                    Newspaper newspaper = new Newspaper();
                    putMapOnObj(newspaper, np);
                    try {
                        newspaper.setStandard(standardInfoService.getById((Serializable) ((Map) np.get("standard")).get("id")));
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                    newspaperService.save(newspaper);
                    paper.setNewspaper(newspaper);
                }
                id = paperService.add(paper);
                break;
            case "achAppraisal":
                AchAppraisalService achAppraisalService = (AchAppraisalService) StaticFactory.getBean(AchAppraisalService.class);
                id = (String) args.get("WF_Entity");
                AchAppraisal achAppraisal = achAppraisalService.getById(id);
                init = achAppraisal.getArgMap();
                moveMap(info, init, Arrays.asList("actors"));
                achAppraisal.setArgMap(init);
                achAppraisal.setProcess("9");
                achAppraisalService.update(achAppraisal);
                break;
            case "achAward":
                AchAwardService achAwardService = (AchAwardService) StaticFactory.getBean(AchAwardService.class);
                id = (String) args.get("WF_Entity");
                AchAward achAward = achAwardService.getById(id);
                init = achAward.getArgMap();
                moveMap(info, init, Arrays.asList("actors"));
                achAward.setArgMap(init);
                achAward.setProcess("9");
                achAwardService.update(achAward);
                break;
            case "achTran":
                AchTranService achTranService = (AchTranService) StaticFactory.getBean(AchTranService.class);
                id = (String) args.get("WF_Entity");
                AchTran achTran = achTranService.getById(id);
                init = achTran.getArgMap();
                moveMap(info, init, Arrays.asList("actors"));
                achTran.setArgMap(init);
                achTran.setProcess("9");
                achTranService.update(achTran);
                break;
            case "book":
                BookService bookService = (BookService) StaticFactory.getBean(BookService.class);
                id = (String) args.get("WF_Entity");
                Book book = bookService.getById(id);
                init = book.getArgMap();
                moveMap(info, init, Arrays.asList("actors"));
                book.setArgMap(init);
                book.setProcess("9");
                bookService.update(book);
                break;
            case "patent":
                PatentService patentService = (PatentService) StaticFactory.getBean(PatentService.class);
                id = (String) args.get("WF_Entity");
                Patent patent = patentService.getById(id);
                init = patent.getArgMap();
                moveMap(info, init, Arrays.asList("actors"));
                patent.setArgMap(init);
                patent.setProcess("9");
                patentService.update(patent);
                break;
            case "project":
                ProjectService projectService = (ProjectService) StaticFactory.getBean(ProjectService.class);
                id = (String) args.get("WF_Entity");
                Project project = projectService.getById(id);
                init = project.getArgMap();
                //todo 这儿需要把分数给补上   patent.setScore();
                moveMap(info, init, Arrays.asList("actors"));
                project.setArgMap(init);
                project.setProcess("9");
                projectService.update(project);
                break;
            case "others":
                OthersService othersService = (OthersService) StaticFactory.getBean(OthersService.class);
                id = (String) args.get("WF_Entity");
                Others others = othersService.getById(id);
                init = others.getArgMap();
                //todo 这儿需要把分数给补上   patent.setScore();
                moveMap(info, init, Arrays.asList("actors"));
                others.setArgMap(init);
                others.setProcess("9");
                othersService.update(others);
                break;
            case "food":
                FoodService foodService = (FoodService) StaticFactory.getBean(FoodService.class);
                id = (String) args.get("WF_Entity");
                Food food = foodService.getById(id);
                init = food.getArgMap();
                //todo 这儿需要把分数给补上   patent.setScore();
                moveMap(info, init, Arrays.asList("actors"));
                food.setArgMap(init);
                food.setProcess("9");
                foodService.update(food);
            case "instrument":
                InstrumentService instrumentService = (InstrumentService) StaticFactory.getBean(InstrumentService.class);
                id = (String) args.get("WF_Entity");
                Instrument instrument = instrumentService.getById(id);
                init = instrument.getArgMap();
                //todo 这儿需要把分数给补上   patent.setScore();
                moveMap(info, init, Arrays.asList("actors"));
                instrument.setArgMap(init);
                instrument.setProcess("9");
                instrumentService.update(instrument);
            case "medicine":
                MedicineService medicineService = (MedicineService) StaticFactory.getBean(MedicineService.class);
                id = (String) args.get("WF_Entity");
                Medicine medicine = medicineService.getById(id);
                init = medicine.getArgMap();
                //todo 这儿需要把分数给补上   patent.setScore();
                moveMap(info, init, Arrays.asList("actors"));
                medicine.setArgMap(init);
                medicine.setProcess("9");
                medicineService.update(medicine);
            default:
                break;
        }

        relationService.insertRelation((String) id, type, (ArrayList) info.get("actors"));

        OrderActorDao orderActorDao = (OrderActorDao) util.StaticFactory.getBean(OrderActorDao.class);
        List<OrderActor> x = orderActorDao.getByOrder(orderId);
        for (OrderActor orderActor : x) {
            orderActor.setStatus(0);
            orderActorDao.update(orderActor);
        }


        HashMap<String, Object> fxxk = new HashMap<>();
        fxxk.put("Status", "AllCompelete");
        execution.getEngine().order().addVariable(orderId, fxxk);

    }
}
