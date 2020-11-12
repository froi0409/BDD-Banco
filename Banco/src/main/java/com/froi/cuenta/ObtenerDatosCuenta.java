/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author froi-pc
 */
public class ObtenerDatosCuenta {
    
    private Connection connection;
    
    public ObtenerDatosCuenta(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Metodo que sirve para obtener algún dato de la cuenta a partir de su código
     * @param dato dato que se desea obtener del cliente
     * @param codigoCuenta Código de la cuenta
     * @return retorna el dato que se desea obtener
     */
    public String obtenerDato(String dato, String codigoCuenta) {
        
        String query = "SELECT " + dato + " FROM CUENTA WHERE codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, codigoCuenta);
            ResultSet result = preSt.executeQuery();
            result.next();
            
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error al obtener dato de cliente: " + e.getMessage());
            return null;
        }
        
    }
    
    public String propietario(String codigoCuenta) {
        
        String query = "SELECT C.nombre FROM CLIENTE C INNER JOIN CUENTA CU ON C.dpi=CU.cliente WHERE CU.codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, codigoCuenta);
            ResultSet result = preSt.executeQuery();
            
            result.next();
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error propietario cuenta: " + e.getMessage());
            return null;
        }
        
    }
    
}
