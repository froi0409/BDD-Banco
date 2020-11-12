/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class SolicitudesPendientes {
    
    private Connection connection;
    
    public SolicitudesPendientes (Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Permite saber las solicitudes de asociación pendientes que tiene cada cuenta
     * @param cuenta Cuenta de la que se desea saber las solicitudes de asociación que tiene pendientes
     * @param datos ArrayList que almacenará los datos
     */
    private void pendientes(String cuenta, ArrayList<String[]> datos) {
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        String query = "SELECT C.cuenta,CL.nombre,C.intentos FROM CUENTA_ASOCIADA C INNER JOIN CLIENTE CL ON CL.dpi = C.cliente WHERE C.cuenta = ? AND C.estado = 'EN ESPERA'";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, cuenta);
            ResultSet result = preSt.executeQuery();
            
            while (result.next()) {
                String[] atributos = new String[3];
                
                atributos[0] = result.getString(1);
                atributos[1] = result.getString(2);
                atributos[2] = result.getString(3);
               
                datos.add(atributos);
            }
            
        } catch (Exception e) {
            System.out.println("Error agregar solicitudes pendientes: " + e.getMessage());
        }
        
    }
    
    /**
     * Método que permite obtener todas las cuentas de un cliente que tienen solicitudes de asociación pendientes
     * @param dpiCliente Cliente que desea saber las solicitudes de asociación que tiene pendientes de responder
     * @return ArrayList que contiene su cuenta, nombre del cliente que envió la solicitud y la cantidad de intentos que se ha dado para asociar las cuentas
     */
    public ArrayList<String[]> listar(String dpiCliente) {
        ArrayList<String> cuentas = new ArrayList<String>();
        ArrayList<String[]> datos = new ArrayList<String[]>();
        
        String query = "SELECT codigo FROM CUENTA WHERE cliente = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setString(1, dpiCliente);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                cuentas.add(result.getString(1));
            }
            
        } catch (Exception e) {
            System.out.println("Error lista cuentas: " + e.getMessage());
        }
        
        for(String element: cuentas) {
            
            pendientes(element,datos);
            
        }
        
        return datos;
        
    }
    
}
