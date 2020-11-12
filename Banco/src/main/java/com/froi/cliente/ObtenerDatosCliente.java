/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author froi-pc
 */
public class ObtenerDatosCliente {
    
    private Connection connection;
    
    public ObtenerDatosCliente(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Metodo que sirve para obtener algún dato del cliente a partir de su código
     * @param dato dato que se desea obtener del cliente
     * @param codigoCliente Código del Cliente
     * @return retorna el dato que se desea obtener
     */
    public String obtenerDato(String dato, String codigoCliente) {
        
        String query = "SELECT " + dato + " FROM CLIENTE WHERE codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, codigoCliente);
            ResultSet result = preSt.executeQuery();
            result.next();
            
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error al obtener dato de cliente: " + e.getMessage());
            return null;
        }
        
    }
    
}
