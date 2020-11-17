/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

/**
 *
 * @author froi-pc
 */
public class ActualizarDatoCajero {
    
    private Connection connection;

    public ActualizarDatoCajero(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que permite actualizar un dato de un cajero
     * @param campo Campo a actualizar
     * @param datoAntiguo Dato antiguo
     * @param datoNuevo Dato Nuevo
     * @param codigoCajero Codigo del Cajer
     * @param codigoGerente Codigo del gerente que actualiza
     * @return 
     */
    public boolean actualizar(String campo, String datoAntiguo, String datoNuevo, String codigoCajero, String codigoGerente) {
        
        String update = "UPDATE CAJERO SET " + campo +  " = ? WHERE codigo = ?";
        String insert = "INSERT INTO CAMBIO_CAJERO VALUES (?,CURDATE(),?,?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(update);
             PreparedStatement preSt2 = connection.prepareStatement(insert)) {
            
            preSt.setString(1, datoNuevo);
            preSt.setString(2, codigoCajero);
            
            preSt2.setNull(1, Types.NULL);
            preSt2.setString(2, campo);
            preSt2.setString(3, datoAntiguo);
            preSt2.setString(4, datoNuevo);
            preSt2.setString(5, codigoGerente);
            preSt2.setString(6, codigoCajero);
            
            preSt.executeUpdate();
            preSt2.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error de Actualización de Cajero: " + e.getMessage());
            return false;
        }
        
    }
    
}
