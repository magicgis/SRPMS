package api;

import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.StaffService;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Trans.getSubMap;

/**
 * Created by guofan on 2015/5/7.
 */
@Path("/staff")
@RestController
public class StaffApi {
    @Autowired
    StaffService staffService;

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
        Staff temp = staffService.getById(id);
//        temp.setEdu();
//        Staff y = (Staff) map2Obj(args, Staff.class);
        return staffService.update(temp);
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
