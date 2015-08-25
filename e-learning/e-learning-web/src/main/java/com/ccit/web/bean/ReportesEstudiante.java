/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.web.util.WebUtil;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class ReportesEstudiante implements Serializable {

    @Resource(name = "lookup")
    private DataSource lookup;
    private static JasperReport reporte = null;
    private static ResourceBundle bundle = null;
    private StreamedContent file;
    private StreamedContent reporteEstudiante;

    /**
     * Creates a new instance of CarnetBean
     */
    public ReportesEstudiante() {
    }

    @PostConstruct
    public void init() {
        Connection con = null;
        try {
            bundle = ResourceBundle.getBundle("dynamic");
            UsuarioDTo userLogged = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");

            String path_jrxml = bundle.getString("path_reporte_estudiantes");
            String img = bundle.getString("path_reporte_img");
            Map parametros = new HashMap();
            parametros.put("IMG", img);
            con = lookup.getConnection();

            try {
                reporte = JasperCompileManager.compileReport(path_jrxml);
                JasperPrint print = JasperFillManager.fillReport(reporte,
                        parametros, con);

                //  JasperExportManager.exportReportToPdfFile(print, "/home/marino/Documentos/carnet"+userLogged.getNumDoc()+".pdf");
                byte[] archivo = JasperRunManager.runReportToPdf(reporte, parametros, con);
                ByteArrayInputStream in = new ByteArrayInputStream(archivo);
                reporteEstudiante = new DefaultStreamedContent(in, "application/pdf", "reporteEstudiantes.pdf");
                //  pdfResourse= new ReportesEstudiante.MyResource(archivo);
            } catch (JRException ex) {
                Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            // horarioEstudiantil = new PdfResource(manualpdf);
            System.out.println("Reporte estudiantes generado con exito");
        } catch (SQLException ex) {

            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }




    }

    public StreamedContent getFile() {
        try {
            String path_manual = bundle.getString("path_reporte_manual");
            FileInputStream in = new FileInputStream(new File(path_manual));
            byte[] manualpdf = new byte[in.available()];
            in.read(manualpdf);
            file = new DefaultStreamedContent(in, "application/pdf", "manual.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public StreamedContent getReporteEstudiante() {
        return reporteEstudiante;
    }

    public void setReporteEstudiante(StreamedContent reporteEstudiante) {
        this.reporteEstudiante = reporteEstudiante;
    }
}
