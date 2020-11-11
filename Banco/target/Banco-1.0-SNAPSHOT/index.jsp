<%-- 
    Document   : index
    Created on : 10/11/2020, 19:03:38
    Author     : froi-pc
--%>

<%@page import="com.froi.banco.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Conexion conexion = new Conexion();
        conexion.crearConexion();
        %>
        <% response.sendRedirect("inicio.jsp"); %>
    </body>
</html>
