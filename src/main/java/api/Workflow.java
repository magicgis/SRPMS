package api;

import engine.Engine;
import engine.entity.OrderActor;
import engine.entity.OrderActorDao;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
        } else {
            return "DONE";
        }
    }

    /**
     * 获取对应名称process的id
     *
     * @param name process名称
     * @return process Id
     */
    @GET
    @Path("/process/{name}")
    @Produces("text/plain;charset=UTF-8")
    public String getProcessName(@PathParam("name") String name) {
        return engine.getProcessByName(name).getId();
    }

    /**
     * 获取所有process Id
     *
     * @return 所有的process id
     */
    @GET
    @Path("/allProcess")
    @Produces("application/json;charset=UTF-8")
    public Map<String, String> getProcessId() {
        Map<String, String> rs = new HashMap<>();
        for (Process u : engine.getAllProcess()) {
            rs.put(u.getName(), u.getId());
        }
        return rs;
    }


    /**
     * 启动流程
     *
     * @param args json格式的参数
     * @return 生成的order
     */
    @POST
    @Path("/start")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Order startProcess_beta(HashMap<String, Object> args) {
        String user = (String) args.get("WF_User");
        args.remove("WF_User");
        String processName = (String) args.get("WF_Process");
        String processId = engine.getProcessByName(processName).getId();
        args.remove("WF_Process");
        return engine.startInstanceById(processId, user, args);
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
    public List<Task> execute_beta(HashMap<String, Object> args) {
        String user = (String) args.get("WF_User");
        args.remove("WF_User");
        String taskId = (String) args.get("WF_Task");
        args.remove("WF_Task");
        if (args.containsKey("actors")) {
            List<Map<String, Object>> actors = (List<Map<String, Object>>) args.get("actors");
            List<String> aList = new ArrayList<>();
            StringBuffer as = new StringBuffer();
            StringBuffer actosStr = new StringBuffer();
            for (Map<String, Object> u : actors) {
                String aId = (String) u.get("id");
                if (aList.contains(aId)) {
                    continue;
                }
                aList.add(aId);
                as.append(aId).append(",");
                actosStr.append(u.get("actor")).append(",");
            }
            args.put("WF_Actor", as);
            args.put("ActorList", actosStr);
        }
        List<Task> ans = new ArrayList<>();
        List<Task> tasks = engine.execute(taskId, user, (Map) args);
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
     * 获取用户所有已完成任务
     *
     * @param user 用户
     * @return 已完成任务
     */
    @GET
    @Path("/{user}/hisTask")
    @Produces("application/json;charset=UTF-8")
    public List<HistoryTask> getHisTask(@PathParam("user") String user) {
        return engine.getHisTaskByActor(user);
    }


    /**
     * 获取用户各种类型的order
     *
     * @param user   用户
     * @param type   order类型，如book，paper
     * @param member 是主导还是参与
     * @param limit  获取数量（分页用）
     * @param offset 起始数量（分页用）
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
        Integer role = null;
        if (member.equals("1st")) {
            role = 1;
        } else if (member.equals("2nd")) {
            role = 0;
        }
        if (type.equals("all")) {
            type = null;
        }
        List<OrderActor> list = orderActorDao.getByAll(user, type, role);
        List<Order> ans = new ArrayList<>();
        for (OrderActor u : list) {
            ans.add(engine.getOrder(u.getOrder()));
        }
        return getSubMap(ans, limit, offset);
    }


    /**
     * 获取order的最新任务
     *
     * @param orderId orderid
     * @return 该order发展到的最新任务
     */
    @GET
    @Path("/ord{order}/task")
    @Produces("application/json;charset=UTF-8")
    public List<Task> getTaskByOrder(@PathParam("order") String orderId) {
        return engine.getTaskByOrder(orderId);
    }

    /**
     * 获取order已完成的任务列表
     *
     * @param orderId orderId
     * @return 已完成列表
     */
    @GET
    @Path("/ord{order}/hisTask")
    @Produces("application/json;charset=UTF-8")
    public List<HistoryTask> getHisTaskByOrder(@PathParam("order") String orderId) {
        return engine.getHisTaskByOrder(orderId);
    }

    /**
     * 获取用户待确认任务
     *
     * @param user 用户
     * @return 待确认任务列表
     */
    @GET
    @Path("/{user}/confirmTask")
    @Produces("application/json;charset=UTF-8")
    public Map getConfirmTask(@PathParam("user") String user,
                              @QueryParam("limit") Integer limit,
                              @QueryParam("offset") Integer offset) {
        return getSubMap(engine.getConfirmTask(user), limit, offset);
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
        for (Order u : list) {
            List<Task> tasks = engine.getTaskByOrder(u.getId());
            engine.execute(tasks.get(0).getId(), user, args);
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
        List<Order> list = engine.getAllOrderByActor(user);
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
            engine.execute(tasks.get(0).getId(), user, args);
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

}
