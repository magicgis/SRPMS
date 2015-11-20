package service.imp;

import org.snaker.engine.entity.Order;
import service.imp.standard.StandardUtil;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2015/6/12.
 */
//@Service
public class StandardBase {
    protected final String DEFAULT_MSG = "未加载";
    protected final boolean DEFAULT_FLAG = false;
    final String SRC_ENCODING = "ISO-8859-1";
    final String DES_ENCODING = "utf-8";
    protected final String[] MY_SCHOOL_NAME = {"湖北中医药大学", "湖北省中医院"};
    protected final String PARTNER_ID = "9999";
    protected final String STUNDENT_ID = "9998";
    Map validInfo = new HashMap();
    protected Properties prop;
    private InputStream in;
    protected final String MESSAGE = "msg";
    protected final String IS_VALID = "valid";
    private StandardUtil tool;

    public StandardBase() throws FileNotFoundException {
        this.tool = new StandardUtil();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        this.prop = new Properties();
//        this.in = Object.class.getResourceAsStream("/alertInfo.properties");
        this.in = new FileInputStream(StandardBase.class.getClassLoader().getResource("alertInfo.properties").getPath());
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map getMaxMap(Order order) {
        Map map = order.getVariableMap();
        Set<String> keySet = map.keySet();
        Pattern pattern = Pattern.compile("WF_\\d+_Submission");
        String maxKey = "";
        int max = -1;
        String tempKey = "";
        int temp = 0;
        for (String key : keySet) {
            Matcher m = pattern.matcher(key);
            if (m.find()) {
                tempKey = m.group();
                String tempStr = tempKey.substring(3, tempKey.length() - 11);
                temp = Integer.parseInt(tempStr);
                if (temp > max) {
                    max = temp;
                    maxKey = tempKey;
                }
            }
        }
        return (Map) map.get(maxKey);
    }
    public Map sysValidTime(){
        return tool.getSysStartAndEndTime();
    }

    //stringConvert方法重载，将str从ISO-8859-1变换为utf-8
    public String stringConvert(String str) {
        return tool.stringConvert(str, SRC_ENCODING, DES_ENCODING);
    }

    //从properties文件中读取相应代码的字符串
    public String getMsg(String code) {
        return stringConvert(prop.getProperty(code));
    }

    //拼接消息字符串
    public String getValidMsg(String param1, String param2, String param3) {
        return getMsg(param1) + getMsg(param2) + getMsg(param3);
    }

    //   检测页面结构返回页面类型
    public String getPageType(Order order, Map map) {
        String type = null;
        Map variableMap = order.getVariableMap();
        if (map == null) return type;
//        Map map = getMaxMap(order);
        if ("paper".equals(variableMap.get("WF_Type")))
            type = (String) map.get("type");
        else
            type = (String) variableMap.get("WF_Type");
        if (type == null || type.trim().equals("")) {
            type = null;
        }
        return type;
    }

    //    需修改proper中页面结构参数，适配type
    public Map paramNullCheck(Map pageElemtName, Map map) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        Set keySet = pageElemtName.keySet();
        Iterator keyIt = keySet.iterator();
        while(keyIt.hasNext()){
            String key = (String) keyIt.next();
            String pageValue = (String) map.get(key);
            if (pageValue == null|| pageValue.trim().equals("")){
                validInfo.put(MESSAGE, "请填写" + pageElemtName.get(key) + "信息，该信息不能为空");
                return validInfo;
            }
        }
//        for (int i = 0; i < pageStructrue.length; i++) {
//            String valueStr = (String) map.get(pageStructrue[i]);
//            if (valueStr == null || valueStr.trim().equals("")) {
//                validInfo.put(MESSAGE, "请填写" + pageElemtName.get(pageStructrue[i]) + "信息，该信息不能为空");
//                return validInfo;
//            }
//        }
        validInfo.put(IS_VALID, true);
        return validInfo;
    }

    //    该方法用于从map中提取参与人员列表统计实际人数（去除多数名单为的重复情况）
    public List<Map> getAbsoluteAuthors(Map map) {
        List<Map> authors = getActors(map);
        if (authors.size() == 0)
            return null;
        List<Map> abAuthors = new ArrayList<>();
        for (Map author : authors) {
            String id = (String) author.get("staff.id");
//            列表中没有人，或是本校学生（id必然重复）校外人员（id必然重复）
            if (abAuthors.size() == 0 || !isMyStaff(author))
                abAuthors.add(author);
            else {
                boolean flag = false;
                for (Map abAuthor : abAuthors) {
                    if (id.equals(abAuthor.get("staff.id"))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) abAuthors.add(author);
            }
        }
        return abAuthors;
    }
//    单位列表
    public  List<Map> getUnits(Map map){return (List<Map>) map.get("units");}
//    我校(及医院)单位排名
    public int getMySchRank(List<Map> units){
        for (Map unit : units){
            String name = (String) unit.get("unit");
            int i = 0;
            while (name!=null && i < MY_SCHOOL_NAME.length && !name.equals(MY_SCHOOL_NAME[i]))
                i++;
            if (name.equals(MY_SCHOOL_NAME[i]))
                return Integer.parseInt((String) unit.get("rank"));
        }
        return 0;
    }

    //参与人员列表
    public List<Map> getActors(Map map) {
        return (List<Map>) map.get("actors");
    }

    //我校教师及学生
    public List<Map> getMyActors(List<Map> actors) {
        List<Map> myActorList = new ArrayList<>();
        for (Map actor : actors) {
            String unit = (String) actor.get("unit");
            String id = (String) actor.get("staff.id");
//            检查角色的署名单位
            if (id.equals(STUNDENT_ID)) myActorList.add(actor);
            else
                for (int i = 0; i < MY_SCHOOL_NAME.length; i++) {
                    if (unit.equals(MY_SCHOOL_NAME[i])) {
                        myActorList.add(actor);
                        break;
                    }
                }
        }
        return myActorList;
    }

    //    主要角色列表
    public List<Map> getChiefActors(List<Map> actors, String chiefRole) {
        List<Map> chiefActors = new ArrayList<>();
        for (Map actor : actors) {
            String role = (String) actor.get("role");
            if (!(role == null) && role.equals(chiefRole)) chiefActors.add(actor);
        }
        return chiefActors;
    }

    //    我校职工
    public List<Map> getMyStaffActors(List<Map> actors) {
        List<Map> myStaffActors = new ArrayList<>();
        for (Map actor : actors) {
            if (isMyStaff(actor))
                myStaffActors.add(actor);
        }
        return myStaffActors;
    }
    //    我校教师及学生

    //    我校教师

    //    我校学生

    //    检查是否是我校职工
    public boolean isMyStaff(Map actor) {
        String unit = (String) actor.get("unit");
        String staffId = (String) actor.get("staff.id");
        boolean flag = false;
        for (int i = 0; i < MY_SCHOOL_NAME.length; i++) {
            if (!staffId.equals(PARTNER_ID)
                    && !staffId.equals(STUNDENT_ID)
                    && unit.equals(MY_SCHOOL_NAME[i])) {
                flag = true;
                break;
            }
        }
        return flag;
    }
//  我校所在的位置
//  获取到账列表
    //StandardId适配
    public String selectId(String type) {
        String typeId = "standard.id";
        if (type.equals("newsPaper")) typeId = "newspaper." + typeId;
        else if (type.equals("magPaper")) typeId = "mag." + typeId;
        else if (type.equals("conferPaper")) typeId = "confer." + typeId;
        return typeId;
    }

    //    排序权重计算
    public double positionWeight(int n, int i) {
//        n代表总数，i代表排位
        double weight = 0;
        if (i > n || i < 0) return weight;
        for (int j = 0; j <= n; j++) {
            weight += j;
        }
        weight = (n - i + 1) / weight;
        return weight;
    }

    public double totalScore(List<Map> actors){
        double sum = 0 ;
        for (Map actor : actors){
            sum += Double.parseDouble((String) actor.get("score"));
        }
        return sum;
    }
    public boolean isSumCheckPass(double sum , List<Map> actors){
        if (totalScore(actors) - sum <= 0) return true;
        return false;
    }
//    确定时间为有效时段
    public  boolean isVaildTime(String testedTime){
        boolean flag = false;
        Date before = tool.stringToDate((String) sysValidTime().get("startTime"));
        Date after = tool.stringToDate((String) sysValidTime().get("endTime"));
        Date date = tool.stringToDate(testedTime);
        if (date.getTime() > after.getTime() || date.getTime() < before.getTime())
            flag = true;
        return flag;
    }
    public Map cheifAcrorScoreCheck(int actorNum,double chScore,double sum){
        Map map = new HashMap();
        map.put("flag",false);
        map.put(MESSAGE,DEFAULT_MSG);
        if (actorNum <= 3 && (chScore / sum) > 0.7) {
            map.put(MESSAGE, "负责人分数不应超过70%.");
            return map;
        }
        if (actorNum <= 6 && (chScore / sum) > 0.6) {
            map.put(MESSAGE, "负责人分数不应超过60%.");
            return map;
        }
        if ((chScore / sum) > 0.5) {
            map.put(MESSAGE, "负责人分数不应超过50%.");
            return map;
        }
        map.put("flag",true);
        map.put(MESSAGE,"文件未限定");
        return map;
    }


}//the end of the class
