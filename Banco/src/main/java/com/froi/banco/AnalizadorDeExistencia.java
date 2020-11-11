/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author froi-pc
 */
public class AnalizadorDeExistencia {
    
    private Connection connection;
    
    public AnalizadorDeExistencia(Connection connection) {
        this.connection = connection;
    }
    
    public boolean analizar(){
    
        String query = "SELECT COUNT(*) FROM GERENTE";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            ResultSet result = preSt.executeQuery();
            result.next();
            int cant = result.getInt(1);
            if(cant == 0){
                return false;
            }
            else {
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Error Analizar Existencia: " + e.getMessage());
            return false;
        }
    
        
    }
    
}
