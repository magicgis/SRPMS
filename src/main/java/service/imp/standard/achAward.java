package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.HashMap;
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
        put("dept.id", "成果名称");
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
        return null;
    }

    @Override
    public Map confirmCheck(Map map) {
        return null;
    }
}
