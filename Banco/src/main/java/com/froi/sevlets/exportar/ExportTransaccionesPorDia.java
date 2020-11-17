/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.froi.sevlets.exportar;

import com.froi.banco.Conexion;
import com.froi.banco.ConvertToList;
import com.froi.cajero.reportes.ListadoTransaccionesPorDia;
import com.froi.entidades.ColeccionDatos;
import com.froi.gerente.reportes.HistorialTransaccionesPorCliente;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author froi-pc
 */
@WebServlet(name = "ExportTransaccionesPorDia", urlPatterns = {"/ExportTransaccionesPorDia"})
public class ExportTransaccionesPorDia extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {

            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transaccionesPorDia.pdf");
            
            String fechaInicial = request.getSession().getAttribute("fechaInicial").toString();
            String fechaFinal = request.getSession().getAttribute("fechaFinal").toString();
            String codigoCajero = request.getSession().getAttribute("usuario").toString();
            
            ListadoTransaccionesPorDia reporte = new ListadoTransaccionesPorDia(Conexion.getConnection(), fechaInicial, fechaFinal);
            
            
            ArrayList<String[]> listaAux = new ArrayList<>();
            ArrayList<String> fechas = reporte.obtenerFechas(codigoCajero);

            for(String fecha: fechas) {
                ArrayList<String[]> transacciones = reporte.obtenerTransacciones(codigoCajero, fecha);
                
                for(String[] element : transacciones) {
                    
                    String[] datos = new String[6];
                    
                    datos[0] = element[0];
                    datos[1] = element[1];
                    datos[2] = element[2];
                    datos[3] = element[3];
                    datos[4] = element[4];
                    datos[5] = fecha;
                    
                    listaAux.add(datos);
                    
                }
                
            }
            ConvertToList convertidor = new ConvertToList();
            
            List<ColeccionDatos> lista = convertidor.getArrayList(listaAux);
            
            File file = new File(request.getServletContext().getRealPath("/resources/cajero/cajero_transacciones_por_dia.jrxml"));
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Fecha", "Del " + fechaInicial + " al " + fechaFinal);
            parameters.put("Balance", "Balance: " + reporte.getBalance(codigoCajero));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReports, parameters, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
            
        } catch (Exception  e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

}
