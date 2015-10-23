package api;

import entity.AchTran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.AchTranService;
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
 * Created by guofan on 2015/10/22.
 */
@RestController
@Path("/achTran")
public class AchTranApi {
    @Autowired
    AchTranService achTranService;

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
        return getSubMap(achTranService.search(search, sort, ord), limit, offset);
    }

    @POST
    @Path("/achTran")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        AchTran achTran;
        if ("".equals(args.getFirst("id"))) {
            achTran = new AchTran();
            achTran.setProcess("0");//表示刚填表
        }
        else {
            achTran = achTranService.getById((Serializable) args.getFirst("id"));
        }
        putMapOnObj(achTran, x);
        achTran.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        achTran.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        if ("".equals(achTran.getId())) {
            achTran.setId(null);
            return achTranService.add(achTran);
        }
        else {
            achTranService.update(achTran);
            return achTran.getId();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        AchTran achTran = achTranService.getById(id);
        achTran.setArgMap(args);
        return achTranService.update(achTran);
    }
}
