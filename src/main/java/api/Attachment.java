package api;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by guofan on 4/24/2015.
 */
@Path("/file")
public class Attachment {
//    @Context
//    ServletContext context;

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
        /*上传到的位置，此处不应该信任传来的文件名 TODO*/
        File uploadedFileLocation = new File(upload, fileDetail.getFileName());
        // save it
        if (writeToFile(uploadedInputStream, uploadedFileLocation)) {
            //把保存的信息和上传的人，以及各种关联起来。
        }
        String output = "File uploaded to : " + uploadedFileLocation.toString();
        System.out.println(uploadedFileLocation.getAbsolutePath());

        return Response.status(200).entity(output).build();
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
    @Path("/download/{id}")
//    @Produces({"application/vnd.ms-word"})
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
        File file = new File(upload, "t.sql");
        return Response.ok(file).header("Content-Disposition", "attachment;filename=" + "t.sql").build();
    }

}
