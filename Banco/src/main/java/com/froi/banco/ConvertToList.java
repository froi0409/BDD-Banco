/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

import com.froi.entidades.ColeccionDatos;
import java.util.ArrayList;

/**
 *
 * @author froi-pc
 */
public class ConvertToList {
    
 
    /**
     * Permite obtener un ArrayList de tipo ColeccionDatos a partir de otro de tipo String[]
     * @param entrada ArrayList tipo String[]
     * @return ArrayList tipo ColeccionDatos
     */
    public ArrayList<ColeccionDatos> getArrayList(ArrayList<String[]> entrada) {
        ArrayList<ColeccionDatos> lista = new ArrayList<ColeccionDatos>();
        
        for(String[] element: entrada) {
        
            ColeccionDatos coleccion = new ColeccionDatos();
        
            for(int i = 0; i < element.length; i++) {
                
                switch(i){
                    case 0:
                        coleccion.setPrimeraColumna(element[i]);
                        break;
                    case 1:
                        coleccion.setSegundaColumna(element[i]);
                        break;
                    case 2:
                        coleccion.setTerceraColumna(element[i]);
                        break;
                    case 3:
                        coleccion.setCuartaColumna(element[i]);
                        break;
                    case 4:
                        coleccion.setQuintaColumna(element[i]);
                        break;
                    case 5:
                        coleccion.setSextaColumna(element[i]);
                        break;
                    case 6:
                        coleccion.setSeptimaColumna(element[i]);
                        break;
                    case 7:
                        coleccion.setOctavaColumna(element[i]);
                        break;
                    case 8:
                        coleccion.setNovenaColumna(element[i]);
                        break;
                }
                
            }
            
            lista.add(coleccion);
            
        }
        
        return lista;
    }
    
}
