package service.imp;

import org.snaker.engine.entity.Order;

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

    public StandardBase() throws FileNotFoundException {
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

    //乱码问题纠正，str需纠正的字符串，form为原是编码，to目标编码集一般为utf-8
    public String stringConvert(String str, String from, String to) {
        try {
            return new String(str.getBytes(from), to);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    排序权重计算
    public double positionWeight(int n, int i) {
        double weight = 0;
        if (i > n || i < 0) return weight;
        for (int j = 0; j <= n; j++) {
            weight += j;
        }
        weight = (n - i + 1) / weight;
        return weight;
    }

    //stringConvert方法重载，将str从ISO-8859-1变换为utf-8
    public String stringConvert(String str) {
        return stringConvert(str, SRC_ENCODING, DES_ENCODING);
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
    public Map paramNullCheck(String[] pageStructrue, Map pageElemtName, Map map) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        for (int i = 0; i < pageStructrue.length; i++) {
            String valueStr = (String) map.get(pageStructrue[i]);
            if (valueStr == null || valueStr.trim().equals("")) {
                validInfo.put("msg", "请填写" + pageElemtName.get(pageStructrue[i]) + "信息，该信息不能为空");
                return validInfo;
            }
        }
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
            if (abAuthors.size() == 0
                    || id.equals("9998")
                    || id.equals("9999"))
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

    //返回参与人员列表
    public List<Map> getActors(Map map) {
        return (List<Map>) map.get("actors");
    }

    //    我校教师及学生
    public List<Map> getMyActors(Map map) {
        List nullList = new ArrayList();
        if (!(boolean) map.get(IS_VALID))
            return nullList;
        else
            return (List<Map>) map.get("myKeyAuthors");
    }

    //    我校教师
    public List<Map> getMyTchActors(Map map) {
        List nullList = new ArrayList();
        if (!(boolean) map.get(IS_VALID))
            return nullList;
        else
            return (List<Map>) map.get("myTchKeyAuthors");
    }

    //    我校学生
    public List<Map> getMyStuActors(Map map) {
        List nullList = new ArrayList();
        if (!(boolean) map.get(IS_VALID))
            return nullList;
        else
            return (List<Map>) map.get("myStuKeyAuthors");
    }

    public List<Map> getKeyActors(Map map) {
        List nullList = new ArrayList();
        if (!(boolean) map.get(IS_VALID))
            return nullList;
        else
            return (List<Map>) map.get("keyAuthors");
    }

    //    检查是否是我校职工
    public boolean isMyStaff(Map actor) {
        String unit = (String) actor.get("unit");
        String staffId = (String) actor.get("staff.id");
        boolean flag = false;
        for (int i = 0; i < MY_SCHOOL_NAME.length; i++) {
            if (unit.equals(MY_SCHOOL_NAME[i])
                    && !staffId.equals("9999")
                    & !staffId.equals("9998")) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    //获取关键角色参与人员列表Map其中包含本校和全部关键角色列表
    public Map getRoleArray(String cheifRole, Map map) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        List<Map> Authors;
        List<Map> ChiefAuthors = new ArrayList<>();
        List<Map> MyChiefAuthors = new ArrayList<>();
        List<Map> MyTchChiefAuthors = new ArrayList<>();
        List<Map> MyStuChiefAuthors = new ArrayList<>();
        Authors = (List<Map>) map.get("actors");
        if (Authors == null) return validInfo;
        for (Map author : Authors) {
            String unit = (String) author.get("unit");
            String id = (String) author.get("staff.id");
            String role = (String) author.get("role");
            if (unit != null && unit.trim().equals("")) {
                validInfo.put(MESSAGE, getMsg("2121"));
                return validInfo;
            }
//        主要角色列表生成
            boolean myFlag = false;
//            检查角色的署名单位
            for (int i = 0; i < MY_SCHOOL_NAME.length; i++) {
                if (unit.equals(MY_SCHOOL_NAME[i])) {
                    myFlag = true;
                    break;
                }
            }
            if (role.equals(cheifRole)) {
                ChiefAuthors.add(author);
//                我校教师
                if ((id != null && !id.equals("")
                        && !id.equals("9998")
                        && !id.equals("9999"))
                        && myFlag
                        )
                    MyTchChiefAuthors.add(author);
//                我校学生
                else if (id.equals("9998")) {
                    MyStuChiefAuthors.add(author);
                }
//                我校师生
                if (myFlag || id.equals("9998"))
                    MyChiefAuthors.add(author);
            }

        }
        validInfo.put("keyAuthors", ChiefAuthors);
        validInfo.put("myKeyAuthors", MyChiefAuthors);
        validInfo.put("myTchKeyAuthors", MyTchChiefAuthors);
        validInfo.put("myStuKeyAuthors", MyStuChiefAuthors);
        validInfo.put(MESSAGE, getMsg("1001"));
        validInfo.put(IS_VALID, true);
        return validInfo;
    }

    public String selectId(String type) {
        String typeId = "standard.id";
        if (type.equals("newsPaper")) typeId = "newspaper." + typeId;
        else if (type.equals("magPaper")) typeId = "mag." + typeId;
        else if (type.equals("conferPaper")) typeId = "confer." + typeId;
        return typeId;
    }
}//the end of the class
