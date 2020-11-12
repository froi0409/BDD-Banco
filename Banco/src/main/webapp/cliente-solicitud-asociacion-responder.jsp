<%-- 
    Document   : cliente-solicitud-asociacion-responder
    Created on : 12/11/2020, 02:27:19
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Solicitudes de Asociación Pendientes</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "cliente-barra-herramientas.html" %>

        <div class="container" align="center" style="margin-top: 100px" align="center">
                <h1>Solicitudes de Asociación Pendientes</h1>
                <table class="table table-bordered">
                    <thead>
                        <tr class="table-secondary">
                            <th scope="col">Cuenta Propia</th>
                            <th scope="col">Cliente que Solicita</th>
                            <th scope="col">Intentos</th>
                            <th scope="col" colspan="2">Acción</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        
                        <%
                        ArrayList<String[]> datos = (ArrayList<String[]>) request.getAttribute("lista");
                        for(String[] element: datos) {
                            out.println("<tr>");
                            out.println("<td>" + element[0] + "</td>");
                            out.println("<td>" + element[1] + "</td>");
                            out.println("<td>" + element[2] + "</td>");
                            out.println("<td> <form method=\"POST\" action=\"AceptarSolicitud\"><button type = \"submit\" class=\"btn btn-danger\" value=\"" + element[3] + "-" + element[0] + "\" name=\"cuenta\">Aceptar</button></td></form>");
                            out.println("<td> <form method=\"POST\" action=\"RechazarSolicitud\"><button type = \"submit\" class=\"btn btn-outline-danger\" value=\"" + element[3] + "-" + element[0] + "\" name=\"cuenta\">Rechazar</button></td></form>");
                            out.println("</tr>");  
                        }

                        %>
                    </tbody>
                </table>

            </div>
        
        <%@include file = "scripts.html"%>
    </body>
</html>