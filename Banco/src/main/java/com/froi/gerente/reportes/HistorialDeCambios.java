/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.gerente.reportes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class HistorialDeCambios {
    
    private Connection connection;
    
    public HistorialDeCambios(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que permite obtener los datos del historial de cambios de un gerente
     * @return ArrayList de tipo String[] que contiene los datos de cada cambio que se ha realizado en la entidad gerente
     */
    public ArrayList<String[]> gerente(){
        String query = "SELECT * FROM CAMBIO_GERENTE";
        ArrayList<String[]> lista = new ArrayList<>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[6];
                
                datos[0] = result.getString(1);
                datos[1] = result.getString(2);
                datos[2] = result.getString(3);
                datos[3] = result.getString(4);
                datos[4] = result.getString(5);
                datos[5] = result.getString(6);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error Historial de Cambios de Cliente");
            return null;
        }
        
    }
    
    /**
     * Método que permite obtener los datos del historial de cambios de un cliente
     * @return ArrayList de tipo String[] que contiene los datos de cada cambio que se ha realizado en la entidad cliente
     */
    public ArrayList<String[]> cliente() {
        String query = "SELECT * FROM CAMBIO_CLIENTE";
        return modeloLista(query);
    }
    
    /**
     * Método que permite obtener los datos del historial de cambios de un cajero
     * @return ArrayList tipo Sting[] que contiene los datos de cada cambio que se ha realizado en la entidad cajero
     */
    public ArrayList<String[]> cajero() {
        String query = "SELECT * FROM CAMBIO_CAJERO";
        return modeloLista(query);
    }
    
    /**
     * Define el modelo que tiene el historial de cambios del cliente y el cajero
     * @param query Query que define el historial de cambios que se retornará (entre cliente y cajero)
     * @return ArrayList tipo String[] que contiene los datos de la entidad solicitada
     */
    private ArrayList<String[]> modeloLista(String query) {
        ArrayList<String[]> lista = new ArrayList<>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[7];
                
                datos[0] = result.getString(1);
                datos[1] = result.getString(2);
                datos[2] = result.getString(3);
                datos[3] = result.getString(4);
                datos[4] = result.getString(5);
                datos[5] = result.getString(6);
                datos[6] = result.getString(7);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error Historial de Cambios de Cliente");
            return null;
        }
        
    }
    
}
