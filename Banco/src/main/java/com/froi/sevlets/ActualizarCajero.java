/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.AnalizadorDeExistencia;
import com.froi.banco.Conexion;
import com.froi.banco.Encriptador;
import com.froi.cajero.ActualizarDatoCajero;
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
@WebServlet(name = "ActualizarCajero", urlPatterns = {"/ActualizarCajero"})
public class ActualizarCajero extends HttpServlet {

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
        
        AnalizadorDeExistencia existe = new AnalizadorDeExistencia(Conexion.getConnection());
        ActualizarDatoCajero actualizarDatosCajero = new ActualizarDatoCajero(Conexion.getConnection());
        Cajero cajero = (Cajero) request.getSession().getAttribute("cajero");
        
        String codigoGerente = request.getSession().getAttribute("usuario").toString();
        String codigoCajero = cajero.getCodigo();
        String campo = request.getParameter("campo");
        String datoAntiguo;
        String datoNuevo;
        boolean codigoExistente = false;
        
        if (campo.equals("codigo")) {
            datoAntiguo = cajero.getCodigo();
            datoNuevo = request.getParameter("nuevoDatoTexto");
            if(existe.codigo(datoNuevo)){
                request.setAttribute("mensaje", "Error: Ya existe un usuario con el código solicitado, favor intentar de nuevo");
                request.getRequestDispatcher("gerente-actualizar-cajero-datos.jsp").forward(request, response);
                response.sendRedirect("#");
                codigoExistente = true;
            }
        } else if (campo.equals("nombre")) {
            datoAntiguo = cajero.getNombre();
            datoNuevo = request.getParameter("nuevoDatoTexto");
        } else if (campo.equals("turno")) {
            datoAntiguo = cajero.getTurno();
            datoNuevo = request.getParameter("nuevoDatoOpcionTurno");
        } else if (campo.equals("direccion")) {
            datoAntiguo = cajero.getDireccion();
            datoNuevo = request.getParameter("nuevoDatoTexto");
        } else if (campo.equals("sexo")) {
            datoAntiguo = cajero.getSexo();
            datoNuevo = request.getParameter("nuevoDatoOpcion");
        } else if (campo.equals("password")) {
            Encriptador encriptador = new Encriptador();
            datoAntiguo = cajero.getPassword();
            datoNuevo = encriptador.encriptar(request.getParameter("nuevoDatoPassword"));
        } else {
            datoNuevo = null;
            datoAntiguo = null;
        } 
        
        if (!codigoExistente && actualizarDatosCajero.actualizar(campo, datoAntiguo, datoNuevo, codigoCajero, codigoGerente)) {
            request.setAttribute("mensaje", "El campo " + campo + " del cajero fue actualizado a " + datoNuevo + " con éxito");
            request.getRequestDispatcher("gerente-actualizar-cajero-datos.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "Error: Hubo un error al tratar de actualizar la información");
            request.getRequestDispatcher("gerente-actualizar-cajero-datos.jsp").forward(request, response);
        }
        
    }

}
