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
public class ReporteClientesSinTransacciones extends ReporteTiempo {
    
    public ReporteClientesSinTransacciones(Connection connection, String tiempoInicial, String tiempoFinal) {
        super(connection, tiempoInicial, tiempoFinal);
    }
    
    /**
     * Método que permite obtener la información de los clientes que no han realizado transacciones en un intervalo de tiempo
     * @return ArrayList tipo String[] que contiene la información de los clientes que no han realizado transacciones
     */
    public ArrayList<String[]> obtener() {
        String query = "SELECT dpi,nombre FROM CLIENTE WHERE dpi NOT IN (SELECT C.cliente FROM TRANSACCION T JOIN CUENTA C ON T.cuenta_destino=C.codigo OR T.cuenta_origen=C.codigo WHERE T.fecha BETWEEN ? AND ?)";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = getConnection().prepareStatement(query)) {
            
            preSt.setString(1, getTiempoInicial());
            preSt.setString(2, getTiempoFinal());
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[2];
                
                datos[0] = result.getString(1);
                datos[1] = result.getString(2);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error Clientes que no han realizado transacciones: " + e.getMessage());
            return null;
        }
        
    }
    
}
