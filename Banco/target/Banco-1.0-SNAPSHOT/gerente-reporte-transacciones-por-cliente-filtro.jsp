<%-- 
    Document   : gerente-reporte-transacciones-por-cliente-filtro
    Created on : 15/11/2020, 16:32:42
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
        
        <form action="VerificarLimites" method="POST">
            <div class="pt-2 mt-5 mr-2"></div>
            <div class="container formulario pt-2"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <h1>Ingrese Datos</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-8">
                        <label for="filtro">Filtro:</label>
                        <input type="text" class="form-control" name="filtro"  required/>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt- mb-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-4">
                        <label for="limiteTransferencias">Limite Inferior:</label>
                        <input type="number" step=".01" class="form-control" placeholder="xxxx.xx" name="limiteInferior" oninvalid="setCustomValidity('Cantidad monetaria inválida. Por favor, ingrese una cantidad monetaria válida.')" min="0.01" required/>
                    </div>
                    <div class="col-md-4">
                        <label for="limiteTransferenciasSumadas">Limite Superior:</label>
                        <input type="number" step=".01" class="form-control" placeholder="xxxx.xx" name="limiteSuperior" oninvalid="setCustomValidity('Cantidad monetaria inválida. Por favor, ingrese una cantidad monetaria válida.')" min="0.01" required/>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1 mb-4">
                    <div class="col-md-5" align="center">
                        <input type="submit" value="Consultar" class="btn btn-danger"/>
                    </div>
                </div>
            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>