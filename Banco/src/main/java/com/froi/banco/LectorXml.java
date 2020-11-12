/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

import com.froi.entidades.Gerente;
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
            LectorGerente lecGerente = new LectorGerente();
            lector.leerTag(path, "GERENTE", lecGerente, gerente);

            LectorCliente lecCliente = new LectorCliente(errores);
            lecCliente.read(path);
            
//            CitaMedica cita = new CitaMedica();
//            LectorCita lecCita = new LectorCita();
//            lector.leerTag(path, "cita", lecCita, cita);
//            
        } catch (Exception ex) {
            Logger.getLogger(LectorXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
