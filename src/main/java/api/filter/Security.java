package api.filter;

import com.sun.jersey.core.util.ReaderWriter;
import org.glassfish.jersey.server.ContainerException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static util.Args.TokenUser;

/**
 * Created by guofan on 2015/5/7.
 */
@Auth
public class Security implements ContainerRequestFilter {

    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        if(TokenTime==null){
//            TokenTime = new HashMap<>();
//        }
//        if(TokenUser==null){
//            TokenUser = new HashMap<>();
//        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();
        final StringBuilder b = new StringBuilder();
        try {
            if (in.available() > 0) {
                ReaderWriter.writeTo(in, out);
                byte[] requestEntity = out.toByteArray();
//                out.
                String temp = out.toString();
//                printEntity(b, requestEntity);
                requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
            }
//            return requestContext;
        } catch (IOException ex) {
            throw new ContainerException(ex);
        }


        if (TokenUser.get(token) != null) {
            Long time = System.currentTimeMillis() / 1000;


            TokenUser.remove(token);
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build());

        } else {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build());

        }
    }
}
