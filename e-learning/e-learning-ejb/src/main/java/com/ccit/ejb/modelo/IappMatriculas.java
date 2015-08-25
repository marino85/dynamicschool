/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author innovasoft
 */
@Entity
@Table(name = "iapp_matriculas",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappMatriculas.findAll", query = "SELECT i FROM IappMatriculas i"),
    @NamedQuery(name = "IappMatriculas.findByIdMatricula", query = "SELECT i FROM IappMatriculas i WHERE i.idMatricula = :idMatricula"),
    @NamedQuery(name = "IappMatriculas.findByFechaMatricula", query = "SELECT i FROM IappMatriculas i WHERE i.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "IappMatriculas.findByFechaCreacion", query = "SELECT i FROM IappMatriculas i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappMatriculas.findByFechaModificacion", query = "SELECT i FROM IappMatriculas i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappMatriculas.findByUsuarioCreacion", query = "SELECT i FROM IappMatriculas i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappMatriculas.findByUsuarioModificacion", query = "SELECT i FROM IappMatriculas i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappMatriculas.findByEstadoRegistro", query = "SELECT i FROM IappMatriculas i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappMatriculas implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
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
    @Column(name =     "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_vencimiento_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoPago;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "iappMatriculas")
    private IappCalCurso iappCalCurso; 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMatricula")
    private Collection<IappCalAsignaciones> iappCalAsignacionesCollection;
    @OneToMany(mappedBy = "idMatricula")
    private Collection<IappCalEvaluacion> iappCalEvaluacionCollection;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "forma_pago")
    private String formaPago;
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(allocationSize=1,name="PGSSQL_MATRICULAS_SEG",schema="general",sequenceName="iapp_matriculas_id_matricula_seq")
    @GeneratedValue(generator="PGSSQL_MATRICULAS_SEG",strategy= GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_matricula")
    private Integer idMatricula;
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
    @Size(min = 1, max = 25)
    @Column(name = "estado_registro")
    private String estadoRegistro;  
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private IappUsuario idUsuario;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private IappCursos idCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMatricula")
    private Collection<IappSoluciones> iappSolucionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMatricula")
    private Collection<IappEntregas> iappEntregasCollection;

    public IappMatriculas() {
    }

    public IappMatriculas(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public IappMatriculas(Integer idMatricula, Date fechaMatricula, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idMatricula = idMatricula;
        this.fechaMatricula = fechaMatricula;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
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

   

    public IappUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(IappUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public IappCursos getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(IappCursos idCurso) {
        this.idCurso = idCurso;
    }

    @XmlTransient
    public Collection<IappSoluciones> getIappSolucionesCollection() {
        return iappSolucionesCollection;
    }

    public void setIappSolucionesCollection(Collection<IappSoluciones> iappSolucionesCollection) {
        this.iappSolucionesCollection = iappSolucionesCollection;
    }

    @XmlTransient
    public Collection<IappEntregas> getIappEntregasCollection() {
        return iappEntregasCollection;
    }

    public void setIappEntregasCollection(Collection<IappEntregas> iappEntregasCollection) {
        this.iappEntregasCollection = iappEntregasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappMatriculas)) {
            return false;
        }
        IappMatriculas other = (IappMatriculas) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappMatriculas[ idMatricula=" + idMatricula + " ]";
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Date getFechaVencimientoPago() {
        return fechaVencimientoPago;
    }

    public void setFechaVencimientoPago(Date fechaVencimientoPago) {
        this.fechaVencimientoPago = fechaVencimientoPago;
    }

    @XmlTransient
    public Collection<IappCalEvaluacion> getIappCalEvaluacionCollection() {
        return iappCalEvaluacionCollection;
    }

    public void setIappCalEvaluacionCollection(Collection<IappCalEvaluacion> iappCalEvaluacionCollection) {
        this.iappCalEvaluacionCollection = iappCalEvaluacionCollection;
    }

    @XmlTransient
    public Collection<IappCalAsignaciones> getIappCalAsignacionesCollection() {
        return iappCalAsignacionesCollection;
    }

    public void setIappCalAsignacionesCollection(Collection<IappCalAsignaciones> iappCalAsignacionesCollection) {
        this.iappCalAsignacionesCollection = iappCalAsignacionesCollection;
    }

   

   

   
    public IappCalCurso getIappCalCurso() {
        return iappCalCurso;
    }

    public void setIappCalCurso(IappCalCurso iappCalCurso) {
        this.iappCalCurso = iappCalCurso;
    }
}
