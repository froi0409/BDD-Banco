/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.ingresadores;

import com.froi.entidades.Transaccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class IngresadorTransaccion extends Ingresador {
    
    private Connection connection;
    private Transaccion transaccion;
    
    public IngresadorTransaccion(Connection connection, Transaccion transaccion) {
        this.connection = connection;
        this.transaccion = transaccion;
    }

    /**
     * Permite ingresar una entidad transaccion, a partir del archivo XML de entrada
     * @param errores ArrayList que contiene los errores que se pueden presentar en el ingreso al sistema
     */
    @Override
    public void ingresoArchivo(ArrayList<String> errores) {
        
        String insert = "INSERT INTO TRANSACCION (codigo,cuenta_destino,fecha,hora,tipo,monto,cajero) VALUES (?,?,?,?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            
            preSt.setString(1, transaccion.getCodigo());
            preSt.setString(2, transaccion.getCuentaDestino());
            preSt.setString(3, transaccion.getFecha());
            preSt.setString(4, transaccion.getHora());
            preSt.setString(5, transaccion.getTipo());
            preSt.setDouble(6, transaccion.getMonto());
            preSt.setString(7, transaccion.getCajero());
            
            preSt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error IA Transaccion: " + e.getMessage());
            errores.add(e.getMessage());
        }
        
    }

    @Override
    public void ingresoNormal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
