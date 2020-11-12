/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

import com.froi.entidades.Cajero;
import com.froi.entidades.Gerente;
import com.froi.entidades.Transaccion;
import com.froi.lectores.Lector;
import com.froi.lectores.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author froi-pc
 */
public class LectorXml {
    
    /**
     * Lee un archivo xml con la estructura definida por el Banco
     * @param path Ruta que el archivo tiene (normalmente dentro del servidor)
     */
    public void read(String path){
        try {
            
            ArrayList<String> errores = new ArrayList<>();
            Lector lector = new Lector();

            Gerente gerente = new Gerente();
            LectorGerente lecGerente = new LectorGerente(errores);
            lector.leerTag(path, "GERENTE", lecGerente, gerente);

            LectorCliente lecCliente = new LectorCliente(errores);
            lecCliente.read(path);
            
            
            Cajero cajero = new Cajero();
            LectorCajero lecCajero = new LectorCajero(errores);
            lector.leerTag(path, "CAJERO", lecCajero, cajero);
            
            Transaccion transaccion = new Transaccion();
            LectorTransaccion lecTransaccion = new LectorTransaccion(errores);
            lector.leerTag(path, "TRANSACCION", lecTransaccion, transaccion);
//            CitaMedica cita = new CitaMedica();
//            LectorCita lecCita = new LectorCita();
//            lector.leerTag(path, "cita", lecCita, cita);
//            
        } catch (Exception ex) {
            Logger.getLogger(LectorXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
