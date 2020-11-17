/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author froi-pc
 */
public class ArchivoDeEntrada {
    private HttpServletRequest request;
    private String path;
    private String parametroFileHtml;
    
    /**
     * Constructor de la clase ArchivoDeEntrada, inicializa atributos necesarios del objeto
     * @param request - propiedad request del servlet desde el que se crea el objeto
     * @param parametroFileHtml - parametro que se obtiene del input file html 'name="parametroFileHtml"'
     */
    public ArchivoDeEntrada(HttpServletRequest request, String parametroFileHtml){
        this.request = request;
        this.parametroFileHtml = parametroFileHtml;
        setArchivo();
    }
    
    /**
     * Método que sirve para crear el archivo dentro del servidor
     */
    private void setArchivo(){
         //String description = request.getParameter(""); //Recuperamos lo obtenido en el input file de html
        
         try{
            Part filePart = request.getPart(parametroFileHtml); //Obtenemos lo que nos envía el input file de html
            String nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); //Obtenemos el nombre del archivo
            InputStream fileContent = filePart.getInputStream(); //Obtenemos elarchivo
            OutputStream salida = new FileOutputStream(nombreArchivo); //Reescribimos el archivo
            fileContent.transferTo(salida); //Convertimos el archivo de entrada al archivo reescrito
            File file = Paths.get(filePart.getSubmittedFileName()).toFile(); //Creamos el archivo en el servidot

            path = file.getAbsolutePath().replace("/" + file.getName(), "") + "/" + nombreArchivo; //obtenemos el path completo del archivo dentro del servidor

            System.out.println(path); //Imprimimos en consola el path completo
            System.out.println(nombreArchivo);
         } catch (Exception ex) {
             System.out.println("Fallo al crear el archivo en el servidor: " + ex.getMessage());
         }
    }
    
    /**
     * 
     * @return Path del archivo dentro del servidor
     */
    public String getPath(){
        return path;
    }
    
}
