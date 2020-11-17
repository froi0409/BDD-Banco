/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.entidades;

/**
 *
 * @author froi-pc
 */
public class Estructura extends Entidad{
    
    private String tipo;
    private String descripcion;

    public Estructura(String tipo, String descripcion){
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
