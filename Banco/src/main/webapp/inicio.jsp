<%-- 
    Document   : inicio.jsp
    Created on : 10/11/2020, 19:03:22
    Author     : froi-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Banco "El Billetón"</title>
    </head>
    <body>
        
        <div class="container-fluid">

            <!-- Añadimos la fila que será la cabecera de la página -->
            <div class="row fondoInicio align-items-center">

                <div class="col-3" align="left">
                    <h2>BANCO EL BILLETÓN</h2>
                </div>
                <div class="col-6" align="center">
                </div>
                <div class="col-3" align="right">
                    &nbsp;&nbsp;
                    <form action="AnalizadorDatosLogin" method="POST" class="form">
                        <button type="submit" class="btn btn-outline-light form">Iniciar Sesión</button>
                    </form>
                </div>

            </div>
            
        </div>
        <%@include file="inicio-barra-herramientas.html" %>

        <%@include file = "scripts.html"%>
    </body>
</html>