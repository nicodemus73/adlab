<%-- 
    Document   : buscarImagen
    Created on : 07-oct-2020, 20:21:33
    Author     : elchu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="buscarImagen" enctype="multipart/form-data">

        <h1>Búsqueda de imagenes</h1>
        Palabra clave: 
            <input type="text" name="palabra"><br><br>

        <input type="submit" name="Submit" value="Submit"> 
        <br><br>
       
        <a href="menu.jsp">Menu principal</a>

        </form>
    </body>
</html>
