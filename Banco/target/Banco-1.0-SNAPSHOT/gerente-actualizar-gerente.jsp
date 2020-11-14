<%-- 
    Document   : gerente-actualizar-gerente
    Created on : 14/11/2020, 02:33:43
    Author     : froi-pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Actualizar Cajero</title>
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
        
        <form action="ActualizarCajero" method="POST">
            <div class="pt-2 mt-5 mr-2"></div>
            <div class="container formulario pt-2"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1">
                    <h1>Ingrese Datos:</h1>
                </div>
                <div class="row justify-content-center ml-2 pt- mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    <div class="col-md-4">
                        <label for="campo">Atributo a Actualizar:</label><br>
                        <select id="campo" name="campo" class="btn-block" onclick="tipoEntrada2()">
                            <option value="codigo">Usuario (Codigo)</option>
                            <option value="nombre">Nombre</option>
                            <option value="turno">Turno</option>
                            <option value="direccion">Dirección</option>
                            <option value="password">Contraseña</option>
                            <option value="sexo">Sexo</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label>Dato Nuevo:</label><br>
                        <input type="text" id="texto" class="form-control" name="nuevoDatoTexto" style="display: block" required/>
                        <input type="date" id="fecha" class="form-control" name="nuevoDatoFecha" style="display: none"/>
                        <input type="password" id="contraseña" class="form-control" name="nuevoDatoPassword" style="display: none"/>
                        <select class="btn-block" id="opcion" name="nuevoDatoOpcion" style="display: none">
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select>
                        <select class="btn-block" id="opcionTurno" class="form-control" name="nuevoDatoOpcionTurno" style="display: none">
                            <option value="MATUTINO">Matutino</option>
                            <option value="VESPERTINO">Vespertino</option>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center ml-2 pt-1 mt-5 mr-1 mb-4">
                    <div class="col-md-5" align="center">
                        <input type="submit" value="Aceptar" class="btn btn-danger"/>
                    </div>
                </div>
            </div>
        </form>
        
        <div style="margin-top: 100px; margin-bottom: 100px; padding-left: 300px; padding-right: 300px;n" class="container-fluid">
            <h4>Datos del Cliente:</h4>
                <table class="table table-bordered">
                    <thead>
                        <tr class="table-secondary">
                            <th scope="col">Usuario</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Turno</th>
                            <th scope="col">Dirección</th>
                            <th scope="col">Sexo</th>
                            <th scope="col">Contraseña</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <%
                        Cajero cajero = (Cajero) request.getSession().getAttribute("cajero");
                        out.println("<tr>");
                        out.println("<td>" + cajero.getCodigo() + "</td>");
                        out.println("<td>" + cajero.getNombre() + "</td>");
                        out.println("<td>" + cajero.getTurno() + "</td>");
                        out.println("<td>" + cajero.getDireccion() + "</td>");
                        out.println("<td>" + cajero.getSexo() + "</td>");
                        out.println("<td>" + cajero.getPassword() + "</td>");
                        out.println("</tr>");
                        %>
                    </tbody>
                </table>
        </div>
        
        <script src="estilos/funciones.js"></script>
        <%@include file = "scripts.html"%>
    </body>
</html>
