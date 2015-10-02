package service.imp;

import dao.StandardDao;
import entity.Standard;
import org.snaker.engine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StandardService;
import service.imp.standard.StandardCheckInf;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2015/6/8.
 */
@Service
public class StandardServiceImp extends StandardBase implements StandardService {

    @Autowired
    private StandardDao standardDao;

    public StandardServiceImp() throws FileNotFoundException {
    }

    //功能调试代码!!
    public void rex() {
        String test = "WF_95_Submission";
        Pattern pattern = Pattern.compile("WF_\\d+_Submission");
        Matcher m = pattern.matcher(test);
//        List<String> result = new ArrayList<String>();
        m.find();
        String res = m.group();
        System.out.println(res.substring(3, res.length() - 11));
    }

    @Override
    public Map scoreCalculation(Order order, Map map) {
        Map calFlow = new HashMap();
        calFlow.put(IS_VALID, DEFAULT_FLAG);
        calFlow.put(MESSAGE, DEFAULT_MSG);
        String type = getPageType(order, map);
        if (type == null) {
            validInfo.put(MESSAGE, getMsg("1023"));
            return validInfo;
        }
        StandardCheckInf standardCheck = null;
        try {
            Class<?> standardCheckClass = Class.forName("service.imp.standard." + type);
            standardCheck = (StandardCheckInf) standardCheckClass.newInstance();
        } catch (ClassNotFoundException e) {
//            validInfo.put(IS_VALID,false);
            validInfo.put(MESSAGE, "PageStandardClassNotFoundException");
            return validInfo;
//            e.printStackTrace();
        } catch (InstantiationException e) {
            validInfo.put(MESSAGE, "PageStandardInstantiationException");
            e.printStackTrace();
            return validInfo;
        } catch (IllegalAccessException e) {
            validInfo.put(MESSAGE, "PageStandardIllegalAccessException");
            e.printStackTrace();
            return validInfo;
        }
//        检测字段空值
//        calFlow = paramNullCheck(type, map);
        calFlow = standardCheck.paramNullCheck(map);
        if (!(boolean) calFlow.get(IS_VALID)) return calFlow;
//        有效性规则检测与 填写者合法性
        calFlow = standardCheck.isValid(map);
        if (!(boolean) calFlow.get(IS_VALID)) return calFlow;

//        获取附表分数与极值
        String stIdType = selectId(type);
//        System.out.println(type);
        String stId = (String) map.get(stIdType);
//        System.out.println(stId);
        Standard st = standardDao.getById(stId);
//        calFlow = standardCheck.getScoreAndExtremumFromTable(standardDao, map);
        if (st == null) {
//            System.out.println("+++++++++");
            calFlow.put(MESSAGE, "附表中没有相关规定");
            //        极值约束
            calFlow = standardCheck.isExtrrmumBand(map, 0, 0);
            if (!(boolean) calFlow.get(IS_VALID)) return calFlow;
            calFlow = standardCheck.getFinalScore(map, 0);
            return calFlow;
//            return calFlow;
        }
        else {
            int sum = st.getValue();
            int min = st.getMin() == null ? 0 : st.getMin();
            int max = st.getMax();
//            System.out.println(st.getValue());
            //        极值约束
            calFlow = standardCheck.isExtrrmumBand(map, min, max);
            if (!(boolean) calFlow.get(IS_VALID)) return calFlow;
            calFlow = standardCheck.getFinalScore(map, sum);
            return calFlow;
        }
    }

    @Override
    public Map confirmChecking(Order order) {
        return confirmChecking(order, getMaxMap(order));
    }

    @Override
    public Map confirmChecking(Order order, Map map) {

        Map validInfo = new HashMap();
        validInfo.put(MESSAGE, DEFAULT_MSG);
        validInfo.put(IS_VALID, DEFAULT_FLAG);
        String type = getPageType(order, map);
        if (type == null) {
            validInfo.put(MESSAGE, getMsg("1023"));
            return validInfo;
        }
        StandardCheckInf standardCheck = null;
        try {
            Class<?> standardCheckClass = Class.forName("service.imp.standard." + type);
            standardCheck = (StandardCheckInf) standardCheckClass.newInstance();
        } catch (ClassNotFoundException e) {
//            validInfo.put(IS_VALID,false);
            validInfo.put(MESSAGE, "PageStandardClassNotFoundException");
            return validInfo;
//            e.printStackTrace();
        } catch (InstantiationException e) {
            validInfo.put(MESSAGE, "PageStandardInstantiationException");
            e.printStackTrace();
            return validInfo;
        } catch (IllegalAccessException e) {
            validInfo.put(MESSAGE, "PageStandardIllegalAccessException");
            e.printStackTrace();
            return validInfo;
        }
        validInfo = scoreCalculation(order, map);
        if (!(boolean) validInfo.get(IS_VALID)) return validInfo;
        validInfo = standardCheck.confirmCheck(map, validInfo);
        if (!(boolean) validInfo.get(IS_VALID)) return validInfo;
        validInfo.put(MESSAGE, "已经确认，如需修改请撤销！");
        validInfo.put(IS_VALID, true);
        return validInfo;
    }
}//the end of the class
