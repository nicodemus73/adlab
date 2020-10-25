<%-- 
    Document   : list
    Created on : 06-oct-2020, 20:37:51
    Author     : mo
--%>

<%@page import="java.util.List"%>
<%@page import="org.me.image.Image"%>
<%@page import="org.me.image.client.WSConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar imagenes</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            String user = (String) ses.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
            } else {
                try {
        %>
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
                List<Object> list = WSConnection.listImages();
                for (Object o : list) {
                    Image img = (Image) o;
                    String title = img.getTitle();
            %>
            <tr>
                <td><%  out.println("");%></td>
                <td><%  out.println(img.getDescription());%></td>
                <td><%  out.println(img.getKeywords());%></td>
                <td><%
                    String autor = img.getAuthor();
                    out.println(autor);
                    %></td>
                <td><%  out.println(img.getCreationDate());%></td>
                <td><%  out.println(img.getStorageDate());%></td>
                <%
                    String filename = img.getFileName();
                    int id = img.getId(); %>
                <td>
                    <%
                        out.println("<a href=image.jsp?name=" + filename + "&id=" + id + ">" + filename + "</a>");
                        if (autor.equals(user)) {
                    %>
                    <form action=selectImage method="POST">
                        <input type="hidden" value="<%out.print(filename);%>" name="name"/>
                        <input type="hidden" value="<%out.print(id);%>" name="id"/>
                        <input type="submit" value="Modificar" name="action"/>
                        <input type="submit" value="Eliminar" name="action"/>
                    </form>
                </td>
            </tr>
            <%          }
                            }
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            %>
        </table><br><br>
        <a href="menu.jsp">Vuelve al menu</a>
    </body>
</html>
