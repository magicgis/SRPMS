import engine.SnakerEngineUtils;
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
    SnakerEngineUtils snakerEngineUtils;

    @org.junit.Test
    public void testEasy() {
        /*部署*/
        String id = snakerEngineUtils.initFlows();

        /*启动流程*/
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("X-NextTaskActor", "xgfan,actor");
        List<Task> tasks = snakerEngineUtils.startAndExecute(id, "xgfan", null);
        snakerEngineUtils.execute(tasks.get(0).getId(), "xgfan", args);
        args.remove("X-NextTaskActor");
        args.put("X-NextTaskActor", "school");
        tasks = snakerEngineUtils.getUserActiveTask("xgfan");
        snakerEngineUtils.execute(tasks.get(0).getId(), "xgfan", args);
        tasks = snakerEngineUtils.getUserActiveTask("actor");
        snakerEngineUtils.execute(tasks.get(0).getId(), "actor", args);
        tasks = snakerEngineUtils.getUserActiveTask("School");
        args.remove("X-NextTaskActor");
        args.put("X-NextTaskActor", "xgfan");
        args.put("decByCol", false);
        snakerEngineUtils.execute(tasks.get(0).getId(),"school",args);
    }

}
