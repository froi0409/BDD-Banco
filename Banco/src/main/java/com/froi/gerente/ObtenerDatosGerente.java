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
public class ObtenerDatosGerente {
    
    private Connection connection;
    
    public ObtenerDatosGerente(Connection connection){
        this.connection = connection;
    }
    
    /**
     * Comprueba si existe algun gerente con tales características
     * @param campo Campo que se desea consultar
     * @param dato Dato que se desea consultar
     * @return retorna true si existe otro gerente con esas características
     */
    public boolean existsOther(String campo, String dato) {
        
        String query = "SELECT COUNT(*) FROM GERENTE WHERE " + campo + " = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dato);
            ResultSet result = preSt.executeQuery();
            result.next();
            
            if(result.getInt(1) == 0) {
                return false;
            } else {
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Error exists other: " + e.getMessage());
            return true;
        }
        
    }

    /**
     * Metodo que sirve para obtener algún dato del gerente a partir de su código
     * @param dato dato que se desea obtener del gerente
     * @param codigo Código del gerente
     * @return retorna el dato que se desea obtener
     */
    public String obtenerDato(String dato, String codigo) {
        
        String query = "SELECT " + dato + " FROM GERENTE WHERE codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, codigo);
            ResultSet result = preSt.executeQuery();
            result.next();
            
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error al obtener dato de cajero: " + e.getMessage());
            return null;
        }
        
    }

}
