package api;

import entity.BaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BaseInfoService;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Trans.getSubMap;

/**
 * Created by yxm on 2015/5/11.
 */
@Path("/baseinfo")
@RestController
public class BaseInfoApi {
    @Autowired
    BaseInfoService baseInfoService;

    /**
     * 获取所有的帐号信息
     *
     * @param limit
     * @param offset
     * @param search
     * @param sort
     * @param ord
     * @return
     */
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        return getSubMap(baseInfoService.search(search, sort, ord), limit, offset);
    }

    /**
     * 根据tableName获取List
     *
     * @param tableName
     * @return
     */
    @GET
    @Path("/{tableName}")
    @Produces("application/json;charset=UTF-8")
    public List getByTableName(@PathParam("tableName") String tableName) {
        return baseInfoService.getByTableName(tableName);
    }

    /**
     * 新增baseInfo
     *
     * @param args
     * @return
     */
    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public boolean add(HashMap<String, String> args) {
        BaseInfo baseInfo = new BaseInfo();
        String tableName = (String) args.get("tableName");
        String id = baseInfoService.getNewID(tableName);
        baseInfo.setTableName(tableName);
        baseInfo.setValue((String) args.get("value"));
        baseInfo.setId(id);
        baseInfo.setKeyCode(id);
        return baseInfoService.save(baseInfo);
    }

    /**
     * 修改
     *
     * @param id
     * @param args
     * @return
     */
    @PUT
    @Path("/{id}")
    @Produces("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        BaseInfo baseInfo = baseInfoService.getById(id);
        baseInfo.setTableName((String) args.get("tableName"));
        baseInfo.setValue((String) args.get("value"));
        return baseInfoService.update(baseInfo);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    @Produces("application/json;charset=UTF-8")
    public boolean detele(@PathParam("id") String id) {
        return baseInfoService.delete(baseInfoService.getById(id));
    }

}
