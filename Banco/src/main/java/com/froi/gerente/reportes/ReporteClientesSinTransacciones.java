/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.gerente.reportes;

import java.sql.Connection;

/**
 *
 * @author froi-pc
 */
public class ReporteClientesSinTransacciones {
    
    private Connection connection;
    
    public ReporteClientesSinTransacciones(Connection connection) {
        this.connection = connection;
    }
    
}
