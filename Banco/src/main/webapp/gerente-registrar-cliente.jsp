<%-- 
    Document   : gerente-registrar-cliente
    Created on : 13/11/2020, 01:15:50
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Registrar Cliente</title>
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
        
        <!-- Creamos la ventana de registro de cliente -->
        <form action="ClienteVerDatos" method="POST" enctype="multipart/form-data">
            <div class="pt-2 mt-5 mr-2"></div>
            <div class="container formulario pt-2"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <h1>Registrar Cajero</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-6">
                        <label for="nombreCliente">Nombre:</label>
                        <input type="text" class="form-control" placeholder="Nombre del Cliente" name="nombreCliente" required/>
                    </div>
                    <div class="col-md-6">
                        <label for="fechaNacimientoCliente">Fecha de Nacimiento:</label>
                        <input type="date" max="2004-11-17" class="form-control" name="fechaNacimientoCliente" required/>
                    </div>
                    
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-6">
                        <label for="dpiCliente">DPI:</label>
                        <input type="number" class="form-control" placeholder="DPI del Cliente" name="dpiCliente" required/>
                    </div>
                    <div class="col-md-6">
                        <label for="codigoCliente">Codigo:</label>
                        <input type="text" class="form-control" placeholder="Código del Cliente" name="codigoCliente" required/>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-6">
                        <label for="direccionCliente">Dirección:</label>
                        <input type="text" class="form-control" placeholder="Dirección del Cliente" name="direccionCliente" required/>
                    </div>
                    <div class="col-md-3">
                        <label for="sexoCliente">Sexo:</label><br>
                        <select name="sexoCliente" class="btn-block">
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label for="dpiPdfCliente">PDF del DPI:</label><br>
                        <input type="file" class="btn-block" name="ruta" accept="text/xml" value="Elegir"/>
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