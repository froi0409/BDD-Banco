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
public class ReporteClientesConMasDinero {
    
    private Connection connection;
    
    public ReporteClientesConMasDinero(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que nos permite obtener a los 10 clientes que tienen más dinero en sus cuentas
     * @return ArrayList tipo String[] que contiene los datos de los clientes que tienen más dinero en sus cuentas
     */
    public ArrayList<String[]> obtener() {
        String query = "SELECT C.cliente,CL.nombre,SUM(C.credito) FROM CUENTA C JOIN CLIENTE CL ON CL.dpi=C.cliente GROUP BY C.cliente ORDER BY SUM(C.credito) DESC LIMIT 10";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[3];
                
                datos[0] = result.getString(1);
                datos[1] = result.getString(2);
                datos[2] = result.getString(3);
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error Clientes con más dinero en sus cuentas: " + e.getMessage());
            return null;
        }
        
    }
    
}
