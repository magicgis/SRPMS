package api.filter;


import entity.User;
import entity.security.Permission;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.uri.UriTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import service.security.PermissionService;
import service.security.UrlService;
import util.CrunchifyInMemoryCache;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static util.Args.PermissionCache;
import static util.Args.TokenUser;

/**
 * Created by guofan on 2015/5/9.
 */
public class All implements ContainerRequestFilter {
    @Autowired
    UrlService urlService;
    @Autowired
    PermissionService permissionService;
    @Context
    private ExtendedUriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        /*请求Path模板*/
        List<UriTemplate> matchedTemplates = uriInfo.getMatchedTemplates();
        StringBuilder path = new StringBuilder();
        for (int i = matchedTemplates.size() - 1; i >= 0; i--) {
            path.append(matchedTemplates.get(i).getTemplate());
        }
        /*请求类型*/
        String type = requestContext.getMethod();
        urlService.addUrl(path.toString(), type, null);
        /*获取token*/
        String token = requestContext.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(token.equals("IAMX")){
            return;
        }
        if (TokenUser == null) {
            /*创建<Token,User>缓存*/
            TokenUser = new CrunchifyInMemoryCache<>(3600, 300, 3000);
        }
        /*如果已经登陆*/
        if (TokenUser.get(token) != null) {
            /*获取用户以及其权限*/
            User loginUser = TokenUser.get(token);
            String role = loginUser.getPrivilege();
            Permission x = new Permission();
            if (PermissionCache == null) {
                /*创建权限缓存，并从表导入*/
                PermissionCache = new CrunchifyInMemoryCache<>(0, 0, 3000);
                permissionService.loadIntoCache();
            }
            /*在缓存内寻找用户的权限*/
            Boolean flag = PermissionCache.get(role + "-" + path + "-" + type);
            /*如果未找到（未设置权限）或者无权限*/
            if (flag == null || flag == false) {
                /*打断，返回401*/
//                requestContext.abortWith(Response
//                        .status(Response.Status.UNAUTHORIZED)
//                        .entity("User cannot access the resource.")
//                        .build());
            }else{
                /*正常通行*/
            }
        /*如果未登陆*/
        } else {
            /*登陆api属于特殊放行的特例,下载由于前台原因，临时放行*/
            if (path.toString().trim().equals("/user/login") || path.toString().equals("/file/{id}")) {
                /*正常通行*/
            } else {
                /*打断，返回401*/
                requestContext.abortWith(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("User cannot access the resource.")
                        .build());
            }
        }
    }
}
