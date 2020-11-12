<%-- 
    Document   : inicio-subir-archivo
    Created on : 11/11/2020, 00:20:34
    Author     : froi-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file = "links.html"%>
        <title>Subir Archivo</title>
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
        
        <form action="LecturaArchivo" method="POST" enctype="multipart/form-data">
            <div class="container">
                <div class="row justify-content-center pt-4 mr-1 mt-5">
                    <div class="col-md-6 formulario">
                        
                        <div class="form-group mx-sm-5 pt3" align="center">
                            <h2>Seleccionar Archivo</h2>
                        </div>
                        
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Seleccione la ruta del archivo<br></label>
                            <input type="file" class="btn-block" name="ruta" accept="text/xml" value="Elegir"/>
                        </div>
                        
                        <div class="form-group mx-sm-4 pb-5">
                            <input type="submit" class="btn btn-danger ingresar btn-block" value="Subir Archivo"/>
                        </div>
                        
                    </div>
                </div>
            </div>
        </form>
        
        <%@include file="scripts.html" %>
    </body>
</html>
