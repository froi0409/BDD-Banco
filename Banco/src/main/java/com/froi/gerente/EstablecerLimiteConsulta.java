/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.gerente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author froi-pc
 */
public class EstablecerLimiteConsulta {
    
    private Connection connection;
    
    public EstablecerLimiteConsulta(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Establece los valores del limite 1 y limite 2 de la tabla LIMITES_CONSULTA
     * @param limite1 limite para consulta de transacciones que superen este limite
     * @param limite2 limite para la consulta de transacciones sumadas que superen este limite
     * @return true si el limite es actualizado o establecido con éxito, de lo contrario retorna false
     */
    public boolean establecerLimites(double limite1, double limite2) {
        String update = "UPDATE LIMITES_CONSULTA SET limite_1=?, limite_2=? WHERE codigo = 0";
        String insert = "INSERT INTO LIMITES_CONSULTA VALUES (0,?,?)";
        String query = "SELECT COUNT(*) FROM LIMITES_CONSULTA";
        
        try (PreparedStatement preSt = connection.prepareStatement(query);
             PreparedStatement preSt2 = connection.prepareStatement(insert);
             PreparedStatement preSt3 = connection.prepareStatement(update)) {
            
            ResultSet result = preSt.executeQuery();
            result.next();
            if(result.getInt(1) == 0) {
                
                preSt2.setDouble(1, limite1);
                preSt2.setDouble(2, limite2);
                preSt2.executeUpdate();
                
            } else {
                
                preSt3.setDouble(1, limite1);
                preSt3.setDouble(2, limite2);
                preSt3.executeUpdate();
                
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("Error al establecer límites: " + e.getMessage());
            return false;
        }
        
    }
    
    /**
     * Le da un valor inicial al limite de la consulta
     */
    public void establecerInicialmente(){
        String insert = "INSERT INTO LIMITES_CONSULTA VALUES(0,100.00,150)";
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            preSt.executeUpdate();
            System.out.println("Limites preestablecidos con éxito");
        } catch (Exception e) {
            System.out.println("Error al establecer inicialmente el limite: " + e.getMessage());
        }
    }
    
}
