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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        //create path components to save the file
        final Part filePart = request.getPart("imagen");
        final String fileName = (String) getFileName(filePart);
        final String path ="/tmp";
        OutputStream outta = null;
        InputStream filecontent = null;
        PrintWriter out = response.getWriter();
        
        
        try {
            
            
            OurDao.startDB(); 
            
            outta = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();
            
            int read = 0;
            final byte[] bytes = new byte[1024];
       
            while((read = filecontent.read(bytes)) != -1){
                outta.write(bytes, 0, read);
            }
             
                   
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String clave = request.getParameter("clave");
            String author = request.getParameter("author");
            String fechaC = request.getParameter("fechaC");
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
            String fechaS = dateFormat.format(date); 
                    
        
            OurDao.enregistrar( titulo, descripcion, clave, author, fechaC, fechaS, fileName ); 
            
            out.println("New file " + fileName + "created at " + path + "<br><br>");   
            out.println("<a href=\"menu.jsp\">Vuelve al Menu</a>");
            
    } catch (FileNotFoundException fne){
            out.println("\"Faltan campos por añadir.");
            out.println("<br/> ERROR: " + fne.getMessage());

    } catch (IOException | ClassNotFoundException | SQLException e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
    }
        finally {
            OurDao.stopDB();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
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