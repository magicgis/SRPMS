package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/11/20.
 */
public class achAward extends StandardBase implements StandardCheckInf {
    final private Map KEY_ROLE = new HashMap() {{
        put("chiefActor", "负责人");
    }};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "成果名称");
//        put("dept.id", "成果名称");
        put("awdprop", "奖励性质");
        put("date", "获奖时间");
    }};
    public achAward() throws FileNotFoundException {
    }
    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_ELEM_NAME,map);
    }

    @Override
    public Map isValid(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,DEFAULT_MSG);
        return validInfo;
    }

    @Override
    public Map isExtrrmumBand(Map map, double min, double max) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,DEFAULT_MSG);
        return validInfo;
    }

    @Override
    public Map getFinalScore(Map map, double tableScore, double tableScore2) {

        Map validInfo = new HashMap();
        validInfo.put("hasSum",true);
        validInfo.put("actors",tableScore);
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,"请确认分数。");
        return map;
    }

    @Override
    public Map confirmCheck(Map map,double max,double min) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        List<Map> actors = (List<Map>) map.get("actors");
        if (map.get("score") != null) {
            validInfo = superCheck(map,max,min);
            if (!(boolean)validInfo.get(IS_VALID)){
                return validInfo;
            }
            validInfo.put(IS_VALID,DEFAULT_FLAG);
            double sum = Double.parseDouble((String) map.get("score"));
//            文件第三条第2款
            int actorNum = actors.size();
            List<Map> chiefActors = getChiefActors(actors, (String) KEY_ROLE.get("chiefActor"));
            for (Map chiefActor : chiefActors) {
                double chScore = Double.parseDouble((String) chiefActor.get("score"));
                Map info = chiefAcrorScoreCheck(actorNum, chScore, sum);
                if (!(boolean) info.get("flag")) {
                    validInfo.put(MESSAGE, info.get(MESSAGE));
                    return validInfo;
                }
            }
        }
        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "确认提交？");
        return validInfo;
    }
}
