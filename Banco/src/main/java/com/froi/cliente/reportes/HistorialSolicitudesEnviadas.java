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
public class HistorialSolicitudesEnviadas {
    
    private Connection connection;
    
    public HistorialSolicitudesEnviadas(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Obtiene la información de las solicitudes de asociación que un cliente ha enviado
     * @param dpiCliente DPI del cliente que se desea consultar
     * @return ArrayList tipo String[] que contiene la información de todas las solicitudes de asociación que el cliente ha enviado
     */
    public ArrayList<String[]> obtener(String dpiCliente){
        String query = "SELECT CA.*,C.codigo,CL.nombre FROM CUENTA_ASOCIADA CA JOIN CUENTA C ON C.codigo=CA.cuenta JOIN CLIENTE CL ON C.cliente=CL.dpi WHERE CA.cliente=?";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dpiCliente);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                String[] datos = new String[5];
                
                datos[0] = result.getString("cuenta");
                datos[1] = result.getString("nombre");
                datos[2] = result.getString("estado");
                datos[3] = result.getString("intentos");
                datos[4] = result.getString("fecha");
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error historial de solicitudes de asociación enciadas: " + e.getMessage());
            return null;
        }
    }
    
}
