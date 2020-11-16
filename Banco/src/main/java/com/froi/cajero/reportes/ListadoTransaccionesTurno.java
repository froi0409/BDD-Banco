/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cajero.reportes;

import com.froi.entidades.Cajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class ListadoTransaccionesTurno {
    
    private Connection connection;
    
    public ListadoTransaccionesTurno(Connection connection) {
        this.connection = connection;
    }
    
    public ArrayList<String[]> obtenerTransacciones(String codigoCajero){
        ArrayList<String[]> lista = new ArrayList<>();
        
        String query = " SELECT codigo,cuenta_destino,fecha,hora,tipo,monto FROM TRANSACCION WHERE cajero=? AND fecha=CURDATE() AND hora BETWEEN ? AND ? ORDER BY hora DESC";
        String query2 = "SELECT turno FROM CAJERO WHERE codigo=?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query);
             PreparedStatement preSt2 = connection.prepareStatement(query2)) {
            
            preSt2.setString(1, codigoCajero);
            ResultSet turno = preSt2.executeQuery();
            turno.next();
            Cajero cajero = new Cajero();
            
            String horaInicio = cajero.getHoraInicio(turno.getString(1));
            String horaFin = cajero.getHoraFinal(turno.getString(1));
            
            preSt.setString(1, codigoCajero);
            preSt.setString(2, horaInicio);
            preSt.setString(3, horaFin);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[6];
                
                datos[0] = result.getString("codigo");
                datos[1] = result.getString("cuenta_destino");
                datos[2] = result.getString("fecha");
                datos[3] = result.getString("hora");
                datos[4] = result.getString("tipo");
                datos[5] = result.getString("monto");
                
                lista.add(datos);
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Error obtener transacciones turno: " + e.getMessage());
            return null;
        }
        
    }
    
    public String totalCaja(String codigoCajero) {
        
        String query="SELECT SUM(monto) FROM TRANSACCION WHERE cajero=? AND fecha=CURDATE() AND hora BETWEEN ? AND ?";
        String query2 = "SELECT turno FROM CAJERO WHERE codigo=?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query);
             PreparedStatement preSt2 = connection.prepareStatement(query2)) {
            
            preSt2.setString(1, codigoCajero);
            ResultSet turno = preSt2.executeQuery();
            turno.next();
            Cajero cajero = new Cajero();
            
            String horaInicio = cajero.getHoraInicio(turno.getString(1));
            String horaFin = cajero.getHoraFinal(turno.getString(1));
            
            preSt.setString(1, codigoCajero);
            preSt.setString(2, horaInicio);
            preSt.setString(3, horaFin);
            
            ResultSet result = preSt.executeQuery();
            result.next();
            
            if(result.next()) {
                return result.getString(1);
            } else {
                return "0.00";
            }
            
            
            
        } catch (Exception e) {
            System.out.println("Error total caja del día: " + e.getMessage());
            return "0.00";
        }
        
    }
    
}
