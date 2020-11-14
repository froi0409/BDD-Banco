/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

/**
 *
 * @author froi-pc
 */
public class ActualizarDatoCliente {
        
    private Connection connection;
    
    public ActualizarDatoCliente(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que permite actualizar un dato de un cliente
     * @param campo Campo a actualizar
     * @param datoAntiguo Dato antigui
     * @param datoNuevo Dato nuevo
     * @param dpiCliente DPI del cliente
     * @param codigoGerente Codigo del gerente que actualiza
     * @return retorna true si la información del cliente fue actualizada con éxito, de lo contrario retorna false
     */
    public boolean actualizar(String campo, String datoAntiguo, String datoNuevo, String dpiCliente, String codigoGerente) {
        
        String update = "UPDATE CLIENTE SET " + campo + " = ? WHERE dpi = ?";
        String insert = "INSERT INTO CAMBIO_CLIENTE VALUES (?,CURDATE(),?,?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(update);
             PreparedStatement preSt2 = connection.prepareStatement(insert)) {
            
            preSt.setString(1, datoNuevo);
            preSt.setString(2, dpiCliente);
            
            preSt2.setNull(1, Types.NULL);
            preSt2.setString(2, campo);
            preSt2.setString(3, datoAntiguo);
            preSt2.setString(4, datoNuevo);
            preSt2.setString(5, codigoGerente);
            preSt2.setString(6, dpiCliente);
            
            preSt.executeUpdate();
            preSt2.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error de Actualización de Cliente: " + e.getMessage());
            return false;
        }
        
    }
    
}
