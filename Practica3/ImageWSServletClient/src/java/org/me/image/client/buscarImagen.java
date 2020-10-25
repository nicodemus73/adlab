/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.me.image.ImageWS_Service;

/**
 *
 * @author elchu
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
@MultipartConfig

public class buscarImagen extends HttpServlet {

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
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession ses = request.getSession(false);
        String user = (String) ses.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                HashMap<String, String> map = new HashMap<>();
                if (!"".equals(request.getParameter("title"))) {
                    map.put("title", "%" + request.getParameter("title") + "%");//
                }
                if (!"".equals(request.getParameter("description"))) {
                    map.put("description", "%" + request.getParameter("description") + "%");
                }
                if (!"".equals(request.getParameter("keywords"))) {
                    map.put("keywords", "%" + request.getParameter("keywords") + "%");
                }
                if (!"".equals(request.getParameter("author"))) {
                    map.put("author", "%" + request.getParameter("author") + "%");
                }
                if (!"".equals(request.getParameter("cdate"))) {
                    map.put("cdate", "%" + request.getParameter("cdate") + "%");
                }
                if (!"".equals(request.getParameter("filename"))) {
                    map.put("filename", "%" + request.getParameter("filename") + "%");
                }
                //afageixo el % per buscar patrons, paraules dintre de paraules

                if (map.isEmpty()) {

                    out.println("No hay resultados con las entradas correspondientes");
                    out.print("<br><br>");
                    //boton menu
                    String resp = "<a href=\"menu.jsp\">Menu principal</a>";
                    out.println(resp);
                    out.print("<br><br>");
                    resp = "<a href=\"buscarImagen.jsp\">Buscar una nueva imagen</a>";
                    //boton busqueda
                    out.println(resp);
                    out.print("<br><br>");
                } else {
                    ResultSet rs;
                    //rs = OurDao.consultar(map);
                    out.println("Listado de imagenes: <br>");

                    out.println("<table>\n"
                            + "            <tr>\n"
                            + "                <th>Titulo</th>\n"
                            + "                <th>Descripcion</th>\n"
                            + "                <th>Palabras Clave</th>\n"
                            + "                <th>Autor</th>\n"
                            + "                <th>Fecha de creacion</th>\n"
                            + "                <th>Fecha de subida</th>\n"
                            + "                <th>Nombre del archivo</th>\n"
                            + "            </tr>");
                    /*while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getString("TITLE") + "</td>");
                        out.println("<td>" + rs.getString("DESCRIPTION") + "</td>");
                        out.println("<td>" + rs.getString("KEYWORDS") + "</td>");
                        String autor = rs.getString("AUTHOR");
                        out.println("<td>" + autor + "</td>");
                        out.println("<td>" + rs.getString("CREATION_DATE") + "</td>");
                        out.println("<td>" + rs.getString("STORAGE_DATE") + "</td>");
                        String filename = rs.getString("FILENAME");
                        int id = rs.getInt("ID");
                        out.println("<td><a href=image.jsp?name=" + filename + "&id=" + id + ">" + filename + "</a>");
                        if (autor.equals(user)) {
                            out.println("<form action=selectImage method=\"POST\">"
                                    + "<input type=\"hidden\" value=\"" + filename + "\" name=\"name\"/>"
                                    + "<input type=\"hidden\" value=\"" + Integer.toString(id) + "\" name=\"id\"/>"
                                    + "<input type=\"submit\" value=\"Modificar\" name=\"action\"/>"
                                    + "<input type=\"submit\" value=\"Eliminar\" name=\"action\"/> </form> </td>");
                        }
                    }*/
                    out.println("</table>");
                    out.println("<a href=\"buscarImagen.jsp\">Hacer otra busqueda</a><br><br>");
                    out.println("<a href=\"menu.jsp\">Vuelve al menu</a>");
                }
            } catch (IOException e) {
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(buscarImagen.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(buscarImagen.class.getName()).log(Level.SEVERE, null, ex);
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
