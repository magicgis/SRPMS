package service.imp.standard;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/7/20.
 */
public class newsPaper extends Paper {
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

    public newsPaper() throws FileNotFoundException {
    }

    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_STRUCT, PAGE_ELEM_NAME, map);
    }

//    public Map getScoreAndExtremumFromTable(StandardDao standardDao, Map info) {
//        String id = (String) info.get("newspaper.standard.id");
//        return super.getScoreAndExtremumFromTable(standardDao, info, id);
//    }

    public Map isExtrrmumBand(Map map, int min, int max) {
        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        int numWord = Integer.parseInt((String) map.get("numWord"));
        if (numWord < min) {
            validInfo.put(MESSAGE, "发表文章没有达到字数最低要求！");
            return validInfo;
        }
        validInfo.put(MESSAGE, getMsg("1001"));
        validInfo.put(IS_VALID, true);
        return validInfo;
    }

}
