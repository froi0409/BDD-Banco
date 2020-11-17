<%-- 
    Document   : cliente-transaccion-confirmar
    Created on : 12/11/2020, 21:41:42
    Author     : froi-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Confirmar Transacción</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "cliente-barra-herramientas.html" %>

        <form action="TransaccionConfirmar" method="POST">
            <div class="pt-2 mt-5 mr-2"></div>
            <div class="container formulario pt-2"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1" align="center">
                    <h1>Verificar Datos de la Transacción</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1" align="center"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-10">
                        <label><b>Cuenta Origen:</b></label><br>
                        <% out.println(request.getSession().getAttribute("cuentaOrigen")); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1" align="center">
                    <div class="col-md-5" align="center">
                        <label><b>Cuenta Destino:</b></label><br>
                        <% out.println(request.getSession().getAttribute("cuentaDestino")); %>
                    </div>
                    <div class="col-md-5" align="center">
                        <label><b>Propietario de la Cuenta Destino:</b></label><br>
                        <% out.println(request.getSession().getAttribute("propietarioCuentaDestino")); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1">
                    <div class="col-md-5" align="center">
                        <label><b>Monto a Transferir:</b></label><br>
                        <% out.println("Q " + request.getSession().getAttribute("monto")); %>
                    </div>
                    <div class="col-md-5" align="center">
                        <label><b>Fecha:</b></label><br>
                        <% out.println(request.getSession().getAttribute("fecha")); %>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1 mb-4">
                    <div class="col-md-5" align="center">
                        <div><label>¿Desea continuar con la transferencia?</label><br></div>
                        <input type="submit" value="Continuar" class="btn btn-danger"/>
                    </div>
                </div>

            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>