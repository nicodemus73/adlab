/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.me.image.ImageWS_Service;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "selectImage", urlPatterns = {"/selectImage"})
public class selectImage extends HttpServlet {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ImageWSApplication/ImageWS.wsdl")
    private ImageWS_Service service;

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
        HttpSession ses = request.getSession(false);
        if(ses.getAttribute("user") == null) response.sendRedirect("login.jsp");
        else {
            int id = Integer.parseInt(request.getParameter("id"));
            ses.setAttribute("imageId", id);
            ses.setAttribute("imageName", request.getParameter("name"));
            String action = request.getParameter("action");
            if("Modificar".equals(action)) response.sendRedirect("modificarImagen.jsp");
            else if("Eliminar".equals(action)) response.sendRedirect("eliminarImagen.jsp");
            else response.sendRedirect("error.jsp");
        }
    }
    
    public static String getImageName(int id, String filename){
        
        if(filename==null || filename.isEmpty()) return null;
        String[] splitted = filename.split("\\.");
        if(splitted.length != 2){
            System.err.println("Nombre de archivo incompatible: "+ filename +" Tamaño: "+splitted.length);
            return null;
        }
        return splitted[0] + Integer.toString(id) + '.' + splitted[1];
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
