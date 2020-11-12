/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author froi-pc
 */
public class EnviarSolicitud {
    
    private Connection connection;
    
    public EnviarSolicitud(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que nos permite determinar si una cuenta existe en el sistema (utilizado para realizar asociaciones)
     * @param cuenta Número de la cuenta que se desea verificar
     * @return retorna true si la cuenta existe en el sistema, de lo contrario retorna false
     */
    public boolean existeCuenta(String cuenta) {
        
        String query = "SELECT COUNT(*) FROM CUENTA WHERE codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, cuenta);
            
            ResultSet result = preSt.executeQuery(); 
            result.next(); //Movemos el cursor al primer (y debería ser único) resultado
            
            if (result.getInt(1) == 1) {
                return true;
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
    }
    
    /**
     * Método que nos permite saber si aún se tienen intentos para poder llevar a cabo la asociación de cuentas
     * @param cuenta Número de la cuenta que se desea asociar
     * @param dpiCliente DPI del cliente que desea realizar la solicitud de asociacion
     * @return retorna el número de intentos que tiene la solicitud
     */
    public int intentoValido(String cuenta, String dpiCliente) {
        
        String query = "SELECT intentos FROM CUENTA_ASOCIADA WHERE cuenta = ? AND cliente = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, cuenta);
            preSt.setString(2, dpiCliente);
            ResultSet result = preSt.executeQuery();
            
            if(result.next()) {
                return result.getInt(1);
            } else {
                return 0;
            }
            
        } catch (Exception e) {
            System.out.println("Error intento válido: " + e.getMessage());
            return 0;
        }
        
    }
    
    /**
     * Método que nos permite saber si la cuenta que estamos solicitando asociar es propia, o no
     * @param cuenta Numero de cuenta que se desea asociar
     * @param dpiCliente DPI del cliente que desea mandar la solicitud de asociación
     * @return retorna true si la cuenta es propia, de lo contrario retorna false
     */
    public boolean cuentaPropia(String cuenta, String dpiCliente) {
    
        String query = "SELECT COUNT(*) FROM CUENTA WHERE codigo = ? AND cliente = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, cuenta);
            preSt.setString(2, dpiCliente);
            
            ResultSet result = preSt.executeQuery();
            result.next();
            
            if(result.getInt(1) == 1) {
                return true;
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
            System.out.println("Error determinar cuenta propia: " + e.getMessage());
            return false;
        }
        
        
    }
    
    /**
     * Método que nos permite saber el estado en el que se encuentra una solicitud
     * @param dpiCliente DPI del cliente que solicita la asociación de la cuenta
     * @param cuenta Cuenta que el cliente desea asociar
     * @return retorna el estado de la solicitd de asociación
     */
    public String estadoSolicitud(String dpiCliente, String cuenta) {
        String query = "SELECT estado FROM CUENTA_ASOCIADA WHERE cliente = ? AND cuenta = ?";
    
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dpiCliente);
            preSt.setString(2, cuenta);
            ResultSet result = preSt.executeQuery();
            
            result.next();
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error Estado de Solicitud: " + e.getMessage());
            return null;
        }
    
    }
    
    /**
     * Envía la solicitud de asociación de cuenta
     * @param dpiCliente DPI del cliente que desea asociar la cuenta
     * @param cuenta Cuenta que se desea asociar
     * @param intentos Número de intentos que se han hecho para asociar al cliente con la cuenta
     * @return retorna true si la solicitud se envió con éxito, de lo contrario retorna false
     */
    public boolean enviarSolicitud(String dpiCliente, String cuenta, int intentos) {
        
        String update;
        int intentosAux = intentos+1;
        
        if(intentos == 0){
            update = "INSERT INTO CUENTA_ASOCIADA VALUES (?,?,'EN ESPERA'," + intentosAux + ")";
        } else {
            update = "UPDATE CUENTA_ASOCIADA SET intentos = " + intentosAux + ", estado='EN ESPERA' WHERE cliente = ? AND cuenta = ?";
        }
        
        System.out.println("\n\n" + intentos + "    " + update + "\n\n");
        
        try (PreparedStatement preSt = connection.prepareStatement(update)) {
            
            preSt.setString(1, dpiCliente);
            preSt.setString(2, cuenta);
           
            preSt.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error enviar solicitud: " + e.getMessage());
            return false;
        }
        
    }
    
}
