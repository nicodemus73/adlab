/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 *
 * @author mo
 */
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {
    
    int x=1;
   

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //create path components to save the file
        final Part filePart = request.getPart("imagen");
        final String fileName = (String) getFileName(filePart);
        final String path ="/tmp";
        OutputStream outta = null;
        InputStream filecontent = null;
        PrintWriter out = response.getWriter();
        
        
        try {
            String query; 
            PreparedStatement statement; 
            
            OurConnection.startDB(); 
            
            outta = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();
            
            int read = 0;
            final byte[] bytes = new byte[1024];
       
            while((read = filecontent.read(bytes)) != -1){
                outta.write(bytes, 0, read);
            }
            out.println("New file " + fileName + "created at " + path);    
                   
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String clave = request.getParameter("clave");
            String author = request.getParameter("author");
            String fechaC = request.getParameter("fechaC");
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
            String fechaS = dateFormat.format(date); 
            
            query = "select id from image";
            statement = OurConnection.connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
           
            query = "insert into IMAGE  values(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = OurConnection.connection.prepareStatement(query);
            statement.setInt(1, x+1);
            statement.setString(2, titulo);
            statement.setString(3, descripcion);
            statement.setString(4, clave);
            statement.setString(5, author);
            statement.setString(6,fechaC);
            statement.setString(7, fechaS);
            statement.setString(8, fileName);
            statement.executeUpdate();
            
            

            
        
            
    } catch (FileNotFoundException fne){
            out.println("\"You either did not specify a file to upload or are \"\n" +
"                + \"trying to upload a file to a protected or nonexistent \"\n" +
"                + \"location.");
            out.println("<br/> ERROR: " + fne.getMessage());

    } catch (IOException | ClassNotFoundException | SQLException e) {
        System.err.println(e.getMessage());
    }
        finally {
            OurConnection.stopDB();
            if (outta != null){
                outta.close();
            }
            if (filecontent != null){
                outta.close();
                
            }
            if (out != null){
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
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}



