<%-- 
    Document   : cliente-reporte-cambio-dinero
    Created on : 15/11/2020, 04:21:25
    Author     : froi-pc
--%>

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
        
        <div class="container" align="center" style="margin-top: 100px">
            <h1>Estado de Cuenta: <% out.println(request.getAttribute("codigoCuenta")); %></h1><br>
            <h4><% out.println(request.getAttribute("fechaInicial") + " - " + request.getAttribute("fechaFinal")); %></h4>
            
        <div class="container-fluid" align="center">
          <form action="ExportTransaccionesCambioDinero" method="POST" style="padding-bottom: 50px;">
              <% request.getSession().setAttribute("fechaInicial", request.getAttribute("fechaInicial"));
                request.getSession().setAttribute("fechaFinal", request.getAttribute("fechaFinal"));
                request.getSession().setAttribute("codigoCuenta", request.getAttribute("codigoCuenta"));
              %>
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
                    <th scope="col">Saldo Antes</th>
                    <th scope="col">Saldo Después</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    ArrayList<String[]> estadoCuenta = (ArrayList<String[]>) request.getAttribute("estadoCuenta");
                    request.getSession().setAttribute("estadoCuenta", estadoCuenta);
                    
                    for(String[] element: estadoCuenta){
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