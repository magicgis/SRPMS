import engine.Engine;
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

    @org.junit.Test
    public void testEasy() {
        /*部署*/
        String id = engine.initFlows();

        /*启动流程*/

//        args.put("WF-NextTaskActor", "xgfan");
        Order ord = engine.startInstanceById(id, "xgfan", null);

        HashMap<String, Object> args = new HashMap<String, Object>();
        List<Task> tasks = engine.getTaskByOrder(ord.getId());
        args.put("WF-NextTaskActor", "xgfan,lishi");
        args.put("申请人", "xgfan");
        args.put("要求", "无");
        engine.execute(tasks.get(0).getId(), "xgfan", args);



        args = new HashMap<String, Object>();
        args.put("批复","同意！");
        args.put("WF-NextTaskActor", "col");
        tasks = engine.getTaskByActor("xgfan");
        engine.execute(tasks.get(0).getId(), "xgfan", args);


        args = new HashMap<String, Object>();
        args.put("批复","我也同意！");
        args.put("WF-NextTaskActor", "col");
        tasks = engine.getTaskByActor("lishi");
        engine.execute(tasks.get(0).getId(), "lishi", args);

        args = new HashMap<String, Object>();
        args.put("DecByCol",false);
        args.put("WF-NextTaskActor", "xgfan");
        tasks = engine.getTaskByActor("col");
        engine.execute(tasks.get(0).getId(), "col", args);

        args = new HashMap<String, Object>();
        tasks = engine.getTaskByOrder(ord.getId());
        args.put("WF-NextTaskActor", "xgfan,lishi");
        args.put("二次申请人", "xgfan");
        args.put("二次要求", "无");
        engine.execute(tasks.get(0).getId(), "xgfan", args);

//        args.put("WF-NextTaskActor", "xgfan");
//        args.put("decByCol", false);
//        engine.execute(tasks.get(0).getId(), "school", args);
        for(Task u :engine.getTaskByOrder(ord.getId())){
            System.out.println(u.getVariableMap());
        }
//        System.out.println(engine.getTaskByOrder(ord.getId()).get(0).getVariableMap());
    }

}
