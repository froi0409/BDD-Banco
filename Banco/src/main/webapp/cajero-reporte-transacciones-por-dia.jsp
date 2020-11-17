<%-- 
    Document   : cajero-reporte-transacciones-por-dia
    Created on : 16/11/2020, 15:09:51
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.cajero.reportes.ListadoTransaccionesPorDia"%>
<%@page import="com.froi.cajero.reportes.ListadoTransaccionesPorDia"%>
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
        <%@include file = "cajero-barra-herramientas.html" %>
        <%
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        String codigoCajero = request.getSession().getAttribute("usuario").toString();
        
        ListadoTransaccionesPorDia reporte = new ListadoTransaccionesPorDia(Conexion.getConnection(), fechaInicial, fechaFinal);
        %>
        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-secondary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
        </c:choose>
        
        <div class="container" align="center" style="margin-top: 100px">
            <h1>Transacciones Realizadas por Día</h1><br>
            <h4><% out.println(fechaInicial + " - " + fechaFinal); %></h4>
        <div class="container-fluid" align="center">
          <form action="ExportTransaccionesPorDia" method="POST" style="padding-bottom: 50px;">
              <% request.getSession().setAttribute("fechaInicial", request.getParameter("fechaInicial"));
                request.getSession().setAttribute("fechaFinal", request.getParameter("fechaFinal"));
              %>
              <input type="submit" value="Descargar" class="btn btn-danger"/>
          </form>
              <div style="padding-top: 15px"><h3>Balance: Q <% out.print(reporte.getBalance(codigoCajero)); %></h3></div>
<%
        ArrayList<String> fechas = reporte.obtenerFechas(codigoCajero);
        
        for (String fecha: fechas) {
        %>    
            <div class="container" align="center" style="margin-top: 50px; margin-bottom: 50px;">
            <h4>Transacciones: <% out.println(fecha); %></h4><br>
            <table class="table table-bordered">
                <thead>
                    <tr class="table-secondary">
                    <th scope="col">Codigo Transferencia</th>
                    <th scope="col">Cuenta</th>
                    <th scope="col">Hora</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Monto</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    ArrayList<String[]> transacciones = reporte.obtenerTransacciones(codigoCajero, fecha);
                    
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