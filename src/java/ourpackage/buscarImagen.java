/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;



/**
 *
 * @author elchu
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
@MultipartConfig

public class buscarImagen extends HttpServlet {
    static String s;
    

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
 
        try (PrintWriter out = response.getWriter()) {
            HttpSession session1 = request.getSession(false);
        if (session1 == null) {
            response.sendRedirect("login.jsp");
            return;
        } 
            String user = (String) session1.getAttribute("user");
            /* TODO output your page here. You may use following sample code. */
            
            HashMap<String, String> map = new HashMap<>();
            if (!"".equals(request.getParameter("title"))) map.put("title", "%"+request.getParameter("title")+"%");//
            if (!"".equals(request.getParameter("description")))map.put("description", "%"+request.getParameter("description")+"%");
            if (!"".equals(request.getParameter("keywords"))) map.put("keywords", "%"+request.getParameter("keywords")+"%");
            if (!"".equals(request.getParameter("author"))) map.put("author", "%"+request.getParameter("author")+"%");
            if (!"".equals(request.getParameter("cdate"))) map.put("cdate", "%"+request.getParameter("cdate")+"%");
            if (!"".equals(request.getParameter("filename"))) map.put("filename", "%"+request.getParameter("filename")+"%");
//afageixo el % per buscar patrons, paraules dintre de paraules

            if (map.isEmpty()) {
                out.println("No hay resultados con las entradas correspondientes") ;
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
                OurDao.startDB(); 
                ResultSet rs = OurDao.consultar(map);
                out.println("Listado de imagenes: <br><br>");
                
                out.println("<table>\n" +
"            <tr>\n" +
"                <th>Titulo</th>\n" +
"                <th>Descripcion</th>\n" +
"                <th>Palabras Clave</th>\n" +
"                <th>Autor</th>\n" +
"                <th>Fecha de creacion</th>\n" +
"                <th>Fecha de subida</th>\n" +
"                <th>Nombre del archivo</th>\n" +
"                <th>Modificar</th>\n" +
"                <th>Eliminar</th>\n" +
"            </tr>");
                HashMap<String, String> m = new HashMap<>();
                Vector<String> v = new Vector<>();
                int i = 0;
                while(rs.next()) {
                
                out.println("<tr>");
                    out.println("<td>"+rs.getString("TITLE")+"</td>");
                    out.println("<td>"+rs.getString("DESCRIPTION")+"</td>");
                    out.println("<td>"+rs.getString("KEYWORDS")+"</td>");
                    out.println("<td>"+rs.getString("AUTHOR")+"</td>");
                    out.println("<td>"+rs.getString("CREATION_DATE")+"</td>");
                    out.println("<td>"+rs.getString("STORAGE_DATE")+"</td>");
                    out.println("<td>"+rs.getString("FILENAME")+"</td>");
                    if (rs.getString("AUTHOR").equals(user)) {
                        session1.setAttribute("ID",rs.getString("ID")); 
                        v.addElement(rs.getString("ID"));
                                                
                        
                    
                        //session1.setAttribute("vector",s[i]);
                        s = rs.getString("ID");
                        //aixi no m'agrada pq pots canviarlo desde el navegador
                        out.print("<td> <a href=\"modificarImagen.jsp\">Modificar esta imagen</a> </td>");
                        //HttpServletResponse sendRedirect = response.sendRedirect(/modificarImagen.jsp); 
                        out.print("<td> <a href=\"eliminarImagen.jsp\">Eliminar esta imagen</a> </td>");
                    }
                    out.println("<br><br>");
                }
                out.println("</table>");
                out.println("provandooooo: ");
                for (int w = 0; w<v.size(); w++) {
                    out.println(v.get(i));
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
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
