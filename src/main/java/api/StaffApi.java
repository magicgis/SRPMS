package api;

import entity.Staff;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BaseInfoService;
import service.StaffService;
import service.UserService;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Trans.*;

/**
 * Created by guofan on 2015/5/7.
 */
@Path("/staff")
@RestController
public class StaffApi {
    @Autowired
    StaffService staffService;
    @Autowired
    UserService userService;
    @Autowired
    BaseInfoService baseInfoService;

    /**
     * 所有员工信息（非User)
     *
     * @param limit  每页数量（分页用）
     * @param offset 偏移量（分页用）
     * @param search 搜索关键字
     * @param sort   排序
     * @param ord    升序还是降序
     * @return 所有员工信息
     */
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        return getSubMap(staffService.search(search, sort, ord), limit, offset);
    }

    /**
     * 新增员工
     *
     * @param args 新增员工json
     * @return TorF
     */
    @POST
    @Path("/new")
    @Consumes("application/json;charset=UTF-8")
    public boolean add(HashMap<String, Object> args) {
        return false;//todo
    }


    /**
     * 启用员工帐号，帐号为工号，密码为工号
     *
     * @param id 员工ID
     * @return T/F
     */
    @POST
    @Path("/enable/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean enableStaff(@PathParam("id") String id) {
        Staff staff = staffService.getById(id);
        if (staff.getUser() == null) {
            User user = new User();
            user.setId(staff.getId());
            try {
                user.setPwd(MD5(user.getId() + user.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //TODO 设置权限
            user.setPrivilege(null);
            user.setUStaff(staff);
            user.setStatus(1);
            return userService.save(user);
        } else {
            User user = staff.getUser();
            user.setStatus(1);
            return userService.update(user);
        }
    }

    /**
     * 重置密码，密码为其工号
     *
     * @param id 员工工号
     * @return T/F
     */
    @PUT
    @Path("/reset/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean resetStaff(@PathParam("id") String id) {
        Staff staff = staffService.getById(id);
        if (staff != null && staff.getUser() != null) {
            User user = staff.getUser();
            String pwd = MD5(user.getId() + user.getId());
            user.setPwd(pwd);
            return userService.update(user);
        }
        return false;
    }

    /**
     * 禁用员工帐号
     *
     * @param id 员工工号
     * @return T/F
     */
    @POST
    @Path("/disable/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean disableStaff(@PathParam("id") String id) {
        Staff staff = staffService.getById(id);
        if (staff.getUser() == null) {
            return false;
        } else {
            User user = staff.getUser();
            user.setStatus(0);
            return userService.update(user);
        }
    }


    /**
     * 修改员工信息
     *
     * @param id   主键
     * @param args 修改后的员工json
     * @return TorF
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        /*旧的*/
        Staff staff = staffService.getById(id);
        HashMap<String, Object> first = new HashMap<>(args);
        if (putMapOnObj(staff, args)) {
            try {
                staff.setSCol(baseInfoService.getById(first.get("sCol.id").toString()));
                staff.setSDept(baseInfoService.getById(first.get("sDept.id").toString()));
                staff.setSRank(baseInfoService.getById(first.get("sRank.id").toString()));
            } catch (NullPointerException e) {
                return false;
            }
            return staffService.update(staff);
        }
        return false;
    }

    /**
     * 删除员工信息
     *
     * @param id 主键
     * @return TorF
     */
    @DELETE
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean delete(@PathParam("id") String id) {
        return false;//todo
    }

    /**
     * 获取员工信息
     *
     * @param id 主键
     * @return 员工信息
     */
    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public Staff getById(@PathParam("id") String id) {
        return staffService.getById(id);
    }

    @GET
    @Path("/json")
    @Consumes("application/json;charset=UTF-8")
    public List<Staff> getAllStaff(@QueryParam("query") String query) {
        return staffService.search(query, null, null);
    }

}
