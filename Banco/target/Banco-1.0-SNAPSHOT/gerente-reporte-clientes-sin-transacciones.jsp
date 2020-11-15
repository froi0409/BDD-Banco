<%-- 
    Document   : gerente-reporte-clientes-sin-transacciones
    Created on : 15/11/2020, 14:09:52
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.banco.Conexion"%>
<%@page import="com.froi.gerente.reportes.ReporteClientesSinTransacciones"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Reporte</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "gerente-barra-herramientas.html" %>

        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-secondary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
        </c:choose>
        
        
        <div align="center" style="padding-top: 100px;">
            <h1>Clientes Que No Han Realizado Transacciones:</h1>
            <h4><% out.println(request.getParameter("fechaInicial") + " - " + request.getParameter("fechaFinal")); %></h4>
        </div>
        <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">DPI</th>
                    <th scope="col">Nombre Cliente</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    ReporteClientesSinTransacciones reporte = new ReporteClientesSinTransacciones(Conexion.getConnection(), request.getParameter("fechaInicial"), request.getParameter("fechaFinal"));
                    
                    ArrayList<String[]> clientesSinTrnsferencias = reporte.obtener();
                    
                    for(String[] element: clientesSinTrnsferencias){
                        out.println("<tr>");
                        out.println("<td>" + element[0] + "</td>");
                        out.println("<td>" + element[1] + "</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>

        </div>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>