/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.cliente.EnviarSolicitud;
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
@WebServlet(name = "SolicitudDeAsociacionEnviar", urlPatterns = {"/SolicitudDeAsociacionEnviar"})
public class SolicitudDeAsociacionEnviar extends HttpServlet {

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
        
        String dpiCliente = request.getSession().getAttribute("dpi").toString();
        String cuenta = request.getSession().getAttribute("cuentaSolicitada").toString();
        int intentos = solicitud.intentoValido(cuenta, dpiCliente);
        
        if(solicitud.enviarSolicitud(dpiCliente, cuenta, intentos)) {
            request.setAttribute("mensaje", "¡¡¡Solicitud Enviada con Éxito!!!");
            request.getRequestDispatcher("cliente-solicitud-asociacion-datos.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "Error: Hubo un problema al tratar de enviar la solicitud");
            request.getRequestDispatcher("cliente-solicitud-asociacion-datos.jsp").forward(request, response);
        }
        
    }

}
