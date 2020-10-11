<%-- 
    Document   : registroUsuarios
    Created on : 11-oct-2020, 21:44:02
    Author     : elchu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
    </head>
    <body>
        <h1>Registrate en el sistema</h1>
        <form action="registroUsuario" method="POST">
            Usuario: <input type="text"     name="usuari"/><br>
            Contraseña: <input type="password" name="password"/><br>
            <input type="Submit"   value="Enviar"/><br>
        </form>
    </body>
</html>
