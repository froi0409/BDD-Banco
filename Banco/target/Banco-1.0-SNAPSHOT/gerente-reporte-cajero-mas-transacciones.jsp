<%-- 
    Document   : gerente-reporte-cajero-mas-transacciones
    Created on : 15/11/2020, 15:03:16
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.gerente.reportes.CajeroConMasTransacciones"%>
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
        
        <% 
            CajeroConMasTransacciones cajero = new CajeroConMasTransacciones(Conexion.getConnection(), request.getParameter("fechaInicial"), request.getParameter("fechaFinal")); 
            String[] cajeroDatos = cajero.obtenerCajero();
        %>
        <div align="center" style="padding-top: 100px;">
            <h1>Cajero con Más transacciones: <% out.print(cajeroDatos[0] + " - " + cajeroDatos[1]); %></h1><br>
            <h4>Cantidad de Transacciones: <% out.print(cajeroDatos[2]); %></h4>
        </div>
        <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">Codigo Transaccion</th>
                    <th scope="col">Cuenta</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Hora</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Monto</th>
                  
                    </tr>
                </thead>
                <tbody>
                    <%
                    
                    ArrayList<String[]> transaccionesCajero = cajero.obtenerTransacciones();
                    for(String[] element: transaccionesCajero){
                        out.println("<tr>");
                        out.println("<td>" + element[0] + "</td>");
                        out.println("<td>" + element[1] + "</td>");
                        out.println("<td>" + element[2] + "</td>");
                        out.println("<td>" + element[3] + "</td>");
                        out.println("<td>" + element[4] + "</td>");
                        out.println("<td>Q " + element[5] + "</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>

        </div>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>