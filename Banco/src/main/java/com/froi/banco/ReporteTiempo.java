/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.banco;

import java.sql.Connection;

/**
 *
 * @author froi-pc
 */
public class ReporteTiempo {
    
    private Connection connection;
    private String tiempoInicial;
    private String tiempoFinal;

    public ReporteTiempo(Connection connection, String tiempoInicial, String tiempoFinal) {
        this.connection = connection;
        this.tiempoInicial = tiempoInicial;
        this.tiempoFinal = tiempoFinal;
    }

    
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(String tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }

    public String getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(String tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }
    
    
    
}
