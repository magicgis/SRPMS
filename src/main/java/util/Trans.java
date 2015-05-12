package util;

import entity.Staff;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static String MD5(String args) throws Exception {
        String original = args;
        MessageDigest md = MessageDigest.getInstance("MD5");
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
        String fdname = null;
        Method getter = null;
        Method setter = null;
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
     * @param src 源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
     */
    public static String change(String src) {
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }

    public static Object map2Obj(Map map, Class className) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String x = mapper.writeValueAsString(map);
        return mapper.readValue(x, className);
    }

}
