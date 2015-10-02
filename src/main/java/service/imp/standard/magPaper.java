package service.imp.standard;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/7/19.
 */
public class magPaper extends Paper {

    final private String[] PAGE_STRUCT
            = {"name", "pubDate", "mag.name", "mag.standard.id", "vol", "iss", "bgPage"};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "论文名称");
        put("pubDate", "发表时间");
        put("mag.name", "期刊名称");
        put("mag.standard.id", "期刊级别");
        put("vol", "卷号");
        put("iss", "期号");
        put("bgPage", "起止页码");

    }};

    public magPaper() throws FileNotFoundException {
    }

    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_STRUCT, PAGE_ELEM_NAME, map);
    }
//    public Map getScoreAndExtremumFromTable(StandardDao standardDao, Map info){
//        String id = (String) info.get("mag.standard.id");
//        return super.getScoreAndExtremumFromTable(standardDao,info,id);
//    }
}
