/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.transaccion;

import com.froi.cuenta.ObtenerDatosCuenta;
import com.froi.entidades.Transaccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import sun.tools.java.Type;

/**
 *
 * @author froi-pc
 */
public class TransaccionPortal {
    
    private Connection connection;
    
    public TransaccionPortal(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Método que permite llevar a cabo una transferencia
     * @param cuentaOrigen Código de la cuenta que transferirá el dinero
     * @param cuentaDestino Código de la cuenta que recibirá el dinero
     * @param dinero Cantidad monetaria que será transferida
     * @return retorna true si la transferencia se pudo llevar a cabo
     */
    public boolean realizarTransferencia(double dinero, Transaccion transaccion) {
        
        ObtenerDatosCuenta datosCuenta = new ObtenerDatosCuenta(connection);
        double dineroCuentaOrigen = Double.parseDouble(datosCuenta.obtenerDato("credito", transaccion.getCuentaOrigen())) - dinero;
        double dineroCuentaDestino = Double.parseDouble(datosCuenta.obtenerDato("credito", transaccion.getCuentaDestino())) + dinero;
        
        String update = "UPDATE CUENTA SET credito = ? WHERE codigo = ?";
        String insert = "INSERT INTO TRANSACCION VALUES (?,?,?,CURDATE(),CURTIME(),?,?,?)";
        
        try (PreparedStatement preSt = connection.prepareStatement(update);
                PreparedStatement preSt2 = connection.prepareStatement(update);
                PreparedStatement preSt3 = connection.prepareStatement(insert)) {
            
            preSt.setDouble(1, dineroCuentaOrigen);
            preSt.setString(2, transaccion.getCuentaOrigen());
            
            preSt2.setDouble(1, dineroCuentaDestino);
            preSt2.setString(2, transaccion.getCuentaDestino());
            
            preSt.executeUpdate();
            preSt2.executeUpdate();
            
            preSt3.setString(1, transaccion.getCodigo());
            preSt3.setString(2, transaccion.getCuentaDestino());
            preSt3.setString(3, transaccion.getCuentaOrigen());
            preSt3.setString(4, transaccion.getTipo());
            preSt3.setDouble(5, transaccion.getMonto());
            preSt3.setNull(6, Type.NULL);
            
            preSt3.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al realizar la transferencia: " +e.getMessage());
            return false;
        }
        
    }
    
    /**
     * Método que permite comprobar si el propietario de la cuenta de origen (en una transferencia) posee el dinero que desea transferir
     * @param cuentaOrigen Código de la cuenta que desea realizar la transferencia
     * @param dinero cantidad de dinero que el cliente desea transferir
     * @return retorna true si el cliente posee el dinero suficiente (en la cuenta seleccionada) para realizar la transferencia, de lo contrario retorna false
     */
    public boolean comprobarValidezTransaccion(String cuentaOrigen, double dinero) {
        
        ObtenerDatosCuenta datosCuenta = new ObtenerDatosCuenta(connection);
        double dineroCuentaOrigen = Double.parseDouble(datosCuenta.obtenerDato("credito", cuentaOrigen));
        
        if (dineroCuentaOrigen >= dinero) {
            return true;
        } else {
            return false;
        }
        
    }
    
}
