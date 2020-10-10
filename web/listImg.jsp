<%-- 
    Document   : list
    Created on : 06-oct-2020, 20:37:51
    Author     : mo
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="ourpackage.OurDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar imagenes</title>
    </head>
    <body>
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
                <td><%out.println(rs.getString("TITLE"));%></td>
                <td><%out.println(rs.getString("DESCRIPTION"));%></td>
                <td><%out.println(rs.getString("KEYWORDS"));%></td>
                <td><%out.println(rs.getString("AUTHOR"));%></td>
                <td><%out.println(rs.getString("CREATION_DATE"));%></td>
                <td><%out.println(rs.getString("STORAGE_DATE"));%></td>
                <td><%out.println(rs.getString("FILENAME"));%></td>
            </tr>
            <%      }
                    OurDao.stopDB();
                } catch(Exception e){
                    System.err.println(e.getMessage());
                }
            %>
        </table>
    </body>
</html>
