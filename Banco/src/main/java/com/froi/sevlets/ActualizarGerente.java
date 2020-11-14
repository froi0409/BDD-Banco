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
import com.froi.gerente.ActualizarDatoGerente;
import com.froi.gerente.ObtenerDatosGerente;
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
@WebServlet(name = "ActualizarGerente", urlPatterns = {"/ActualizarGerente"})
public class ActualizarGerente extends HttpServlet {

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
        ActualizarDatoGerente actualizarDatosGerente = new ActualizarDatoGerente(Conexion.getConnection());
        ObtenerDatosGerente datosGerente = new ObtenerDatosGerente(Conexion.getConnection());
        
        String codigoGerente = request.getSession().getAttribute("usuario").toString();
        String campo = request.getParameter("campo");
        String datoAntiguo;
        String datoNuevo;
        boolean codigoExistente = false;
        
        if (campo.equals("codigo")) {
            datoAntiguo = codigoGerente;
            datoNuevo = request.getParameter("nuevoDatoTexto");
            if(existe.codigo(datoNuevo)){
                request.setAttribute("mensaje", "Error: Ya existe un usuario con el código solicitado, favor intentar de nuevo");
                request.getRequestDispatcher("gerente-actualizar-cajero-datos.jsp").forward(request, response);
                response.sendRedirect("#");
                codigoExistente = true;
            }
            request.getSession().setAttribute("usuario", codigoGerente);
        } else if (campo.equals("nombre")) {
            datoAntiguo = datosGerente.obtenerDato("nombre", codigoGerente);
            datoNuevo = request.getParameter("nuevoDatoTexto");
        } else if (campo.equals("turno")) {
            datoAntiguo = datosGerente.obtenerDato("turno", codigoGerente);
            datoNuevo = request.getParameter("nuevoDatoOpcionTurno");
        } else if (campo.equals("direccion")) {
            datoAntiguo = datosGerente.obtenerDato("direccion", codigoGerente);
            datoNuevo = request.getParameter("nuevoDatoTexto");
        } else if (campo.equals("sexo")) {
            datoAntiguo = datosGerente.obtenerDato("sexo", codigoGerente);
            datoNuevo = request.getParameter("nuevoDatoOpcion");
        } else if (campo.equals("password")) {
            Encriptador encriptador = new Encriptador();
            datoAntiguo = datosGerente.obtenerDato("password", codigoGerente);
            datoNuevo = encriptador.encriptar(request.getParameter("nuevoDatoPassword"));
        } else {
            datoNuevo = null;
            datoAntiguo = null;
        } 
        
        if (!codigoExistente && actualizarDatosGerente.actualizar(campo, datoAntiguo, datoNuevo, codigoGerente)) {
            request.setAttribute("mensaje", "El campo " + campo + " fue actualizado a " + datoNuevo + " con éxito");
            request.getRequestDispatcher("gerente-actualizar-gerente.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "Error: Hubo un error al tratar de actualizar su información");
            request.getRequestDispatcher("gerente-actualizar-gerente.jsp").forward(request, response);
        }
        
    }

}
