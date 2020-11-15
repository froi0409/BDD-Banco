<%-- 
    Document   : gerente-reporte-transacciones-mayores-limite
    Created on : 15/11/2020, 11:44:06
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.gerente.reportes.ReporteClienteTransacciones"%>
<%@page import="com.froi.banco.Conexion"%>
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
            <h1>Clientes con Transacciones Mayores a Limite</h1>
        </div>
        <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">DPI</th>
                    <th scope="col">Nombre Cliente</th>
                    <th scope="col">Codigo Transaccion</th>
                    <th scope="col">Cuenta</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Hora</th>
                    <th scope="col">Monto</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    ReporteClienteTransacciones reporte = new ReporteClienteTransacciones(Conexion.getConnection());
                    
                    ArrayList<String[]> transaccionesMayoresLimite = reporte.obtener();
                    
                    for(String[] element: transaccionesMayoresLimite){
                        out.println("<tr>");
                        out.println("<td>" + element[0] + "</td>");
                        out.println("<td>" + element[1] + "</td>");
                        out.println("<td>" + element[2] + "</td>");
                        out.println("<td>" + element[3] + "</td>");
                        out.println("<td>" + element[4] + "</td>");
                        out.println("<td>" + element[5] + "</td>");
                        out.println("<td>" + element[6] + "</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>

        </div>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>