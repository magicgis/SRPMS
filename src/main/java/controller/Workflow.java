package controller;

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
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DATE:2015/3/8
 * TIME:19:14
 * Created by guofan on 2015/3/8
 */
@Path("/workflow")
@RestController
public class Workflow {
    @Context
    ServletContext context;
    @Autowired
    Engine engine;
    @Autowired
    OrderActorDao orderActorDao;

    @GET
    @Path("/init")
    @Produces("text/plain;charset=UTF-8")
    public String init(){
        List<Process> list = engine.getAllProcess();
        if(list==null||list.size()==0) {
            return engine.initFlows();
        }else{
            return list.get(0).getId();
        }
    }

    @GET
    @Path("/process/{name}")
    @Produces("text/plain;charset=UTF-8")
    public String getProcessName(@PathParam("name")String name){
        return engine.getProcessByName(name).getId();
    }

    @GET
    @Path("/allProcess")
    @Produces("application/json;charset=UTF-8")
    public Map<String,String> getProcessId(){
        Map<String,String> rs = new HashMap<String, String>();
        for(Process u:engine.getAllProcess()){
            rs.put(u.getName(),u.getId());
        }
        return rs;
    }

    @POST
    @Path("/start")
    @Consumes("application/x-www-form-urlencoded;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Order startProcess(MultivaluedMap<String, String> formParams){
        Map<String,String> args = new HashMap<String, String>();
        for(String key:formParams.keySet()){
            args.put(key,formParams.getFirst(key));
        }
        String user = args.get("WF_User");
        args.remove("WF_User");
        String processName = args.get("WF_Process");
        String processId = engine.getProcessByName(processName).getId();
        args.remove("WF_Process");
        /*由于目前仍不能根据用户获取所属学院*/
        args.put("WF_Col","信息工程学院");
        return engine.startInstanceById(processId,user,(Map)args);
    }

    @POST
    @Path("/start")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Order startProcess_beta(HashMap<String,Object> args){
        String user = (String) args.get("WF_User");
        args.remove("WF_User");
        String processName = (String) args.get("WF_Process");
        String processId = engine.getProcessByName(processName).getId();
        args.remove("WF_Process");
        /*由于目前仍不能根据用户获取所属学院*/
        args.put("WF_Col","信息工程学院");
        return engine.startInstanceById(processId,user,(Map)args);
    }

    @POST
    @Path("/execute")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json;charset=UTF-8")
    public List<Task> execute(MultivaluedMap<String, String> formParams){
        Map<String,String> args = new HashMap<String, String>();
        for(String key:formParams.keySet()){
            args.put(key,formParams.getFirst(key));
        }
        String user = args.get("WF_User");
        args.remove("WF_User");
        String taskId = args.get("WF_Task");
        args.remove("WF_Task");
        List<Task> ans = new ArrayList<Task>();
        List<Task> tasks =  engine.execute(taskId, user, (Map) args);
        if(tasks==null||tasks.size()==0) {
            return null;
        }
        for(Task u:tasks){
            u.setModel(null);
            if(u.getTaskName().equals("Confirm")&&engine.isYourTask(u.getId(), user)){
                engine.execute(u.getId(), user, new HashMap<String, Object>());
//                tasks.remove(u);
            }
        }
        return tasks;
    }

    @POST
    @Path("/execute")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public List<Task> execute_beta(HashMap<String,Object> args){
        String user = (String) args.get("WF_User");
        args.remove("WF_User");
        String taskId = (String) args.get("WF_Task");
        args.remove("WF_Task");
        if (args.containsKey("actors")) {
            List<Map<String, Object>> actors = (List<Map<String, Object>>) args.get("actors");
            String as = "";
            for (Map<String, Object> u : actors) {
                as = as + u.get("actor") + ",";
            }
            args.put("WF_Actor",as);
        }
        List<Task> ans = new ArrayList<Task>();
        List<Task> tasks =  engine.execute(taskId, user, (Map) args);
        if(tasks==null||tasks.size()==0) {
            return null;
        }
        for(Task u:tasks){
            u.setModel(null);
            if(u.getTaskName().equals("Confirm")&&engine.isYourTask(u.getId(), user)){
                engine.execute(u.getId(), user, new HashMap<String, Object>());
//                tasks.remove(u);
            }
        }
        return tasks;
    }

    @GET
    @Path("/{user}/task")
    @Produces("application/json;charset=UTF-8")
    public List<Task>  getTask(@PathParam("user")String user){
        return engine.getTaskByActor(user);
    }

    @GET
    @Path("/{user}/hisTask")
    @Produces("application/json;charset=UTF-8")
    public List<HistoryTask> getHisTask(@PathParam("user")String user){
        return engine.getHisTaskByActor(user);
    }

    @GET
    @Path("/{user}/order/major")
    @Produces("application/json;charset=UTF-8")
    public List<Order> getMajorOrder(@PathParam("user")String user){
        return engine.getOrderByActor(user);
    }

    @GET
    @Path("/{user}/order/others")
    @Produces("application/json;charset=UTF-8")
    public List<Order> getOtherOrder(@PathParam("user")String user){
        List<OrderActor> list = orderActorDao.getOrderByActorAndPole(user, 0);
        List<Order> ans = new ArrayList<Order>();
        for(OrderActor u:list){
            ans.add(engine.getOrder(u.getOrder()));
        }
        return ans;
    }

    @GET
    @Path("/{user}/order/all")
    @Produces("application/json;charset=UTF-8")
    public List<Order> getAllOrder(@PathParam("user")String user){
        List<OrderActor> list = orderActorDao.getByActor(user);
        List<Order> ans = new ArrayList<Order>();
        for(OrderActor u:list){
            ans.add(engine.getOrder(u.getOrder()));
        }
        return ans;
    }

    @GET
    @Path("/ord{order}/task")
    @Produces("application/json;charset=UTF-8")
    public List<Task> getTaskByOrder(@PathParam("order")String orderId){
        return engine.getTaskByOrder(orderId);
    }

    @GET
    @Path("/ord{order}/hisTask")
    @Produces("application/json;charset=UTF-8")
    public List<HistoryTask> getHisTaskByOrder(@PathParam("order")String orderId){
        return engine.getHisTaskByOrder(orderId);
    }

    @GET
    @Path("/{user}/confirmTask")
    @Produces("application/json;charset=UTF-8")
    public List<Task> getConfirmTask(@PathParam("user")String user){
        return engine.getConfirmTask(user);
    }

    @POST
    @Path("/submitAll")
    @Produces("text/plain;charset=UTF-8")
    public boolean SubmitAll(@FormParam("WF_User")String user){
        List<Order>list = engine.getOrderByActor(user);
        for(Order u:list){
            List<Task> tasks = engine.getTaskByOrder(u.getId());
            if(tasks.size()!=1||!tasks.get(0).getTaskName().equals("Submit")){
                return false;
            }
        }
        Map<String,Object> args = new HashMap<String, Object>();
        args.put("WF_Memo","Submit By Program");
        for(Order u:list){
            List<Task> tasks = engine.getTaskByOrder(u.getId());
            engine.execute(tasks.get(0).getId(), user, args);
        }
        return true;
    }

    @POST
    @Path("/getBack")
    @Consumes("application/x-www-form-urlencoded")
    public boolean getBack(@FormParam("WF_User")String user,@FormParam("WF_Order")String order){
        return engine.setOrderRestart(order,user);
    }

    @POST
    @Path("/getBack")
    @Consumes("application/json;charset=UTF-8")
    public boolean getBack_beta(HashMap<String,String>args){
        return engine.setOrderRestart(args.get("order"),args.get("user"));
    }

    @DELETE
    @Path("/ord{order}")
    public void deleteOrder(@PathParam("order") String order){
//        Order ord = engine.getOrder(order);
        //todo 此处实际上还是需要做个判断的
        engine.stopOrder(order);
    }

}
