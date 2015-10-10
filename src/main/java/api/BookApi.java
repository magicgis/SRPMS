package api;

import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BaseInfoService;
import service.BookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static util.Trans.getSubMap;
import static util.Trans.putMapOnObj;

/**
 * Created by guofan on 2015/10/11.
 */
@RestController
@Path("/book")
public class BookApi {
    @Autowired
    BookService bookService;
    @Autowired
    BaseInfoService baseInfoService;

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAll(@QueryParam("limit") Integer limit,
                      @QueryParam("offset") Integer offset,
                      @QueryParam("search") String search,
                      @QueryParam("sort") String sort,
                      @QueryParam("order") String ord) {
        return getSubMap(bookService.search(search, sort, ord), limit, offset);
    }

    @POST
    @Path("/book")
    public Serializable add(MultivaluedMap args) {
        HashMap<String, Object> x = new HashMap<>();
        for (Object key : args.keySet()) {
            x.put((String) key, args.getFirst(key));
        }
        Book book;
        if ("".equals(args.getFirst("id"))) {
            book = new Book();
            book.setProcess("0");//表示刚填表
        }
        else {
            book = bookService.getById((Serializable) args.getFirst("id"));
        }
        putMapOnObj(book, x);
//        book.setStandard(standardInfoService.getById((Serializable) args.getFirst("standard.id")));
        book.setDept(baseInfoService.getById((Serializable) args.getFirst("dept.id")));
        if ("".equals(book.getId())) {
            book.setId(null);
            return bookService.add(book);
        }
        else {
            bookService.update(book);
            return book.getId();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean update(@PathParam("id") String id, HashMap<String, Object> args) {
        Book book = bookService.getById(id);
        book.setArgMap(args);
        return bookService.update(book);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public boolean delete(@PathParam("id") String id) {
        return false;//todo
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json;charset=UTF-8")
    public Object getById(@PathParam("id") String id) {
        return null;//todo
    }
}
