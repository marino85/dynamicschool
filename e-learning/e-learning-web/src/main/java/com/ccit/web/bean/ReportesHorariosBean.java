/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.web.util.FiltroReportes;
import com.ccit.web.util.WebUtil;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author marino
 */
@ManagedBean(name = "reportesHorariosBean")
@RequestScoped
public class ReportesHorariosBean implements Serializable {

    @Resource(name = "lookup")
    private DataSource lookup;
    private static JasperReport reporteSedes = null;
    private static JasperReport reporteDocente = null;
    private static JasperReport reporteAula = null;
   
    private boolean isSedes = false;
    private boolean isDocente = false;
    private boolean isAula = false;
    private FiltroReportes filtro = new FiltroReportes();
    private static ResourceBundle bundle = null;

    @PostConstruct
    public void init() {
        System.out.println("init reporte horario");
        bundle = ResourceBundle.getBundle("dynamic");
    }

    public void generarReporteDocentes() {
        Connection con = null;
        try {
            //pdfResourseDocente = null;
            String pathReporteDocente = bundle.getString("path_reporte_docente");
            String img = bundle.getString("path_reporte_img");
            Map parametros = new HashMap();
            parametros.put("FECHA", filtro.getFechaInicio());
            UsuarioDTo userLogged = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
            parametros.put("ID_USUARIO", userLogged.getIdPerfil().equals(Constants.PERFIL_PROFESOR) ? userLogged.getIdUsuario() : filtro.getIdProfesor());
            parametros.put("IMG", img);
            con = lookup.getConnection();
            reporteDocente = JasperCompileManager.compileReport(pathReporteDocente);
            JasperPrint print = JasperFillManager.fillReport(reporteDocente,
                    parametros, con);
            byte[] archivo = JasperRunManager.runReportToPdf(reporteDocente, parametros, con);
            WebUtil.getRequest().getSession().setAttribute("file", archivo);

            System.out.println("Ok");
            this.setIsDocente(true);
            descargarArchivo();
        } catch (SQLException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public void generarReporteSedes() {
        Connection con = null;
        try {
           // pdfResourseSede = null;

            String pathReporteSede = bundle.getString("path_reporte_sede");
            String img = bundle.getString("path_reporte_img");
            Map parametros = new HashMap();
            Calendar ini = Calendar.getInstance();
            Calendar fin = Calendar.getInstance();
            ini.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            fin.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            parametros.put("FECHA_INI", ini.getTime());
            parametros.put("FECHA_FIN", fin.getTime());
            parametros.put("ID_SEDE", filtro.getIdSede());
            parametros.put("IMG", img);
            con = lookup.getConnection();
            reporteSedes = JasperCompileManager.compileReport(pathReporteSede);
            JasperPrint print = JasperFillManager.fillReport(reporteSedes,
                    parametros, con);
            byte[] archivo = JasperRunManager.runReportToPdf(reporteSedes, parametros, con);
            WebUtil.getRequest().getSession().setAttribute("file", archivo);
            System.out.println("Ok");
            this.setIsSedes(true);
            descargarArchivo();
        } catch (SQLException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public void generarReportesAula() {
        Connection con = null;
        try {
           // pdfResourseAula = null;

            String pathReporteDocente = bundle.getString("path_reporte_estudiantes_clase");
            String img = bundle.getString("path_reporte_img");
            Map parametros = new HashMap();
            parametros.put("FECHA", filtro.getFechaInicio());
            UsuarioDTo userLogged = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
            parametros.put("ID_PROFESOR", userLogged.getIdPerfil().equals(Constants.PERFIL_PROFESOR) ? userLogged.getIdUsuario() : filtro.getIdProfesor());
            parametros.put("ID_CLASE", filtro.getIdClase());
            parametros.put("ID_AULA", filtro.getIdAula());
            parametros.put("HORA", filtro.getHora());
            parametros.put("IMG", img);
            con = lookup.getConnection();
            reporteAula = JasperCompileManager.compileReport(pathReporteDocente);
            JasperPrint print = JasperFillManager.fillReport(reporteAula,
                    parametros, con);
            byte[] archivo = JasperRunManager.runReportToPdf(reporteAula, parametros, con);
            WebUtil.getRequest().getSession().setAttribute("file", archivo);

            System.out.println("Ok");
            this.setIsAula(true);
            descargarArchivo();
        } catch (SQLException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void valueChangeSedes(ValueChangeEvent evt) {

        this.getFiltro().setIdSede(Integer.parseInt(evt.getNewValue().toString()));
    }

    private Date formatearFecha(Date date) {
        try {
            Date res = new SimpleDateFormat().parse(new SimpleDateFormat("d/M/y").format(date));

            return res;
        } catch (ParseException ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Creates a new instance of ReportesHorariosBean
     */
    public ReportesHorariosBean() {
    }

    public boolean isIsSedes() {
        return isSedes;
    }

    public void setIsSedes(boolean isSedes) {
        this.isSedes = isSedes;
    }

    public FiltroReportes getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroReportes filtro) {
        this.filtro = filtro;
    }

    

    public boolean isIsDocente() {
        return isDocente;
    }

    public void setIsDocente(boolean isDocente) {
        this.isDocente = isDocente;
    }

    public boolean isIsAula() {
        return isAula;
    }

    public void setIsAula(boolean isAula) {
        this.isAula = isAula;
    }

    
}
