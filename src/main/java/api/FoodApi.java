package api;

import engine.Engine;
import entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BaseInfoService;
import service.FoodService;
import service.StandardInfoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static util.Trans.getSubMap;
import static util.Trans.putMapOnObj;

/**
 * Created by guofan on 2015/11/19.
 */
@RestController
@Path("/food")
public class FoodApi {
    @Autowired
    FoodService foodService;
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
        return getSubMap(foodService.search(search, sort, ord), limit, offset);
    }

    @POST
    @Path("/food")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        Food food;
        if ("".equals(args.getFirst("id"))) {
            food = new Food();
            food.setProcess("0");//表示刚填表
        }
        else {
            food = foodService.getById((Serializable) args.getFirst("id"));
        }
        putMapOnObj(food, x);
        food.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        food.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        if ("".equals(food.getId())) {
            food.setId(null);
            return foodService.add(food);
        }
        else {
            foodService.update(food);
            return food.getId();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        Food food = foodService.getById(id);
        food.setArgMap(args);
        return foodService.update(food);
    }

    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") String id) {
        Food food = foodService.getById(id);
        String orderId = (String) food.getArgMap().get("WF_OrderId");
        if (orderId != null) {
            engine.stopOrder(orderId);
        }
        return foodService.delete(food);
    }
}
