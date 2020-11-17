/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.transaccion;

import com.froi.cuenta.ObtenerDatosCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import sun.tools.java.Type;

/**
 *
 * @author froi-pc
 */
public class TransaccionCajero {
    
    private Connection connection;

    public TransaccionCajero (Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que permite llevar a cabo una transferencia
     * @param transaccion Objeto tipo Transaccion que contiene los atributos de la misma
     * @param monto Monto de la transaccion
     * @param tipo Tipo de la transaccion
     * @return Retorna true si la transacción se llevó a cabo con éxito, de lo contrario retorna false
     */
    public boolean realizar(com.froi.entidades.Transaccion transaccion, double monto, String tipo) {
        
        ObtenerDatosCuenta datosCuenta = new ObtenerDatosCuenta(connection);
        String insert = "INSERT INTO TRANSACCION VALUES (?,?,?,CURDATE(),CURTIME(),?,?,?)";
        String update = "UPDATE CUENTA SET credito = ? WHERE codigo = ?";
        double dinero = Double.parseDouble(datosCuenta.obtenerDato("credito", transaccion.getCuentaDestino())) + monto;
        
        try (PreparedStatement preSt = connection.prepareStatement(insert);
             PreparedStatement preSt2 = connection.prepareStatement(update)) {
            
            preSt.setString(1, transaccion.getCodigo());
            preSt.setString(2, transaccion.getCuentaDestino());
            preSt.setNull(3, Type.NULL);
            preSt.setString(4, tipo);
            preSt.setDouble(5, monto);
            preSt.setString(6, transaccion.getCajero());
            
            preSt2.setDouble(1, dinero);
            preSt2.setString(2, transaccion.getCuentaDestino());
            
            preSt.executeUpdate();
            preSt2.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error deposito: " + e.getMessage());
            return false;
        }
        
    }
    
}
