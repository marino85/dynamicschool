/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author innovasoft
 */
@Entity
@Table(name = "iapp_test_results", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappTestResult.findAll", query = "SELECT i FROM IappTestResult i"),
    @NamedQuery(name = "IappTestResult.findByIdCalificacion", query = "SELECT i FROM IappTestResult i WHERE i.idCalificacion = :idCalificacion"),
    @NamedQuery(name = "IappTestResult.findByFechaCreacion", query = "SELECT i FROM IappTestResult i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappTestResult.findByFechaModificacion", query = "SELECT i FROM IappTestResult i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappTestResult.findByUsuarioCreacion", query = "SELECT i FROM IappTestResult i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappTestResult.findByUsuarioModificacion", query = "SELECT i FROM IappTestResult i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappTestResult.findByEstadoRegistro", query = "SELECT i FROM IappTestResult i WHERE i.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "IappTestResult.findByNoCorrectas", query = "SELECT i FROM IappTestResult i WHERE i.noCorrectas = :noCorrectas")})
public class IappTestResult implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calificacion")
    private BigDecimal calificacion;
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_CALIF_SEQ", schema = "general", sequenceName = "iapp_cal_evaluacion_id_calificacion_seq")
    @GeneratedValue(generator = "PGSSQL_CALIF_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_calificacion")
    private Integer idCalificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_registro")
    private String estadoRegistro;
    @Column(name = "no_correctas")
    private Integer noCorrectas;
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne
    private IappEnrollments idMatricula;
    @JoinColumn(name = "id_evaluacion", referencedColumnName = "id_evaluacion")
    @ManyToOne(optional = false)
    private IappTests idEvaluacion;

    public IappTestResult() {
    }

    public IappTestResult(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public IappTestResult(Integer idCalificacion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idCalificacion = idCalificacion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getNoCorrectas() {
        return noCorrectas;
    }

    public void setNoCorrectas(Integer noCorrectas) {
        this.noCorrectas = noCorrectas;
    }

    public IappEnrollments getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(IappEnrollments idMatricula) {
        this.idMatricula = idMatricula;
    }

    public IappTests getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(IappTests idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }
    
    public boolean isEditable(int duracion){
        Calendar c=Calendar.getInstance();
        c.setTime(fechaInicio);
        c.add(Calendar.MINUTE,duracion);
        return c.getTime().before(new Date());
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacion != null ? idCalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappTestResult)) {
            return false;
        }
        IappTestResult other = (IappTestResult) object;
        if ((this.idCalificacion == null && other.idCalificacion != null) || (this.idCalificacion != null && !this.idCalificacion.equals(other.idCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappTestResult[ idCalificacion=" + idCalificacion + " ]";
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

   

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }
    
}
