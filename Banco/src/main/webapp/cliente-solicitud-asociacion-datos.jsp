<%-- 
    Document   : cliente-solicitud-asociacion-datos
    Created on : 11/11/2020, 23:30:34
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file = "cabecera.jsp" %>
        <%@include file = "cliente-barra-herramientas.html" %>

        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-secondary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
        </c:choose>
        
        <form action="SolicitudDeAsociacionDatos" method="POST">
            <div class="container"> 
                <div class="row justify-content-center pt-5 mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-6 formulario">
                        <div class="form-group text-center pt-3">
                            <h1>Solicitud de Asociación</h1>
                        </div>
                        <div class="form-froup mx-sm-5 pt3">
                        </div>
                        <div class="form-group mx-sm-5 pt-3">
                            <label for="cuenta">Numero de la cuenta que desea asociar: </label>
                            <input type="text" class="form-control" placeholder="Ingrese Cuenta" name="cuenta" required/>
                        </div>
                        <div class="form-group mx-sm-5 pb-5"><!-- comment -->

                            <input type="submit" class="btn btn-danger ingresar btn-block" value="Enviar"/>

                        </div>

                    </div>
                </div>
            </div>
        </form>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>
