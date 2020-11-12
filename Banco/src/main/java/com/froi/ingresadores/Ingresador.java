/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.ingresadores;

import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public abstract class Ingresador {
    
    public abstract void ingresoArchivo(ArrayList<String> errores);
    
    public abstract void ingresoNormal();
    
}
