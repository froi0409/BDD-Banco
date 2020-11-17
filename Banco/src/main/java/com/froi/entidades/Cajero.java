/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.entidades;

import com.froi.banco.Encriptador;

/**
 *
 * @author froi-pc
 */
public class Cajero extends Entidad {
    
    private String codigo;
    private String nombre;
    private String turno;
    private String dpi;
    private String direccion;
    private String sexo;
    private String password;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Encriptador encriptador = new Encriptador();
        this.password = encriptador.encriptar(password);
    }
    
    public String getHoraInicio(String turno){
        if(turno.equalsIgnoreCase("VESPERTINO")){
            return "13:00:00";
        } else {
            return "06:00:00";
        }
    }
    
    public String getHoraFinal(String turno){
        if(turno.equalsIgnoreCase("VESPERTINO")){
            return "23:59:00";
        } else {
            return "14:30:00";
        }
    }
    
}
