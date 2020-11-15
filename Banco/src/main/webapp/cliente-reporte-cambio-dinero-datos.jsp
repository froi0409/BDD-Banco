<%-- 
    Document   : cliente-reporte-cambio-dinero-datos
    Created on : 15/11/2020, 03:54:20
    Author     : froi-pc
--%>

<%@page import="com.froi.cliente.ObtenerDatosCliente"%>
<%@page import="com.froi.banco.Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Reporte</title>
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
        
        <form action="ClienteReporteCambioDinero" method="POST">
            <div class="pt-5 mt-5 mr-5"></div>
            <div class="container formulario pt-4"> 
                <div class="row justify-content-center pt-1 mt-5 mr-1" align="center">
                    <h1>Ingrese Datos:</h1>
                </div>
                <div class="row justify-content-center pt-1 mt-5 mr-1"> <!-- Utilizamos el sistema de filas de bootstrap -->
                    
                    <div class="col-md-6">
                        <div class="form-group mx-sm-5 pb-5">
                            <label for="codigoCuenta">Cuenta a Consultar:</label>
                            <select name="codigoCuenta" class="btn-block">
                                <%
                                ObtenerDatosCliente datosCliente = new ObtenerDatosCliente(Conexion.getConnection());
                                ArrayList<String> cuentas = datosCliente.cuentasPropias(request.getSession().getAttribute("dpi").toString());
                                
                                for(String element : cuentas) {
                                    out.println("<option value=\"" + element + "\">" + element + "</option>");
                                }

                            %>         
                            </select>
                        </div>
                    </div>                    
                </div>
                <div class="row justify-content-center pt-1 mt-1 mr-1 mb-3">
                    <div class="col-md-3">
                        <label for="fechaInicial">Fecha Inicial:</label>
                        <input type="date" class="form-control" name="fechaInicial" required/>
                    </div>
                    <div class="col-md-3">
                        <label for="fechaFinal">Fecha Final:</label>
                        <input type="date" class="form-control" name="fechaFinal" required/>
                    </div>
                </div>
                <div class="row justify-content-center pt-1 mt-1 mr-1">
                    <div class="col-md-6" align="center">
                        <input type="submit" value="Consultar Estado de Cuenta" class="btn btn-danger"/>
                    </div>
                </div>
            </div>
        </form>
        
        
        <%@include file = "scripts.html"%>
    </body>
</html>