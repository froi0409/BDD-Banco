/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.ingresadores;

import com.froi.entidades.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class IngresadorGerente extends Ingresador {

    private Gerente gerente;
    private Connection connection;

    public IngresadorGerente(Connection connection, Gerente gerente) {
        this.gerente = gerente;
        this.connection = connection;
    }
    
    /**
     * Permite ingresar a un gerente, a partir del archivo XML de entrada
     * @param errores ArrayList que contiene los errores que se presenten durante el ingreso al sistema
     * @return retorna true si la entidad es ingresada con éxito, de lo contrario retorna false
     */
    @Override
    public boolean ingresoArchivo(ArrayList<String> errores) {
        
        String insert = "INSERT INTO GERENTE VALUES (?,?,?,?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            
            preSt.setString(1, gerente.getCodigo());
            preSt.setString(2, gerente.getNombre());
            preSt.setString(3, gerente.getTurno());
            preSt.setString(4, gerente.getDpi());
            preSt.setString(5, gerente.getDireccion());
            preSt.setString(6, gerente.getSexo());
            preSt.setString(7, gerente.getPassword());
            
            preSt.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error IA Gerente: " + e.getMessage());
            errores.add("Error IA Cliente: " + e.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean ingresoNormal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
