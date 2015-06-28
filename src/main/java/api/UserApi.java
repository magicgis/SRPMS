package api;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.StaffService;
import service.UserService;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.Map;

import static util.Args.TokenUser;
import static util.Trans.*;

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
     * 获取所有的帐号信息
     *
     * @param limit  每页数量
     * @param offset 偏移值
     * @param search 关键字
     * @param sort   排序关键字
     * @param ord    升序或者降序
     * @return 所有帐号信息
     */
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        if (sort != null) {
            sort = sort.replace('_', '.');
        }
        return getSubMap(userService.search(search, sort, ord), limit, offset);
    }

    /**
     * 新增帐号
     *
     * @param args 新增的帐号信息
     * @return TorF
     */
    @POST
    @Path("/new")
    @Consumes("application/json;charset=UTF-8")
    public boolean add(HashMap<String, Object> args) {
        return false;//todo
    }

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


    @DELETE
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean delete(@PathParam("id") String id) {
        User temp = userService.getById(id);
        temp.setStatus(0);
        return userService.update(temp);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public User getById(@PathParam("id") String id) {
        return userService.getById(id);
    }

    @POST
    @Path("/login")
    @Consumes("application/json;charset=UTF-8")
    public String login(Map<String, String> args) {
        String staId = args.get("username");
        String pwd = args.get("password");
        User u = userService.getUser(staId, pwd);
        if (u == null || u.getStatus() == null || u.getStatus() == 0) {
            return null;
        } else {
            Long time = System.currentTimeMillis() / 1000;
            String token = null;
            try {
                token = MD5(staId + pwd + String.valueOf(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
            TokenUser.put(token, u);
            return token;
        }
    }

    @GET
    @Path("/current")
    @Produces("application/json;charset=UTF-8")
    public User getCurrentUser(@HeaderParam("Authorization") String token) {
        return TokenUser.get(token);
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
