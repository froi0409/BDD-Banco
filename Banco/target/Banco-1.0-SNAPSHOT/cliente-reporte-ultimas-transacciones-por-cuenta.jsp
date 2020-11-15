<%-- 
    Document   : cliente-reporte-ultimas-transacciones-por-cuenta
    Created on : 15/11/2020, 05:36:17
    Author     : froi-pc
--%>

<%@page import="com.froi.cliente.reportes.ReporteUltimasTransacciones"%>
<%@page import="com.froi.banco.Conexion"%>
<%@page import="java.util.ArrayList"%>
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
        <%@include file = "cliente-barra-herramientas.html" %>

        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-secondary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
        </c:choose>
        <div align="center" style="padding-top: 100px;">
            <h1>Reporte: Últimas 15 Transacciones Del Año (Por Cada Cuenta)</h1>
        </div>
        <%
        
        ReporteUltimasTransacciones reporte = new ReporteUltimasTransacciones(Conexion.getConnection());
        String dpiCliente = request.getSession().getAttribute("dpi").toString();
        ArrayList<String> cuentas = reporte.obtenerCuentas(dpiCliente);
        
        for (String cuenta: cuentas) {
        %>    
            <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <h4>Transacciones: <% out.println(cuenta); %></h4><br>
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">Codigo Transferencia</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Hora</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Monto</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    ArrayList<String[]> transacciones = reporte.obtenerTransacciones(cuenta);
                    
                    for(String[] element: transacciones){
                        out.println("<tr>");
                        out.println("<td>" + element[0] + "</td>");
                        out.println("<td>" + element[1] + "</td>");
                        out.println("<td>" + element[2] + "</td>");
                        out.println("<td>" + element[3] + "</td>");
                        out.println("<td>" + element[4] + "</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>

        </div>
        
        <%   
        }
        %>
        
        <%@include file = "scripts.html"%>
    </body>
</html>