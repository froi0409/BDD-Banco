/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.cliente.EnviarSolicitud;
import com.froi.cuenta.ObtenerDatosCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author froi-pc
 */
@WebServlet(name = "SolicitudDeAsociacionDatos", urlPatterns = {"/SolicitudDeAsociacionDatos"})
public class SolicitudDeAsociacionDatos extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EnviarSolicitud solicitud = new EnviarSolicitud(Conexion.getConnection());
        String cuenta = request.getParameter("cuenta");
        String dpiCliente = request.getSession().getAttribute("dpi").toString();

        if (!solicitud.existeCuenta(cuenta)) { //Verifica si existe la cuenta en el banco
            request.setAttribute("mensaje", "Error: La cuenta \"" + cuenta + "\" no existe en el banco");
            request.getRequestDispatcher("cliente-solicitud-asociacion-datos.jsp").forward(request, response);
        } else if (solicitud.estadoSolicitud(dpiCliente, cuenta) != null && solicitud.estadoSolicitud(dpiCliente, cuenta).equals("CUENTA ASOCIADA")){ //Verifica si la cuenta ya se encuentra asociada
            request.setAttribute("mensaje", "Error: La cuenta " + cuenta + " ya se encuentra asociada");
            request.getRequestDispatcher("cliente-solicitud-asociacion-datos.jsp").forward(request, response);
        } else if (solicitud.intentoValido(cuenta, dpiCliente) > 3) { //Verifica si no se han sobrepasado los intentos
            request.setAttribute("mensaje", "Error: Se ha sobrepasado el límite de intentos para asociar la cuenta \"" + cuenta + "\"");
            request.getRequestDispatcher("cliente-solicitud-asociacion-datos.jsp").forward(request, response);
        } else if (solicitud.cuentaPropia(cuenta, dpiCliente)) { //Verifica si es una cuenta propia
            request.setAttribute("mensaje", "Error: No se puede asociar una cuenta propia");
            request.getRequestDispatcher("cliente-solicitud-asociacion-datos.jsp").forward(request, response);
        } else if (solicitud.estadoSolicitud(dpiCliente, cuenta) != null && solicitud.estadoSolicitud(dpiCliente, cuenta).equals("EN ESPERA")) { //Verifica si ya se ha mandado una solicitud de asociación
            request.setAttribute("mensaje", "Error: Ya has enviado una solicitud de asociación a la cuenta " + cuenta + ". Espera a que la solicitud sea respondida para poder enviar otra");
            request.getRequestDispatcher("cliente-solicitud-asociacion-datos.jsp").forward(request, response);
        } else { //Envía la solicitud de asociacion.
            ObtenerDatosCuenta obtenerDatosCuenta = new ObtenerDatosCuenta(Conexion.getConnection());

            request.getSession().setAttribute("cuentaSolicitada", cuenta);
            request.getSession().setAttribute("propietarioCuentaSolicitada", obtenerDatosCuenta.propietario(cuenta));
            request.getRequestDispatcher("cliente-solicitud-asociacion-enviar.jsp").forward(request, response);
        }
        
    }

}
