/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.lectores;

import com.froi.banco.Conexion;
import com.froi.entidades.Estructura;
import com.froi.entidades.Transaccion;
import com.froi.ingresadores.IngresadorTransaccion;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class LectorTransaccion extends LectorArchivo {
    
    private Transaccion transaccion;
    private ArrayList<String> errores;
    
    public LectorTransaccion(ArrayList<String> errores) {
        transaccion = new Transaccion();
        this.errores = errores;
    }

    /**
     * Convierte una ArrayList de atributos a una Entidad Transaccion
     * @param atributos ArrayList que contiene los atributos que debe tener una Transacción
     */
    @Override
    public void convertToEntidad(ArrayList<Estructura> atributos) {
        
        for(Estructura element: atributos) {
            
            String elemento = element.getTipo();
            String descripcion = element.getDescripcion();
            
            if (elemento.equals("CODIGO")) {
                transaccion.setCodigo(descripcion);
            } else if (elemento.equals("CUENTA-ID")) {
                transaccion.setCuentaDestino(descripcion);
            } else if (elemento.equals("FECHA")) {
                transaccion.setFecha(descripcion);
            } else if (elemento.equals("HORA")) {
                transaccion.setHora(descripcion);
            } else if (elemento.equals("TIPO")) {
                transaccion.setTipo(descripcion);
            } else if (elemento.equals("MONTO")) {
                transaccion.setMonto(Double.parseDouble(descripcion));
            } else if (elemento.equals("CAJERO-ID")) {
                transaccion.setCajero(descripcion);
            }
            
        }
        
        IngresadorTransaccion ingresador = new IngresadorTransaccion(Conexion.getConnection(), transaccion);
        ingresador.ingresoArchivo(errores);
        
    }
    
}
