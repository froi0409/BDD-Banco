/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class ObtenerDatosCuenta {
    
    private Connection connection;
    
    public ObtenerDatosCuenta(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Metodo que sirve para obtener algún dato de la cuenta a partir de su código
     * @param dato dato que se desea obtener del cliente
     * @param codigoCuenta Código de la cuenta
     * @return retorna el dato que se desea obtener
     */
    public String obtenerDato(String dato, String codigoCuenta) {
        
        String query = "SELECT " + dato + " FROM CUENTA WHERE codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, codigoCuenta);
            ResultSet result = preSt.executeQuery();
            result.next();
            
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error al obtener dato de la cuenta: " + e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Método que sirve para obtener al propietario de lacuenta a traves del código de la cuenta
     * @param codigoCuenta Número de cuenta de la cuál se desea saber el propietario
     * @return 
     */
    public String propietario(String codigoCuenta) {
        
        String query = "SELECT C.nombre FROM CLIENTE C INNER JOIN CUENTA CU ON C.dpi=CU.cliente WHERE CU.codigo = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, codigoCuenta);
            ResultSet result = preSt.executeQuery();
            
            result.next();
            return result.getString(1);
            
        } catch (Exception e) {
            System.out.println("Error propietario cuenta: " + e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Método que nos permite obtener a los propietarios de varias cuentas y almacenarlo en un ArrayList junto con el código de cuenta
     * @param cuentas ArrayList que únicamente contiene los códigos de varias cuentas
     * @return ArrayList tipo String[] que contiene e String[0] el código de una cuenta y en String[1] El nombre del propetario de la cuenta
     */
    public ArrayList<String[]> propietarioPorCuenta(ArrayList<String> cuentas) {
        
        ArrayList<String[]> lista = new ArrayList<String[]>();
        String query = "SELECT CU.codigo,CL.nombre FROM CUENTA CU INNER JOIN CLIENTE CL ON CU.cliente=CL.dpi WHERE CU.codigo = ?";
        
        for(String element: cuentas) { //Recorremos cada una de las cuentas que contiene el ArrayList de entrada
            
            try (PreparedStatement preSt = connection.prepareStatement(query)) {
                
                preSt.setString(1, element);
                ResultSet result = preSt.executeQuery();
                
                result.next();
                
                String[] datos = new String[2];
                
                datos[0] = result.getString(1); //Inresamos al String[0] el código de la cuenta
                datos[1] = result.getString(2); //Ingresamos al String[1] el nombre del propietario de la cuenta
                
                lista.add(datos); //Añadimos los datos de la cuenta al ArrayList principal
                
            } catch (Exception e) {
                System.out.println("Error al buscar al propietario de la cuenta " + cuentas + ": " + e.getMessage());
            }
            
        }
        
        return lista;
        
    }
    
    /**
     * Comprueba si existe alguna otra cuenta con tales características
     * @param campo Campo que se desea consultar
     * @param dato Dato que se desea consultar
     * @return retorna true si existe otra cuenta con esas características
     */
    public boolean existsOther(String campo, String dato) {
        
        String query = "SELECT COUNT(*) FROM CUENTA WHERE " + campo + " = ?";
        
        try (PreparedStatement preSt = connection.prepareStatement(query)) {
            
            preSt.setString(1, dato);
            ResultSet result = preSt.executeQuery();
            result.next();
            
            if(result.getInt(1) == 0) {
                return false;
            } else {
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Error exists other: " + e.getMessage());
            return true;
        }
        
    }
    
}
