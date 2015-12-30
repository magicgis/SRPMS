package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.RelationService;
import service.UserService;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by guofan on 2015/10/19.
 */
@RestController
@Path("/entity")
public class EntityApi {
    @Autowired
    RelationService relationService;
    @Autowired
    UserService userService;


    /**
     * 普通教师使用
     * 无分页
     *
     * @param id
     * @param type
     * @param member
     * @return
     */
    @GET
    @Path("/{id}/{type}/{member}/noPag")
    @Produces("application/json;charset=UTF-8")
    public List<Object> getUserList(@PathParam("id") String id, @PathParam("type") String type,
                                    @PathParam("member") String member) {
        Integer real;
        if ("1st".equals(member)) {
            real = 1;
        }
        else if ("2nd".equals(member)) {
            real = 0;
        }
        else {
            real = null;
        }
        if ("all".equals(type)) {
            return relationService.getEntity(id, real);
        }
        else {
            return relationService.getEntity(id, type, real);
        }
    }

    /**
     * 学院使用
     * 无分页
     *
     * @param id
     * @param type
     * @return
     */
    @GET
    @Path("/col/{id}/{type}/noPag")
    @Produces("application/json;charset=UTF-8")
    public List<Object> getDeptList(@PathParam("id") String id, @PathParam("type") String type) {
//        if ("all".equals(type)) {
//            return deptRefService.getEntity(id, null);
//        }
//        else {
//            return deptRefService.getEntity(id, type);
//        }
        return null;
    }

    @GET
    @Path("/staff/{user}")
    @Produces("application/json;charset=UTF-8")
    public List getStaffScoreInfo(@PathParam("user") String userId, @QueryParam("type") String type, @QueryParam("role") Integer role) {
        return relationService.getTeacherRelation(userId, type, role);
    }


}
