package service.imp;

import dao.StandardDao;
import entity.Standard;
import org.snaker.engine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StandardService;
import service.imp.standard.StandardCheckInf;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/6/8.
 */
@Service
public class StandardServiceImp extends StandardBase implements StandardService {

    @Autowired
    private StandardDao standardDao;

    public StandardServiceImp() throws FileNotFoundException {
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
            System.out.print(type);
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
        String stId = (String) map.get(stIdType);
//        System.out.println(stId);
        Standard st = standardDao.getById(stId);
//        System.out.println(st.getValue());
        if (st == null) {
            calFlow.put(MESSAGE, "附表中没有相关规定");
            //        极值约束
            calFlow = standardCheck.isExtrrmumBand(map, 0, 0);
            if (!(boolean) calFlow.get(IS_VALID)) return calFlow;
            calFlow = standardCheck.getFinalScore(map, 0, 0);
            return calFlow;
//            return calFlow;
        }
        else {
            BigDecimal sumt = st.getValue();
            BigDecimal sumt2 = st.getValue();
            BigDecimal mint = st.getMin() == null ? new BigDecimal(0) : st.getMin();
            BigDecimal maxt = st.getMax() == null ? new BigDecimal(999):st.getMax();
            double sum = sumt.doubleValue();
            double sum2 = sumt2.doubleValue();
            double min = mint.doubleValue();
            double max = maxt.doubleValue();
            //        极值约束
            calFlow = standardCheck.isExtrrmumBand(map, min, max);
            if (!(boolean) calFlow.get(IS_VALID)) return calFlow;
            calFlow = standardCheck.getFinalScore(map, sum, sum2);
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
        validInfo = standardCheck.confirmCheck(map);
        if (!(boolean) validInfo.get(IS_VALID)) return validInfo;
        validInfo.put(MESSAGE, "已经确认，如需修改请撤销！");
        validInfo.put(IS_VALID, true);
        return validInfo;
    }
}//the end of the class








