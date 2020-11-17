<%-- 
    Document   : cajero-retiro-cliente
    Created on : 14/11/2020, 11:41:40
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Retiro</title>
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
        </c:choose>
        
        <form action="TransaccionCajeroDatos?operacion=retiro" method="POST">
            <div class="pt-2 mt-5 mr-2"></div>
            <div class="container formulario pt-2"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <h1>Retiro: Ingrese Datos</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-6">
                        <label for="numeroCuenta">No. de Cuenta:</label>
                        <input type="number" min="0" class="form-control" placeholder="No. Cuenta" name="numeroCuenta" required/>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-6">
                        <label for="monto">Cantidad a Retirar (Q):</label>
                        <input type="number" step=".01" class="form-control" placeholder="xxxx.xx" value="monto" name="monto"  oninvalid="setCustomValidity('Cantidad monetaria inválida. Por favor, ingrese una cantidad monetaria válida.')" min="0.01" required/>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1 mb-4">
                    <div class="col-md-5" align="center">
                        <input type="submit" value="Retirar" class="btn btn-danger"/>
                    </div>
                </div>
            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>