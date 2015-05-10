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
import javax.ws.rs.core.*;
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
        uriInfo.getMatchedTemplates();
        List<UriTemplate> matchedTemplates = uriInfo.getMatchedTemplates();
        /*请求模板*/
        StringBuilder path = new StringBuilder();
        for (int i = matchedTemplates.size() - 1; i >= 0; i--) {
            path.append(matchedTemplates.get(i).getTemplate());
        }
        /*请求类型*/
        String type = requestContext.getMethod();
        urlService.addUrl(path.toString(), type, null);
        /*获取token*/
        String token = requestContext.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (TokenUser == null) {
            TokenUser = new CrunchifyInMemoryCache<>(3600, 300, 3000);
        }
        if (TokenUser.get(token) != null) {
            if (TokenUser != null) {
                User loginUser = TokenUser.get(token);
                String role = loginUser.getPrivilege();
                Permission x = new Permission();
                if (PermissionCache == null) {
                    PermissionCache = new CrunchifyInMemoryCache<>(0, 0, 3000);
                    permissionService.loadIntoCache();
                }
                Boolean flag = PermissionCache.get(role + "-" + path + "-" + type);
                if (flag == null || flag == false) {
                    requestContext.abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("User cannot access the resource.")
                            .build());
                }
            } else {
                TokenUser = new CrunchifyInMemoryCache<>(3600, 300, 3000);
                requestContext.abortWith(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("User cannot access the resource.")
                        .build());
            }
        } else {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build());
        }
    }
}
