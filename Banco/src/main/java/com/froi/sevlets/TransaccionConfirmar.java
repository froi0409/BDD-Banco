/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets;

import com.froi.banco.Conexion;
import com.froi.entidades.Transaccion;
import com.froi.transaccion.TransaccionPortal;
import java.io.IOException;
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
@WebServlet(name = "TransaccionConfirmar", urlPatterns = {"/TransaccionConfirmar"})
public class TransaccionConfirmar extends HttpServlet {

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
        
        TransaccionPortal transferir = new TransaccionPortal(Conexion.getConnection());
        Transaccion transaccion = new Transaccion();
        
        LocalDateTime locaDate = LocalDateTime.now();
        int hora  = locaDate.getHour();
        int minutos = locaDate.getMinute();
        int segundos = locaDate.getSecond();
        
        Calendar c = new GregorianCalendar();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        String año = Integer.toString(c.get(Calendar.YEAR));
        
        //Establecemos código de la transacción
        String codigo = request.getSession().getAttribute("cuentaOrigen").toString() + dia + mes + año + hora + minutos + segundos;
        double monto = Double.parseDouble(request.getSession().getAttribute("monto").toString());
        
        transaccion.setCodigo(codigo);
        transaccion.setCuentaDestino(request.getSession().getAttribute("cuentaDestino").toString());
        transaccion.setCuentaOrigen(request.getSession().getAttribute("cuentaOrigen").toString());
        transaccion.setTipo("BANCA");
        transaccion.setMonto(Double.parseDouble(request.getSession().getAttribute("monto").toString()));
        
        //Eliminamos los atributos de la sesión (unicamente para evitar conflictos)
        request.getSession().removeAttribute("cuentaDestino");
        request.getSession().removeAttribute("cuentaOrigen");
        request.getSession().removeAttribute("monto");
        
        if(transferir.realizarTransferencia(monto, transaccion)) {
            request.getSession().setAttribute("mensajeRespuesta", "Transferencia realizada con éxito");
            response.sendRedirect("/Banco/TransaccionInicio");
        } else {
            request.getSession().setAttribute("mensajeRespuesta", "Error: Hubo un problema al ejecutar la Transaccion");
            response.sendRedirect("/Banco/TransaccionInicio");
        }
        
    }

}
