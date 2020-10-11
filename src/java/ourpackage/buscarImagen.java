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
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
            /*ArrayList<String> id = new ArrayList<>();
            ArrayList<String> tithle = new ArrayList<>();
            ArrayList<String> descr = new ArrayList<>();
            ArrayList<String> keyword = new ArrayList<>();
            ArrayList<String> author = new ArrayList<>();
            ArrayList<String> cdate = new ArrayList<>();
            ArrayList<String> sdate = new ArrayList<>();
            ArrayList<String> filename = new ArrayList<>(); esto es por si se puede buscar mas de una palabra por campo, 
            futura optimizacion*/
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
               /*resp = "<a href=";
               resp += "'";
               resp += "buscarImagen.jsp";
               resp += "'";
               resp += ">Buscar una nueva imagen</a>";*/
               out.println(resp);
               /*
               
               imprimir de forma correcta: out.println("request.getParameter(\"filename\")"); amb les ralles abans les comillas
               
               descomentaa aixo per veure el usuari de la sessio
               out.print("<br><br>");
               resp = "<h1>";
               resp += user;
               resp += "</h1>";
               out.print(resp);*/
               out.print("<br><br>");
               
            } else {
                OurDao.startDB(); 
                //ArrayList<String> s = OurDao.consultar(map);
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
"            </tr>");
                while(rs.next()) {
                /*    <tr>
                <td><%out.println(rs.getString("TITLE"));%></td>
                <td><%out.println(rs.getString("DESCRIPTION"));%></td>
                <td><%out.println(rs.getString("KEYWORDS"));%></td>
                <td><%out.println(rs.getString("AUTHOR"));%></td>
                <td><%out.println(rs.getString("CREATION_DATE"));%></td>
                <td><%out.println(rs.getString("STORAGE_DATE"));%></td>
                <td><%out.println(rs.getString("FILENAME"));%></td>
            </tr>*/
                out.println("<tr>");
                    out.println("<td>"+rs.getString("TITLE")+"</td>");
                    out.println("<td>"+rs.getString("DESCRIPTION")+"</td>");
                    out.println("<td>"+rs.getString("KEYWORDS")+"</td>");
                    out.println("<td>"+rs.getString("AUTHOR")+"</td>");
                    out.println("<td>"+rs.getString("CREATION_DATE")+"</td>");
                    out.println("<td>"+rs.getString("STORAGE_DATE")+"</td>");
                    out.println("<td>"+rs.getString("FILENAME")+"</td>");
                    
                    out.println("<br><br>");
                    if (rs.getString("AUTHOR").equals(user)) out.print("<a href=\"modificarImagen.jsp\">Modificar esta imagen</a>");
                
                }
                out.println("</table>");
                
                
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
