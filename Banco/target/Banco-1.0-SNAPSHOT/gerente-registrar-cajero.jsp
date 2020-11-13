<%-- 
    Document   : gerente-registrar-cajero
    Created on : 13/11/2020, 01:16:01
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Registrar Cajero</title>
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
        
        <!-- Creamos Ventana de registro de Cajero -->
        <form action="CajeroRegistrar" method="POST">
            <div class="pt-2 mt-5 mr-2"></div>
            <div class="container formulario pt-2"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <h1>Registrar Cajero</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-6">
                        <label for="nombreCajero">Nombre:</label>
                        <input type="text" class="form-control" placeholder="Nombre del Cajero" name="nombreCajero" required/>
                    </div>
                    <div class="col-md-6">
                        <label for="codigoCajero">Código:</label>
                        <input type="text" class="form-control" placeholder="Código del Cajero" name="codigoCajero" required/>
                    </div>
                    
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-12">
                        <label for="dpiCajero">DPI:</label>
                        <input type="number" class="form-control" placeholder="DPI del Cajero" name="dpiCajero" required/>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-6">
                        <label for="direccionCajero">Dirección:</label>
                        <input type="text" class="form-control" placeholder="Dirección del Cajero" name="direccionCajero" required/>
                    </div>
                    <div class="col-md-6">
                        <label for="sexoCajero">Sexo:</label><br>
                        <select name="sexoCajero" class="btn-block">
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1 mb-4">
                    <div class="col-md-5" align="center">
                        <div><label>* Todos los campos son obligatorios</label><br></div>
                        <input type="submit" value="Registrar Cajero" class="btn btn-danger"/>
                    </div>
                </div>

            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>