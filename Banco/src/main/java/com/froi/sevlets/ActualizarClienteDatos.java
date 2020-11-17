/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.banco.GeneradorCuenta;
import com.froi.cliente.ObtenerDatosCliente;
import com.froi.cuenta.ObtenerDatosCuenta;
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
@WebServlet(name = "ActualizarClienteDatos", urlPatterns = {"/ActualizarClienteDatos"})
public class ActualizarClienteDatos extends HttpServlet {

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
        
        ObtenerDatosCliente datosCliente = new ObtenerDatosCliente(Conexion.getConnection());
        String dpiCliente = request.getParameter("dpiCliente");
        
        if(datosCliente.exists(dpiCliente)) {
            System.out.println("Si entró");
            Cliente cliente = new Cliente();
            String codigoCliente = datosCliente.obtenerCodigo(dpiCliente);
            
            cliente.setDpi(dpiCliente);
            cliente.setCodigo(codigoCliente);
            cliente.setNombre(datosCliente.obtenerDato("nombre", codigoCliente));
            cliente.setBirth(datosCliente.obtenerDato("birth", codigoCliente));
            cliente.setDireccion(datosCliente.obtenerDato("direccion", codigoCliente));
            cliente.setSexo(datosCliente.obtenerDato("sexo", codigoCliente));
            cliente.setPassword(datosCliente.obtenerDato("password", codigoCliente));
            
            request.getSession().setAttribute("cliente", cliente);
            request.getRequestDispatcher("gerente-actualizar-cliente.jsp").forward(request, response);
            
        } else {
            System.out.println("Error");
            request.setAttribute("mensaje", "Error: Cliente no existente en el banco");
            request.getRequestDispatcher("gerente-actualizar-cliente-datos.jsp").forward(request, response);
        }
        
    }

}
