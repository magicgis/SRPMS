package service.imp;

import dao.BaseInfoDao;
import dao.StaRefDao;
import dao.StaffDao;
import entity.BaseInfo;
import entity.StaRef;
import entity.Staff;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DataDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/11/28.
 */
@Service
public class DataDisplayImp implements DataDisplay {
    //public class DataDisplayImp{
    final String[] TITLES = {"工号", "姓名", "学院", "论文", "项目", "著作", "专利",
            "成果转化", "成果获奖", "成果鉴定", "新药", "新食品",
            "新器具", "其他新产品", "总分"};
    final int ScoreIndex = 3;
    final String[] ItemKeys = {"id", "name", "col", "paper", "project", "book", "patent",
            "achTran", "achAward", "achAppraisal", "medicine", "food",
            "instrument", "others", "sum"};
    @Autowired
    StaRefDao staRefDao;
    @Autowired
    StaffDao staffDao;

    @Autowired
    BaseInfoDao baseInfoDao;

    public HSSFWorkbook utilMaplistToExcel(Map<String, List<Map<String, Object>>> dataMap,
                                           String[] keys, String[] titles) {
        HSSFWorkbook wb = new HSSFWorkbook();
        for (String key : dataMap.keySet()) {
            List<Map<String, Object>> dataMaplist = dataMap.get(key);
//            创建表格
            HSSFSheet sheet = wb.createSheet(key);
            if (dataMaplist.size() == 0) return null;
//            标题行
            int i = 0;
            HSSFRow rowTitle = sheet.createRow(i);
            for (int j = 0; j < keys.length; j++) {
//                    填写一个单元格
                HSSFCell cell = rowTitle.createCell(j);
                cell.setCellValue(TITLES[j]);
            }
            for (Map<String, Object> rowMap : dataMaplist) {
//                创建一行
                HSSFRow row = sheet.createRow(++i);
                for (int j = 0; j < keys.length; j++) {
//                    填写一个单元格
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue(String.valueOf(rowMap.get(keys[j])));
                }
            }
        }
        return wb;
    }

    @Override
    public HSSFWorkbook getColStaff() {
        Map<String, List<Map<String, Object>>> table = tableMap();
        return utilMaplistToExcel(table, ItemKeys, TITLES);
    }

    @Override
    public Map<String, Object> getStaffItemScore(String staffId) {
        return null;
    }

    //    @Override
    public HSSFWorkbook getColItem(List<Map<String, Object>> itemList) {
        return null;
    }

    //    @Override
    public Map<String, Object> getStaffScore(String staffId) {
        Map<String, Object> staffScore = new HashMap<>();
        Staff staff = staffDao.getById(staffId);
        double[] scoreArr = new double[ItemKeys.length];
        initDoubleArr(scoreArr);
//        人员基本信息
        staffScore.put("id", staff.getId());
        staffScore.put("name", staff.getName());
        staffScore.put("col", staff.getCol().getValue());
//        获取人员分数列表
        List<StaRef> actorsInfo = staRefDao.findByPropertyA("staff", staff.getId());
        for (StaRef actorInfo : actorsInfo) {
            int i = getElemIndex(ItemKeys, actorInfo.getType(), ScoreIndex);
            double tempScore = actorInfo.getScore().doubleValue();
            if (i < ItemKeys.length) {
                scoreArr[i] += tempScore;
                scoreArr[ItemKeys.length - 1] += tempScore;
            }
        }
        for (int i = ScoreIndex; i < ItemKeys.length; i++) {
            staffScore.put(ItemKeys[i], scoreArr[i]);
        }
        return staffScore;
    }

    //    @Override
    public List<Map<String, Object>> getStaffOfCol(String colId) {
        List<Map<String, Object>> res = new ArrayList<>();
        List<Staff> staffList = staffDao.findByPropertyA("col", colId);
        double[] sum = new double[ItemKeys.length];
        int j = 0;
        for (Staff staff : staffList) {
            Map<String, Object> staffScore = getStaffScore(staff.getId());
            res.add(j++, staffScore);
        }
        return res;
    }

    public List<Map<String, Object>> findByStaffName(String staffName) {
        List<Map<String, Object>> res = new ArrayList<>();
        List<Staff> staffList = staffDao.findByPropertyA("name", staffName);
        int i = 0;
        for (Staff staff : staffList) {
            Map<String, Object> staffScore = getStaffScore(staff.getId());
            res.add(i++, staffScore);
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getColScore(String colName) {
        List<Map<String, Object>> res = new ArrayList<>();
        List<BaseInfo> allCol = baseInfoDao.findByPropertyA("tableName", "院系");
        List<BaseInfo> colList = baseInfoDao.findByPropertyF("value", colName);
        if (colName.equals("all")) {
            for (BaseInfo col : allCol) {
                Map<String, Object> aCol = new HashMap<>();
                aCol = sumListScore(getStaffOfCol(col.getId()));
                aCol.put(ItemKeys[0], col.getId());
                aCol.put(ItemKeys[1], " ");
                aCol.put(ItemKeys[2], col.getValue());
                res.add(aCol);
            }
//            res.add(sumListScore(res));
            return res;
        } else if (colList != null && colList.size() != 0 && colList.size() == 1) {
            BaseInfo col = colList.get(0);
            res = getStaffOfCol(col.getId());
            res.add(sumListScore(res));
        }
        return res;
    }

    public Map<String, List<Map<String, Object>>> tableMap() {
        Map<String, List<Map<String, Object>>> res = new HashMap<>();
        List<BaseInfo> allCol = baseInfoDao.findByPropertyA("tableName", "院系");
        List<Map<String, Object>> allColRes = getColScore("all");
        allColRes.add(sumListScore(allColRes));
        res.put("全校总计", allColRes);
        for (BaseInfo col : allCol) {
            List<Map<String, Object>> tempColRes = getStaffOfCol(col.getId());
            tempColRes.add(sumListScore(tempColRes));
            res.put(col.getValue(), tempColRes);
        }
        return res;
    }

    //    @Override
//    public HSSFWorkbook getColItem(){
//        HSSFWorkbook colItem = new HSSFWorkbook();
////        查询论文
////        查询项目
////        查询专利
////        查询著作
//        return colItem;
//    }
    private Map<String, Object> sumListScore(List<Map<String, Object>> list) {
        Map<String, Object> res = new HashMap();
        double[] sum = new double[ItemKeys.length];
        res.put("id", " ");
        res.put("name", "总计");
        res.put("col", " ");
        for (Map<String, Object> elem : list) {
            for (String key : elem.keySet()) {
                int i = getElemIndex(ItemKeys, key, ScoreIndex);
                if (i < ItemKeys.length)
                    sum[i] += Double.valueOf((Double) elem.get(key));
            }
        }
        for (int i = ScoreIndex; i < ItemKeys.length; i++) {
            res.put(ItemKeys[i], sum[i]);
        }
        return res;
    }

    private Map initPersonScoreMap(String[] params) {
        Map map = new HashMap();
        for (int k = 0; k < params.length; k++) {
            map.put(params[k], 0);
        }
        return map;
    }

    private int getElemIndex(String[] param, String type, int start) {
        int i;
        for (i = start; i < param.length && !(param[i].equals(type)); i++) ;
//        if (i >= param.length) return param.length - 2;//others
        return i;
    }

    private void initDoubleArr(double[] d) {
        for (int i = 0; i < d.length; i++) {
            d[i] = 0;
        }
    }
}
