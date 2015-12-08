package api;

import engine.Engine;
import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import entity.Staff;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.StaffService;
import service.StandardService;
import util.Args;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.*;

import static util.Trans.getSubMap;

/**
 * DATE:2015/3/8
 * TIME:19:14
 * Created by guofan on 2015/3/8
 */
@Path("/workflow")
@RestController
@SuppressWarnings({"unchecked", "unused"})
public class Workflow {
    @Context
    ServletContext context;
    @Autowired
    Engine engine;
    @Autowired
    OrderActorDao orderActorDao;
    @Autowired
    StandardService standardService;
    @Autowired
    StaffService staffService;

    /**
     * 初始化部署
     *
     * @return status
     */
    @GET
    @Path("/init")
    @Produces("text/plain;charset=UTF-8")
    public String init() {
        engine.initFlows();
        List<Process> list = engine.getAllProcess();
        if (list == null || list.size() == 0) {
            return "NONE";
        }
        else {
            return "DONE";
        }
    }


    /**
     * 启动流程
     *
     * @return 生成的order
     */
    @POST
    @Path("/start")
    @Produces("application/json;charset=UTF-8")
    public Order startProcess_beta(@FormParam("WF_User") String staff,
                                   @FormParam("WF_Process") String processName,
                                   @FormParam("WF_Type") String type) {
        String processId = engine.getProcessByName(processName).getId();
        return engine.startInstanceById(processId, staff, type);
    }

    @POST
    @Path("/start/{type}/{entityId}")
    @Produces("application/json;charset=UTF-8")
    public Order startFxxkProcess(@PathParam("type") String type, @PathParam("entityId") String entityId) {
        return engine.startInstanceByEntity(entityId, type);
    }


    /**
     * 执行任务
     *
     * @param args json
     * @return 后续任务（实际无用）
     */
    @POST
    @Path("/execute")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Object execute_beta(HashMap<String, Object> args) {
        //获取用户id和taskId
        String user = (String) args.get("WF_User");
        args.remove("WF_User");
        String taskId = (String) args.get("WF_Task");
        args.remove("WF_Task");
        //如果传来的含有actors
        if (args.containsKey("actors")) {
            List<Map<String, Object>> actors = (List<Map<String, Object>>) args.get("actors");
            //防止重复（可能存在挂多个单位的情况）
            List<String> aList = new ArrayList<>();
            //生成下一步的执行者
            StringBuffer as = new StringBuffer();
            //单纯的用于显示
            StringBuffer actosStr = new StringBuffer();
            //遍历所有的actor Map
            for (Map<String, Object> u : actors) {
                //获取用户id
                String aId = (String) u.get("staff.id");
                //避免多次分发任务以及跳过校外人员和学生
                if (aList.contains(aId) || Args.SPECIAL_STAFF_ID.contains(aId)) {
                    continue;
                }
                aList.add(aId);
                as.append(aId).append(",");
                actosStr.append(u.get("staff.name")).append(",");
            }
            args.put("WF_Actor", as);
            args.put("ActorList", actosStr);
        }
        List<Task> ans = new ArrayList<>();
        /*获取当前task*/
        Task task = engine.getTask(taskId);
        /*可能会产生的task列表*/
        List<Task> tasks;
        if (task.getTaskName().equals("Submission") && "true".equals(args.get("IsComplete"))) {
        /*获取当前order*/
            Order order = engine.getOrder(task.getOrderId());
            /* 使用检验 开始*/
            Map re = standardService.confirmChecking(order, args);
            if ((boolean) re.get("valid") || args.containsKey("TEST")) {
                tasks = engine.execute(taskId, user, (Map) args);
            }
            else {
                return re;
            }
            /* 使用检验 结束*/

            /* 不使用检验 开始*/
//            tasks = engine.execute(taskId, user, (Map) args);
            /* 不使用检验 结束*/
        }
        else {
            tasks = engine.execute(taskId, user, (Map) args);
        }
        if (tasks == null || tasks.size() == 0) {
            return null;
        }
        for (Task u : tasks) {
            u.setModel(null);
            if (u.getTaskName().equals("Confirm") && engine.isYourTask(u.getId(), user)) {
                engine.execute(u.getId(), user, new HashMap<String, Object>());
//                tasks.remove(u);
            }
        }
        return tasks;
    }


    @POST
    @Path("/getScore")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Object getScore(HashMap<String, Object> args) {
        Order x = null;
        try {
            String taskId = (String) args.get("WF_Task");
            x = engine.getOrder(engine.getTask(taskId).getOrderId());
        } catch (Exception e) {
        }
        return standardService.scoreCalculation(x, args);
    }

    /**
     * 获取用户所有待办任务
     *
     * @param user 用户
     * @return 待办任务task
     */
    @GET
    @Path("/{user}/task")
    @Produces("application/json;charset=UTF-8")
    public List<Task> getTask(@PathParam("user") String user) {
        return engine.getTaskByActor(user);
    }


    /**
     * 获取用户各种类型的order
     * 分页
     *
     * @param user   用户
     * @param type   order类型，如book，paper
     * @param member 是主导还是参与
     * @param limit  获取数量（分页用）
     * @return order
     */
    @GET
    @Path("/order/{user}/{type}/{member}")
    @Produces("application/json;charset=UTF-8")
    public Map getOrder(@PathParam("user") String user,
                        @PathParam("type") String type,
                        @PathParam("member") String member,
                        @QueryParam("limit") Integer limit,
                        @QueryParam("offset") Integer offset) {
        Staff staff = staffService.getById(user);
        String actorId = null;
        Integer role = null;
        if ("1".equals(staff.getUser().getPrivilege())) {
            actorId = user;
            if (member.equals("1st")) {
                role = 1;
            }
            else if (member.equals("2nd")) {
                role = 0;
            }
            else {
                // magic number
                role = 10;
            }
        }
        else if ("2".equals(staff.getUser().getPrivilege())) {
            actorId = staff.getCol().getId();
            role = 2;
        }
        else if ("3".equals(staff.getUser().getPrivilege())) {
            //todo 此处实际是可以区分出各类管理员的，此时暂时不管这些
            actorId = null;
            role = 3;
        }
        if (type.equals("all")) {
            type = null;
        }
        List<OrderActor> list = orderActorDao.getByAll(actorId, type, role);
        List<Order> ans = new ArrayList<>();
        for (OrderActor u : list) {
            ans.add(engine.getOrder(u.getOrder()));
        }
        return getSubMap(ans, limit, offset);
    }


    /**
     * 获取用户各种类型的order
     * 不分页
     *
     * @param user   用户
     * @param type   order类型，如book，paper
     * @param member 是主导还是参与
     * @return order
     */
    @GET
    @Path("/order/{user}/{type}/{member}/noPag")
    @Produces("application/json;charset=UTF-8")
    public List getOrder(@PathParam("user") String user,
                         @PathParam("type") String type,
                         @PathParam("member") String member) {
        Staff staff = staffService.getById(user);
        String actorId = null;
        Integer role = null;
        if ("1".equals(staff.getUser().getPrivilege())) {
            actorId = user;
            if (member.equals("1st")) {
                role = 1;
            }
            else if (member.equals("2nd")) {
                role = 0;
            }
            else {
                // magic number
                role = 10;
            }
        }
        else if ("2".equals(staff.getUser().getPrivilege())) {
            actorId = staff.getCol().getId();
            role = 2;
        }
        else if ("3".equals(staff.getUser().getPrivilege())) {
            //todo 此处实际是可以区分出各类管理员的，此时暂时不管这些
            actorId = null;
            role = 3;
        }
        if (type.equals("all")) {
            type = null;
        }
        List<OrderActor> list = orderActorDao.getByAll(actorId, type, role);
        List<Order> ans = new ArrayList<>();
        for (OrderActor u : list) {
            ans.add(engine.getOrder(u.getOrder()));
        }
        return ans;
    }


    /**
     * 获取用户待确认任务
     * 不分页！
     *
     * @param user 用户
     * @return 待确认任务列表
     */
    @GET
    @Path("/{user}/confirmTask")
    @Produces("application/json;charset=UTF-8")
    public List getConfirmTask(@PathParam("user") String user) {
        return engine.getConfirmTask(user);
    }

    /**
     * 统一提交
     *
     * @param user 用户
     * @return TorF
     */
    @POST
    @Path("/submitByTeacher")
    @Produces("text/plain;charset=UTF-8")
    public boolean teacherSubmit(@FormParam("WF_User") String user) {
        List<Order> list = engine.getMainOrderByActor(user);
        List<String> teacherFlag = Arrays.asList("Submission", "Confirm");
        for (Order u : list) {
            List<Task> tasks = engine.getTaskByOrder(u.getId());
            String taskName = tasks.get(0).getTaskName();
            if (tasks.size() != 1 || teacherFlag.contains(taskName)) {
                return false;
            }
        }
        Map<String, Object> args = new HashMap<>();
        args.put("WF_Memo", "Submit By Program");
        //todo 需要改进
        for (Order u : list) {
            List<Task> tasks = engine.getTaskByOrder(u.getId());
            if (tasks.get(0).getTaskName().equals("SubmitByTeacher")) {
                engine.execute(tasks.get(0).getId(), user, args);
            }
        }
        return true;
    }

    /**
     * 统一提交
     *
     * @param user 用户
     * @return TorF
     */
    @POST
    @Path("/submitByCol")
    @Produces("text/plain;charset=UTF-8")
    public boolean colSubmit(@FormParam("WF_User") String user) {
        Staff staff = staffService.getById(user);
        String actorId = staff.getCol().getId();
        List<Order> list = engine.getAllOrderByActor(actorId);
        List<String> colFlag = Arrays.asList("Submission", "Confirm", "SubmitByTeacher", "ApprovalByCol");
        for (Order u : list) {
            List<Task> tasks = engine.getTaskByOrder(u.getId());
            String taskName = tasks.get(0).getTaskName();
            if (tasks.size() != 1 || colFlag.contains(taskName)) {
                return false;
            }
        }
        Map<String, Object> args = new HashMap<>();
        args.put("WF_Memo", "Submit By Program");
        for (Order u : list) {
            List<Task> tasks = engine.getTaskByOrder(u.getId());
            if (tasks.get(0).getTaskName().equals("SubmitByCol")) {
                engine.execute(tasks.get(0).getId(), actorId, args);
            }
        }
        return true;
    }

    /**
     * 撤回order
     *
     * @param args json格式参数，包含user和order
     * @return 是否撤回成功
     */
    @POST
    @Path("/getBack")
    @Consumes("application/json;charset=UTF-8")
    public boolean getBack_beta(HashMap<String, String> args) {
        return engine.setOrderRestart(args.get("order"), args.get("user"));
    }

    @POST
    @Path("/reset/{type}/{entityId}")
    public boolean getBack(@PathParam("type") String type, @PathParam("entityId") String entityId) {
        return engine.resetEntityProcess(type, entityId);
    }

    @POST
    @Path("/reset/{orderId}")
    public boolean resetProcess(@PathParam("orderId") String orderId) {
        return engine.resetEntityProcess(orderId);
    }

    /**
     * 删除order
     *
     * @param order orderId
     * @return 是否删除成功
     */
    @DELETE
    @Path("/ord{order}")
    public Boolean deleteOrder(@PathParam("order") String order) {
//        Order ord = engine.getOrder(order);
        //todo 此处实际上还是需要做个判断的
        try {
            engine.stopOrder(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取所有的新期刊的order
     *
     * @return 分页 order List
     */
    @GET
    @Path("/allMagOrder")
    @Produces("application/json;charset=UTF-8")
    public Map getAllMagOrder(@QueryParam("limit") Integer limit,
                              @QueryParam("offset") Integer offset) {
        return getSubMap(engine.getOrderByProcee("newMag"), limit, offset);
    }

    @GET
    @Path("/fix")
    public String fixIt() {
        engine.fix();
        return "Done";
    }

}
