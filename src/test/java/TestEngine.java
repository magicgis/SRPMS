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
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("X-NextTaskActor", "xgfan,actor");
        Order ord = engine.startInstanceById(id, "xgfan", null);
        List<Task> tasks = engine.getTaskByOrder(ord.getId());
        engine.execute(tasks.get(0).getId(), "xgfan", args);
        args.remove("X-NextTaskActor");
        args.put("X-NextTaskActor", "school");
        tasks = engine.getTaskByActor("xgfan");
        engine.execute(tasks.get(0).getId(), "xgfan", args);
        tasks = engine.getTaskByActor("actor");
        engine.execute(tasks.get(0).getId(), "actor", args);
        tasks = engine.getTaskByActor("School");
        args.remove("X-NextTaskActor");
        args.put("X-NextTaskActor", "xgfan");
        args.put("decByCol", false);
        engine.execute(tasks.get(0).getId(), "school", args);
    }

}
