/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        String fechaS = dateFormat.format(date);

        try {

            OurDao.startDB();

            image.id = OurDao.enregistrar(image.title, image.description, image.keywords, image.author, image.creationDate, fechaS, image.fileName);

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
        return image.id;
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
        ArrayList<Image> lista = new ArrayList<>();
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyId")
    private Image SearchbyId(@WebParam(name = "id") int id) {
        Image tmp = new Image();
        try {
            //TODO write your implementation code here:
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "%"+id+"%");//controlar el sql injection
            OurDao.startDB(); // els stopDB shan de posar? 
            ResultSet rs;
            rs = OurDao.consultar(map);
            if (rs.next()) {
                tmp.fileName = rs.getString("filename");
                tmp.id = rs.getInt("id");
                tmp.title = rs.getString("titol");
                tmp.description = rs.getString("descripcio");
                tmp.keywords = rs.getString("tags");
                tmp.author = rs.getString("autor");
                tmp.creationDate = rs.getString("datac");
            }
            OurDao.stopDB();
            //else q retorno si la base de datos no hay nada?
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImageWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        //q pasa si la imagen es vacia
        return tmp;
    }

    /**
     * Web service operation
     * @param title
     * @return 
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "%"+title+"%");//controlar el sql ijection
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                Image tmp = new Image();
                tmp.fileName = rs.getString("filename");
                tmp.id = rs.getInt("id");
                tmp.title = rs.getString("titol");
                tmp.description = rs.getString("descripcio");
                tmp.keywords = rs.getString("tags");
                tmp.author = rs.getString("autor");
                tmp.creationDate = rs.getString("datac");
                lista.add(tmp);
            }
            OurDao.stopDB();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImageWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista; //que pasa si esta vacia
    }

    /**
     * Web service operation
     * @param creaDate
     * @return 
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate) {
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "%"+creaDate+"%");//controlar el sql ijection
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                Image tmp = new Image();
                tmp.fileName = rs.getString("filename");
                tmp.id = rs.getInt("id");
                tmp.title = rs.getString("titol");
                tmp.description = rs.getString("descripcio");
                tmp.keywords = rs.getString("tags");
                tmp.author = rs.getString("autor");
                tmp.creationDate = rs.getString("datac");
                lista.add(tmp);
            }
            OurDao.stopDB();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImageWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista; //que pasa si esta vacia
    }

    /**
     * Web service operation
     * @param author
     * @return 
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "author") String author) {
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "%"+author+"%");//controlar el sql ijection
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                Image tmp = new Image();
                tmp.fileName = rs.getString("filename");
                tmp.id = rs.getInt("id");
                tmp.title = rs.getString("titol");
                tmp.description = rs.getString("descripcio");
                tmp.keywords = rs.getString("tags");
                tmp.author = rs.getString("autor");
                tmp.creationDate = rs.getString("datac");
                lista.add(tmp);
            }
            OurDao.stopDB();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImageWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista; //que pasa si esta vacia
    }

    /**
     * Web service operation
     * @param keywords
     * @return 
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public List SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "%"+keywords+"%");//controlar el sql ijection
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                Image tmp = new Image();
                tmp.fileName = rs.getString("filename");
                tmp.id = rs.getInt("id");
                tmp.title = rs.getString("titol");
                tmp.description = rs.getString("descripcio");
                tmp.keywords = rs.getString("tags");
                tmp.author = rs.getString("autor");
                tmp.creationDate = rs.getString("datac");
                lista.add(tmp);
            }
            OurDao.stopDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImageWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista; //que pasa si esta vacia
    }
}
