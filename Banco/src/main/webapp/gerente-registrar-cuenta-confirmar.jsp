<%-- 
    Document   : gerente-registrar-cuenta-confirmar
    Created on : 13/11/2020, 17:59:38
    Author     : froi-pc
--%>

<%@page import="com.froi.entidades.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Verificar Datos de Cuenta</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "gerente-barra-herramientas.html" %>

        <!-- Creamos Ventana de registro de Cajero -->
        <form action="RegistrarCuenta" method="POST">
            <div class="mt-5"></div>
            <div class="container formulario"> 
                <div class="row justify-content-center mt-5">
                    <h1>Verificar Datos del Solicitante</h1> <% Cliente cliente = (Cliente) request.getSession().getAttribute("cliente"); %>
                </div>
                <div class="row justify-content-center pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-4" align="center">
                        <label for="codigoCuenta"><b>No. de Cuenta Nueva:</b></label><br>
                        <% out.println(request.getSession().getAttribute("codigoCuenta").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <div class="col-md-4" align="center">
                        <label for="dpiCliente"><b>DPI del Cliente:</b><br></label><br>
                        <% out.println(cliente.getDpi()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="codigoCliente"><b>Código del Cliente:</b><br></label><br>
                        <% out.println(cliente.getCodigo()); %>
                    </div>
                </div>
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <div class="col-md-4" align="center">
                        <label for="nombreCliente"><b>Nombre del Cliente:</b><br></label><br>
                        <% out.println(cliente.getNombre()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="turnoCajero"><b>Fecha de Nacimiento</b><br></label><br>
                        <% out.println(cliente.getBirth()); %>
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
                        <a class="btn btn-outline-danger" href="gerente-registrar-cuenta.jsp" role="button">Cancelar</a>
                    </div>
                </div>
            </div>
                    
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>