package engine.filter;

import entity.Mag;
import org.snaker.engine.SnakerInterceptor;
import org.snaker.engine.core.Execution;
import service.MagService;
import service.StandardInfoService;

import java.util.Map;

/**
 * Created by guofan on 2015/6/28.
 */
public class NewMag implements SnakerInterceptor {

    @Override
    public void intercept(Execution execution) {
        Map<String, Object> args = execution.getOrder().getVariableMap();
        Mag mag = new Mag();
        StandardInfoService standardInfoService = (StandardInfoService) util.StaticFactory.getBean(StandardInfoService.class);
        MagService magService = (MagService) util.StaticFactory.getBean(MagService.class);
        //todo wtf
        mag.setStandard(standardInfoService.getById("1013"));
        Map<String, Object> info = (Map) args.get("WF_0_Submission");
        util.Trans.putMapOnObj(mag, info);
        magService.save(mag);
    }

}
