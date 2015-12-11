package service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/12/8.
 */
public interface DataDisplay {
//    员工得分明细Excel
    public HSSFWorkbook getColStaff();
//    条目信息明细Excel
//    public HSSFWorkbook getColItem(List<Map<String,Object>> itemList);
//      得出个人得分明细
    public Map<String,Object> getStaffScore(String staffId);
//    得出学院的个人的分明细
    public List<Map<String, Object>> getColScoreByAdmin(String adminName);
    public List<Map<String, Object>> getColScore(String colName);

}
