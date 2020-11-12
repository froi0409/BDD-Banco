/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.ingresadores;

import com.froi.entidades.Cuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class IngresadorCuenta extends Ingresador {

    private Cuenta cuenta;
    private Connection connection;
    
    public IngresadorCuenta(Connection connection, Cuenta cuenta) {
        this.connection = connection;
        this.cuenta = cuenta;
    }
    
    /**
     * Permite ingresar una cuenta (perteneciente a un cliente) a partir del archivo de entrada
     * @param errores ArrayList que contiene los errores que se puedan dar durante el ingreso a la base de datos
     */
    @Override
    public void ingresoArchivo(ArrayList<String> errores) {
    
        String insert = "INSERT INTO CUENTA VALUES (?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            
            preSt.setString(1, cuenta.getCodigo());
            preSt.setString(2, cuenta.getFecha());
            preSt.setDouble(3, cuenta.getCredito());
            preSt.setString(4, cuenta.getCliente());
            
            preSt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error IA Cuenta: " + e.getMessage());
            errores.add(e.getMessage());
        }
        
    }

    @Override
    public void ingresoNormal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
