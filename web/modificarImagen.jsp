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
        <title>Modificar Imagen</title>
    </head>
    <body>
        <h1>Elige el campo que quieras modificar e introduce el valor nuevo que le quieras dar</h1>  
        <form method="POST" action="modificarImagen" >

            <label for="campo">Elige un campo a modificar:</label>
            <select name="campo" id="campo">
                <option value="title">Título</option>
                <option value="description">Descripción</option>
                <option value="keywords">Palabras clave</option>
                <option value="creation_date">Fecha creación</option>
            </select> 
            <br><br>
            <input type="text" name="valor" required><br><br>
            <input type="submit" name="submit" value="Submit">
        </form>
    </body>
</html>
