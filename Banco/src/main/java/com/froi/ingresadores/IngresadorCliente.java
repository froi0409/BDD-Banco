/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.ingresadores;

import com.froi.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class IngresadorCliente extends Ingresador {

    private Cliente cliente;
    private Connection connection;
    
    /**
     * Constructor de la clase
     * @param connection Conexión a la base de datos
     * @param cliente Cliente que será ingresado
     */
    public IngresadorCliente(Connection connection, Cliente cliente) {
        this.cliente = cliente;
        this.connection = connection;
    }
    
    /**
     * Permite ingresar a un cliente desde un archivo xml al sistema
     * @param errores ArrayList que contiene los errores que se pueden presentar en el ingreso al sistema
     * @return retorna true si la entidad es ingresada con éxito, de lo contrario retorna false
     */
    @Override
    public boolean ingresoArchivo(ArrayList<String> errores) {
    
        String insert = "INSERT INTO CLIENTE VALUES (?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            
            preSt.setString(1, cliente.getDpi());
            preSt.setString(2, cliente.getCodigo());
            preSt.setString(3, cliente.getNombre());
            preSt.setString(4, cliente.getBirth());
            preSt.setString(5, cliente.getDireccion());
            preSt.setString(6, cliente.getSexo());
            preSt.setString(7, cliente.getDpiPdf());
            preSt.setString(8, cliente.getPassword());
            
            preSt.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error IA Cliente: " + e.getMessage());
            errores.add("Error IA Cliente: " + e.getMessage());
            return false;
        }
        
    }

    /**
     * Permite ingresar a un cliente al sistema 
     * @return Retorna true si el cliente fue ingresado con éxito, de lo contrario retorna false;
     */
    @Override
    public boolean ingresoNormal() {
    
        String insert = "INSERT INTO CLIENTE VALUES (?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(insert)) {
            
            preSt.setString(1, cliente.getDpi());
            preSt.setString(2, cliente.getCodigo());
            preSt.setString(3, cliente.getNombre());
            preSt.setString(4, cliente.getBirth());
            preSt.setString(5, cliente.getDireccion());
            preSt.setString(6, cliente.getSexo());
            preSt.setString(7, cliente.getDpiPdf());
            preSt.setString(8, cliente.getPassword());
            
            preSt.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al Tratar de ingresar al cliente: " + e.getMessage());
            return false;
        }
        
    }
    
    
    
}
