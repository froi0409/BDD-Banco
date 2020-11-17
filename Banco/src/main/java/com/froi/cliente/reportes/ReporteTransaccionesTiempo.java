/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente.reportes;

import com.froi.banco.ReporteTiempo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class ReporteTransaccionesTiempo extends ReporteTiempo {
    
    private double balance;
    
    public ReporteTransaccionesTiempo(Connection connection, String tiempoInicial, String tiempoFinal) {
        super(connection, tiempoInicial, tiempoFinal);
    }
    
    /**
     * Obtiene el cambio de dinero de una cuenta, por cada transacción
     * @param codigoCuenta
     * @return 
     */
    public ArrayList<String[]> obtenerTransacciones(String codigoCuenta) {
        DecimalFormat df = new DecimalFormat("#.00");
        String query = "SELECT * FROM TRANSACCION WHERE (cuenta_destino=? OR cuenta_origen=?) AND fecha BETWEEN ? AND ? ORDER BY fecha DESC,hora DESC";
        String query2 = "SELECT credito FROM CUENTA WHERE codigo=?";
        String query3 = "SELECT * FROM TRANSACCION WHERE (cuenta_destino=? OR cuenta_origen=?) AND (fecha > ? AND fecha <= CURDATE()) ORDER BY fecha DESC,hora DESC";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = getConnection().prepareStatement(query);
             PreparedStatement dinero = getConnection().prepareStatement(query2);
             PreparedStatement dineroFechaFinal = getConnection().prepareStatement(query3)) {
            
            //Obtenermos el dinero actual de la cuenta  
            dinero.setString(1, codigoCuenta);
            ResultSet obtenerDinero = dinero.executeQuery();
            obtenerDinero.next();
            balance = obtenerDinero.getDouble(1); //Obtuvimos el dinero actual
            
            //Encontramos el dinero que la cuenta poseía en la fecha final establecida
            dineroFechaFinal.setString(1, codigoCuenta);
            dineroFechaFinal.setString(2, codigoCuenta);
            dineroFechaFinal.setString(3, getTiempoFinal());
            ResultSet obtenerDinero2 = dineroFechaFinal.executeQuery();
            
            while(obtenerDinero2.next()) {
                double dineroAux = obtenerDinero2.getDouble("monto");
                if (obtenerDinero2.getString("tipo").equals("BANCA") && obtenerDinero2.getString("cuenta_origen").equals(codigoCuenta)){
                    dineroAux *= -1;
                }
                balance -= dineroAux;
            }
            // Al finalizar este ciclo, obtenemos el resultado del dinero en la fecha final
            
            // Obtenemos el balance de la fecha inicial a la fecha final
            preSt.setString(1, codigoCuenta);
            preSt.setString(2, codigoCuenta);
            preSt.setString(3, getTiempoInicial());
            preSt.setString(4, getTiempoFinal());
            
            ResultSet result = preSt.executeQuery();
            
            while (result.next()) {
                String[] datos = new String[7];
                
                datos[6] = df.format(balance); //obtenemos el saldo antes de la transacción
                System.out.println("saldo antes: " + datos[5]);
                
                double dineroAux = result.getDouble("monto");
                if (result.getString("tipo").equals("BANCA") && result.getString("cuenta_origen").equals(codigoCuenta)){
                    dineroAux *= -1;
                }
                balance -= dineroAux;
                
                datos[0] = result.getString("codigo");
                datos[1] = result.getString("fecha");
                datos[2] = result.getString("hora");
                datos[3] = result.getString("tipo");
                datos[4] = df.format(dineroAux); //Se omite datos[6] porque ya fué declarado en la parte posterior
                datos[5] = df.format(balance);
                
                lista.add(datos);
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error reporte de transacciones tiempo: " + e.getMessage());
            return null;
        }
    
    }
    
    public double getSaldo() {
        return balance;
    }
    
}
