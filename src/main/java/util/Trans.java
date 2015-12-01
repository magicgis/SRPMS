package util;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * DATE:2015/4/20
 * TIME:21:02
 * Created by guofan on 2015/4/20
 */
public class Trans {
    /**
     * 分页
     *
     * @param list   原有列表
     * @param offset 起始行数
     * @param limit  所需要行数
     * @return Map
     */
    public static Map getSubMap(List list, Integer limit, Integer offset) {
        int size = list.size();
        limit = limit == null ? size : limit;
        offset = offset == null ? 0 : offset;
        HashMap<String, Object> ans = new HashMap<>();
        if (limit + offset > size) {
            ans.put("rows", list.subList(offset, size));
            ans.put("total", Integer.toString(size));
        }
        else {
            offset = offset > size ? size : offset;
            ans.put("rows", list.subList(offset, size > (limit + offset) ? (limit + offset) : size));
            ans.put("total", Integer.toString(size));
        }
        return ans;
    }

    /**
     * MD5加密
     *
     * @param args
     * @return
     * @throws Exception
     */
    public static String MD5(String args) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        md.update(args.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    /**
     * 传入对象
     *
     * @param obj 前提obj存在set get方法
     * @return 返回对象存在的属性值
     * @throws Exception
     */
    public static Object moveOneToAnother(Class userClass, Object obj, Object newObj) {
        Field[] fields = userClass.getDeclaredFields();//获得对象方法集合
        String fdname;
        Method getter;
        Method setter;
        try {
            for (Field field : fields) {// 遍历该数组
                fdname = field.getName();// 得到字段名，
                getter = userClass.getMethod("get" + changeMethodName(fdname));// 根据字段名找到对应的get方法，null表示无参数
                Object name = getter.invoke(obj);// 调用该字段的get方法
                if (name != null) {
                    setter = userClass.getMethod("set" + changeMethodName(fdname), name.getClass());
                    setter.invoke(newObj, name);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return newObj;
    }

    /**
     * 将map中的 与obj公有的key（field）部分
     * 赋值给obj
     * 会自动跳过嵌套的map部分
     *
     * @param obj 需要赋值对象
     * @param map map
     * @Object 赋值之后的obj
     */
    public static Boolean putMapOnObj(Object obj, Map<String, Object> map) {
        Class cls = obj.getClass();
        map = nestMap(map);
        /*获取所有Function*/
        Method[] methods = cls.getMethods();
        String fdname;
        for (Method method : methods) {
            int len = method.getName().length();
            fdname = method.getName().startsWith("set") ? changeMethodName(method.getName().substring(3, len)) : null;
            if (fdname != null && map.containsKey(fdname)) {
                try {
                    if (!(map.get(fdname) instanceof Map)) {
                        Class type = method.getParameterTypes()[0];
                        if (String.class.equals(type)) {
                            method.invoke(obj, (String) map.get(fdname));
                        }
                        else if (BigDecimal.class.equals(type)) {
                            method.invoke(obj, BigDecimal.valueOf(Double.valueOf((String) map.get(fdname))));
                        }
                        else if (Integer.class.equals(type)) {
                            method.invoke(obj, Integer.valueOf((String) map.get(fdname)));
                        }
                        else if (Double.class.equals(type)){
                            method.invoke(obj, Double.valueOf((String) map.get(fdname)));
                        }
                        else  {
                            method.invoke(obj, map.get(fdname));
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return true;
    }

    /**
     * @param src 源字符串
     * @return 字符串，将src的第一个字母转换为小写，src为空时返回null
     */
    public static String changeMethodName(String src) {
        if (src != null) {
            StringBuilder sb = new StringBuilder(src);
            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
            return sb.toString();
        }
        else {
            return null;
        }
    }

    /**
     * @param src 源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
     */
    public static String changeTypeName(String src) {
        if (src != null) {
            StringBuilder sb = new StringBuilder(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        }
        else {
            return null;
        }
    }

    /**
     * 将平铺的map转换为嵌套的map
     *
     * @param map 平铺的map
     * @return 嵌套的map
     */
    public static Map<String, Object> nestMap(Map<String, Object> map) {
        Set<String> keys = new HashSet<>();
        keys.addAll(map.keySet());
        int size = 1;
        String flag = null;
        /*第一次遍历，取出最深层次的key*/
        for (String s : keys) {
            /*两次转义*/
            String[] temp = s.split("\\.");
            int dotNum = temp.length;
            if (size < dotNum) {
                size = dotNum;
                flag = s.substring(0, s.lastIndexOf("."));
            }
        }
        /*递归跳出*/
        if (flag == null) {
            return map;
        }
        Map<String, Object> subMap = new HashMap<>();
        /*第二次遍历，将最深层次变为map*/
        for (String v : keys) {
            if (v.startsWith(flag + ".")) {
                subMap.put(v.substring(flag.length() + 1, v.length()), map.get(v));
                map.remove(v);
            }
        }
        /*将最深层次map放入*/
        map.put(flag, subMap);
        /*递归*/
        return nestMap(map);
    }

    public static Map<String, Object> str2Map(String str) {
        /*以下两个均为正则表达式*/
        String x = str.replaceAll("\\{\"", "{,\"").replaceAll("\\}", ",}");
        /*分割用的对,和:进行分割*/
        String[] temp = x.split("(?<=\"):(?=(\"|\\{))|(?<=(\"|\\}|\\{)),(?=(\"|\\}))");
        List list = new ArrayList();
        int i = 0;
        List<Integer> l = new ArrayList<>();
        for (String s : temp) {
            list.add(s.replace("\"", ""));
            if (s.equals("{")) {
            /*添加下标*/
                l.add(list.size() - 1);
            }
            else if (s.equals("}")) {
            /*找出最后一个{的下标*/
                int lastNum = l.get(l.size() - 1);
            /*切割*/
                List map = new ArrayList();
                map.addAll(list.subList(lastNum, list.size()));
            /*删除到上一个{之前*/
                while (list.size() > lastNum) {
                    list.remove(lastNum);
                }
                list.add(list2Map(map));
                l.remove(l.size() - 1);
            }
        }
        return (Map<String, Object>) list.get(0);
    }

    public static Map<String, Object> list2Map(List list) {
        Map<String, Object> ans = new HashMap<>();
        for (int i = 1; i < list.size() - 1; i += 2) {
            ans.put(list.get(i).toString(), list.get(i + 1));
        }
        return ans;
    }

    static public HashMap argMap(String arg) {
        if (arg == null) {
            return new HashMap();
        }
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<HashMap<String, Object>>() {
        };
        try {
            return mapper.readValue(arg, typeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public String getFileData(Object fileData) {
        List<Map> files = new ArrayList<>();
        try {
            files = (List<Map>) fileData;
        } catch (ClassCastException e) {
            files.add((Map) fileData);
        }
        String atts = null;
        for (Map file : files) {
            Map temp = (Map) file.get(file.keySet().toArray()[0]);
            String fileKey = (String) temp.get("fileKey");
            if (atts == null) {
                atts = fileKey;
            }
            else {
                atts = atts + "," + fileKey;
            }
        }
        return atts;
    }

    static public void moveMap(Map src, Map target, List<String> keys) {
        for (String key : keys) {
            if (src.get(key) != null) {
                target.put(key, src.get(key));
            }
        }
    }

}
