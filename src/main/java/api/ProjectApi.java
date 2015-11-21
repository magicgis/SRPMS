package api;

import engine.Engine;
import entity.Project;
import entity.StaRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BaseInfoService;
import service.ProjectService;
import service.StaRefService;
import service.StandardInfoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.Trans.putMapOnObj;

/**
 * Created by guofan on 2015/8/29.
 */
@RestController
@Path("/project")
public class ProjectApi {

    @Autowired
    ProjectService projectService;
    @Autowired
    StaRefService staRefService;
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
        return util.Trans.getSubMap(projectService.search(search, sort, ord), limit, offset);
    }


    @GET
    @Path("/{id}/userList")
    @Produces("application/json;charset=UTF-8")
    public List<StaRef> getUserList(@PathParam("id") String id) {
        entity.Project x = projectService.getById(id);
        return staRefService.getUserList(x);
    }

    @POST
    @Path("/{id}/userList")
    @Consumes("application/json;charset=UTF-8")
    public boolean addUserList(@PathParam("id") String id, HashMap<String, Object> args) {
        //todo 将用户添加到用户表
        return false;
    }

    @POST
    @Path("/project")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        Project project;
        if ("".equals(args.getFirst("id"))) {
            project = new Project();
            project.setProcess("0");//表示刚填表
        }
        else {
            project = projectService.getById((Serializable) args.getFirst("id"));
        }
        putMapOnObj(project, x);
        project.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        project.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        if ("".equals(project.getId())) {
            project.setId(null);
            return projectService.add(project);
        }
        else {
            projectService.update(project);
            return project.getId();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        Project project = projectService.getById(id);
        project.setArgMap(args);
        return projectService.update(project);
    }

    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") String id) {
        Project project = projectService.getById(id);
        String orderId = (String) project.getArgMap().get("WF_OrderId");
        if (orderId != null) {
            engine.stopOrder(orderId);
        }
        return projectService.delete(project);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public Object getById(@PathParam("id") String id) {
        return null;//todo
    }
}
