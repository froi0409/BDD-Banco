<%-- 
    Document   : cliente-reporte-cuenta-mas-dinero
    Created on : 15/11/2020, 06:27:37
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.cliente.reportes.ReporteCuentaConMasDinero"%>
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
        <% 
        ReporteCuentaConMasDinero reporte = new ReporteCuentaConMasDinero(Conexion.getConnection()); 
        String dpiCliente = request.getSession().getAttribute("dpi").toString();
        String codigoCuenta = reporte.obtenerCuenta(dpiCliente);
        %>
        <div class="container" align="center" style="margin-top: 100px">
            <h1>Cuenta con Más Dinero: <% out.println(codigoCuenta); %></h1><br>
            
            <div class="container-fluid" align="center">
                <form action="ExportCuentaMasDinero" method="POST" style="padding-bottom: 50px;">
                    <input type="submit" value="Descargar" class="btn btn-danger"/>
                </form>
            </div>
            
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
                    ArrayList<String[]> transacciones = reporte.obtenerTransacciones(dpiCliente, request.getParameter("fechaInicial"));
                    request.getSession().setAttribute("transacciones", transacciones);
                    System.out.println(request.getParameter("fechaInicial"));
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
        
        <%@include file = "scripts.html"%>
    </body>
</html>
