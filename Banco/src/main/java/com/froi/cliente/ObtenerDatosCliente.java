/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    
    /**
     * Método que sirve para obtener las cuentas que un cliente tiene asociadas 
     * @param dpiCliente DPI del cliente del que se quieren saber las cuentas
     * @return ArrayList tipo String que contiene las cuentas que el cliente tiene asociadas
     */
    public ArrayList<String> cuentasAsociadas(String dpiCliente) {
        
        String query = "SELECT cuenta FROM CUENTA_ASOCIADA WHERE cliente = ? AND estado = 'CUENTA ASOCIADA'";
        ArrayList<String> lista = new ArrayList<String>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dpiCliente);
            ResultSet result = preSt.executeQuery();
            
            while (result.next()) {
                lista.add(result.getString(1));
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error al obtener cuenta asociada: " + e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Método que sirve para obtener las cuentas propias de un cliente
     * @param dpiCliente DPI del cliente
     * @return ArrayList tipo String que contiene las cuentas del cliente
     */
    public ArrayList<String> cuentasPropias(String dpiCliente) {
        
        String query = "SELECT codigo FROM CUENTA WHERE cliente = ?";
        ArrayList<String> lista = new ArrayList<String>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dpiCliente);
            ResultSet result = preSt.executeQuery();
            
            while (result.next()) {
                lista.add(result.getString(1));
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error al obtener cuenta asociada: " + e.getMessage());
            return null;
        }
        
    }
    
}
