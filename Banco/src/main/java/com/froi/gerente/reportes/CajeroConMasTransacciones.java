/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.gerente.reportes;

import com.froi.banco.ReporteTiempo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class CajeroConMasTransacciones extends ReporteTiempo {
    
    public CajeroConMasTransacciones(Connection connection, String tiempoInicial, String tiempoFinal) {
        super(connection, tiempoInicial, tiempoFinal);
    }
    
    /**
     * Método que permite obtener los datos del cajero con más transacciones
     * @return ArrayList tipo String[] que contiene el nombre y la cantidad de transacciones que el cajero ha realizado
     */
    public ArrayList<String[]> obtenerCajero() {
        String datosCajero = "SELECT cajero,COUNT(cajero) FROM TRANSACCION WHERE fecha BETWEEN ? AND ? GROUP BY cajero ORDER BY COUNT(cajero) DESC LIMIT 1 ";
        ArrayList<String[]> lista = new ArrayList<>();
        
        try (PreparedStatement preSt = getConnection().prepareStatement(datosCajero)) {
            
            preSt.setString(1, getTiempoInicial());
            preSt.setString(2, getTiempoFinal());
            
            ResultSet result = preSt.executeQuery();
            result.next();
            
            String[] datos = new String[2];
            
            datos[0] = result.getString(1);
            datos[1] = result.getString(2);
            
            lista.add(datos);
            return lista;
        } catch (Exception e) {
            System.out.println("Error cajero con más transacciones: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Método que permite obtener las transacciones que el cajero con más transacciones realizó
     * @return ArrayList tipo String[] que contiene los datos de las transacciones del cajero con más transacciones en el intervalo de tiempo seleccionado
     */
    public ArrayList<String[]> obtenerTransacciones() {
        String query = "SELECT cajero,COUNT(cajero) FROM TRANSACCION WHERE fecha BETWEEN ? AND ? GROUP BY cajero ORDER BY COUNT(cajero) DESC LIMIT 1";
        String query2 = "SELECT codigo,cuenta_destino,fecha,hora,tipo,monto FROM TRANSACCION WHERE cajero = ? AND fecha BETWEEN ? AND ?";
        ArrayList<String[]> lista = new ArrayList<>();
        
        try (PreparedStatement preSt = getConnection().prepareStatement(query);
             PreparedStatement preSt2 = getConnection().prepareStatement(query2)) {
            
            preSt.setString(1, getTiempoInicial());
            preSt.setString(2, getTiempoFinal());
            
            ResultSet result = preSt.executeQuery();
            result.next();
            
            String codigoCajero = result.getString(1);
            
            preSt2.setString(1, codigoCajero);
            preSt2.setString(2, getTiempoInicial());
            preSt2.setString(3, getTiempoFinal());
            
            ResultSet result2 = preSt2.executeQuery();
            
            while(result2.next()) {
                String[] dato = new String[6];
                
                dato[0] = result2.getString(1);
                dato[1] = result2.getString(2);
                dato[2] = result2.getString(3);
                dato[3] = result2.getString(4);
                dato[4] = result2.getString(5);
                dato[5] = result2.getString(6);
                
                lista.add(dato);
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error obtener transacciones cajero: " + e.getMessage());
            return null;
        }
        
    }
    
}
