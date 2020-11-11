/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.lectores;

import com.froi.entidades.Entidad;
import com.froi.entidades.Estructura;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author froi-pc
 */
public class Lector {
        
    private ArrayList<Estructura> atributos = new ArrayList<Estructura>();
    private int cont = 0;
    
    /**
     * Identifica los valores de los 'hijos' de cada nodo principal
     * @param path Ruta del archivo xml (normalmente denro del servidor)
     * @param tagName Nombre de la etiqueta que define al nodo
     * @param lector Lector específico de una entidad (normalmente, la entidad debe existir dentro de la base de datos)
     * @param entidad Entidad a analizar (normalmente, la entidad debe existir dentro de la base de datos)
     */
    public void leerTag(String path, String tagName, LectorArchivo lector, Entidad entidad){
        
        try {
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(new File(path));
            NodeList listaTag = documento.getElementsByTagName(tagName);
            
            
            for(int i = 0; i < listaTag.getLength(); i++){
                
                Node nodo = listaTag.item(i);
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    
                    Element e = (Element) nodo;
                    
                    NodeList hijos = e.getChildNodes();
                    
                    for(int j = 0; j < hijos.getLength(); j++){
                        
                        Node hijo = hijos.item(j);
                        
                        if(hijo.getNodeType() == Node.ELEMENT_NODE) {
                            
                            System.out.println("Propiedad: " + hijo.getNodeName() + "     Valor: " + hijo.getTextContent());
                            atributos.add(new Estructura(hijo.getNodeName(),hijo.getTextContent()));
                            
                        }
                        
                    }
                    
                    System.out.println("");
                    lector.convertToEntidad(atributos);
                    atributos.clear();
                }
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LectorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    /**
     * Obtenemos los datos de cada 'hijo' que posee el nodo
     * @return 
     */
    public ArrayList<Estructura> getAtributos() {
        return atributos;
    }
    
}
