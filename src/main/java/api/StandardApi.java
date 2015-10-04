package api;

import entity.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.StandardInfoService;

import javax.ws.rs.*;
import java.util.List;
import java.util.Map;

import static util.Trans.getSubMap;

/**
 * Created by guofan on 2015/10/4.
 */
@Path("/baseinfo")
@RestController
public class StandardApi {
    @Autowired
    StandardInfoService standardInfoService;

    /**
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
        return getSubMap(standardInfoService.search(search, sort, ord), limit, offset);
    }

    /**
     * 根据类别获取List
     *
     * @param type
     * @return
     */
    @GET
    @Path("/type/{type}")
    @Produces("application/json;charset=UTF-8")
    public List getByTableName(@PathParam("type") String type) {
        return standardInfoService.getByType(type);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json;charset=UTF-8")
    public Standard getById(@PathParam("id") String id) {
        return standardInfoService.getById(id);
    }

}
