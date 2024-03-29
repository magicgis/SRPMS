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
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "专利名称");
        put("pubDate", "公开时间");
        put("dept.id", "所属部门");
        put("standard.id", "专利级别");
        put("apprDate", "获批时间");
//        put("endfillDate", "终止填写时间");
//        put("patenPubNo", "公开专利号");
        put("patentNo", "专利号");
    }};

    public patent() throws FileNotFoundException {
    }

    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck( PAGE_ELEM_NAME, map);
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
        List<Map>myStaffActors = getMyStaffActors(actors);
        List<Map> myTchFirstActor = getChiefActors(myStaffActors,(String) KEY_ROLE.get("firstActor"));
        List<Map> myTchChiefActor = getChiefActors(myStaffActors,(String) KEY_ROLE.get("chiefActor"));
        if (myTchChiefActor.size() == 0 && myTchFirstActor.size() == 0) {
            validInfo.put(MESSAGE, "需要我校员工是第一专利权人或者知识产权所有人。");
            return validInfo;
        }
        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "该记录有效");
        return validInfo;
    }

    @Override
    public Map isExtrrmumBand(Map map, double min, double max) {
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
    public Map getFinalScore(Map map, double tableScore, double tableScore2) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "分数计算完成");
        validInfo.put("hasSum", true);
        validInfo.put("sum", tableScore);
        return validInfo;
    }

    @Override
    public Map confirmCheck(Map map,double max, double min) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        List<Map> actors = (List<Map>) map.get("actors");
        double sum = Double.parseDouble((String) map.get("score"));
        if (map.get("score") != null) {
            validInfo = superCheck(map,max,min);
            if (!(boolean)validInfo.get(IS_VALID)){
                return validInfo;
            }
            validInfo.put(IS_VALID,DEFAULT_FLAG);

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
