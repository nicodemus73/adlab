<%-- 
    Document   : index
    Created on : 30-sep-2020, 16:42:21
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Hola! Accede a tu cuenta</h1>
        <form action="login" method="POST">
            Usuario: <input type="text"     name="usuari"/><br>
            Contraseña: <input type="password" name="password"/><br>
            <input type="Submit"   value="Enviar"/><br>
        </form>
    </body>
</html>
