<%-- 
    Document   : gerente-registrar-cliente-confirmar
    Created on : 13/11/2020, 04:08:20
    Author     : froi-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Verificar Datos de Cliente</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "gerente-barra-herramientas.html" %>

        <!-- Creamos Ventana de registro de Cajero -->
        <form action="ClienteRegistrar" method="POST">
            <div class="mt-5"></div>
            <div class="container formulario mb-5"> 
                <div class="row justify-content-center mt-5">
                    <h1>Verificar Datos del Nuevo Cliente</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-4" align="center">
                        <label for="nombreCliente"><b>Nombre:</b></label><br>
                        <% out.println(request.getSession().getAttribute("nombreCliente").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="codigoCliente"><b>Codigo</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("codigoCliente").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-4" align="center">
                        <label for="dpiCliente"><b>DPI:</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("dpiCliente").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="passwordCliente"><b>Contraseña (Provisional):</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("passwordCliente").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-4" align="center">
                        <label for="direccionCliente"><b>Dirección</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("direccionCliente").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="sexoCliente"><b>Sexo:</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("sexoCliente").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-4" align="center">
                        <label for="fechaNacimientoCliente"><b>Fecha de Nacimiento</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("fechaNacimientoCliente").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="codigoCuenta"><b>Número de Primera Cuenta:</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("codigoCuenta").toString()); %>
                    </div>
                </div>
                    <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1 mb-4">
                        <div class="col-md-4"><label>¿Desea continuar con el registro del cliente?</label><br></div>
                        
                    </div>
                <div class="row justify-content-center mb-4">
                    <div class="col-md-2 justify-content-end" align="center">
                        <input type="submit" value="Continuar" class="btn btn-danger"/>
                    </div>
                    <div class="col-md-2 justify-content-start" align="center">
                        <a class="btn btn-outline-danger" href="gerente-registrar-cliente.jsp" role="button">Cancelar</a>
                    </div>
                </div>
            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>