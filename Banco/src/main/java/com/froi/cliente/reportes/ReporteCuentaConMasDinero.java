/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente.reportes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class ReporteCuentaConMasDinero {
    
    private Connection connection;
    
    public ReporteCuentaConMasDinero(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Obtiene la cuenta con más dinero del cliente y sus transacciones desde la fecha que el usuario ingresó
     * @param dpiCliente DPI del cliente del que se consulta
     * @param fechaInicio Fecha de inicio del reporte
     * @return ArrayList de tipo String[] que contiene los datos de las transacciones
     */
    public ArrayList<String[]> obtenerTransacciones(String dpiCliente, String fechaInicio) {
        String codigoCuenta = "SELECT codigo FROM CUENTA WHERE cliente = ? ORDER BY credito DESC LIMIT 1";
        String query = "SELECT * FROM TRANSACCION WHERE cuenta_destino=? OR cuenta_origen=? AND fecha BETWEEN ? AND CURDATE()";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query);
             PreparedStatement codigo = connection.prepareStatement(codigoCuenta)) {

            codigo.setString(1, dpiCliente);
            ResultSet resultCodigo = codigo.executeQuery();
            
            resultCodigo.next();
            
            preSt.setString(1, resultCodigo.getString(1));
            preSt.setString(2, resultCodigo.getString(1));
            preSt.setString(3, fechaInicio);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[8];
                
                datos[0] = result.getString(1);
                datos[1] = result.getString(2);
                datos[2] = result.getString(3);
                datos[3] = result.getString(4);
                datos[4] = result.getString(5);
                datos[5] = result.getString(6);
                datos[6] = result.getString(7);
                datos[7] = result.getString(8);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error al obtener transacciones de cuenta con más dinero: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Obtiene la cuenta del cliente que tiene más dinero
     * @param dpiCliente DPI del cliente a consultar
     * @return Coidgo de la cuenta del cliente que tiene más dinero
     */
    public String obtenerCuenta(String dpiCliente) {
        String query = "SELECT codigo FROM CUENTA WHERE cliente = ? ORDER BY credito DESC LIMIT 1";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            preSt.setString(1, dpiCliente);
            
            ResultSet result = preSt.executeQuery();
            
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error al obtener cuenta con más dinero: " + e.getMessage());
            return null;
        }
        
    }
    
}
