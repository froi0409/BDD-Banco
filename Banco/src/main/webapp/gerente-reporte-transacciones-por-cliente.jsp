<%-- 
    Document   : gerente-reporte-transacciones-por-cliente
    Created on : 15/11/2020, 16:47:18
    Author     : froi-pc
--%>

<%@page import="com.froi.gerente.reportes.HistorialTransaccionesPorCliente"%>
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
            <h1>Reporte: Transacciones de Clientes con el Filtro "<% out.print(request.getAttribute("filtro")); %>"</h1><br>
            <h4>Q ${limiteInferior} - Q ${limiteSuperior}</h4>
        </div>
        <div align="center" style="padding-top: 50px;">
            <form action="ExportListaClientesFiltros" method="POST">
                <input type="submit" value="Descargar" class="btn btn-danger"/>
            </form>
        </div>
        <%
        
        HistorialTransaccionesPorCliente reporte = new HistorialTransaccionesPorCliente(Conexion.getConnection(), Double.parseDouble(request.getAttribute("limiteInferior").toString()), Double.parseDouble(request.getAttribute("limiteSuperior").toString()));
            
        request.getSession().setAttribute("limiteInferior", request.getAttribute("limiteInferior").toString());
        request.getSession().setAttribute("limiteSuperior", request.getAttribute("limiteSuperior").toString());
        request.getSession().setAttribute("filtro", request.getAttribute("filtro").toString());
        
        ArrayList<String[]> clientes = reporte.obtenerClientes(request.getAttribute("filtro").toString());
        
        for (String[] cliente: clientes) {
        %>    
            <div class="container" align="center" style="margin-top: 100px; margin-bottom: 50px;">
            <h4>Transacciones: <% out.println(cliente[0] + " - " + cliente[1]); %></h4><br>
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">Codigo Transferencia</th>
                    <th scope="col">Cuenta</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Hora</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Monto</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    ArrayList<String[]> transacciones = reporte.obtenerTransacciones(cliente[0]);
                    
                    for(String[] element: transacciones){
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
        
        <%   
        }
        %>
        
        <%@include file = "scripts.html"%>
    </body>
</html>