/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.transaccion.TransaccionPortal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author froi-pc
 */
@WebServlet(name = "TransaccionDatos", urlPatterns = {"/TransaccionDatos"})
public class TransaccionDatos extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

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
        
        TransaccionPortal transaccion = new TransaccionPortal(Conexion.getConnection());
        String cuentaOrigen = request.getParameter("cuentaOrigen");
        String cuentaDestino = request.getParameter("cuentaDestino");
        double dinero = Double.parseDouble(request.getParameter("monto"));
        
        DecimalFormat df = new DecimalFormat("#.00");
        String monto = df.format(dinero);
        
        if (transaccion.comprobarValidezTransaccion(cuentaOrigen, dinero)) {
            
        } else {
            request.getSession().setAttribute("mensajeRespuesta", "Error: Usted no posee Q " + monto + " disponibles en la cuenta " + cuentaOrigen);
            response.sendRedirect("/Banco/TransaccionInicio"); 
        }
        
    }

}
