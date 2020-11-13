/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.transaccion;

import com.froi.cuenta.ObtenerDatosCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, double dinero) {
        
        ObtenerDatosCuenta datosCuenta = new ObtenerDatosCuenta(connection);
        double dineroCuentaOrigen = Double.parseDouble(datosCuenta.obtenerDato("credito", cuentaOrigen)) - dinero;
        double dineroCuentaDestino = Double.parseDouble(datosCuenta.obtenerDato("credito", cuentaDestino)) + dinero;
        
        String update = "UPDATE CUENTA SET credito = ? WHERE codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(update);
                PreparedStatement preSt2 = connection.prepareStatement(update)) {
            
            preSt.setDouble(1, dineroCuentaOrigen);
            preSt.setString(2, cuentaOrigen);
            
            preSt2.setDouble(1, dineroCuentaDestino);
            preSt2.setString(2, cuentaDestino);
                    
            preSt.executeUpdate();
            preSt2.executeUpdate();
            
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
