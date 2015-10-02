package api;

import entity.StaRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.ProjectService;
import service.StaRefService;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Path("/new")
    @Consumes("application/json;charset=UTF-8")
    public boolean add(HashMap<String, Object> args) {
        return false;//todo
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        return false;//todo
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
