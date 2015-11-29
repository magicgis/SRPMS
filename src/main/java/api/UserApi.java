package api;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.StaffService;
import service.UserService;

import javax.ws.rs.*;
import java.util.HashMap;

import static util.Trans.putMapOnObj;

/**
 * Created by guofan on 2015/5/6.
 */
@Path("/user")
@RestController
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    StaffService staffService;

    /**
     * 修改
     *
     * @param id
     * @param args
     * @return T/F
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        User user = userService.getById(id);
        return putMapOnObj(user, args) && userService.update(user);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public User getById(@PathParam("id") String id) {
        return userService.getById(id);
    }


    /**
     * 修改密码
     * <oldPwd,旧密码>
     * <newPwd,新密码>
     * <user,userId>
     *
     * @param args json
     * @return T/F
     */
    @PUT
    @Path("/password")
    @Produces("application/json;charset=UTF-8")
    public boolean editPasswd(HashMap<String, String> args) {
        String old = args.get("oldPwd");
        String now = args.get("newPwd");
        String userId = args.get("user");
        return userService.editpasswd(userService.getUser(userId, old), now);
    }
}
