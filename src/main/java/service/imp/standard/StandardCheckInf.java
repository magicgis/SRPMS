package service.imp.standard;

import java.util.Map;

/**
 * Created by DELL on 2015/7/19.
 */
public interface StandardCheckInf {
    //    public Map paramNullCheck(Map map,String[] pageStruct, Map pageElemName);
//    页面元素内容空检测
    Map paramNullCheck(Map map);

    //    有效性检测，判断填写内容是否符合分数初定条件
    Map isValid(Map map);

    //    查表或是代码中获取原始基础分数以及约束条件
//    public Map getScoreAndExtremumFromTable(StandardDao standardDao, Map info);
    //    二次有效性检查，检查填写信息是否超出约束
    Map isExtrrmumBand(Map map, int min, int max);

    //    根据完整信息确定权重，并且根据文件决定分数是自动分配还是给出总分
    Map getFinalScore(Map map, int tableScore);

    //    确认检测，考察分数分配是否符合规定
    Map confirmCheck(Map map, Map score);
}
