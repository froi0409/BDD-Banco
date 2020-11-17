/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.AnalizadorDeExistencia;
import com.froi.banco.Conexion;
import com.froi.banco.Encriptador;
import com.froi.cliente.ActualizarDatoCliente;
import com.froi.entidades.Cliente;
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
@WebServlet(name = "ActualizarCliente", urlPatterns = {"/ActualizarCliente"})
public class ActualizarCliente extends HttpServlet {


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
        AnalizadorDeExistencia existe = new AnalizadorDeExistencia(Conexion.getConnection());
        ActualizarDatoCliente actualizarDatoCliente = new ActualizarDatoCliente(Conexion.getConnection());
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        String dpiCliente = cliente.getDpi();
        String codigoGerente = request.getSession().getAttribute("usuario").toString();
        String campo = request.getParameter("campo");
        String datoAntiguo;
        String datoNuevo;
        boolean codigoExistente = false;
        
        if (campo.equals("birth")) {
            datoAntiguo = cliente.getBirth();
            datoNuevo = request.getParameter("nuevoDatoFecha");
        } else if (campo.equals("password")) {
            Encriptador encriptador = new Encriptador();
            datoAntiguo = cliente.getPassword();
            datoNuevo = encriptador.encriptar(request.getParameter("nuevoDatoPassword")); //encriptamos la contraseña
        } else if (campo.equals("sexo")) {
            datoAntiguo = cliente.getSexo();
            datoNuevo = request.getParameter("nuevoDatoOpcion");
        } else if (campo.equals("direccion")) {
            datoAntiguo = cliente.getDireccion();
            datoNuevo = request.getParameter("nuevoDatoTexto");
        } else if (campo.equals("codigo")) {
            datoAntiguo = cliente.getCodigo();
            datoNuevo = request.getParameter("nuevoDatoTexto");
            if(existe.codigo(datoNuevo)){
                request.setAttribute("mensaje", "Error: Ya existe un usuario con el código solicitado, favor intentar de nuevo");
                System.out.println("Entra");
                request.getRequestDispatcher("gerente-actualizar-cliente-datos.jsp").forward(request, response);
                response.sendRedirect("#");
                codigoExistente = true;
                System.out.println("por algún motivo sigue");
            }
        } else if (campo.equals("nombre")) {
            datoAntiguo = cliente.getNombre();
            datoNuevo = request.getParameter("nuevoDatoTexto");
        } else {
            datoNuevo = null;
            datoAntiguo = null;
        }
        
        System.out.println("campo: " + campo);
        
        if(!codigoExistente && actualizarDatoCliente.actualizar(campo, datoAntiguo, datoNuevo, dpiCliente, codigoGerente)) {
            request.setAttribute("mensaje", "El campo " + campo + " del cliente fue actualizado a " + datoNuevo + " con éxito");
            request.getRequestDispatcher("gerente-actualizar-cliente-datos.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "Error: Hubo un error al tratar de actualizar la información");
            request.getRequestDispatcher("gerente-actualizar-cliente-datos.jsp").forward(request, response);
        }
        
    }

}
