package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/11/11.
 */
public class achAppraisal extends StandardBase implements StandardCheckInf{
    final private Map KEY_ROLE = new HashMap() {{
        put("chiefActor", "负责人");
    }};

    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "专利名称");
        put("appType", "鉴定类别");
        put("standard.id", "鉴定等级");
        put("certificateNo", "证书编号");
    }};

    public achAppraisal() throws FileNotFoundException {
    }

    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_ELEM_NAME,map);
    }

    @Override
    public Map isValid(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        validInfo.put(MESSAGE, "有效性未处理");
//        参与人已经填写
        List<Map> actors = getActors(map);
        if (actors == null || actors.size() == 0) {
            validInfo.put(MESSAGE, "请填写参与人信息");
            return validInfo;
        }
//        考察参与单位
        List<Map> units = getUnits(map);
        if (units == null || units.size() == 0){
            validInfo.put(MESSAGE, "请填写参与单位信息");
            return validInfo;
        }
        return validInfo;
    }

    @Override
    public Map isExtrrmumBand(Map map, double min, double max) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "约束未处理");
//        List<Map> actors = getActors(map);
        return validInfo;
    }

    @Override
    public Map getFinalScore(Map map, double tableScore, double tableScore2) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        validInfo.put(MESSAGE, "算分未处理");
        double sum = 0;
        List<Map> units = getUnits(map);
        if (getMySchRank(units) == 1){
            sum = tableScore;
        }else {
            sum = tableScore * positionWeight(units.size(),getMySchRank(units));
        }
        validInfo.put("hasSum",true);
        validInfo.put("sum", sum);
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,"分数计算完成");
        return validInfo;
    }

    @Override
    public Map confirmCheck(Map map,double max , double min) {
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
