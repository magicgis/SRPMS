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
    public Map getFinalScore(Map map, double tableScore, double tableScore2) {
        double calScore = 0;
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        int caseSlct = 0;
        List<Map> actors = new ArrayList<>();
        List<Map> myCheifActors = new ArrayList<>();
        List<Map> cheifActors = new ArrayList<>();
        List<Map> units = new ArrayList<>();
        units = getUnits(map);
        actors = getActors(map);
        cheifActors = getChiefActors(actors, (String) KEY_ROLE.get("cheifActor"));
        myCheifActors = getMyStaffActors(cheifActors);
//        立项
        if (isVaildTime((String) map.get("apprDate"))) calScore += tableScore;
//        结题
        if ( map.get("realDate")!=null &&isVaildTime((String) map.get("realDate")))
            calScore += tableScore2;
//        到账
        List<Map> funds = new ArrayList<>();


//        是否是独立项目
        if (map.get("attr").equals("独立项目")) caseSlct += 101;
//        是否是负责人
        if (myCheifActors.size() != 0) caseSlct += 10;
//        是否是主持单位
        if (units != null && units.size() != 0 && getMySchRank(units) == 1) caseSlct += 100;
        boolean flag = false;
        switch (caseSlct) {
            case 0://不主持不负责不独立（自动）
            case 10://不主持负责不独立
                calScore = calScore / units.size();
//                break;
            case 100://主持不负责不独立（自动）
            case 101://主持不负责独立
                List<Map> resActors = new ArrayList<>();
                map.put("hasSum", flag);
                for (Map actor : actors) {
                    int i = Integer.parseInt((String) actor.get("rank"));
                    if (isMyStaff(actor))
                        actor.put("score", calScore * positionWeight(actors.size(), i));
                    else actor.put("score", 0);
                    resActors.add(actor);
                }
                break;
            case 11://不主持负责独立（分配）
            case 111://主持负责独立
                break;
            case 110://主持负责不独立（分配/(n+1)）
                break;
            default://001不主持不负责独立
                validInfo.put(MESSAGE, "文件未对此情况进行规定。");
        }

        return null;
    }

    @Override
    public Map confirmCheck(Map map, Map score) {
        return null;
    }
}
