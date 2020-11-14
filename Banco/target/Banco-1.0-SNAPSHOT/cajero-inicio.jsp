<%-- 
    Document   : cajero-inicio
    Created on : 11/11/2020, 22:03:00
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Banco "El Billetón"</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "cajero-barra-herramientas.html" %>

        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-secondary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
        </c:choose>
        
        
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>