import dao.AccountDao;
import engine.Engine;
import engine.OrderActorDao;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.*;

/**
 * DATE:2015/1/23
 * TIME:19:44
 * Created by guofan on 2015/1/23
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestEngine extends AbstractJUnit4SpringContextTests {

    @Autowired
    Engine engine;
    @Autowired
    OrderActorDao orderActorDao;
//    @Autowired
@Autowired
private SnakerEngine snakerEngine;



    @org.junit.Test
    public void testEasy() {
        /*部署*/
        String id = engine.initFlows();

        /*启动流程*/

//        args.put("WF-NextTaskActor", "xgfan");
//        String id = engine.getAllProcessId().get(0);
        Order ord = engine.startInstanceById(id, "xgfan", null);

        HashMap<String, Object> args = new HashMap<String, Object>();



        args = new HashMap<String, Object>();
        args.put("WF-NextTaskActor", "xgfan,lishi");
        args.put("申请人", "xgfan");
        args.put("要求", "无");
        args.put("WF-Type","论文");
        args.put("WF-Col","信息");
        args.put("IsComplete",true);
        List<Task> tasks = engine.getTaskByOrder(ord.getId());
        engine.execute(tasks.get(0).getId(), "xgfan", args);
        String i = tasks.get(0).getId();
//        snakerEngine.executeTask(tasks.get(0).getId(),"xgfan",args);



        args = new HashMap<String, Object>();
        args.put("批复","同意！");
        args.put("WF-NextTaskActor", "col");
        tasks = engine.getTaskByActor("xgfan");
        engine.execute(tasks.get(0).getId(), "xgfan", args);

        Task newTask = snakerEngine.task().withdrawTask(i,"xgfan");
        args = new HashMap<String, Object>();
        args.put("WF-NextTaskActor", "xgfan");
        args.put("申请人", "xgfan");
        args.put("要求", "无");
        args.put("WF-Type","论文");
        args.put("WF-Col","信息工程");
        args.put("IsComplete",true);
        engine.execute(newTask.getId(), "xgfan", args);

//        args = new HashMap<String, Object>();
//        args.put("批复","我也同意！");
//        args.put("WF-NextTaskActor", "col");
//        tasks = engine.getTaskByActor("lishi");
//        engine.execute(tasks.get(0).getId(), "lishi", args);
//
//        args = new HashMap<String, Object>();
//        args.put("DecByCol",false);
//        args.put("WF-NextTaskActor", "xgfan");
//        tasks = engine.getTaskByActor("col");
//        engine.execute(tasks.get(0).getId(), "col", args);

//        args = new HashMap<String, Object>();
//        args.put("WF-NextTaskActor", "xgfan");
//        args.put("申请人", "xgfan");
//        args.put("要求", "无");
//        args.put("IsComplete",false);
//        tasks = engine.getTaskByActor("xgfan");
//        engine.execute(tasks.get(0).getId(), "xgfan", args);


//        args = new HashMap<String, Object>();
//        tasks = engine.getTaskByOrder(ord.getId());
//        args.put("WF-NextTaskActor", "xgfan,lishi");
//        args.put("二次申请人", "xgfan");
//        args.put("二次要求", "无");
//        engine.execute(tasks.get(0).getId(), "xgfan", args);

//        args.put("WF-NextTaskActor", "xgfan");
//        args.put("decByCol", false);
//        engine.execute(tasks.get(0).getId(), "school", args);
//        for(Task u :engine.getTaskByOrder(ord.getId())){
//            System.out.println(u.getVariableMap());
//        }
//        System.out.println(engine.getTaskByOrder(ord.getId()).get(0).getVariableMap());
    }

//    @org.junit.Test
    public void t(){
        Task task = snakerEngine.query().getActiveTasks(new QueryFilter().setOperator("xgfan")).get(0);
//        task.setVariable("wfaklwjegoawjeofa");
//        snakerEngine.task().updateTask(task);
        System.out.println(task.getVariableMap());
    }

//    @org.junit.Test
    public void testDao(){
//        engine.initFlows();
//        System.out.println(engine.getProcessByName("basicProcess_Beta"));
        orderActorDao.save("orderId","actorId",0);
    }


//    @org.junit.Test
    public void testMain(){
        String s = "WF-162350-Submission";
        System.out.println(s.matches("WF-\\d+-\\D+"));
        System.out.println(s.substring(s.indexOf('-') + 1, s.lastIndexOf('-')));
    }

}
