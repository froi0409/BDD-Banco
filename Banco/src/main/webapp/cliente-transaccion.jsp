<%-- 
    Document   : cliente-transaccion
    Created on : 12/11/2020, 10:40:18
    Author     : froi-pc
--%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Transacción</title>
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
        
        <form action="TransaccionDatos" method="POST">
            <div class="pt-5 mt-5 mr-5"></div>
            <div class="container formulario pt-4"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1" align="center">
                    <h1>Transacción</h1>
                </div>
                <div class="row justify-content-center pt-1 mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-6">
                        <div class="form-group mx-sm-5 pb-5">
                            <label for="">Cuenta Origen </label>
                            <select name="cuentaOrigen" class="btn-block">
                                <%
                                ArrayList<String> datosPropios = (ArrayList<String>) request.getAttribute("listaCuentasPropias");

                                for(String element : datosPropios) {
                                    out.println("<option value=\"" + element + "\">" + element + "</option>");
                                }

                            %>         
                            </select>
                        </div>
                        <div class="form-group mx-sm-5 pb-5"><!-- comment -->
                            <label for="Monto">Monto a Transferir (Q):</label>
                            <input type="number" step=".01" class="form-control" placeholder="xxxx.xx" value="monto" name="monto"  oninvalid="setCustomValidity('Cantidad monetaria inválida. Por favor, ingrese una cantidad monetaria válida.')" min="0.01" required/>
                            
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="cuentaDestino">Cuenta Destino:</label>
                        <select name="cuentaDestino" class="btn-block">
                            <%
                            ArrayList<String[]> datos = (ArrayList<String[]>) request.getAttribute("listaCuentasAsociadas");

                            for(String[] element : datos) {
                                out.println("<option value=\"" + element[0] + "\">" + element[0] + " - " + element[1] + "</option>");
                            }

                            %>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center pt-1 mt-1 mr-1">
                    <div class="col-md-6" align="center">
                        <div><label>* Todos los campos son obligatorios</label><br></div>
                        <input type="submit" value="Realizar Transacción" class="btn btn-danger"/>
                    </div>
                </div>

            </div>
        </form>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>