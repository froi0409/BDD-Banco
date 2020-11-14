/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.cuenta.ObtenerDatosCuenta;
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
@WebServlet(name = "DepositoDatos", urlPatterns = {"/DepositoDatos"})
public class DepositoDatos extends HttpServlet {

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
        
        ObtenerDatosCuenta datosCuenta = new ObtenerDatosCuenta(Conexion.getConnection());
        double dinero = Double.parseDouble(request.getParameter("monto"));
        
        DecimalFormat df = new DecimalFormat("#.00");
        String codigoCuenta = request.getParameter("numeroCuenta");
        String monto = df.format(dinero);
        
        if(datosCuenta.existsOther("codigo", codigoCuenta)) {
            
            String propietarioCuenta = datosCuenta.propietario(codigoCuenta);
            
            request.getSession().setAttribute("monto", monto);
            request.getSession().setAttribute("codigoCuenta", codigoCuenta);
            request.getSession().setAttribute("propietarioCuenta", propietarioCuenta);
            
            request.getRequestDispatcher("cajero-deposito-confirmar.jsp").forward(request, response);
            
        } else {
            request.setAttribute("mensaje", "Error: La cuenta " + codigoCuenta + " no existe dentro del banco");
            request.getRequestDispatcher("cajero-deposito-cliente.jsp").forward(request, response);
        }
        
    }

}
