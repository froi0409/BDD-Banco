<%-- 
    Document   : cajero-retiro-confirmar
    Created on : 14/11/2020, 11:42:28
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Confirmar Depósito</title>
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
        
        <form action="RetiroRealizar" method="POST">
            <div class="pt-2 mt-5 mr-2"></div>
            <div class="container formulario pt-2"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <h1>Verificar Datos del Retiro</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-4" align="center">
                        <label for="dpiCliente"><b>Dueño de la Cuenta:</b></label><br>
                        <% out.println(request.getSession().getAttribute("dpiPropietario").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="numeroCuenta"><b>Dueño de la Cuenta:</b></label><br>
                        <% out.println(request.getSession().getAttribute("propietarioCuenta").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-4" align="center">
                        <label for="numeroCuenta"><b>No. de Cuenta:</b></label><br>
                        <% out.println(request.getSession().getAttribute("codigoCuenta").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="monto"><b>Cantidad a Retirar:</b></label><br>
                        <% out.println("- Q " + request.getSession().getAttribute("monto").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1 mb-4">
                    <div class="col-md-6" align="center"><label>¿Desea continuar con el Retiro?</label><br></div>
                </div>
                <div class="row justify-content-center mb-4">
                    <div class="col-md-2 justify-content-end" align="center">
                        <input type="submit" value="Continuar" class="btn btn-danger"/>
                    </div>
                    <div class="col-md-2 justify-content-start" align="center">
                        <a class="btn btn-outline-danger" href="cajero-deposito-cliente.jsp" role="button">Cancelar</a>
                    </div>
                </div>
            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>