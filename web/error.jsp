<%-- 
    Document   : error
    Created on : 30-sep-2020, 20:01:13
    Author     : mo
--%>
<%@ page isErrorPage = "true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Error</title>
    </head>
    <body>
        <h1>Oops!</h1>
        <p>Un error ha sucedido</p>
        <p>Aqui esta la excepcion: </p>
        Exception is : <%= exception %>  
       <!--- <pre> exception.printStackTrace(response.getWriter()); %> </pre>
        <!--<h1>Error. Usuari o password incorrectes</h1>-->
        <br><br><a href="login.jsp">Vuelve al Login</a>
    </body>
</html>
