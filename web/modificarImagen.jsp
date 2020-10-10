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
     <h1>Elige el campo que quieras modificar e introduce el valor nuevo que le quieras dar</h1>  
            <form method="POST" action="modificarImagen" >

     <label for="campo">Elige un campo a modificar:</label>
     <select name="campo" id="campo">
        <option value="titulo">Título</option>
        <option value="descripcion">Descripción</option>
        <option value="clave">Palabras clave</option>
        <option value="fechaC">Fecha creación</option>
    </select> 
           <br><br>
        <input type="text" name="valor"><br><br>
        
        <input type="submit" name="submit" value="Submit">
       </form>
    </body>
</html>
