<%-- 
    Document   : gerente-registrar-cajero-confirmar
    Created on : 13/11/2020, 02:59:08
    Author     : froi-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Verificar Datos de Cajero</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "gerente-barra-herramientas.html" %>

        <!-- Creamos Ventana de registro de Cajero -->
        <form action="CajeroRegistrar" method="POST">
            <div class="mt-5"></div>
            <div class="container formulario"> 
                <div class="row justify-content-center mt-5">
                    <h1>Verificar Datos del Nuevo Cajero</h1>
                </div>
                <div class="row justify-content-center pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-4" align="center">
                        <label for="nombreCajero"><b>Nombre:</b></label><br>
                        <% out.println(request.getSession().getAttribute("nombreCajero").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="codigoCajero"><b>Codigo</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("codigoCajero").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <div class="col-md-4" align="center">
                        <label for="dpiCajero"><b>DPI:</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("dpiCajero").toString()); %>
                    </div>
                    <div class="col-md-4" align="center">
                        <label for="passwordCajero"><b>Contraseña (Provisional):</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("passwordCajero").toString()); %>
                    </div>
                </div>
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <div class="col-md-4" align="center">
                        <label for="direccionCajero"><b>Dirección</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("direccionCajero").toString()); %>
                    </div>
                    <div class="col-md-2" align="center">
                        <label for="turnoCajero"><b>Turno</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("turnoCajero").toString()); %>
                    </div>
                    <div class="col-md-2" align="center">
                        <label for="sexoCajero"><b>Sexo</b><br></label><br>
                        <% out.println(request.getSession().getAttribute("sexoCajero").toString()); %>
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
                        <a class="btn btn-outline-danger" href="gerente-registrar-cajero.jsp" role="button">Cancelar</a>
                    </div>
                </div>
            </div>
                    
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>