/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.gerente.EstablecerLimiteConsulta;
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
@WebServlet(name = "EstablecerLimites", urlPatterns = {"/EstablecerLimites"})
public class EstablecerLimites extends HttpServlet {

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
        
        double limite1 = Double.parseDouble(request.getParameter("limiteTransferencias"));
        double limite2 = Double.parseDouble(request.getParameter("limiteTransferenciasSumadas"));
        EstablecerLimiteConsulta establecerLimite = new EstablecerLimiteConsulta(Conexion.getConnection());
        
        
        if (limite2 <= limite1) {
            request.setAttribute("mensaje", "Error: El límite de transacciones sumadas debe ser mayor al limite de transacciones");
            request.getRequestDispatcher("gerente-establecer-limites.jsp").forward(request, response);
        } else if (!establecerLimite.establecerLimites(limite1, limite2)) {
            request.setAttribute("mensaje", "Error: Ocurrió un error inesperado al tratar de actualizar los límites");
            request.getRequestDispatcher("gerente-establecer-limites.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "¡¡¡Se han actualizado los límites con éxito!!!");
            request.getRequestDispatcher("gerente-establecer-limites.jsp").forward(request, response);
        }
            
    }

}
