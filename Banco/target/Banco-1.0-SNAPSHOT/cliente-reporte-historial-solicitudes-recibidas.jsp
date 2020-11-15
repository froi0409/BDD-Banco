<%-- 
    Document   : cliente-reporte-historial-solicitudes-recibidas
    Created on : 15/11/2020, 06:59:41
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.cliente.reportes.HistorialSolicitudesRecibidas"%>
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
        <%@include file = "cliente-barra-herramientas.html" %>

        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-secondary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
        </c:choose>
        <div align="center" style="padding-top: 100px;">
            <h1>Historial de Solicitudes de Asociación Recibidas</h1>
        </div>
        <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">DPI Solicitante</th>
                    <th scope="col">Nombre del Solicitante</th>
                    <th scope="col">Cuenta</th>
                    <th scope="col">Estado de Solicitud</th>
                    <th scope="col">Intentos</th>
                    <th scope="col">Fecha de Respuesta</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    HistorialSolicitudesRecibidas historial = new HistorialSolicitudesRecibidas(Conexion.getConnection());
                    String dpiCliente = request.getSession().getAttribute("dpi").toString();
                    
                    ArrayList<String[]> historialRecibidas = historial.obtener(dpiCliente);
                    
                    for(String[] element: historialRecibidas){
                        out.println("<tr>");
                        out.println("<td>" + element[0] + "</td>");
                        out.println("<td>" + element[1] + "</td>");
                        out.println("<td>" + element[2] + "</td>");
                        out.println("<td>" + element[3] + "</td>");
                        out.println("<td>" + element[4] + "</td>");
                        out.println("<td>" + element[5] + "</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>

        </div>
        
        <%@include file = "scripts.html"%>
    </body>
</html>