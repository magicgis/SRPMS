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
    public Map confirmCheck(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID,DEFAULT_FLAG);
        validInfo.put(MESSAGE,DEFAULT_MSG);
        double sum = Double.parseDouble((String) map.get("score"));
        List<Map> actors = getActors(map);
        if (SumCheckPass(sum,actors)<0) {
            validInfo.put(MESSAGE, "个人分数分配总和超出总分！");
            return validInfo;
        }
        if (SumCheckPass(sum,actors)<0.01&&SumCheckPass(sum,actors)>=0) {
            validInfo.put(MESSAGE, "还有"+SumCheckPass(sum,actors)+"！");
            return validInfo;
        }
        int count = 0;
        List<Map> chiefActors = getChiefActors(actors, (String) KEY_ROLE.get("chiefActor"));
        for (Map actor : actors) {
            if (Double.parseDouble((String) actor.get("score")) != 0) count++;
        }
        for (Map actor:chiefActors){
            double chScore = Double.parseDouble((String) actor.get("score"));
            Map info = chiefAcrorScoreCheck(count,chScore,sum);
            if (!(boolean)info.get("flag")){
                validInfo.put(MESSAGE,info.get(MESSAGE));
                return validInfo;
            }
        }
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,"请分配分数。");
        return validInfo;
    }
}
