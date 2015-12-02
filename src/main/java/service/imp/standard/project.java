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
        put("chiefActor", "负责人");
    }};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "项目名称");
        put("attr", "项目属性");
        put("apprDate", "立项时间");
//        put("projtype", "项目类别");
        put("standard.id", "项目类别与项目等级");
//        put("projrank", "项目等级");
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
        List<Map> chiefActors = getChiefActors(actors, (String) KEY_ROLE.get("chiefActor"));
//        人员列表不能为空
        if (actors == null || actors.size() == 0) {
            validInfo.put(MESSAGE, "请填写参与人信息");
            return validInfo;
        }
        if (chiefActors.size() > 1) {
            validInfo.put(MESSAGE, "项目中只能有一个负责人。");
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
        return validInfo;
    }

    @Override
    public Map getFinalScore(Map map, double tableScore, double tableScore2) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        int caseSlct = 0;
        List<Map> actors = new ArrayList<>();
        List<Map> myChiefActors = new ArrayList<>();
        List<Map> chiefActors = new ArrayList<>();
        List<Map> units = new ArrayList<>();
        units = getUnits(map);
        actors = getActors(map);
        chiefActors = getChiefActors(actors, (String) KEY_ROLE.get("chiefActor"));
        myChiefActors = getMyStaffActors(chiefActors);
        int isAppr = Integer.parseInt((String) map.get("isAppr"));
        double calScore = 0;
        double startScore = 0;
        double moneyScore = 0;
        double endScore = 0;
        double currentMoney = 0;
        double historyMnoey = 0;
        double leftMoneyScore = 0;//补差
        boolean isMoneyProject = false;
//        是否立项
        if (isAppr == 1) {
//            今年到账金额以及历史到账金额
            List<Map> funds = (List<Map>) map.get("fund");
            if (funds != null && funds.size() != 0) {
                for (Map fund : funds) {
                    double res = 0;
                    res = Double.parseDouble((String) fund.get("mny"))
                            - Double.parseDouble((String) fund.get("outMny"));
                    historyMnoey += res;
                    if (isVaildTime((String) fund.get("time")))
                        currentMoney += res;
                }
            }
//            String s = map.get()
            if (map.get("projrank").equals("横向")
                    || map.get("projrank").equals("社会服务项目"))
                isMoneyProject = true;
//            计算到账积分
            moneyScore = getMoneyWeight(map, currentMoney, false) * currentMoney;
//            计算立项积分
            if (!isMoneyProject
                    && isVaildTime((String) map.get("apprDate"))) {
                startScore = tableScore;
            }
            if (isMoneyProject) {
                //  && isVaildTime((String) map.get("apprDate"))){
                startScore = tableScore * currentMoney;
            }
//            计算结题积分
            if (!isMoneyProject
                    && map.get("realDate") != null
                    && isVaildTime((String) map.get("realDate"))) {
                endScore = tableScore2;
                leftMoneyScore = historyMnoey
                        * (getMoneyWeight(map, historyMnoey, true) - getMoneyWeight(map, historyMnoey, false));
            }
            if (isMoneyProject
                    && map.get("realDate") != null
                    && isVaildTime((String) map.get("realDate"))) {
                endScore = historyMnoey * tableScore2;
                leftMoneyScore = historyMnoey
                        * (getMoneyWeight(map, historyMnoey, true) - getMoneyWeight(map, historyMnoey, false));
            }
            moneyScore += leftMoneyScore;
//            未统计到账分数
            calScore = startScore + endScore;
//        是否是独立项目
            if (map.get("attr").equals("独立项目")) caseSlct += 110;
            else calScore = calScore / (units.size() + 1);
//        是否是负责人
            if (myChiefActors.size() != 0) caseSlct += 1;
//        是否是主持单位
            if (units != null && units.size() != 0 && getMySchRank(units) == 1) caseSlct += 10;
        }
//        未立项
        else {
            calScore = tableScore;
            if (!(map.get("attr").equals("独立项目")))
                calScore /= 5;
            caseSlct = 111;
        }
        System.out.println("--------" + calScore);
        System.out.println("--------" + caseSlct);
        boolean flag = false;
        switch (caseSlct) {
            case 10:
            case 110:
                List<Map> resActors = new ArrayList<>();
                validInfo.put("hasSum", flag);
                for (Map actor : actors) {
                    int i = Integer.parseInt((String) actor.get("rank"));
                    if (isMyStaff(actor))
                        if (!isMoneyProject)
                            actor.put("score", calScore * positionWeight(actors.size(), i) + moneyScore);
                        else
                            actor.put("score", calScore + moneyScore);
                    else actor.put("score", 0);
                    resActors.add(actor);
                }
                validInfo.put(MESSAGE, "按照文件规定分数为自动分配");
                validInfo.put(IS_VALID, true);
                validInfo.put("actors", resActors);
                return validInfo;
            case 11:
            case 111:
                validInfo.put(IS_VALID, true);
                validInfo.put(MESSAGE, "分数需要分配");
                validInfo.put("hasSum", true);
                validInfo.put("sum", Math.rint(calScore + moneyScore));
                return validInfo;
            default:
                validInfo.put(MESSAGE, "文件未对此情况进行规定。");
                validInfo.put(IS_VALID, false);
                return validInfo;
        }
//        return null;
    }

    @Override
    public Map confirmCheck(Map map, double max, double min) {
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

    private double getMoneyWeight(Map map, double money, boolean finalCal) {
//          分/万元
        String type = (String) map.get("projtype");
        if (type.equals("自然科学")) {
            if (!finalCal || money <= 60) return 15;
            else return 18;
        } else if (type.equals("社会服务项目")) {
            if (!finalCal || money <= 30) return 8;
            else return 10;
        } else {
            if (!finalCal || money <= 15) return 20;
            else return 25;
        }

    }
}
