/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.AnalizadorDeExistencia;
import com.froi.banco.Conexion;
import com.froi.cliente.ObtenerDatosCliente;
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
@WebServlet(name = "AnalizadorDatosLogin", urlPatterns = {"/AnalizadorDatosLogin"})
public class AnalizadorDatosLogin extends HttpServlet {

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
        
        AnalizadorDeExistencia analizador = new AnalizadorDeExistencia(Conexion.getConnection());
        Conexion conexion = new Conexion();
        
        String usuario = "";
        String password = "";
        
        usuario += request.getParameter("usuario");
        password += request.getParameter("password");
       
        if (!analizador.analizar()) { //analiza si la base de datos está vacia
            
            if (usuario.equals(conexion.getUser()) && password.equals(conexion.getPassword())) { //analiza si el usuario y contraseña corresponden a los datos del sistema
                request.getRequestDispatcher("inicio-subir-archivo.jsp").forward(request, response); //Redirecciona a la pantalla de la subida de archivos, siempre que se ingrese el usuario y password del administrador de la base de datos
            }
            request.setAttribute("mensaje", "Sistema Vacío, favor contactar al Banco");
            request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
        } else {
            if (usuario.equals("null") && password.equals("null")) {
                request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
            }
            
            if (analizador.credenciales("CLIENTE", usuario, password)) { //analiza si las credenciales corresponden a las de un cliente
                ObtenerDatosCliente obtener = new ObtenerDatosCliente(Conexion.getConnection());
                
                String dpi = obtener.obtenerDato("dpi", usuario);
                
                request.getSession().setAttribute("dpi", dpi);
                request.getSession().setAttribute("usuario", usuario);
                request.getRequestDispatcher("cliente-inicio.jsp").forward(request, response);
            } else if (analizador.credenciales("CAJERO", usuario, password)) { //analiza si las credenciales correspondena a las de un cajero
                request.getSession().setAttribute("usuario", usuario);
                request.getRequestDispatcher("cajero-inicio.jsp").forward(request, response);
            } else if (analizador.credenciales("GERENTE", usuario, password)) { //analiza si las credenciales corresponden a las de un gerente
                request.getSession().setAttribute("usuario", usuario);
                request.getRequestDispatcher("gerente-inicio.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje", "Usuario o contraseña incorrecta, favor de revisar sus datos.");
                request.getRequestDispatcher("inicio-sesion.jsp").forward(request, response);
            }
            
        }
        
    }
}
