/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Samuel
 */
@WebService(serviceName = "ImageWS")
public class ImageWS {

    /**
     * Web service operation
     *
     * @param image
     * @return
     */
    @WebMethod(operationName = "RegistrerImage")
    public int RegistrerImage(@WebParam(name = "image") Image image) {
        if (image.getFileName() == null || image.getFileName().isEmpty()) {
            //throw new FileNotFoundException();
        }

        String basepath = ImageWS.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        //CAMBIAR POR CARPETA CORRECTA
        basepath = basepath.substring(0, basepath.lastIndexOf("adlab"));
        final String path = basepath + "adlab/web/images";
        //OutputStream outta = null;
        //InputStream filecontent = null;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        String fechaS = dateFormat.format(date);

        try {

            OurDao.startDB();

            image.setId(OurDao.enregistrar(image.getTitle(), image.getDescription(), image.getKeywords(), image.getAuthor(), image.getCreationDate(), fechaS, image.getFileName()));

            /*outta = new FileOutputStream(new File(path + File.separator + selectImage.getImageName(id, fileName)));
            /filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                outta.write(bytes, 0, read);
            }
            */
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        } /*finally {

            if (outta != null) {
                outta.close();
            }
            if (filecontent != null) {
                filecontent.close();

            }
            if (out != null) {
                out.close();

            }
        }*/
        return image.getId();
    }

    /*
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }*/

    /**
     * Web service operation
     * @param image
     * @return 
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @param image
     * @return 
     */
    @WebMethod(operationName = "DeleteImage")
    public int DeleteImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        return 0;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "ListImages")
    public List ListImages() {
        
        ArrayList<Image> list = new ArrayList<>();
        try {
            OurDao.startDB();
        } catch(ClassNotFoundException | SQLException e) {
            
        }
        <table>
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Palabras Clave</th>
                <th>Autor</th>
                <th>Fecha de creacion</th>
                <th>Fecha de subida</th>
                <th>Nombre del archivo</th>
            </tr>
            <% 
                try{
                    OurDao.startDB();
                    ResultSet rs = OurDao.getAllImages();
                    while(rs.next()) {
            %>
            <tr>
                <td><%  out.println(rs.getString("TITLE"));%></td>
                <td><%  out.println(rs.getString("DESCRIPTION"));%></td>
                <td><%  out.println(rs.getString("KEYWORDS"));%></td>
                <td><%
                        String autor = rs.getString("AUTHOR");
                        out.println(autor);
                    %></td>
                <td><%  out.println(rs.getString("CREATION_DATE"));%></td>
                <td><%  out.println(rs.getString("STORAGE_DATE"));%></td>
                <%
                        String filename = rs.getString("FILENAME");
                        int id = rs.getInt("ID");%>
                <td>
                    <%
                            out.println("<a href=image.jsp?name="+filename+"&id="+id+">"+filename+"</a>");
                            if(autor.equals(user)){
                    %>
                    <form action=selectImage method="POST">
                        <input type="hidden" value="<%out.print(filename);%>" name="name"/>
                        <input type="hidden" value="<%out.print(id);%>" name="id"/>
                        <input type="submit" value="Modificar" name="action"/>
                        <input type="submit" value="Eliminar" name="action"/>
                    </form>
                </td>
            </tr>
            <%          }
                    }
                    OurDao.stopDB();
                } catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
            %>
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyId")
    private Image SearchbyId(@WebParam(name = "id") int id) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param title
     * @return 
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param creaDate
     * @return 
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param author
     * @return 
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "author") String author) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param keywords
     * @return 
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public List SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        //TODO write your implementation code here:
        return null;
    }
}
