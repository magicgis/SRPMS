package api;

import entity.Mag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.MagService;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Trans.getSubMap;

/**
 * Created by guofan on 2015/5/7.
 */
@Path("/mag")
@RestController
public class MagApi {
    @Autowired
    MagService magService;

    /**
     * 获取所有的mag，带搜索分页功能
     *
     * @param limit  每页数量
     * @param offset 偏移量
     * @param search 关键字
     * @param sort   排序关键字
     * @param ord    升序降序
     * @return 所有的帐号信息
     */
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        return getSubMap(magService.search(search, sort, ord), limit, offset);
    }

    /**
     * 新增期刊
     *
     * @param args 期刊json
     * @return TorF
     */
    @POST
    @Path("/new")
    @Consumes("application/json;charset=UTF-8")
    public boolean add(HashMap<String, Object> args) {
        return false;//todo
    }

    /**
     * 修改期刊
     *
     * @param id   期刊主键
     * @param args 修改过的期刊信息
     * @return TorF
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        return false;//todo
    }

    /**
     * 删除期刊
     *
     * @param id 期刊主键
     * @return TorF
     */
    @DELETE
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean delete(@PathParam("id") String id) {
        return false;//todo
    }

    /**
     * 获取期刊信息
     *
     * @param id 期刊主键
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces("application/json;charset=UTF-8")
    public Mag getById(@PathParam("id") String id) {
        return magService.getById(id);
    }

    /**
     * 获取所有期刊名list
     *
     * @return 期刊名List<String>
     */
    @GET
    @Path("/list")
    public List<String> getAllMag(@QueryParam("query") String query) {
        List<Mag> temp = magService.search(query, null, null);
        List<String> ans = new ArrayList<>();
        for (Mag mag : temp) {
            ans.add(mag.getName());
        }
        return ans;
    }
//    public List<String> getAllMagList() {
//        List<Mag> temp = magService.getAll();
//        List<String> ans = new ArrayList<>();
//        for (Mag mag : temp) {
//            ans.add(mag.getName());
//        }
//        return ans;
//    }

    /**
     * 获取所有的期刊信息JSON
     *
     * @return 期刊信息JSON
     */
    @GET
    @Path("/json")
    @Produces("application/json;charset=UTF-8")
    public List<Mag> getAllMagJson(@QueryParam("query") String query) {
        return magService.search(query, null, null);
    }
}
