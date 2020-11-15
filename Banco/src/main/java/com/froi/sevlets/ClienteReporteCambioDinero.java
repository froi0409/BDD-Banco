/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.cliente.reportes.ReporteTransaccionesTiempo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author froi-pc
 */
@WebServlet(name = "ClienteReporteCambioDinero", urlPatterns = {"/ClienteReporteCambioDinero"})
public class ClienteReporteCambioDinero extends HttpServlet {

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
        
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        String codigoCuenta = request.getParameter("codigoCuenta");
        
        System.out.println(fechaInicial);
        System.out.println(fechaFinal);
        System.out.println(codigoCuenta);
        
        ReporteTransaccionesTiempo reporte = new ReporteTransaccionesTiempo(Conexion.getConnection(),fechaInicial,fechaFinal);
        
        ArrayList<String[]> estadoCuenta = reporte.obtenerTransacciones(codigoCuenta);
        request.setAttribute("estadoCuenta", estadoCuenta);
        request.setAttribute("codigoCuenta", codigoCuenta);
        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.getRequestDispatcher("cliente-reporte-cambio-dinero.jsp").forward(request, response);
        
    }

}
