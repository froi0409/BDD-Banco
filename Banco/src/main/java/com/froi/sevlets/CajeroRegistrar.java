/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.banco.GeneradorContraseña;
import com.froi.entidades.Cajero;
import com.froi.ingresadores.IngresadorCajero;
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
@WebServlet(name = "CajeroRegistrar", urlPatterns = {"/CajeroRegistrar"})
public class CajeroRegistrar extends HttpServlet {

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
        
        Cajero cajero = new Cajero();
        
        //Le damos atributos al cajero
        cajero.setCodigo(request.getSession().getAttribute("codigoCajero").toString());
        cajero.setNombre(request.getSession().getAttribute("nombreCajero").toString());
        cajero.setTurno(request.getSession().getAttribute("turnoCajero").toString());
        cajero.setDpi(request.getSession().getAttribute("dpiCajero").toString());
        cajero.setDireccion(request.getSession().getAttribute("direccionCajero").toString());
        cajero.setSexo(request.getSession().getAttribute("sexoCajero").toString());
        cajero.setPassword(request.getSession().getAttribute("passwordCajero").toString());
        
        IngresadorCajero ingresador = new IngresadorCajero(Conexion.getConnection(), cajero);
        if (ingresador.ingresoNormal()) {
            request.setAttribute("mensaje", "¡¡¡Cajero Registrado Con Éxito!!!");
        } else {
            request.setAttribute("mensaje", "Error: Se produjo un error al tratar de registrar al cajero");
        }
        
        request.getRequestDispatcher("gerente-registrar-cajero.jsp").forward(request, response);
        
    }

}
