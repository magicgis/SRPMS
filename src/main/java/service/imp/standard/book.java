package service.imp.standard;

import service.imp.StandardBase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/11/18.
 */
public class book extends StandardBase implements StandardCheckInf {
    final private Map KEY_ROLE = new HashMap() {{
        put("firstCheifActor", "主编或第一主编");
        put("secondCheifActor", "第二及以下编");
        put("viceCheifActor", "副主编");
        put("actor", "参编");
    }};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "著作名称");
        put("dept.id", "所属部门");
        put("pubType", "出版类型");
        put("pubDate", "出版年月");
        put("isbn", "ISBN号");
        put("isAward", "著作获奖");
        put("publisher", "出版社");
        put("sumWord", "著作总字数");
    }};
    public book() throws FileNotFoundException {
    }

    @Override
    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_ELEM_NAME,map);
    }

    @Override
    public Map isValid(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID,DEFAULT_FLAG);
        validInfo.put(MESSAGE,DEFAULT_MSG);
        String time = (String) map.get("pubDate");
        String awardTime = (String) map.get("bulDate");

        if ((!isVaildTime(time)&&(awardTime==null||awardTime.trim().equals("")))
                ||(!isVaildTime(time)&&!isVaildTime(awardTime))){
            validInfo.put(MESSAGE,"该著作出版时间或是获奖时间不在积分周期内。");
            return  validInfo;
        }
        List<Map> actors = getActors(map);
        if (actors.size()==0){
            validInfo.put(MESSAGE,"请填写参与人员信息");
            return validInfo;
        }
        validInfo.put(IS_VALID,true);
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
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID,DEFAULT_FLAG);
        validInfo.put(MESSAGE,DEFAULT_MSG);
        List<Map>actors = getActors(map);
//        List<Map>firstCheifActors = getChiefActors(actors,"firstCheifActor");
//        List<Map>secondCheifActors = getChiefActors(actors,"secondCheifActor");
//        List<Map>viceCheifActors = getChiefActors(actors,"viceCheifActor");
//        List<Map>Actors = getChiefActors(actors,"actor");
        List<Map> resActors = new ArrayList<>();
        for (Map actor:actors){
            actor.put("score", staticActorScore(actor,map));
            resActors.add(actor);
        }
        validInfo.put("actors",resActors);
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,"请确认分数。");
        return validInfo;
    }

    @Override
    public Map confirmCheck(Map map) {
        Map validInfo = new HashMap();
        validInfo.put(IS_VALID,DEFAULT_FLAG);
        validInfo.put(MESSAGE,DEFAULT_MSG);
        List<Map> actors = getActors(map);
        double sumWord = Double.parseDouble((String) map.get("sumWors"));
        for (Map actor : actors){
            double textNumber = Double.parseDouble((String) actor.get("textNumber"));
            sumWord -= textNumber;
        }
        if (sumWord<0){
            validInfo.put(MESSAGE,"个人字数超出著作总字数。");
            return validInfo;
        }
        validInfo.put(IS_VALID,true);
        validInfo.put(MESSAGE,"是否提交？");
        return validInfo;
    }
    private double staticActorScore(Map actor,Map map){
        double award = 0;
        double inchargeText = 0;
        double unInchargeText = 0;
        double sumWord = Double.parseDouble((String) map.get("sumWord"));
        String awardtype = (String) map.get("awardtype");
        String time = (String) map.get("pubDate");
        String awardDate = (String) map.get("bulDate");
        boolean isAward = (boolean) map.get("isAward");
        String pubType = (String) map.get("pubType");
        String role = (String) actor.get("rple");
        double textNumber= Double.parseDouble((String) actor.get("textNumber"));

        if (isAward){
            if (awardtype == null|| awardtype.trim().equals(""))award = 0;
            else if (awardtype.equals("国家优秀教材一等奖")
                    ||awardtype.equals("国家图书奖")
                    ||awardtype.equals("全国优秀图书奖（科技进步奖科技著作）一等奖"))
                award = 0.5;
            else if (awardtype.equals("国家优秀教材二等奖")
                    ||awardtype.equals("全国优秀图书奖（科技进步奖科技著作）二等奖"))
                award = 0.2;
            else if (awardtype.equals("国家优秀教材三等奖")
                    ||awardtype.equals("全国优秀图书奖（科技进步奖科技著作）三等奖"))
                award=0.1;
            else if (awardtype.equals("新世纪全国高等中医优秀教材奖一等奖"))
                award = 0.2;
            else if (awardtype.equals("新世纪全国高等中医优秀教材奖二等奖"))
                award = 0.1;
            else award=0.05;
        }
        int tyoe;
        if (pubType.equals("公开出版著作"))tyoe=10;
        else if (pubType.equals("教育部规划教材"))tyoe =20;
        else if (pubType.equals("行业规划教材"))tyoe =30;
        else if (pubType.equals("协编教材"))tyoe =40;
        else if (pubType.equals("其他教材"))tyoe =50;
        else tyoe=0;
        if (role.equals(KEY_ROLE.get("firstCheifActor")))tyoe+= 1;
        else if (role.equals(KEY_ROLE.get("secondCheifActor"))) tyoe+=2;
        else if (role.equals(KEY_ROLE.get("viceCheifActor"))) tyoe+= 3;
        else if (role.equals(KEY_ROLE.get("actor"))) tyoe+=4;
        switch (tyoe){
            case 11:
                if (textNumber<=20)inchargeText = 5;
                else  inchargeText = 3;
                unInchargeText = 0.1;
                break;
            case 12:
                inchargeText =3;
                unInchargeText = 0.05;
                break;
            case 13:
                inchargeText =3;
                unInchargeText = 0.02;
                break;
            case 14:
                inchargeText =3;
                unInchargeText =0;
                break;
            case 21:
                inchargeText = 8;
                unInchargeText = 0.5;
                break;
            case 22:
                inchargeText =6;
                unInchargeText = 0.3;
                break;
            case 23:
                inchargeText =5;
                unInchargeText = 0.1;
                break;
            case 24:
                inchargeText =5;
                unInchargeText =0;
                break;
            case 31:
                inchargeText = 6;
                unInchargeText = 0.2;
                break;
            case 32:
                inchargeText =5;
                unInchargeText = 0.1;
                break;
            case 33:
                inchargeText =5;
                unInchargeText = 0.05;
                break;
            case 34:
                inchargeText =4;
                unInchargeText =0;
                break;
            case 41:
                inchargeText = 3;
                unInchargeText = 0.1;
                break;
            case 42:
                inchargeText =3;
                unInchargeText = 0.05;
                break;
            case 43:
                inchargeText =3;
                unInchargeText = 0.02;
                break;
            case 44:
                inchargeText =3;
                unInchargeText =0;
                break;
            case 51:
                inchargeText = 2;
                unInchargeText = 0.05;
                break;
            case 52:
                inchargeText =2;
                unInchargeText = 0.02;
                break;
            case 53:
                inchargeText =2;
                unInchargeText = 0.01;
                break;
            case 54:
                inchargeText =2;
                unInchargeText =0;
                break;
            default:
                inchargeText =0;
                unInchargeText =0;
        }
        double res = textNumber*inchargeText + (sumWord-textNumber)*unInchargeText;
        double awardRes = res*award;
        if (!isVaildTime(time)&&isVaildTime(awardDate)){
            return awardRes;
        }
        return res+awardRes;
    }
}