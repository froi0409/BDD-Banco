/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

/**
 *
 * @author froi-pc
 */
public class AnalizadorCredenciales {
    
    private String usuario;
    private String password;
    
    public AnalizadorCredenciales(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public String analizar(){
        
        AnalizadorDeExistencia existencia = new AnalizadorDeExistencia(Conexion.getConnection());
        
        
        return "";
        
    }
    
}
