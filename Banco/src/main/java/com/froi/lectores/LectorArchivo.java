/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.lectores;

import com.froi.entidades.Estructura;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public abstract class LectorArchivo {
    
    private String path;
    private String tagName;
    private ArrayList<Estructura> atributos;
    
    public LectorArchivo(){
        
        atributos = new ArrayList<Estructura>();
    }
    
    public abstract void convertToEntidad(ArrayList<Estructura> atributos);
    
    public String getPath(){
        return path;
    }
    
    public String getTagName(){
        return tagName;
    }

    public ArrayList<Estructura> getAtributos() {
        return atributos;
    }

    public void setAtributos(ArrayList<Estructura> atributos) {
        this.atributos = atributos;
    }
    
}
