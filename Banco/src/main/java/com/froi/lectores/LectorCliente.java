/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.lectores;

import com.froi.banco.Conexion;
import com.froi.entidades.Cliente;
import com.froi.entidades.Cuenta;
import com.froi.entidades.Estructura;
import com.froi.ingresadores.IngresadorCliente;
import com.froi.ingresadores.IngresadorCuenta;
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
public class LectorCliente {

    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Cuenta> listaCuentas = new ArrayList<>();
    private ArrayList<String> errores = new ArrayList<String>();
    private int cont = 0;
    
    public LectorCliente(ArrayList<String> errores) {
        this.errores = errores;
    }
    
    public void read (String path){ 
    
        try {
            
            String cuenta = "";
            int contCuentas = 0, inicioCuentas = 0, contCuentasAux = 0;
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(new File(path));
            NodeList listaTag = documento.getElementsByTagName("CLIENTE");
            NodeList cuentas = documento.getElementsByTagName("CUENTA");
            NodeList cuentasAux = documento.getElementsByTagName("CUENTAS");
            
            for(int i = 0; i < listaTag.getLength(); i++){
                
                Node nodo = listaTag.item(i);
                Cliente clienteAux = new Cliente();
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    
                    Element e = (Element) nodo;
                    
                    NodeList hijos = e.getChildNodes();
                    
                    for(int j = 0; j < hijos.getLength(); j++){
                        
                        Node hijo = hijos.item(j);
                        int contAux = 0;
                        
                        
                        if(hijo.getNodeType() == Node.ELEMENT_NODE && !hijo.getNodeName().equals("CUENTAS")) {
                            
                            if (hijo.getNodeName().equals("CODIGO")) {
                                clienteAux.setCodigo(hijo.getTextContent());
                            } else if (hijo.getNodeName().equals("NOMBRE")) {
                                clienteAux.setNombre(hijo.getTextContent());
                            } else if (hijo.getNodeName().equals("DPI")) {
                                cuenta = hijo.getTextContent();
                                clienteAux.setDpi(hijo.getTextContent());
                            } else if (hijo.getNodeName().equals("BIRTH")) {
                                clienteAux.setBirth(hijo.getTextContent());
                            } else if (hijo.getNodeName().equals("DIRECCION")) {
                                clienteAux.setDireccion(hijo.getTextContent());
                            } else if (hijo.getNodeName().equals("SEXO")) {
                                clienteAux.setSexo(hijo.getTextContent());
                            } else if (hijo.getNodeName().equals("DPI-PDF")) {
                                clienteAux.setDpiPdf(hijo.getTextContent());
                            } else if (hijo.getNodeName().equals("PASSWORD")) {
                                clienteAux.setPassword(hijo.getTextContent());
                            }
                                                       
                        } else if (hijo.getNodeName().equals("CUENTAS")) {
                            Node nodoAux = cuentasAux.item(contCuentasAux);
                            if(nodoAux.getNodeType() == Node.ELEMENT_NODE) {
                                Element eaux = (Element) nodoAux;
                                
                                NodeList child = eaux.getChildNodes();
                                
                                for (int k = 0; k < child.getLength(); k++) {
                                    
                                    Node kid = child.item(k);
                                    
                                    if(kid.getNodeType() == Node.ELEMENT_NODE && kid.getNodeName().equals("CUENTA")) { //contamos los nodos cuenta que tiene el cliente
                                        contCuentas++;
                                        System.out.println(contCuentas + "  " + kid.getFirstChild().getNodeName());
                                    }
                                    
                                    
                                    
                                }
                                
                            }
                            contCuentasAux++;
                        }
                        
                    }
                    
                    System.out.println("");
                    //lector.convertToEntidad(atributos);
                }
                
                for (int k = inicioCuentas; k < contCuentas; k++) {
                    
                    Node nodoAux = cuentas.item(k);
                    Cuenta cuentaAux = new Cuenta();
                    
                    if(nodoAux.getNodeType() == Node.ELEMENT_NODE){
                        
                        Element ea = (Element) nodoAux;
                        
                        NodeList child = ea.getChildNodes();
                        
                        cuentaAux.setCliente(cuenta);
                        
                        for (int l = 0; l < child.getLength(); l++) {
                            
                            Node kid = child.item(l);
                            
                            if (kid.getNodeType() == Node.ELEMENT_NODE) {
                                
                                if (kid.getNodeName().equals("CODIGO")) {
                                    cuentaAux.setCodigo(kid.getTextContent());
                                } else if (kid.getNodeName().equals("CREADA")) {
                                    cuentaAux.setFecha(kid.getTextContent());
                                } else if (kid.getNodeName().equals("CREDITO")) {
                                    cuentaAux.setCredito(Double.parseDouble(kid.getTextContent()));
                                }
                                
                            }
                            
                        }
                        
                    }
                    
                    listaCuentas.add(cuentaAux);
                    inicioCuentas++;
                }
                
                listaClientes.add(clienteAux);
                
            }
            
            for(Cliente element: listaClientes){
                IngresadorCliente ingresador = new IngresadorCliente(Conexion.getConnection(), element);
                ingresador.ingresoArchivo(errores);
            }
            
            for(Cuenta element: listaCuentas) {
                IngresadorCuenta ingresador = new IngresadorCuenta(Conexion.getConnection(), element);
                ingresador.ingresoArchivo(errores);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LectorArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
