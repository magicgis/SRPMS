package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BasepaperService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;

import static util.Trans.getSubMap;

/**
 * Created by yxm on 2015/5/15.
 */

@Path("/basepaper")
@RestController
public class BasepaperApi {
    @Autowired
    BasepaperService basepaperService;

    @GET
    @Path("/all")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        return getSubMap(basepaperService.search(search, sort, ord), limit, offset);
    }
}
