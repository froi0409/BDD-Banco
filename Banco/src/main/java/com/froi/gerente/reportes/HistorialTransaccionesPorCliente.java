/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.gerente.reportes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class HistorialTransaccionesPorCliente {
    
    private Connection connection;
    private double limiteInferior;
    private double limiteSuperior;

    public HistorialTransaccionesPorCliente(Connection connection, double limiteInferior, double limiteSuperior) {
        this.connection = connection;
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }
    
    /**
     * Obtenemos una lista con los clientes que cunplen con el filto ingresado
     * @param filtro Filtro de la busqueda
     * @return ArrayList tipo String[] que contiene lel dpi y el nombre de los clientes que cumplan con el filtro
     */
    public ArrayList<String[]> obtenerClientes(String filtro){
        ArrayList<String[]> lista = new ArrayList<>();
        String query = "SELECT dpi,nombre FROM CLIENTE WHERE nombre LIKE ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, "%"+ filtro + "%");
            ResultSet result = preSt.executeQuery();
            
            System.out.println(preSt + " " + filtro);
            
            while(result.next()){
                String[] datos = new String[2];
                
                datos[0] = result.getString(1);
                datos[1] = result.getString(2);
                
                lista.add(datos);
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error obtener clientes filtrados: " + e.getMessage());
            return null;
        }
        
    }
    
    public ArrayList<String[]> obtenerTransacciones(String dpiCliente) {
        DecimalFormat df = new DecimalFormat("#.00");
        String query = "SELECT codigo,cuenta_origen,cuenta_destino,fecha,hora,tipo,ABS(MONTO) FROM TRANSACCION WHERE ((cuenta_origen IN (SELECT codigo FROM CUENTA WHERE cliente = ?)) OR (cuenta_destino IN (SELECT codigo FROM CUENTA WHERE cliente = ?) AND tipo='DEBITO')) AND (ABS(monto) > ? AND ABS(monto) < ?)";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dpiCliente);
            preSt.setString(2, dpiCliente);
            preSt.setDouble(3, limiteInferior);
            preSt.setDouble(4, limiteSuperior);
            
            
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                
                
                String[] datos = new String[6];
                
                if(result.getString("tipo").equals("BANCA")) {
                    datos[1] = result.getString("cuenta_origen");
                } else {
                    datos[1] = result.getString("cuenta_destino");
                }
                
                datos[0] = result.getString(1);
                datos[2] = result.getString(4);
                datos[3] = result.getString(5);
                datos[4] = result.getString(6);
                datos[5] = result.getString(7);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error Transacciones mayores a límite: " + e.getMessage());
            return null;
        }
    }
    
}
