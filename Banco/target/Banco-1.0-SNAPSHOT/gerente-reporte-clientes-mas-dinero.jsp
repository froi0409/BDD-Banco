<%-- 
    Document   : gerente-reporte-clientes-mas-dinero
    Created on : 15/11/2020, 14:01:38
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.gerente.reportes.ReporteClientesConMasDinero"%>
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
            <h1>10 Clientes Con Más Dinero En Sus Cuentas</h1>
        </div>
        <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">DPI</th>
                    <th scope="col">Nombre Cliente</th>
                    <th scope="col">Dinero Total</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    ReporteClientesConMasDinero reporte = new ReporteClientesConMasDinero(Conexion.getConnection());
                    
                    ArrayList<String[]> clientesConMasDinero = reporte.obtener();
                    
                    for(String[] element: clientesConMasDinero){
                        out.println("<tr>");
                        out.println("<td>" + element[0] + "</td>");
                        out.println("<td>" + element[1] + "</td>");
                        out.println("<td>Q " + element[2] + "</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>

        </div>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>