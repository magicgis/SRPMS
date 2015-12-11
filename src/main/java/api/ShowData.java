package api;

import dao.StaRefDao;
import dao.StaffDao;
import entity.BaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.DataDisplay;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;


/**
 * Created by DELL on 2015/12/11.
 */
@Path("/datadisplay")
@RestController
public class ShowData {
    @Autowired
    StaRefDao staRefDao;
    @Autowired
    DataDisplay dataDisplay;
    @Autowired
    StaffDao staffDao;
    //    input：教师id
    //    output：教师的分数获取明细
    //    说明：返回StaRef实体List，如果有需求可改为Map
    @GET
    @Path("/staff/{staffid}")
    @Produces("application/json;charset=UTF-8")
    public List getStaffScoreInfo(@PathParam("staffid") String staffId) {
        return staRefDao.findByPropertyA("id",staffId);
    }

    //    input：学院管理员id（程序会以此找到学院的id）
    //    output：学院教师的分数获取明细
//    说明：返回的List中Map结构可以参看service.imp.DataDisplayImp.java中ItemKeys数组
    @GET
    @Path("/col/{adminid}")
    @Produces("application/json;charset=UTF-8")
    public List getColScoreInfo(@PathParam("adminid") String adminId) {
        BaseInfo col = staffDao.getById(adminId).getCol();
        return dataDisplay.getColScore(col.getValue());
    }
    //    input：院系名，“all”为全校概况，
    //    output：学院教师的分数获取明细
    @GET
    @Path("/sch/{colname}")
    @Produces("application/json;charset=UTF-8")
    public List getSchScoreInfo(@PathParam("colname") String colName) {
        return dataDisplay.getColScore(colName);
    }
}
