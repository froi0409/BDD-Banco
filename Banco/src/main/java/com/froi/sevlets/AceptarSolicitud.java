/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.cliente.RespuestaSolicitud;
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
@WebServlet(name = "AceptarSolicitud", urlPatterns = {"/AceptarSolicitud"})
public class AceptarSolicitud extends HttpServlet {

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
        
        RespuestaSolicitud respuesta = new RespuestaSolicitud(Conexion.getConnection());
        String entrada = request.getParameter("cuenta");
        
        
        String[] partes = entrada.split("-");
        String dpiSolicitante = partes[0];
        String cuentaSolicitada = partes[1];
        
        if (respuesta.aceptar(dpiSolicitante, cuentaSolicitada)) {
            request.getSession().setAttribute("mensajeRespuesta", "Asociación de cuenta " + cuentaSolicitada + " se realizó con éxito");
            response.sendRedirect("/Banco/SolicitudDeAsociacionPendiente"); 
        } else {
            request.getSession().setAttribute("mensajeRespuesta", "Error al asociar la cuenta " + cuentaSolicitada + " se realizó con éxito");
            response.sendRedirect("/Banco/SolicitudDeAsociacionPendiente"); 
        }
        
    }

}
