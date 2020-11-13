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
public class GeneradorContraseña {
    
    /**
     * Método que nos permite generar una contraseña aleatoria de 6 caracteres
     * @return 
     */
    public String generarContraseña(){
        String contraseña = "";
        for(int i = 0; i < 6; i++) {
            int numero = (int) (Math.random() * 10);
            contraseña += numero;
        }
        return contraseña;
    }
    
}
