/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.entidades.Cliente;
import com.froi.entidades.Cuenta;
import com.froi.ingresadores.IngresadorCliente;
import com.froi.ingresadores.IngresadorCuenta;
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
@WebServlet(name = "ClienteRegistrar", urlPatterns = {"/ClienteRegistrar"})
public class ClienteRegistrar extends HttpServlet {

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
        
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        
        //Agregamos atributos del cliente
        cliente.setDpi(request.getSession().getAttribute("dpiCliente").toString());
        cliente.setCodigo(request.getSession().getAttribute("codigoCliente").toString());
        cliente.setNombre(request.getSession().getAttribute("nombreCliente").toString());
        cliente.setBirth(request.getSession().getAttribute("fechaNacimientoCliente").toString());
        cliente.setDireccion(request.getSession().getAttribute("direccionCliente").toString());
        cliente.setSexo(request.getSession().getAttribute("sexoCliente").toString());
        cliente.setDpiPdf(request.getSession().getAttribute("pdfDpiCliente").toString());
        cliente.setPassword(request.getSession().getAttribute("passwordCliente").toString());
        
        cuenta.setCodigo(request.getSession().getAttribute("codigoCuenta").toString());
        cuenta.setCredito(0);
        cuenta.setCliente(request.getSession().getAttribute("dpiCliente").toString());
        
        IngresadorCliente ingresadorCliente = new IngresadorCliente(Conexion.getConnection(),cliente);
        IngresadorCuenta ingresadorCuenta = new IngresadorCuenta(Conexion.getConnection(),cuenta);
        if(ingresadorCliente.ingresoNormal() && ingresadorCuenta.ingresoNormal()) {
            request.setAttribute("mensaje", "¡¡¡Cliente se ha registrado con éxito!!!");
            request.getRequestDispatcher("gerente-registrar-cliente.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "Error: Se ha producido un error al tratar de ingresar al cliente.");
            request.getRequestDispatcher("gerente-registrar-cliente.jsp").forward(request, response);
        }
        
    }

}
