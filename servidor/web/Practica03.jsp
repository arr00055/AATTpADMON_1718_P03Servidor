<%-- 
    Document   : Practica03
    Created on : 13-abr-2018, 11:40:07
    Author     : Alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página con el Resultado.</title>
    </head>
    <body>
    <c:out value = "Resultado = ${result}"/> <%--<c:out value = "Resultado = ${result} Bienvenido ${Usuario} con clave ${Clave}"/>--%>
    </body>
</html>
