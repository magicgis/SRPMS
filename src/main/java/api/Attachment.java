package api;

import entity.Data;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import service.DataService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guofan on 4/24/2015.
 */
@Path("/file")
public class Attachment {
    @Context
    ServletContext context;
    @Autowired
    DataService dataService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
        /*获取class路径*/
        URL path = Thread.currentThread().getContextClassLoader().getResource("");
        File classes = new File(path.getPath());
        /*上一级目录*/
        File web_inf = classes.getParentFile();
        /*webapp目录，即根目录*/
        File webapp = web_inf.getParentFile();
        /*upload文件夹*/
        File upload = new File(web_inf, "upload");
        if (!upload.exists()) {
            upload.mkdir();
        }
        String fileName = fileDetail.getFileName();
        /*必要时需要对后缀名进行拦截*/
        String end = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String finalFileName = df.format(new Date()) + "-" + String.valueOf(System.currentTimeMillis() % 1000) + end;
        File uploadedFileLocation = new File(upload, finalFileName);
        String fileId = null;
        if (writeToFile(uploadedInputStream, uploadedFileLocation)) {
            Data up = new Data();
            up.setPath(finalFileName);
            fileId = (String) dataService.add(up);
        }
        return Response.status(200).entity(fileId).build();
    }

    // save uploaded file to new location
    private boolean writeToFile(InputStream uploadedInputStream,
                                File uploadedFileLocation) {
        try {
            OutputStream out;
            new FileOutputStream(new File(
                    uploadedFileLocation.toString()));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation.toString()));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GET
    @Path("/{id}")
    public Response downloadFile(@PathParam("id") String id) {
        /*获取class路径*/
        URL path = Thread.currentThread().getContextClassLoader().getResource("");
        File classes = new File(path.getPath());
        /*上一级目录*/
        File web_inf = classes.getParentFile();
        /*webapp目录，即根目录*/
        File webapp = web_inf.getParentFile();
        /*upload文件夹*/
        File upload = new File(web_inf, "upload");
        /*只是一个示范*/
        String fileName = dataService.getById(id).getPath();
        File file = new File(upload, fileName);
        return Response.ok(file).header("Content-Disposition", "attachment;filename=" + fileName).build();
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteFile(@PathParam("id") String id) {
        /*todo 这儿需要对用户的判断*/
        /*获取class路径*/
        URL path = Thread.currentThread().getContextClassLoader().getResource("");
        File classes = new File(path.getPath());
        /*上一级目录*/
        File web_inf = classes.getParentFile();
        /*webapp目录，即根目录*/
        File webapp = web_inf.getParentFile();
        /*upload文件夹*/
        File upload = new File(web_inf, "upload");
        Data data = dataService.getById(id);
        String fileName = data.getPath();
        File file = new File(upload, fileName);
        if (file.exists()) {
            return dataService.delete(data) && file.delete();
        } else {
            return false;
        }
    }

}
