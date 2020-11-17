/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.cuenta.ObtenerDatosCuenta;
import com.froi.entidades.Transaccion;
import com.froi.transaccion.TransaccionCajero;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author froi-pc
 */
@WebServlet(name = "RetiroRealizar", urlPatterns = {"/RetiroRealizar"})
public class RetiroRealizar extends HttpServlet {

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
        
        ObtenerDatosCuenta datosCuenta = new ObtenerDatosCuenta(Conexion.getConnection());
        TransaccionCajero retiro = new TransaccionCajero(Conexion.getConnection());
        Transaccion transaccion = new Transaccion();
        String codigoCajero = request.getSession().getAttribute("usuario").toString();
        String codigoCuenta = request.getSession().getAttribute("codigoCuenta").toString();
        double creditoCuenta = datosCuenta.getCredito(codigoCuenta);
        
        LocalDateTime locaDate = LocalDateTime.now();
        int hora  = locaDate.getHour();
        int minutos = locaDate.getMinute();
        int segundos = locaDate.getSecond();
        
        Calendar c = new GregorianCalendar();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        String año = Integer.toString(c.get(Calendar.YEAR));
        
        //Establecemos código de la transacción
        String codigo = request.getSession().getAttribute("codigoCuenta").toString() + dia + mes + año + hora + minutos + segundos;
        String dinero = request.getSession().getAttribute("monto").toString();
        double monto = -Double.parseDouble(dinero);
        
        transaccion.setCodigo(codigo);
        transaccion.setCuentaDestino(codigoCuenta);
        transaccion.setMonto(monto);
        transaccion.setCajero(codigoCajero);
        
        //Eliminamoslos atributos de la sesión (Seguridad)
        request.getSession().removeAttribute("codigoCuenta");
        request.getSession().removeAttribute("propietarioCuenta");
        request.getSession().removeAttribute("monto");
        request.getSession().removeAttribute("dpiPropietario");
        
        System.out.println("----------------------------");
        System.out.println(creditoCuenta);
        System.out.println(monto*-1);
        
        if (creditoCuenta >= (monto*-1)) {
            if (retiro.realizar(transaccion, monto,"DEBITO")) {
                request.setAttribute("mensaje", "¡¡¡Retiro de -Q " + dinero + " de la cuenta " + codigoCuenta + " realizado con éxito!!!");
                request.getRequestDispatcher("cajero-retiro-cliente.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje", "Error: Hubo un error al realizar la transferencia");
                request.getRequestDispatcher("cajero-retiro-cliente.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mensaje", "Error: Usted no posee la cantidad de Q " + dinero + "en su cuenta, por lo tanto no se pudo proceder con el retiro");
            request.getRequestDispatcher("cajero-retiro-cliente.jsp").forward(request, response);
        }
        
    }

}
