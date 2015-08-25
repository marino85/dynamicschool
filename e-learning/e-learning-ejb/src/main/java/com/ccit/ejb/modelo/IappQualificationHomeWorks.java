/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "iapp_qualification_home_works", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappQualificationHomeWorks.findAll", query = "SELECT i FROM IappQualificationHomeWorks i"),
    @NamedQuery(name = "IappQualificationHomeWorks.findByIdCalificacion", query = "SELECT i FROM IappQualificationHomeWorks i WHERE i.idCalificacion = :idCalificacion"),
    @NamedQuery(name = "IappQualificationHomeWorks.findByCalificacion", query = "SELECT i FROM IappQualificationHomeWorks i WHERE i.calificacion = :calificacion"),
    @NamedQuery(name = "IappQualificationHomeWorks.findByFechaCreacion", query = "SELECT i FROM IappQualificationHomeWorks i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappQualificationHomeWorks.findByFechaModificacion", query = "SELECT i FROM IappQualificationHomeWorks i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappQualificationHomeWorks.findByUsuarioCreacion", query = "SELECT i FROM IappQualificationHomeWorks i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappQualificationHomeWorks.findByUsuarioModificacion", query = "SELECT i FROM IappQualificationHomeWorks i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappQualificationHomeWorks.findByEstadoRegistro", query = "SELECT i FROM IappQualificationHomeWorks i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappQualificationHomeWorks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_WORKSCAL_SEQ", schema = "general", sequenceName = "iapp_cal_asignaciones_id_calificacion_seq")
    @GeneratedValue(generator = "PGSSQL_WORKSCAL_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_calificacion")
    private Integer idCalificacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "calificacion")
    private BigDecimal calificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
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
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne(optional = false)
    private IappEnrollments idMatricula;
    @JoinColumn(name = "id_asignacion", referencedColumnName = "id_asignacion")
    @ManyToOne(optional = false)
    private IappHomeWorks idAsignacion;

    public IappQualificationHomeWorks() {
    }

    public IappQualificationHomeWorks(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public IappQualificationHomeWorks(Integer idCalificacion, BigDecimal calificacion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idCalificacion = idCalificacion;
        this.calificacion = calificacion;
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

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
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

    public IappEnrollments getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(IappEnrollments idMatricula) {
        this.idMatricula = idMatricula;
    }

    public IappHomeWorks getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(IappHomeWorks idAsignacion) {
        this.idAsignacion = idAsignacion;
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
        if (!(object instanceof IappQualificationHomeWorks)) {
            return false;
        }
        IappQualificationHomeWorks other = (IappQualificationHomeWorks) object;
        if ((this.idCalificacion == null && other.idCalificacion != null) || (this.idCalificacion != null && !this.idCalificacion.equals(other.idCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappQualificationHomeWorks[ idCalificacion=" + idCalificacion + " ]";
    }
    
}
