/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author froi-pc
 */
public class Conexion {
    
    private static Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/BANCO?useSSL=false";
    private final String user = "usuarioBanco";
    private final String password = "admin123";
    
    /**
     * Método que nos sirve para crear la conexión con la base de datos
     */
    public void crearConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión Creada");
            
        } catch (Exception e){
            System.out.println("Error al Crear la Conexión");
        }
    }
    
    public static Connection getConnection(){
        return connection;
    }
    
    public String getUser(){
        return user;
    }
    
    public String getPassword(){
        return password;
    }
    
}
