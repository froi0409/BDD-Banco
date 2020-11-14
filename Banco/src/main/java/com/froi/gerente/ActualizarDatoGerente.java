/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.gerente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

/**
 *
 * @author froi-pc
 */
public class ActualizarDatoGerente {
    
     private Connection connection;

    public ActualizarDatoGerente(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que permite actualizar un dato de un gerente
     * @param campo Campo a actualizar
     * @param datoAntiguo Dato antiguo
     * @param datoNuevo Dato Nuevo
     * @param codigoGerente Codigo del gerente que actualiza
     * @return 
     */
    public boolean actualizar(String campo, String datoAntiguo, String datoNuevo, String codigoGerente) {
        
        String update = "UPDATE GERENTE SET " + campo +  " = ? WHERE codigo = ?";
        String insert = "INSERT INTO CAMBIO_GERENTE VALUES (?,CURDATE(),?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(update);
             PreparedStatement preSt2 = connection.prepareStatement(insert)) {
            
            preSt.setString(1, datoNuevo);
            preSt.setString(2, codigoGerente);
            
            preSt2.setNull(1, Types.NULL);
            preSt2.setString(2, campo);
            preSt2.setString(3, datoAntiguo);
            preSt2.setString(4, datoNuevo);
            preSt2.setString(5, codigoGerente);
            
            preSt.executeUpdate();
            preSt2.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error de Actualización de Cajero: " + e.getMessage());
            return false;
        }
        
    }
    
}
