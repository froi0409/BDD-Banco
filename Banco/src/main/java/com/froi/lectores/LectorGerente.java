/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.lectores;

import com.froi.banco.Conexion;
import com.froi.entidades.Estructura;
import com.froi.entidades.Gerente;
import com.froi.ingresadores.IngresadorGerente;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class LectorGerente extends LectorArchivo {

    private Gerente gerente;
    private ArrayList<String> errores;
    
    public LectorGerente(ArrayList<String> errores) {
        gerente = new Gerente();
        this.errores = errores;
    }
    
    /**
     * Convierte un ArrayList de tipo String en un objeto de tipo Gerente
     * @param atributos ArrayList de tipo String que contiene los atributos que debe tener un Gerente
     */
    @Override
    public void convertToEntidad(ArrayList<Estructura> atributos) {
    
        for(Estructura element: atributos) {
            
            String elemento = element.getTipo();
            String descripcion = element.getDescripcion();
            
            if(elemento.equals("CODIGO")) {
                gerente.setCodigo(descripcion);
            } else if (elemento.equals("NOMBRE")) {
                gerente.setNombre(descripcion);
            } else if (elemento.equals("TURNO")) {
                gerente.setTurno(descripcion);
            } else if (elemento.equals("DPI")) {
                gerente.setDpi(descripcion);
            } else if (elemento.equals("DIRECCION")) {
                gerente.setDireccion(descripcion);
            } else if (elemento.equals("SEXO")) {
                gerente.setSexo(descripcion);
            } else if (elemento.equals("PASSWORD")) {
                gerente.setPassword(descripcion);
            }
            
        }
        
        IngresadorGerente ingresador = new IngresadorGerente(Conexion.getConnection(), gerente);
        ingresador.ingresoArchivo(errores);
        
    }
    
}
