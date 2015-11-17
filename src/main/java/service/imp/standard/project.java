package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/7.
 */
public class project extends StandardBase implements StandardCheckInf {
    final private Map KEY_ROLE = new HashMap() {{
        put("cheifActor", "负责人");
    }};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "项目名称");
        put("attr", "项目属性");
        put("apprDate", "立项时间");
        put("projtype", "项目类别");
        put("projrank", "项目等级");
    }};

    public project() throws FileNotFoundException {
    }

    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_ELEM_NAME, map);
    }

    //未完成
    @Override
    public Map isValid(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        validInfo.put(MESSAGE, "有效性未处理");
        List<Map> actors = getActors(map);
//        人员列表不能为空
        if (actors == null || actors.size() == 0) {
            validInfo.put(MESSAGE, "请填写参与人信息");
            return validInfo;
        }
        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "信息有效");
        return validInfo;
    }
//未完成
//    @Override
//    public Map getScoreAndExtremumFromTable(StandardDao standardDao, Map map) {
////        明确项目id的名称
//        String id = (String) map.get("newspaper.standard.id");
////        if立项（立项时间正确&&立项判定为true）
////          通过id获得立项分数
////        if经费到账（未结题&&当年有记录&&已立项）
////            到账-外拨-学校配套结果位于哪个评分区间
////        if结题结题分数（当年已结题）
////                通过id+1获得结题分数
////                重审经费历史计算经费到账积分
//        return super.getScoreAndExtremumFromTable(standardDao, map, id);
//    }

    @Override
    public Map isExtrrmumBand(Map map, double min, double max) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        validInfo.put(MESSAGE, "约束未处理");
        validInfo.put(IS_VALID, true);
        return null;
    }

    @Override
    public Map getFinalScore(Map map, double tableScore) {
        int caseSlct = 0;
        List<Map> actors = new ArrayList<>();
        List<Map> myActors = new ArrayList<>();
        List<Map> cheifActors = new ArrayList<>();
        List<Map> units = new ArrayList<>();
        units = getUnits(map);
        actors = getActors(map);
        cheifActors = getChiefActors(actors, (String) KEY_ROLE.get("cheifActor"));
       myActors = getMyStaffActors(cheifActors);
        if (map.get("attr").equals("联合项目") || map.get("attr").equals("子课题"))
            if (myActors.size() != 0) caseSlct =1;//我校负责联合
            else caseSlct =2;//非我校负责联合
        else
            if (myActors.size()!=0)

            caseSlct = 3;//我校主持且负责
            else
            caseSlct = 4;//我校主持但不负责


////        我校主持我校负责人
//        if(myActors.size()!=0 && !map.get("attr").equals("联合项目")) caseSlct = 1;
////        非我校主持联合和子课题（自动分配）
//        else if (map.get("attr").equals("联合项目")) caseSlct=2;
////        我校主持非我校负责人（自动分配）
        return null;
    }

    @Override
    public Map confirmCheck(Map map, Map score) {
        return null;
    }
}
