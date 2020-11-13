/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.AnalizadorDeExistencia;
import com.froi.banco.ArchivoDeEntrada;
import com.froi.banco.Conexion;
import com.froi.banco.GeneradorContraseña;
import com.froi.banco.GeneradorCuenta;
import com.froi.cliente.ObtenerDatosCliente;
import com.froi.cuenta.ObtenerDatosCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author froi-pc
 */
@WebServlet(name = "ClienteVerDatos", urlPatterns = {"/ClienteVerDatos"})
@MultipartConfig
public class ClienteVerDatos extends HttpServlet {

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
        
        //Manejo del Archivo del DPI
        String path;
        ArchivoDeEntrada lector = new ArchivoDeEntrada(request,"ruta"); //Enviamos al constructor la propiedad request
        path = lector.getPath();//obtenemos el path del archivo en el servidor
        System.out.println("PATH " + path);
        
        request.getSession().setAttribute("pdfDpiCliente", path);
        
        //Manejo de los demás atributos del cliente
        GeneradorContraseña generadorContraseña = new GeneradorContraseña();
        GeneradorCuenta generadorCuenta = new GeneradorCuenta();
        ObtenerDatosCuenta obtenerDatosCuenta = new ObtenerDatosCuenta(Conexion.getConnection());
        AnalizadorDeExistencia existencia = new AnalizadorDeExistencia(Conexion.getConnection());
        
        String contraseña = generadorContraseña.generarContraseña();
        String codigo = request.getParameter("codigoCliente");
        String cuenta;
        do {
            cuenta = generadorCuenta.generarCuenta();
        } while (obtenerDatosCuenta.existsOther("codigo", cuenta));
        
        //Definimos los atributos que tendrá el cliente
        request.getSession().setAttribute("dpiCliente", request.getParameter("dpiCliente"));
        request.getSession().setAttribute("codigoCliente", codigo);
        request.getSession().setAttribute("nombreCliente", request.getParameter("nombreCliente"));
        request.getSession().setAttribute("fechaNacimientoCliente", request.getParameter("fechaNacimientoCliente"));
        request.getSession().setAttribute("direccionCliente", request.getParameter("direccionCliente"));
        request.getSession().setAttribute("sexoCliente", request.getParameter("sexoCliente"));
        request.getSession().setAttribute("passwordCliente", contraseña);
        
        //Definimos los atributos que tendrá la cuenta
        request.getSession().setAttribute("codigoCuenta", cuenta);
        
        if(existencia.codigo(codigo)) {
            System.out.println("F");
            request.setAttribute("mensaje", "Error: Ya existe un usuario con el código ingresado");
            request.getRequestDispatcher("gerente-registrar-cliente.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("gerente-registrar-cliente-confirmar.jsp").forward(request, response);
        }
        
    }

}
