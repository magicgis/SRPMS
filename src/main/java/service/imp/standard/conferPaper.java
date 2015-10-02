package service.imp.standard;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/7/20.
 */
public class conferPaper extends Paper {
    final private String[] PAGE_STRUCT
            = {"name", "pubDate", "confer.name", "confer.standard.id", "confer.time", "confer.addr"};
    final private Map PAGE_ELEM_NAME = new HashMap() {{
        put("name", "论文名称");
        put("pubDate", "发表时间");
        put("confer.name", "会议名称");
        put("confer.standard.id", "会议级别");
        put("confer.time", "会议时间");
        put("confer.addr", "会议地址");
    }};

    public conferPaper() throws FileNotFoundException {
    }

    public Map paramNullCheck(Map map) {
        return super.paramNullCheck(PAGE_STRUCT, PAGE_ELEM_NAME, map);
    }
//    public Map getScoreAndExtremumFromTable(StandardDao standardDao, Map info){
//        String id = (String) info.get("confer.standard.id");
//        return super.getScoreAndExtremumFromTable(standardDao,info,id);
//    }
}
