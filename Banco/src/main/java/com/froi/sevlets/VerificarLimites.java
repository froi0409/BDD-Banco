/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

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
@WebServlet(name = "VerificarLimites", urlPatterns = {"/VerificarLimites"})
public class VerificarLimites extends HttpServlet {

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
        
        double limiteInferior = Double.parseDouble(request.getParameter("limiteInferior"));
        double limiteSuperior = Double.parseDouble(request.getParameter("limiteSuperior"));
        String filtro = request.getParameter("filtro");
        
        request.setAttribute("limiteInferior", limiteInferior);
        request.setAttribute("limiteSuperior", limiteSuperior);
        request.setAttribute("filtro", filtro);
        
        if (limiteInferior >= limiteSuperior) {
            request.setAttribute("mensaje", "Error: El límite superior debe se mayor al límite inferior");
            request.getRequestDispatcher("gerente-reporte-transacciones-por-cliente-filtro.jsp").forward(request, response);
            
        } else {
            request.getRequestDispatcher("gerente-reporte-transacciones-por-cliente.jsp").forward(request, response);
        }
        
    }

}
