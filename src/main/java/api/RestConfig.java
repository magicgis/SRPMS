package api;

import api.filter.All;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import util.CrunchifyInMemoryCache;
import util.StaticFactory;

import java.io.File;
import java.net.URL;

import static util.Args.StrorePath;
import static util.Args.TokenUser;

/**
 * DATE:2015/1/22
 * TIME:21:59
 * Created by guofan on 2015/1/22
 * Jersey 框架配置
 */
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(MultiPartFeature.class);
        register(All.class);
        packages("api");
        /*可以在这儿初始化大部分需要初始化的对象*/

        /*用户认证Cache*/
        TokenUser = new CrunchifyInMemoryCache<>(3600, 300, 3000);

        /*存储目录*/
                /*获取class路径*/
        URL path = Thread.currentThread().getContextClassLoader().getResource("");
        File classes = new File(path.getPath());
                /*上一级目录 其实放在了与webapp同级目录*/
        File tomcat = classes.getParentFile().getParentFile().getParentFile().getParentFile();
                /*upload文件夹*/
        StrorePath = new File(tomcat, "upload");
        if (!StrorePath.exists()) {
            StrorePath.mkdir();
        }

        /*静态工厂初始化*/
        StaticFactory.init();

    }
}
