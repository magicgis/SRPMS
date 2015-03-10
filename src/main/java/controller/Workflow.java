package controller;

import engine.Engine;
import engine.entity.EasyTask;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
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

    @GET
    @Path("/init")
    @Produces("application/json;charset=UTF-8")
    public String init(){
        return engine.initFlows();
    }

    @POST
    @Path("/process")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json;charset=UTF-8")
    public Order startProcess(MultivaluedMap<String, String> formParams){
        Map<String,String> args = new HashMap<String, String>();
        for(String key:formParams.keySet()){
            args.put(key,formParams.getFirst(key));
        }
        String user = args.get("WF-User");
        args.remove("WF-User");
        String processId = args.get("WF-Process");
        args.remove("WF-Process");
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
        String user = args.get("WF-User");
        args.remove("WF-User");
        String taskId = args.get("WF-TaskId");
        args.remove("WF-TaskId");
        return engine.execute(taskId, user, (Map)args);
    }

    @GET
    @Path("/{user}/task")
    @Produces("application/json;charset=UTF-8")
    public List<Task> getTask(@PathParam("user")String user){
        return engine.getTaskByActor(user);
    }

    @GET
    @Path("/{user}/hisTask")
    @Produces("application/json;charset=UTF-8")
    public List<HistoryTask> getHisTask(@PathParam("user")String user){
        return engine.getHisTaskByActor(user);
    }

    @GET
    @Path("/{user}/order")
    @Produces("application/json;charset=UTF-8")
    public List<Order> getOrder(@PathParam("user")String user){
        return engine.getOrderByActor(user);
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


}
