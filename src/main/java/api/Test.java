//package api;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import java.io.File;
//import java.net.URL;
//
///**
// * Created by guofan on 4/24/2015.
// */
//@Path("/TEST")
//public class Test {
//    @GET
//    @Path("/1")
//    @Produces("text/plain;charset=UTF-8")
//    public String test() {
//        URL path = Thread.currentThread().getContextClassLoader().getResource("");
//        File classes = new File(path.getPath());
//        File web_inf = classes.getParentFile();
//        File webapp = web_inf.getParentFile();
//        for (String s : webapp.list()) {
//            System.out.println(s);
//        }
//        return webapp.toString();
//    }
//}
