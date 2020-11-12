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
    
    /**
     * Analiza si la base de datos está llena o vacía
     * @return returna true si hay datos en el sistema, de lo contrario retorna false;
     */
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
    
    public boolean credenciales(String tabla, String usuario, String password) {
        
        String query = "SELECT codigo,password FROM " + tabla + " WHERE codigo = ? AND password = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, usuario);
            preSt.setString(2, password);
            
            ResultSet result = preSt.executeQuery();
            
            if(result.next()) {
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Error Análisis de Credenciales: " + e.getMessage());
            return false;
        }
         
    }
    
}
