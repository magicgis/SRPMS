package util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * DATE:2015/4/20
 * TIME:21:02
 * Created by guofan on 2015/4/20
 */
public class Trans<T> {
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
        HashMap<String, Object> ans = new HashMap<String, Object>();
        if (limit + offset > size) {
            ans.put("rows", list.subList(offset, size));
            ans.put("total", Integer.toString(size));
        } else {
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
        String original = args;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
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
    public static Object moveOneToAnother(Object obj, Object newObj) throws Exception {
        Class userClass = Class.forName(obj.toString().split("@")[0]);//加载类
        Field[] fields = userClass.getDeclaredFields();//获得对象方法集合
        String fdname;
        Method getter;
        Method setter;
        for (Field field : fields) {// 遍历该数组
            fdname = field.getName();// 得到字段名，
            getter = userClass.getMethod("get" + change(fdname));// 根据字段名找到对应的get方法，null表示无参数
            Object name = getter.invoke(obj);// 调用该字段的get方法
            if (name != null && name instanceof String) {
                setter = userClass.getMethod("set" + change(fdname), String.class);
                setter.invoke(newObj, name.toString());
//                System.out.println(name);
            }
        }
        return newObj;
    }

    /**
     * 将map中的 与obj公有的key（field）部分
     * 赋值给obj
     * 会自动跳过嵌套的map部分
     *
     * @param obj 需要赋值对象
     * @param cls obj的类名
     * @param map map
     * @Object 赋值之后的obj
     */
    public static Object putMapOnObj(Object obj, Class cls, Map<String, Object> map) {
        Method[] methods = cls.getMethods();
        String fdname = null;
        for (Method method : methods) {
            int len = method.getName().length();
            fdname = method.getName().startsWith("set") ? change(method.getName().substring(3,len)) : null;
            if (fdname != null && map.containsKey(fdname)) {
                try {
                    if (!(map.get(fdname) instanceof Map)) {
                        method.invoke(obj, map.get(fdname));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        return obj;
    }

    /**
     * @param src 源字符串
     * @return 字符串，将src的第一个字母转换为小写，src为空时返回null
     */
    public static String change(String src) {
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
            return sb.toString();
        } else {
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
            if (v.startsWith(flag)) {
                subMap.put(v.substring(flag.length() + 1, v.length()), map.get(v));
                map.remove(v);
            }
        }
        /*将最深层次map放入*/
        map.put(flag, subMap);
        /*递归*/
        return nestMap(map);
    }


    /**
     * map转为object
     *
     * @param map       map
     * @param className 类名
     * @return object
     * @throws IOException 不知道什么错误
     */
    public static Object map2Obj(Map map, Class className) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String x = mapper.writeValueAsString(map);
        return mapper.readValue(x, className);
    }

}
