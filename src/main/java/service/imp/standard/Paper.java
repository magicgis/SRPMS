package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DELL on 2015/7/20.
 */
public class Paper extends StandardBase implements StandardCheckInf {
    final private Map KEY_ROLE = new HashMap() {{
        put("firstAuthor", "第一作者");
        put("chiefAuthor", "通讯作者");
    }};

    public Paper() throws FileNotFoundException {
    }

    //    内部方法
    public Map paramNullCheck(Map map,  Map pageElemName) {
        return super.paramNullCheck(pageElemName, map);
    }

//    public Map getScoreAndExtremumFromTable(StandardDao standardDao, Map info, String stdId) {
//        return super.getScoreAndExtremumFromTable(standardDao, info, stdId);
//    }


    @Override
    public Map paramNullCheck(Map map) {
        return null;
    }

    /*
    * 规则检测细则如下
    * 1、 作者列表有效性检验（检验列表是否为空）
    * 2、 发表时间有效检测
    * 3、 文章是否归属我校检测
    *      3.1、没有填写所属单位
    *      3.2、 第一作者及通讯作者非我校职工
    * */
    @Override
    public Map isValid(Map info) {
//        public Map isMagPaperValid(Map info) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        validInfo.put(MESSAGE, DEFAULT_MSG);
        try {
//            初始化
            validInfo.put(MESSAGE, getMsg("1001"));
//           作者列表有效性检验
            List<Map> authors = getActors(info);
            if (authors == null || authors.size() == 0) {
                validInfo.put(MESSAGE, getValidMsg("2001", "actors", "2002"));
                return validInfo;
            }
            String dateStr = (String) info.get("pubDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            Date before = sdf.parse((String) sysValidTime().get("startTime"));
            Date after = sdf.parse((String) sysValidTime().get("endTime"));

            if (date.getTime() > after.getTime() || date.getTime() < before.getTime()) {
                validInfo.put(MESSAGE,"文章录用时间应在"+sysValidTime().get("startTime")+"到"+sysValidTime().get("endTime")+"之间");
                return validInfo;
        }
            List<Map> actors = getActors(info);
            List<Map> myActors = getMyActors(actors);
            List<Map> myFirstAuth = getChiefActors(myActors, (String) KEY_ROLE.get("firstAuthor"));
            List<Map> myStaffActors = getMyStaffActors(actors);
            List<Map> myChiefAuth = getChiefActors(myStaffActors,(String) KEY_ROLE.get("chiefAuthor"));
            for (Map author : authors) {
                String unit = (String) author.get("unit");
                if (unit == null || unit.trim().equals("")) {
                    validInfo.put(MESSAGE, "请填写作者所属单位");
                }
            }
//            第一作者及通讯作者非我校职工
            if (myFirstAuth.size() == 0 && myChiefAuth.size() == 0) {
                validInfo.put("msg", getMsg("2031"));
                return validInfo;
            }
            validInfo.put(IS_VALID, true);
            validInfo.put(MESSAGE, getMsg("1001"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return validInfo;

    }

    @Override
    public Map isExtrrmumBand(Map map, double min, double max) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        List<Map> authors = getAbsoluteAuthors(map);
        if (max < authors.size()) {
            validInfo.put(MESSAGE, getMsg("1022"));
            return validInfo;
        }
        validInfo.put(MESSAGE, getMsg("1001"));
        validInfo.put(IS_VALID, true);
        return validInfo;
    }

    @Override
    public Map getFinalScore(Map map, double tableScore) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        double finalScore = 0.0;
        boolean flag = false;

        List<Map> actors = getActors(map);
        List<Map> myActors = getMyActors(actors);
        List<Map> myStaffActors = getMyStaffActors(actors);

//        Role Actors
        List<Map> myTchFirstAuth = getChiefActors(myStaffActors,(String) KEY_ROLE.get("firstAuthor"));
        List<Map> myFirstAuth = getChiefActors(myActors, (String) KEY_ROLE.get("firstAuthor"));
        List<Map> firstAuth = getChiefActors(actors, (String) KEY_ROLE.get("firstAuthor"));
        List<Map> myTchChiefAuth = getChiefActors(myStaffActors, (String) KEY_ROLE.get("chiefAuthor"));
        List<Map> myChiefAuth =  getChiefActors(myActors, (String) KEY_ROLE.get("chiefAuthor"));
        List<Map> chiefAuth = getChiefActors(actors, (String) KEY_ROLE.get("chiefAuthor"));
        if (myTchFirstAuth.size() != 0 || myTchChiefAuth.size() != 0)
            flag = true;
        double mySchoolChNum = myChiefAuth.size() + myFirstAuth.size();
        double chNum = chiefAuth.size() + firstAuth.size();
        finalScore = tableScore * mySchoolChNum / chNum;
//      文章的第一作者是我校职工而非学生
        if (flag) {
            validInfo.put(IS_VALID, true);
            validInfo.put(MESSAGE, "请分配积分");
            validInfo.put("hasSum", flag);
            validInfo.put("sum", finalScore);
        }
        else {
            validInfo.put("hasSum", flag);
            List<Map> authors = getActors(map);
            List<Map> abAuthors = getAbsoluteAuthors(map);
            List<Map> resAuthors = new ArrayList<>();
            if (abAuthors.size() == 0) return validInfo;
            int n = abAuthors.size();
            for (int i = 1; i <= n; i++) {
                for (Map author : authors) {
                    int rank = Integer.parseInt((String) author.get("rank"));
//                    int rank = (int) author.get("rank");
                    if (rank == i) {
                        double weight = positionWeight(n, rank);
                        if (isMyStaff(author))
                            author.put("score", finalScore * weight);
                        else author.put("score", 0);
                        resAuthors.add(author);
                    }
                }
            }
            validInfo.put("actors", resAuthors);
            validInfo.put(IS_VALID, true);
            validInfo.put(MESSAGE, "根据文件规定第一作者为我校学生分数根据相关条款自动分配。");
        }
        return validInfo;
    }

    @Override
    public Map confirmCheck(Map map, Map validInfo) {
//        double finalScore = (double) validInfo.get("sum");
        Map tempFirstAuthor, tempChiefAuthor;
        double tempMarks;
        List<Map> actors = getActors(map);
        List<Map> myFirstAuth = getChiefActors(actors,(String) KEY_ROLE.get("firstAuthor"));
        List<Map> myTchChiefAuth = getChiefActors(actors,(String) KEY_ROLE.get("chiefAuthor"));
//       求和检测
        if ((boolean) validInfo.get("hasSum")) {
            validInfo.put(IS_VALID, false);
            List<Map> authors = getActors(map);
//            Double sum = Double.parseDouble((String) validInfo.get("sum"));
            double sum = (double) validInfo.get("sum");
            if (super.totalScore(actors) - sum < 0) {
                validInfo.put(MESSAGE, "个人分数分配总和超出总分！");
                return validInfo;
            }


//        获取通讯作者代表分数（通讯作者重要性高于一作，所以分数参照通讯作者）
            if (myTchChiefAuth.size() != 0) {
                tempChiefAuthor = myTchChiefAuth.get(0);
                tempMarks = Double.parseDouble((String) tempChiefAuthor.get("score"));
            }
            //       获取第一作者代表分数
            else if (myFirstAuth.size() != 0) {
                tempFirstAuthor = myFirstAuth.get(0);
                tempMarks = Double.parseDouble((String) tempFirstAuthor.get("score"));
            }
            else {
                validInfo.put(MESSAGE, "第一作者或是通讯作者必须为我校人员，且分配分数");
                return validInfo;
            }
//        检测第一作者分数必须相同
            if (myFirstAuth.size() != 0)
                for (Map firstAuthor : myFirstAuth) {
//                    if ((int) firstAuthor.get("score") != tempMarks) {
                    if (Double.parseDouble((String) firstAuthor.get("score")) != tempMarks) {
                        validInfo.put(MESSAGE, getMsg("1024"));
                        return validInfo;
                    }
                }
//        检测通讯作者分数必须和一作相同
            if (myTchChiefAuth.size() != 0)
                for (Map chiefAuthor : myTchChiefAuth) {
//                    if ((int) chiefAuthor.get("score") != tempMarks) {
                    if (Double.parseDouble((String) chiefAuthor.get("score")) != tempMarks) {
                        validInfo.put(MESSAGE, getMsg("1024"));
                        return validInfo;
                    }
                }
//            其他作者分数必须小于一作
            for (Map author : authors) {
//                if (tempMarks < (double) author.get("score")) {
                if (tempMarks < Double.parseDouble((String) author.get("score"))) {
                    validInfo.put(MESSAGE, getMsg("1025"));
                    return validInfo;
                }
            }
        }
//        List<Map> authors = getActors(map);

        validInfo.put(IS_VALID, true);
        validInfo.put(MESSAGE, "是否确认？");
        return validInfo;
    }
}
