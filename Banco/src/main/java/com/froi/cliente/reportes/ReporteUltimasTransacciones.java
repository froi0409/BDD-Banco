/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente.reportes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class ReporteUltimasTransacciones {
    
    private Connection connection;
    
    public ReporteUltimasTransacciones(Connection connection){
        this.connection = connection;
    }
    
    /**
     * Metodo que nos permite conocer todas las cuentas de un cliente
     * @param dpiCliente DPI del cliente
     * @return ArrayList tipo String que contiene todas las cuentas de un cliente
     */
    public ArrayList<String> obtenerCuentas(String dpiCliente){
        String query = "SELECT codigo FROM CUENTA WHERE cliente = ?";
        ArrayList<String> lista = new ArrayList<String>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dpiCliente);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                
                String datos = result.getString(1);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error historial de solicitudes de asociación enciadas: " + e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Obtenemos las últimas 15 transacciones del año de cada cuenta
     * @param codigoCuenta codigo de la cuenta a consultar
     * @return ArrayList tipo String[] que contiene las últimas 15 transacciones de una cuenta en el último año
     */
    public ArrayList<String[]> obtenerTransacciones(String codigoCuenta){
        DecimalFormat df = new DecimalFormat("#.00");
        String query = "SELECT * FROM TRANSACCION WHERE (cuenta_origen = ? OR cuenta_destino=?) AND fecha >= date_sub(curdate(), interval 12 month) ORDER BY fecha DESC,hora DESC LIMIT 15";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, codigoCuenta);
            preSt.setString(2, codigoCuenta);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                double dineroAux = result.getDouble("monto");
                if (result.getString("tipo").equals("BANCA") && result.getString("cuenta_origen").equals(codigoCuenta)) {
                    dineroAux *= -1;
                }
                
                String[] datos = new String[5];
                
                datos[0] = result.getString("codigo");
                datos[1] = result.getString("fecha");
                datos[2] = result.getString("hora");
                datos[3] = result.getString("tipo");
                datos[4] = df.format(dineroAux);
                
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error historial de solicitudes de asociación recibidas: " + e.getMessage());
            return null;
        }
    }
    
}
