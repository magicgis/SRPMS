package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/4/20
 * TIME:21:02
 * Created by guofan on 2015/4/20
 */
public class ToFrontEnd {
    /**
     * 分页
     * @param list 原有列表
     * @param offset 起始行数
     * @param limit 所需要行数
     * @return Map
     */
    public static Map getSubMap(List list, Integer limit, Integer offset) {
        int size = list.size();
        limit = limit==null?size:limit;
        offset = offset==null?0:offset;
        HashMap<String,Object> ans = new HashMap<String, Object>();
        if (limit+offset > size) {
            ans.put("rows", list.subList(offset, size));
            ans.put("total", Integer.toString(size));
        } else {
            offset = offset >size?size: offset;
            ans.put("rows", list.subList(offset, size > (limit+offset) ? (limit+offset) : size));
            ans.put("total", Integer.toString(size));
        }
        return ans;
    }
}
