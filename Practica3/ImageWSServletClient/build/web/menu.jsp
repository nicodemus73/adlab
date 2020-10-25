<%-- 
    Document   : menu
    Created on : 30-sep-2020, 18:38:53
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú</title>
    </head>
    <body>
        <%
        HttpSession ses = request.getSession(false);
        String user = (String) ses.getAttribute("user");
        if(user == null) response.sendRedirect("login.jsp");
        %>
        <h1>Has accedido al Menú</h1>
        <h2>Bienvenido, <%out.print(user);%></h2>
        <a href="registrarImagen.jsp">Registra Imagen</a><br><br>
        <a href="listImg.jsp">Lista Imagenes</a><br><br>
        <a href="buscarImagen.jsp">Busca Imagen</a><br><br>
        <a href="logout.jsp">Cerrar sesion</a><br><br>
    </body>
</html>
