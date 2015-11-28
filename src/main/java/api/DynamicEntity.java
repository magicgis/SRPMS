package api;

import engine.Engine;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import service.BaseInfoService;
import service.BaseService;
import service.StandardInfoService;
import util.Args;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static util.Trans.getSubMap;
import static util.Trans.putMapOnObj;

/**
 * Created by guofan on 2015/11/22.
 */
@RestController
@Path("/{entity:achAppraisal|achAward|achTran|book|paper|patent|project|others|food|instrument|medicine}")
public class DynamicEntity {
    @PathParam("entity")
    String entity;

    @Autowired
    ApplicationContext webApplicationContext;
    @Autowired
    Engine engine;
    @Autowired
    StandardInfoService standardInfoService;
    @Autowired
    BaseInfoService baseInfoService;

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        BaseService baseService = (BaseService) webApplicationContext.getBean(Args.SERVICES.get(entity));
        return getSubMap(baseService.search(search, sort, ord), limit, offset);


    }

    @GET
    @Path("/all/noPag")
    @Produces("application/json;charset=UTF-8")
    public Object getAll() {
        BaseService baseService = (BaseService) webApplicationContext.getBean(Args.SERVICES.get(entity));
        return baseService.getAll();
    }


    @POST
    @SuppressWarnings("unchecked")
    @Path("/{entity:achAppraisal|achAward|achTran|book|paper|patent|project|others|food|instrument|medicine}")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        BaseService baseService = (BaseService) webApplicationContext.getBean(Args.SERVICES.get(entity));
        VirtualEntity entityObject = null;
        switch (entity) {
            case "achAppraisal":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new AchAppraisal();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (AchAppraisal) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "achAward":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new AchAward();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (AchAward) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "achTran":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new AchTran();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (AchTran) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "book":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new Book();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (Book) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "paper":
                break;
            case "patent":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new Patent();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (Patent) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "project":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new Project();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (Project) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "others":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new Others();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (Others) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "food":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new Food();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (Food) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "instrument":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new Instrument();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (Instrument) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            case "medicine":
                if ("".equals(args.getFirst("id"))) {
                    entityObject = new Medicine();
                    entityObject.setProcess("0");//表示刚填表
                }
                else {
                    entityObject = (Medicine) baseService.getById((Serializable) args.getFirst("id"));
                }
                break;
            default:
                return null;
        }
        if (args.containsKey("standard.id")) {
            entityObject.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        }
        if (args.containsKey("dept.id")) {
            entityObject.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        }
        putMapOnObj(entityObject, x);
        if ("".equals(entityObject.getId())) {
            entityObject.setId(null);
            return baseService.add(entityObject);
        }
        else {
            baseService.update(entityObject);
            return entityObject.getId();
        }

    }

    //
    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        BaseService baseService = (BaseService) webApplicationContext.getBean(Args.SERVICES.get(entity));
        VirtualEntity entity = (VirtualEntity) baseService.getById(id);
        entity.setArgMap(args);
        return baseService.update(entity);
    }


    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") String id) {
        BaseService baseService = (BaseService) webApplicationContext.getBean(Args.SERVICES.get(entity));
        VirtualEntity entity = (VirtualEntity) baseService.getById(id);
        String orderId = (String) entity.getArgMap().get("WF_OrderId");
        //todo 这儿还缺少去数据库里清除所有关系ref
        if (orderId != null) {
            engine.stopOrder(orderId);
        }
        return baseService.deleteById(entity.getId());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json;charset=UTF-8")
    public Object getById(@PathParam("id") String id) {
        BaseService baseService = (BaseService) webApplicationContext.getBean(Args.SERVICES.get(entity));
        return baseService.getById(id);
    }

}
