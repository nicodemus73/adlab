<%-- 
    Document   : registrarImagen
    Created on : 06-oct-2020, 18:56:16
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="registrarImagen" enctype="multipart/form-data">
            <h1>Inserta tu imagen en nuestra Base de datos</h1>
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
           Archivo:
           <input type="file" name="imagen" id="imagen"><br><br>
            <input type="submit" name="submit" value="Submit">
        </form>
    </body>
</html>
