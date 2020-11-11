<%-- 
    Document   : inicio-sesion
    Created on : 10/11/2020, 20:52:12
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Inicio de Sesión</title>
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
                    <form action="inicio-sesion.jsp" method="POST" class="form">
                        <h3>&nbsp;&nbsp;&nbsp;Inicio de Sesión</h3>
                    </form>
                </div>

            </div>
            
        </div>
        <%@include file="inicio-barra-herramientas.html" %>
        
        <c:choose>
            <c:when test = "${mensaje != null}">
                <div class="alert alert-primary container mt-5" align="center" role="alert">
                    ${mensaje}
                </div>
            </c:when>
        </c:choose>
        
        <!-- Creamos la ventana del login -->
        <form action="AnalizadorDatosLogin" method="POST">
            <div class="container"> 
                <div class="row justify-content-center pt-5 mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-6 formulario">
                        <div class="form-group text-center pt-3">
                            <h1>Iniciar Sesión</h1>
                        </div>
                        <div class="form-froup mx-sm-5 pt3">
                        </div>
                        <div class="form-group mx-sm-5 pt-3">
                            <label for="user">Usuario</label>
                            <input type="text" class="form-control" placeholder="Ingrese Usuario" name="usuario" required/>
                        </div>
                        <div class="form-group mx-sm-5 pb-3">
                            <label for="user">Contraseña</label>
                            <input type="password" class="form-control" placeholder="Ingrese Usuario" name="password" required/>
                        </div>
                        <div class="form-group mx-sm-5 pb-5"><!-- comment -->

                            <input type="submit" class="btn btn-danger ingresar btn-block" value="Ingresar"/>

                        </div>

                    </div>
                </div>
            </div>
        </form>
        
        <%@include file = "scripts.html"%>
    </body>
</html>
