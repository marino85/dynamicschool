/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.web.util.WebUtil;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
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
public class CarnetBean implements Serializable {
    
    @Resource(name = "lookup")
    private DataSource lookup;
    
    private static JasperReport reporte = null;
    
    
    
     private static ResourceBundle bundle=null;
     
     private StreamedContent file;

    /**
     * Creates a new instance of CarnetBean
     */
    public CarnetBean() {
    }
    
    @PostConstruct
    public void init(){
        Connection con=null;
        try {
             bundle = ResourceBundle.getBundle("dynamic");
            UsuarioDTo userLogged = (UsuarioDTo)WebUtil.getObjectFromSession("userLogged");
            
            String path_jrxml=bundle.getString("path_reporte_carnet");
            String img=bundle.getString("path_reporte_img");
            
            Map parametros = new HashMap();
            parametros.put("TIPO_DOC", userLogged.getTipoDoc());
            parametros.put("NUM_DOC", userLogged.getNumDoc());
            parametros.put("IMG", img);     
            
            con= lookup.getConnection();
            
            try {
                reporte=JasperCompileManager.compileReport(path_jrxml);
               // JasperPrint print = JasperFillManager.fillReport(reporte,
		//			parametros, con);
              //  JasperExportManager.exportReportToPdfFile(print, "/home/marino/Documentos/carnet"+userLogged.getNumDoc()+".pdf");
                
                byte[] archivo = JasperRunManager.runReportToPdf(reporte, parametros, con);  
                
                ByteArrayInputStream input = new  ByteArrayInputStream(archivo);
                
                file= new DefaultStreamedContent(input, "application/pdf", "carnet.pdf");
            } catch (JRException ex) {
                Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            
            System.out.println("Reporte generado con exito");
        } catch (SQLException ex) {
            System.out.println("Error al obtener conexion");
            Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuariosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    
    
    
    
  
    
}
