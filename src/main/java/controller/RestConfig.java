package controller;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

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
//        register(AuthorizationRequestFilter.class);
        packages("controller");
    }
}
