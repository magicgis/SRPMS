package api;

import entity.Attachment;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.AttachmentService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.Args.StrorePath;

/**
 * Created by guofan on 4/24/2015.
 */
@Path("/file")
@RestController
public class AttachmentApi {
    @Context
    ServletContext context;
    @Autowired
    AttachmentService attachmentService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
        String fileName = fileDetail.getFileName();
        /*必要时需要对后缀名进行拦截*/
        String end = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String finalFileName = df.format(new Date()) + "-" + String.valueOf(System.currentTimeMillis() % 1000) + end;
        File uploadedFileLocation = new File(StrorePath, finalFileName);
        String fileId = null;
        if (writeToFile(uploadedInputStream, uploadedFileLocation)) {
            Attachment up = new Attachment();
            up.setPath(finalFileName);
            fileId = (String) attachmentService.add(up);
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
    @Produces("application/octet-stream")
    public Response downloadFile(@PathParam("id") String id) {
        /*只是一个示范*/
        String fileName = attachmentService.getById(id).getPath();
        File file = new File(StrorePath, fileName);
        return Response.ok(file).header("Content-Disposition", "attachment;filename=" + fileName).build();
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteFile(@PathParam("id") String id) {
        /*todo 这儿需要对用户的判断*/
        Attachment data = attachmentService.getById(id);
        String fileName = data.getPath();
        File file = new File(StrorePath, fileName);
        if (file.exists()) {
            return attachmentService.delete(data) && file.delete();
        }
        else {
            return false;
        }
    }

}
