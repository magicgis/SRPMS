package api;

import engine.Engine;
import entity.Patent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BaseInfoService;
import service.PatentService;
import service.StandardInfoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static util.Trans.getSubMap;
import static util.Trans.putMapOnObj;

/**
 * Created by guofan on 2015/10/2.
 */
@Path("/patent")
@RestController
public class PatentApi {
    @Autowired
    PatentService patentService;
    @Autowired
    StandardInfoService standardInfoService;
    @Autowired
    BaseInfoService baseInfoService;
    @Autowired
    Engine engine;

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        return getSubMap(patentService.search(search, sort, ord), limit, offset);
    }


    @POST
    @Path("/patent")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        Patent patent;
        if ("".equals(args.getFirst("id"))) {
            patent = new Patent();
            patent.setProcess("0");//表示刚填表
        }
        else {
            patent = patentService.getById((Serializable) args.getFirst("id"));
        }
        putMapOnObj(patent, x);
        patent.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        patent.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        if ("".equals(patent.getId())) {
            patent.setId(null);
            return patentService.add(patent);
        }
        else {
            patentService.update(patent);
            return patent.getId();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        Patent patent = patentService.getById(id);
        patent.setArgMap(args);
        return patentService.update(patent);
    }

    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") String id) {
        Patent patent = patentService.getById(id);
        String orderId = (String) patent.getArgMap().get("WF_OrderId");
        if (orderId != null) {
            engine.stopOrder(orderId);
        }
        return patentService.delete(patent);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public Object getById(@PathParam("id") String id) {
        return null;//todo
    }

}
