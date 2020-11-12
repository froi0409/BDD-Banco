/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author froi-pc
 */
public class RespuestaSolicitud {
    
    private Connection connection;
    
    public RespuestaSolicitud(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Permite aceptar una solicitud de asociación de cuenta
     * @param dpiSolicitante Dpi del cliente que solicitó la asociación 
     * @param cuentaSolicitada Número de la cuenta que se deseaba asociar
     * @return retorna true si la aceptación de la solicitud se dió con éxito, de lo contrario retorna false
     */
    public boolean aceptar(String dpiSolicitante, String cuentaSolicitada){
        
        String update = "UPDATE CUENTA_ASOCIADA SET estado = 'CUENTA ASOCIADA' WHERE cliente = ? AND cuenta = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(update)) {
            
            preSt.setString(1, dpiSolicitante);
            preSt.setString(2, cuentaSolicitada);
            
            preSt.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al aceptar asociación: " + e.getMessage());
            return false;
        }
        
    }
    
    /**
     * Permite rechazar una solicitud de asociación de cuenta
     * @param dpiSolicitante Dpi del cliente que solicitó la asociación 
     * @param cuentaSolicitada Número de la cuenta que se deseaba asociar
     * @return retorna true si el rechazo de la solicitud se dió con éxito, de lo contrario retorna false
     */
    public boolean rechazar(String dpiSolicitante, String cuentaSolicitada) {
        
        String update = "UPDATE CUENTA_ASOCIADA SET estado = 'RECHAZADA' WHERE cliente = ? AND cuenta = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(update)) {
            
            preSt.setString(1, dpiSolicitante);
            preSt.setString(2, cuentaSolicitada);
            
            preSt.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al rechazar asociación: " + e.getMessage());
            return false;
        }
        
    }
    
}
