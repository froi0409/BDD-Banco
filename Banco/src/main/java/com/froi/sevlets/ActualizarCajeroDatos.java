/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
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
@WebServlet(name = "ActualizarCajeroDatos", urlPatterns = {"/ActualizarCajeroDatos"})
public class ActualizarCajeroDatos extends HttpServlet {

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
        
        ObtenerDatosCajero datosCajero = new ObtenerDatosCajero(Conexion.getConnection());
        String codigoCajero = request.getParameter("codigoCajero");
        
        if(datosCajero.existsOther("codigo", codigoCajero)) {
            
            Cajero cajero = new Cajero();
            
            cajero.setCodigo(codigoCajero);
            cajero.setNombre(datosCajero.obtenerDato("nombre", codigoCajero));
            cajero.setTurno(datosCajero.obtenerDato("turno", codigoCajero));
            cajero.setDpi(datosCajero.obtenerDato("dpi", codigoCajero));
            cajero.setDireccion(datosCajero.obtenerDato("direccion", codigoCajero));
            cajero.setSexo(datosCajero.obtenerDato("sexo", codigoCajero));
            cajero.setPassword(datosCajero.obtenerDato("password", codigoCajero));
            
            request.getSession().setAttribute("cajero", cajero);
            request.getRequestDispatcher("gerente-actualizar-cajero.jsp").forward(request, response);
            
        } else {
            request.setAttribute("mensaje", "Error: Cajero no existente en el banco");
            request.getRequestDispatcher("gerente-actualizar-cajero-datos.jsp").forward(request, response);
        }
        
    }

}
