/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.utilidades.UtilidadesFecha;
import com.ccit.web.util.WebUtil;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author marino
 */
@Named(value = "horarioConsulta")
@RequestScoped
public class HorarioConsulta implements Serializable {

    @Resource(name = "lookup")
    private DataSource lookup;
    private static JasperReport reporte = null;
    private static ResourceBundle bundle = null;
    

    /**
     * Creates a new instance of CarnetBean
     */
    public HorarioConsulta() {
    }

    @PostConstruct
    public void init() {
        
    }

    public void descargarReporteHorario(){
        bundle = ResourceBundle.getBundle("dynamic");
        Connection con = null;
        try {

            String path_jrxml = bundle.getString("path_reporte_horario");
            String img = bundle.getString("path_reporte_img");

            UsuarioDTo userLogged = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");

            Calendar c = Calendar.getInstance();



            Map parametros = new HashMap();
            parametros.put("FECHA_INI", UtilidadesFecha.getDate(getFormatDate(c, Calendar.MONDAY)));
            parametros.put("FECHA_FIN", UtilidadesFecha.getDate(getFormatDate(c, Calendar.SATURDAY)));
            parametros.put("ID_USUARIO", userLogged.getIdUsuario());
            parametros.put("IMG", img);


            con = lookup.getConnection();

            try {
                reporte = JasperCompileManager.compileReport(path_jrxml);
                JasperPrint print = JasperFillManager.fillReport(reporte,
                        parametros, con);
                // JasperExportManager.exportReportToPdfFile(print, "/home/marino/Documentos/horario"+userLogged.getNumDoc()+".pdf");
                byte[] archivo = JasperRunManager.runReportToPdf(reporte, parametros, con);




                WebUtil.getRequest().getSession().setAttribute("file", archivo);

                descargarArchivo();


            } catch (JRException ex) {
                Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }


            System.out.println("Reporte generado con exito");
        } catch (SQLException ex) {
            System.out.println("Error al obtener conexion");
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
    
    public void descargarArchivo() {
        try {
            
             
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/DowloadServlet");
        } catch (IOException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    public String getFormatDate(Calendar c, int day) {
        String fecha = "";
        c.set(Calendar.DAY_OF_WEEK, day);

        fecha = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());



        return fecha;

    }

   
}
