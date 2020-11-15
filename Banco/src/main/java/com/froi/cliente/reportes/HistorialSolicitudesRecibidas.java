/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente.reportes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class HistorialSolicitudesRecibidas {
    private Connection connection;
    
    public HistorialSolicitudesRecibidas(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Obtiene las solicitudes de asociación que un cliente ha recibido
     * @param dpiCliente DPI del cliente a consultar
     * @return ArrayList tipo String[] que contiene los datos del historial de solicitudes que un cliente ha recibido
     */
    public ArrayList<String[]> obtener(String dpiCliente){
        String query = "SELECT * FROM CUENTA_ASOCIADA WHERE cuenta IN (SELECT codigo FROM CUENTA WHERE cliente = ?)";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dpiCliente);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[5];
                
                datos[0] = result.getString(1);
                datos[1] = result.getString(2);
                datos[2] = result.getString(3);
                datos[3] = result.getString(4);
                datos[4] = result.getString(5);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error historial de solicitudes de asociación recibidas: " + e.getMessage());
            return null;
        }
    }
}
