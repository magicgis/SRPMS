//package api;
//
//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;
//
//import javax.servlet.ServletContext;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.*;
//
///**
// * Created by guofan on 4/24/2015.
// */
////@Path("/file")
//public class Attachment {
//    @Context
//    ServletContext context;
//
//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(
//            @FormDataParam("file") InputStream uploadedInputStream,
//            @FormDataParam("file") FormDataContentDisposition fileDetail) {
//
//        String path = System.getProperty("user.dir");
//        System.out.println(path);
//        String uploadedFileLocation = "d://uploaded/" + fileDetail.getFileName();
//
//        // save it
//        writeToFile(uploadedInputStream, uploadedFileLocation);
//
//        String output = "File uploaded to : " + uploadedFileLocation;
//
//        return Response.status(200).entity(output).build();
//
//    }
//
//    // save uploaded file to new location
//    private void writeToFile(InputStream uploadedInputStream,
//                             String uploadedFileLocation) {
//
//        try {
//            OutputStream out = new FileOutputStream(new File(
//                    uploadedFileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
