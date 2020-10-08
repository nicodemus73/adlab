<%-- 
    Document   : modificarImagen
    Created on : 08-oct-2020, 0:24:42
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form method="POST" action="modificarImagen" >
        <h1>Inserta los campos que quieras modificar</h1>
        Titulo: 
        <input type="text" name="titulo"><br><br>
        Descripción: 
        <input type="text" name="descripcion"><br><br>
        Palabras clave: 
        <input type="text" name="clave"><br><br>
        Autor: 
        <input type="text" name="author"><br><br>
        Fecha creación:
        <input type="text" name="fechaC"><br><br>
        <input type="submit" name="submit" value="Submit">
       </form>
    </body>
</html>
