package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/8.
 */
public class patent extends StandardBase implements StandardCheckInf {
    final private Map KEY_ROLE = new HashMap() {{
        put("firstActor", "第一专利权人");
        put("chiefActor", "知识产权所有人");
    }};
    final private String[] PAGE_STRUCT
            = {"name", "dept.value", "patenPubNo", "patenNo", "standard.id",
            "pubDate", "apprDate", "endfillDate"};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "专利名称");
        put("pubDate", "公开时间");
        put("dept.value", "所属部门");
        put("standard.id", "专利级别");
        put("apprDate", "获批时间");
        put("endfillDate", "终止填写时间");
        put("patenPubNo", "公开专利号");
        put("patenNo", "专利号");
    }};

    public patent() throws FileNotFoundException {
    }

    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_STRUCT, PAGE_ELEM_NAME, map);
    }

    @Override
    public Map isValid(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        validInfo.put(MESSAGE, "未处理");
//        参与人已经填写
        List<Map> actors = getActors(map);
        if (actors == null || actors.size() == 0) {
            validInfo.put(MESSAGE, "请填写参与人信息");
            return validInfo;
        }

        Map firstActorMap = getRoleArray((String) KEY_ROLE.get("firstActor"), map);
        Map chiefActorMap = getRoleArray((String) KEY_ROLE.get("chiefActor"), map);
        List<Map> myTchFirstActor = getMyTchActors(firstActorMap);
        List<Map> myTchChiefActor = getMyTchActors(chiefActorMap);
        if (myTchChiefActor.size() == 0 && myTchFirstActor.size() == 0) {
            validInfo.put(MESSAGE, "需要我校员工是第一专利权人或者知识产权所有人。");
            return validInfo;
        }
        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "该记录有效");
        return validInfo;
    }

//    @Override
//    public Map getScoreAndExtremumFromTable(StandardDao standardDao, Map info) {
////        if国际发明专利sum=200;max=5
////        if中国发明专利sum=50;max=3
////        if外观专利或实用新型专利sum=20;max=1
//        return null;
//    }

    @Override
    public Map isExtrrmumBand(Map map, int min, int max) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        List<Map> actors = getAbsoluteAuthors(map);
        if (max < actors.size()) {
            validInfo.put(MESSAGE, "参与人数最大为" + max + "人。");
            return validInfo;
        }
        validInfo.put(MESSAGE, getMsg("1001"));
        validInfo.put(IS_VALID, true);
        return validInfo;
    }

    @Override
    public Map getFinalScore(Map map, int tableScore) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "请分配积分");
        validInfo.put("hasSum", true);
        validInfo.put("sum", tableScore);
        return validInfo;
    }

    @Override
    public Map confirmCheck(Map map, Map score) {
        List<Map> actors = getActors(map);
        double sum = (double) score.get("sum");
        for (Map actor : actors) {
            double temp = Double.parseDouble((String) actor.get("score"));
            sum -= temp;
        }
        if (sum < 0) {
            score.put(IS_VALID, false);
            score.put(MESSAGE, "个人分数分配总和超出总分！");
            return score;
        }
        score.put(IS_VALID, true);
        score.put(MESSAGE, "是否确认？");
        return score;
    }
}
