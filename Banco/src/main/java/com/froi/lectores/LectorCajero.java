/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.lectores;

import com.froi.banco.Conexion;
import com.froi.entidades.Cajero;
import com.froi.entidades.Estructura;
import com.froi.ingresadores.IngresadorCajero;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class LectorCajero extends LectorArchivo {

    private Cajero cajero;
    private ArrayList<String> errores;
    
    public LectorCajero(ArrayList<String> errores){
        cajero = new Cajero();
        this.errores = errores;
    }
    
    /**
     * Convierte un ArrayList de atributos en una entidad, al mismo tiempo la ingresa al sistema
     * @param atributos ArrayList que contiene los atributos de un cajero
     */
    @Override
    public void convertToEntidad(ArrayList<Estructura> atributos) {
    
        for(Estructura element: atributos) {
            
            String elemento = element.getTipo();
            String descripcion = element.getDescripcion();
            
            if(elemento.equals("CODIGO")) {
                cajero.setCodigo(descripcion);
            } else if(elemento.equals("NOMBRE")) {
                cajero.setNombre(descripcion);
            } else if(elemento.equals("TURNO")) {
                cajero.setTurno(descripcion);
            } else if(elemento.equals("DPI")) {
                cajero.setDpi(descripcion);
            } else if(elemento.equals("DIRECCION")) {
                cajero.setDireccion(descripcion);
            } else if(elemento.equals("SEXO")) {
                cajero.setSexo(descripcion);
            } else if(elemento.equals("PASSWORD")) {
                cajero.setPassword(descripcion);
            }
            
        }
        
        IngresadorCajero ingresador = new IngresadorCajero(Conexion.getConnection(), cajero);
        ingresador.ingresoArchivo(errores);
        
    }
    
    
    
}
