package engine.filter;

import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;

import java.util.HashMap;
import java.util.Map;

/**
 * DATE:2015/4/8
 * TIME:12:19
 * Created by guofan on 2015/4/8
 */
public class Decision implements SnakerInterceptor {
    @Override
    public void intercept(Execution execution) {
        String order = execution.getOrder().getId();
        Map<String, Object> args = new HashMap<>();
        Map<String, Object> dec = execution.getArgs();
        boolean flag = false;
        if (dec.containsKey("DecByCol")) {
            if (dec.get("DecByCol") instanceof String) {
                flag = Boolean.valueOf(dec.get("DecByCol").toString());
            }
            else {
                flag = (Boolean) dec.get("DecByCol");
            }
            if (flag) {
                args.put("Status", "WaitForCollegeSubmit");
            }
            else {
                args.put("Status", "RefuseByCol");
            }
            execution.getEngine().order().addVariable(order, args);
        }
        else if (dec.containsKey("DecByDep")) {
            if (dec.get("DecByDep") instanceof String) {
                flag = Boolean.valueOf(dec.get("DecByDep").toString());
            }
            else {
                flag = (Boolean) dec.get("DecByDep");
            }
            if (flag) {
                //莫名奇妙，不能在这儿处理数据
                // do nothing
            }
            else {
                args.put("Status", "RefuseByDep");
                execution.getEngine().order().addVariable(order, args);
            }
        }

    }
}
