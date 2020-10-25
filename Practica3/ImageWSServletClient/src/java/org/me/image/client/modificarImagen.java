/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mo
 * 
 * 
public class LoginFilter implements Filter 
 */
@WebServlet(name = "modificarImagen", urlPatterns = "/modificarImagen")
public class modificarImagen extends HttpServlet {

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
        HttpSession ses = request.getSession(false);
        if(ses.getAttribute("user") == null) response.sendRedirect("login.jsp");
        else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
            
                OurDao.startDB(); 
                String titulo = request.getParameter("titulo");
                String descripcion = request.getParameter("descripcion");
                String clave = request.getParameter("clave");
                String fechaC = request.getParameter("fechaC");
                String fN = request.getParameter("fileN");
      
                
                int id = (int) ses.getAttribute("imageId");
               
                
                boolean ok = OurDao.enregistrarCanvi(titulo, descripcion, clave, fechaC, fN, id);
                if (ok){
                    out.println("<p>El cambio se ha efectuado correctamente</p>");
                    out.println("<a href=\"menu.jsp\">Vuelve al Menu</a>");
                }
                else {
                    out.println("No s'ha efectuat correctament");
                    out.println("<a href=\"menu.jsp\">Vuelve al Menu</a>");

                }
                    
            } catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
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
