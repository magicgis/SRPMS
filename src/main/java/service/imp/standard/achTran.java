package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/11/19.
 */
public class achTran extends StandardBase implements StandardCheckInf {
    final private Map KEY_ROLE = new HashMap() {{
        put("chiefActor", "负责人");
    }};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "成果名称");
        put("actualMoney", "到账金额");
        put("money", "转让金额");
    }};
    public achTran() throws FileNotFoundException {
    }

    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_ELEM_NAME,map);
    }

    @Override
    public Map isValid(Map map) {
        Map validInfo = new HashMap();
        List<Map>actors = getActors(map);
        validInfo.put(IS_VALID,DEFAULT_FLAG);
        if (actors.size() == 0){
            validInfo.put(MESSAGE,"请填写参与人列表.");
        }
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
//        return null;
    }

    @Override
    public Map getFinalScore(Map map, double tableScore, double tableScore2) {
        double actualMoney = Double.parseDouble((String) map.get("actualMoney"));
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,DEFAULT_MSG);
        validInfo.put("hasSum",true);
        validInfo.put("sum",actualMoney*5);
        return validInfo;
    }

    @Override
    public Map confirmCheck(Map map,double max ,double min) {
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
