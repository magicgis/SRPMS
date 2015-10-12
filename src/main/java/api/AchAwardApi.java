package api;

import entity.AchAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.AchAwardService;
import service.BaseInfoService;
import service.StandardInfoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static util.Trans.getSubMap;
import static util.Trans.putMapOnObj;

/**
 * Created by guofan on 2015/10/12.
 */
@RestController
@Path("/achAward")
public class AchAwardApi {
    @Autowired
    AchAwardService achAwardService;

    @Autowired
    BaseInfoService baseInfoService;
    @Autowired
    StandardInfoService standardInfoService;

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        return getSubMap(achAwardService.search(search, sort, ord), limit, offset);
    }

    @POST
    @Path("/achAward")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        AchAward achAward;
        if ("".equals(args.getFirst("id"))) {
            achAward = new AchAward();
            achAward.setProcess("0");//表示刚填表
        }
        else {
            achAward = achAwardService.getById((Serializable) args.getFirst("id"));
        }
        putMapOnObj(achAward, x);
        achAward.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        achAward.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        if ("".equals(achAward.getId())) {
            achAward.setId(null);
            return achAwardService.add(achAward);
        }
        else {
            achAwardService.update(achAward);
            return achAward.getId();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        AchAward achAward = achAwardService.getById(id);
        achAward.setArgMap(args);
        return achAwardService.update(achAward);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean delete(@PathParam("id") String id) {
        return false;//todo
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public Object getById(@PathParam("id") String id) {
        return null;//todo
    }
}
