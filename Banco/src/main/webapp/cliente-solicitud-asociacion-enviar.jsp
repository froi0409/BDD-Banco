<%-- 
    Document   : cliente-solicitud-asociacion-enviar
    Created on : 11/11/2020, 23:15:43
    Author     : froi-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Solicitar Asociación</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "cliente-barra-herramientas.html" %>

        <form action="SolicitudDeAsociacionEnviar" method="POST">
            <div class="container"> 
                <div class="row justify-content-center pt-5 mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-6 formulario">
                        <div class="form-group text-center pt-3">
                            <h1>Solicitud de Asociación</h1>
                        </div>
                        <div class="form-froup mx-sm-5 pt3">
                        </div>
                        <div class="form-group mx-sm-5 pt-3" align="center">
                            <label><b>Numero de la cuenta que desea asociar: </b></label><br><br>
                            <label><% out.print(request.getSession().getAttribute("cuentaSolicitada")); %></label>
                        </div>
                        <div class="form-group mx-sm-5 pt-3" align="center">
                            <label><b>Propietario de La cuenta: </b></label><br><br>
                            <label><% out.print(request.getSession().getAttribute("propietarioCuentaSolicitada")); %></label><br><br>
                        </div>
                        <div class="form-group mx-sm-5 pb-5"><!-- comment -->

                            <input type="submit" class="btn btn-danger ingresar btn-block" value="Enviar Solicitud"/>

                        </div>

                    </div>
                </div>
            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>
