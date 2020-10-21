/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

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
        if (image.fileName == null || image.fileName.isEmpty()) {
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

        try {

            OurDao.startDB();

            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String clave = request.getParameter("clave");
            String author = request.getParameter("author");
            String fechaC = request.getParameter("fechaC");
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
            String fechaS = dateFormat.format(date);

            int id = OurDao.enregistrar(titulo, descripcion, clave, author, fechaC, fechaS, fileName);

            outta = new FileOutputStream(new File(path + File.separator + selectImage.getImageName(id, fileName)));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                outta.write(bytes, 0, read);
            }

            out.println("New file " + fileName + " created at " + path + "<br><br>");
            out.println("<a href=\"menu.jsp\">Vuelve al Menu</a>");
            OurDao.stopDB();

        } catch (FileNotFoundException fne) {
            out.println("No has especificado una imagen a subir");

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        } finally {

            if (outta != null) {
                outta.close();
            }
            if (filecontent != null) {
                filecontent.close();

            }
            if (out != null) {
                out.close();

            }
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
