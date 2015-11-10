package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/7.
 */
public class project extends StandardBase implements StandardCheckInf {
    final private Map KEY_ROLE = new HashMap() {{
        put("firstActor", "负责人");
    }};
    final private String[] PAGE_STRUCT
            = {"name", "pubDate", "newspaper.name", "numWord", "newspaper.standard.id", "newspaper.period"};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "论文名称");
        put("pubDate", "发表时间");
        put("newspaper.name", "报刊名称");
        put("newspaper.standard.id", "报刊级别");
        put("newspaper.period", "发表时间");
        put("numWord", "论文字数");
    }};

    public project() throws FileNotFoundException {
    }

    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_STRUCT, PAGE_ELEM_NAME, map);
    }

    //未完成
    @Override
    public Map isValid(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, false);
        validInfo.put(MESSAGE, "未处理");
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
        return null;
    }

    @Override
    public Map getFinalScore(Map map, double tableScore) {
//        我校主持我校负责人
//        非我校主持联合和子课题（自动分配）
//        我校主持非我校负责人（自动分配）
        return null;
    }

    @Override
    public Map confirmCheck(Map map, Map score) {
        return null;
    }
}
