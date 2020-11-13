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
public class GeneradorCuenta {
    
    /**
     * Método que nos permite generar un numero de cuenta aleatorio de 10 caracteres
     * @return 
     */
    public String generarCuenta(){
        String cuenta = "";
        for(int i = 0; i < 10; i++) {
            int numero = (int) (Math.random() * 10);
            cuenta += numero;
        }
        return cuenta;
    }
    
}
