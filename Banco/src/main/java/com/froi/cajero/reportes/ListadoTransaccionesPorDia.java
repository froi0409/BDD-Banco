/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cajero.reportes;

import com.froi.banco.ReporteTiempo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class ListadoTransaccionesPorDia extends ReporteTiempo {
    
    private String balance;
    
    public ListadoTransaccionesPorDia(Connection connection, String tiempoInicial, String tiempoFinal){
        super(connection,tiempoInicial,tiempoFinal);
    }
    
    /**
     * Obtiene las trasferencias que un cajero realizó en un día en específico
     * @param codigoCajero Codigo del cajero consultado
     * @param fecha Fecha a consultar
     * @return ArrayList tipo String que contiene las transacciones que realizó el cajero en el día consultado
     */
    public ArrayList<String[]> obtenerTransacciones(String codigoCajero, String fecha){
        ArrayList<String[]> lista = new ArrayList<>();
        String query = "SELECT codigo,cuenta_destino,hora,tipo,monto,fecha FROM TRANSACCION WHERE cajero=? AND fecha = ?";
        
        try (PreparedStatement preSt = getConnection().prepareStatement(query)) {
            
            preSt.setString(1, codigoCajero);
            preSt.setString(2, fecha);
            
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
            System.out.println("Error listado de transacciones por día: " + e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Obtiene todas las fechas en las que un cajero realizó transacciones (en un intervalo de tiempo)
     * @param codigoCajero Codigo del cajero que se está consultando
     * @return ArrayList de tipo string que contiene todas las fechas en las que el cajero consultado realizó transacciones
     */
    public ArrayList<String> obtenerFechas(String codigoCajero) {
       ArrayList<String> lista = new ArrayList<>();
       String query = "SELECT fecha FROM TRANSACCION WHERE cajero = ? AND fecha BETWEEN ? AND ? GROUP BY fecha ORDER BY fecha";
       
        try (PreparedStatement preSt = getConnection().prepareStatement(query)) {
            
            preSt.setString(1, codigoCajero);
            preSt.setString(2, getTiempoInicial());
            preSt.setString(3, getTiempoFinal());
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                lista.add(result.getString(1));
            }
            
            return lista;
        } catch (Exception e) {
            System.out.println("Error al obtenes fechas: " + e.getMessage());
            return null;
        }
       
        
    }
    
    /**
     * Obtiene el balance de las transacciones que el cajero realizó en un intervalo de tiempo
     * @param codigoCajero Codigo del cajero consultado
     * @return Balance de las transacciones realizadas por el cajero en el intervalo de tiempo consultado
     */
    public String getBalance(String codigoCajero){
        
        String query = "SELECT SUM(monto) FROM TRANSACCION WHERE cajero=? AND fecha BETWEEN ? AND ? GROUP BY cajero";
        
        try (PreparedStatement preSt = getConnection().prepareStatement(query)) {
            
            preSt.setString(1, codigoCajero);
            preSt.setString(2, getTiempoInicial());
            preSt.setString(3, getTiempoFinal());
            ResultSet result = preSt.executeQuery();
            
            if(result.next()){
                return result.getString(1);
            } else {
                return "0.00";
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
    }
    
}
 