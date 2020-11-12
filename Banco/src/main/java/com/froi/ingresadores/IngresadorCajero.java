/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.ingresadores;

import com.froi.entidades.Cajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class IngresadorCajero extends Ingresador {

    private Cajero cajero;
    private Connection connection;
    
    public IngresadorCajero(Connection connection, Cajero cajero) {
        this.cajero = cajero;
        this.connection = connection;
    }
    
    /**
     * Permite ingresar a un cajero desde el archivo de entrada
     * @param errores ArrayList que contiene los errores que se pueden generar durante el ingreso del archivo
     */
    @Override
    public void ingresoArchivo(ArrayList<String> errores) {
        
        String insert = "INSERT INTO CAJERO VALUES (?,?,?,?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            
            preSt.setString(1, cajero.getCodigo());
            preSt.setString(2, cajero.getNombre());
            preSt.setString(3, cajero.getTurno());
            preSt.setString(4, cajero.getDpi());
            preSt.setString(5, cajero.getDireccion());
            preSt.setString(6, cajero.getSexo());
            preSt.setString(7, cajero.getPassword());
            
            preSt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error IA Cajero: " + e.getMessage());
            errores.add(e.getMessage());
        }
        
    }

    @Override
    public void ingresoNormal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}