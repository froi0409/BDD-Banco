<%-- 
    Document   : cajero-reporte-transferencias-turno
    Created on : 16/11/2020, 16:21:38
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.froi.cajero.reportes.ListadoTransaccionesTurno"%>
<%@page import="com.froi.banco.Conexion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Banco "El Billetón"</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "cajero-barra-herramientas.html" %>

        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-secondary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
            <c:otherwise>
                <div align="center" style="padding-top: 100px;">
                    <h1>Transacciones Realizadas en el Turno de Hoy</h1>
                    <% 
                        ListadoTransaccionesTurno reporte = new ListadoTransaccionesTurno(Conexion.getConnection());
                        String codigoCajero = request.getSession().getAttribute("usuario").toString();
                    %>
                    <h3>Total Caja: <% out.println("Q " + reporte.totalCaja(codigoCajero)); %></h3>
                </div>
                <div class="container-fluid" align="center">
                    <form action="" method="POST" style="padding-top: 50px;">
                        <input type="submit" value="Descargar" class="btn btn-danger"/>
                    </form>
                </div>
                <div class="container" align="center" style="margin-top: 50px; margin-bottom: 50px;">
                    <table class="table table-bordered">
                        <thead>
                            <tr class="table-secondary">
                            <th scope="col">Cuenta Solicitada</th>
                            <th scope="col">Propietario de la Cuenta</th>
                            <th scope="col">Estado de Solicitud</th>
                            <th scope="col">Intentos</th>
                            <th scope="col">Fecha de Respuesta</th>
                          </tr>
                        </thead>
                        <tbody>
                            <%

                            ArrayList<String[]> transacciones = reporte.obtenerTransacciones(codigoCajero);

                            for(String[] element: transacciones){
                                out.println("<tr>");
                                out.println("<td>" + element[0] + "</td>");
                                out.println("<td>" + element[1] + "</td>");
                                out.println("<td>" + element[2] + "</td>");
                                out.println("<td>" + element[3] + "</td>");
                                out.println("<td>" + element[4] + "</td>");
                                out.println("<td>" + element[4] + "</td>");
                                out.println("</tr>");
                            }
                            %>
                        </tbody>
                    </table>

                </div>
            </c:otherwise>
        </c:choose>
        
        <%@include file = "scripts.html"%>
    </body>
</html>