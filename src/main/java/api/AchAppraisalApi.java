package api;

import engine.Engine;
import entity.AchAppraisal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.AchAppraisalService;
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
@Path("/achAppraisal")
public class AchAppraisalApi {
    @Autowired
    AchAppraisalService achAppraisalService;
    @Autowired
    BaseInfoService baseInfoService;
    @Autowired
    StandardInfoService standardInfoService;
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
        return getSubMap(achAppraisalService.search(search, sort, ord), limit, offset);
    }

    @POST
    @Path("/achAppraisal")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        AchAppraisal achAppraisal;
        if ("".equals(args.getFirst("id"))) {
            achAppraisal = new AchAppraisal();
            achAppraisal.setProcess("0");//表示刚填表
        }
        else {
            achAppraisal = achAppraisalService.getById((Serializable) args.getFirst("id"));
        }
        putMapOnObj(achAppraisal, x);
        achAppraisal.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        achAppraisal.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        if ("".equals(achAppraisal.getId())) {
            achAppraisal.setId(null);
            return achAppraisalService.add(achAppraisal);
        }
        else {
            achAppraisalService.update(achAppraisal);
            return achAppraisal.getId();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        AchAppraisal achAppraisal = achAppraisalService.getById(id);
        achAppraisal.setArgMap(args);
        return achAppraisalService.update(achAppraisal);
    }

    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") String id) {
        AchAppraisal achAppraisal = achAppraisalService.getById(id);
        String orderId = (String) achAppraisal.getArgMap().get("WF_OrderId");
        if (orderId != null) {
            engine.stopOrder(orderId);
        }
        return achAppraisalService.delete(achAppraisal);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public Object getById(@PathParam("id") String id) {
        return null;//todo
    }
}
