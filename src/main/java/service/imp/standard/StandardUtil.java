package service.imp.standard;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2015/11/4.
 */
public class StandardUtil {
    protected final String MESSAGE = "msg";
    protected final String IS_VALID = "valid";
    protected final String DEFAULT_MSG = "未加载";
    protected final boolean DEFAULT_FLAG = false;
    protected final String[] MY_SCHOOL_NAME = {"湖北中医药大学", "湖北省中医院"};
    protected final String PARTNER_ID = "9999";
    protected final String STUNDENT_ID = "9998";
    public Map getSystemTime(){
        Date date = new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(date);
        Map dateMap = new HashMap();
        String[] dateArray = strDate.split("-");
        dateMap.put("year", Integer.parseInt(dateArray[0]));
        dateMap.put("month", Integer.parseInt(dateArray[1]));
        dateMap.put("day",  Integer.parseInt(dateArray[2]));
        return dateMap;
    }

    public Map getSysStartAndEndTime(){
        Map map = new HashMap();
        int currentYear = 0;
        int year = (int) getSystemTime().get("year");
        int month = (int) getSystemTime().get("month");
        if(month <= 6 && month >= 1) currentYear = year-1;
        else currentYear = year;
        String startTime = currentYear +"-01-01";
        String endTime = currentYear +"-11-30";
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return  map;
    }
    //乱码问题纠正，str需纠正的字符串，form为原是编码，to目标编码集一般为utf-8
    public String stringConvert(String str, String from, String to) {
        try {
            return new String(str.getBytes(from), to);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    //功能调试代码!!
    public void rex() {
        String test = "WF_95_Submission";
        Pattern pattern = Pattern.compile("WF_\\d+_Submission");
        Matcher m = pattern.matcher(test);
//        List<String> result = new ArrayList<String>();
        m.find();
        String res = m.group();
        System.out.println(res.substring(3, res.length() - 11));
    }

    //获取关键角色参与人员列表Map其中包含本校和全部关键角色列表
//    public Map getRoleArray(String cheifRole, Map map) {
//        Map validInfo = new HashMap();
//        validInfo.put(MESSAGE, DEFAULT_MSG);
//        validInfo.put(IS_VALID, DEFAULT_FLAG);
//        List<Map> Authors;
//        List<Map> ChiefAuthors = new ArrayList<>();
//        List<Map> MyChiefAuthors = new ArrayList<>();
//        List<Map> MyTchChiefAuthors = new ArrayList<>();
//        List<Map> MyStuChiefAuthors = new ArrayList<>();
//        Authors = (List<Map>) map.get("actors");
//        if (Authors == null) return validInfo;
//        for (Map author : Authors) {
//            String unit = (String) author.get("unit");
//            String id = (String) author.get("staff.id");
//            String role = (String) author.get("role");
//            if (unit != null && unit.trim().equals("")) {
//                validInfo.put(MESSAGE,"请填写作者所属单位");
//                return validInfo;
//            }
////        主要角色列表生成
//            boolean myFlag = false;
////            检查角色的署名单位
//            for (int i = 0; i < MY_SCHOOL_NAME.length; i++) {
//                if (unit.equals(MY_SCHOOL_NAME[i])) {
//                    myFlag = true;
//                    break;
//                }
//            }
//            if (role.equals(cheifRole)) {
//                ChiefAuthors.add(author);
////                我校教师
//                if ((id != null && !id.equals("")
//                        && !id.equals(STUNDENT_ID)
//                        && !id.equals(PARTNER_ID))
//                        && myFlag
//                        )
//                    MyTchChiefAuthors.add(author);
////                我校学生
//                else if (id.equals("9998")) {
//                    MyStuChiefAuthors.add(author);
//                }
////                我校师生
//                if (myFlag || id.equals("9998"))
//                    MyChiefAuthors.add(author);
//            }
//
//        }
//        validInfo.put("keyAuthors", ChiefAuthors);
//        validInfo.put("myKeyAuthors", MyChiefAuthors);
//        validInfo.put("myTchKeyAuthors", MyTchChiefAuthors);
//        validInfo.put("myStuKeyAuthors", MyStuChiefAuthors);
//        validInfo.put(MESSAGE, "情况未处理");
//        validInfo.put(IS_VALID, true);
//        return validInfo;
//    }
}
