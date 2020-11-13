/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.banco.GeneradorContraseña;
import com.froi.cajero.ObtenerDatosCajero;
import com.froi.entidades.Cajero;
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
@WebServlet(name = "CajeroVerDatos", urlPatterns = {"/CajeroVerDatos"})
public class CajeroVerDatos extends HttpServlet {

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
        
        GeneradorContraseña generadorContraseña = new GeneradorContraseña();
        String contraseña = generadorContraseña.generarContraseña();
        String codigo = request.getParameter("codigoCajero");
        String dpi = request.getParameter("dpiCajero");
        
        ObtenerDatosCajero obtenerDatos = new ObtenerDatosCajero(Conexion.getConnection());
        
        request.getSession().setAttribute("codigoCajero", request.getParameter("codigoCajero"));
        request.getSession().setAttribute("nombreCajero", request.getParameter("nombreCajero"));
        request.getSession().setAttribute("turnoCajero", request.getParameter("turnoCajero"));
        request.getSession().setAttribute("dpiCajero", request.getParameter("dpiCajero"));
        request.getSession().setAttribute("direccionCajero", request.getParameter("direccionCajero"));
        request.getSession().setAttribute("sexoCajero", request.getParameter("sexoCajero"));
        request.getSession().setAttribute("passwordCajero", contraseña);
        
        if(obtenerDatos.existsOther("codigo", codigo)) {
            request.setAttribute("mensaje", "Error: Ya existe un cajero con el código ingresado");
            request.getRequestDispatcher("gerente-registrar-cajero.jsp").forward(request, response);
        } else if (obtenerDatos.existsOther("dpi", dpi)) {
            request.setAttribute("mensaje", "Error: Ya existe un cajero con el DPI ingresado");
            request.getRequestDispatcher("gerente-registrar-cajero.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("gerente-registrar-cajero-confirmar.jsp").forward(request, response);
        }
        
    }
    
}
