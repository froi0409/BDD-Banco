<%-- 
    Document   : reporte-gerente-clientes-sin-transferencias-datos
    Created on : 15/11/2020, 14:07:42
    Author     : froi-pc
--%>

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
        
        <form action="gerente-reporte-clientes-sin-transacciones.jsp" method="POST">
            <div class="pt-5 mt-5 mr-5"></div>
            <div class="container formulario pt-4"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1" align="center">
                    <h1>Ingrese Datos:</h1>
                </div>
                <div class="row justify-content-center pt-1 mt-1 mr-1 mb-3 pb-2">
                    <div class="col-md-3">
                        <label for="fechaInicial">Fecha Inicial:</label>
                        <input type="date" class="form-control" name="fechaInicial" required/>
                    </div>
                    <div class="col-md-3">
                        <label for="fechaFinal">Fecha Final:</label>
                        <input type="date" class="form-control" name="fechaFinal" required/>
                    </div>
                </div>
                <div class="row justify-content-center pt-1 mt-1 mr-1">
                    <div class="col-md-6" align="center">
                        <input type="submit" value="Consultar" class="btn btn-danger"/>
                    </div>
                </div>
            </div>
        </form>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>