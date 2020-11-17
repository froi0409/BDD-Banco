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
public class ReporteClienteTransacciones {
    
    private Connection connection;
    
    public ReporteClienteTransacciones(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Permite saber sobre los clientes que han realizado transacciones mayores a un limite
     * @return ArrayList tipo String[] que contiene la información de los clientes que han  realizado transacciones mayores a un límite
     */
    public ArrayList<String[]> obtener(){
        DecimalFormat df = new DecimalFormat("#.00");
        String query = "(SELECT T.*,C.codigo,CL.nombre,CL.dpi FROM TRANSACCION T JOIN CUENTA C ON T.cuenta_origen=C.codigo JOIN CLIENTE CL ON C.cliente = CL.dpi WHERE T.monto > ?) UNION (SELECT T.*,C.codigo,CL.nombre,CL.dpi FROM TRANSACCION T JOIN CUENTA C ON T.cuenta_destino=C.codigo JOIN CLIENTE CL ON CL.dpi=C.cliente WHERE tipo='DEBITO' AND -monto>? )";
        String query2 = "SELECT limite_1 FROM LIMITES_CONSULTA";
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        try (PreparedStatement preSt = connection.prepareStatement(query);
             PreparedStatement preSt2 = connection.prepareStatement(query2)) {
            
            //Obtenemos el limite
            ResultSet limite = preSt2.executeQuery();
            limite.next();
            double limiteConsulta = limite.getDouble(1);
            
            
            //Obtenemos Transacciones mayores al limite
            preSt.setDouble(1, limiteConsulta);
            preSt.setDouble(2, limiteConsulta);
            
            ResultSet result = preSt.executeQuery();
            
            while(result.next()) {
                
                
                String[] datos = new String[7];
                
                datos[0] = result.getString("dpi");
                datos[1] = result.getString("nombre");
                datos[2] = result.getString("codigo");
                datos[3] = result.getString("cuenta_origen");
                datos[4] = result.getString("fecha");
                datos[5] = result.getString("hora");
                datos[6] = result.getString("monto");
                
                if(result.getString("tipo").equals("DEBITO")) {
                    datos[3] = result.getString("cuenta_destino");
                    datos[6] = df.format(-result.getDouble("monto"));
                }
                
                lista.add(datos);
                
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error Transacciones mayores a límite: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Permite obtener el limite del reporte
     * @return Límite del reporte
     */
    public String getLimite(){
        
        String query = "SELECT limite_1 FROM LIMITES_CONSULTA";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            ResultSet result = preSt.executeQuery();
            result.next();
            
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error al Obtener Límite: " + e.getMessage());
            return null;
        }
        
    }
    
}
