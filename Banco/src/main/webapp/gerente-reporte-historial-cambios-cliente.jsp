<%-- 
    Document   : gerente-reporte-historial-cambios-cliente
    Created on : 16/11/2020, 11:23:23
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.gerente.reportes.HistorialDeCambios"%>
<%@page import="com.froi.banco.Conexion"%>
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
            <h1>Historial de Cambios de Cliente</h1>
        </div>
        <div class="container-fluid" align="center">
            <form action="ExportHistorialCambiosCliente" method="POST" style="padding-top: 50px;">
                <input type="submit" value="Descargar" class="btn btn-danger"/>
            </form>
        </div>
        <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">codigo</th>
                    <th scope="col">Fecha de Actualizción</th>
                    <th scope="col">Campo Actualizado</th>
                    <th scope="col">Danto Anterior</th>
                    <th scope="col">Dato Actualizado</th>
                    <th scope="col">Gerente que Actualizó</th>
                    <th scope="col">Cliente</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    HistorialDeCambios reporte = new HistorialDeCambios(Conexion.getConnection());
                    
                    ArrayList<String[]> cambios = reporte.cliente();
                    
                    for(String[] element: cambios){
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