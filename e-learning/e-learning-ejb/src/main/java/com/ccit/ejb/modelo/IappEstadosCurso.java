/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import com.ccit.ejb.modelo.IappCursos;
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
@Table(name = "iapp_estados_curso", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappEstadosCurso.findAll", query = "SELECT i FROM IappEstadosCurso i"),
    @NamedQuery(name = "IappEstadosCurso.findByIdEstadoCurso", query = "SELECT i FROM IappEstadosCurso i WHERE i.idEstadoCurso = :idEstadoCurso"),
    @NamedQuery(name = "IappEstadosCurso.findByDescripcion", query = "SELECT i FROM IappEstadosCurso i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappEstadosCurso.findByFechaCreacion", query = "SELECT i FROM IappEstadosCurso i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappEstadosCurso.findByFechaModificacion", query = "SELECT i FROM IappEstadosCurso i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappEstadosCurso.findByUsuarioCreacion", query = "SELECT i FROM IappEstadosCurso i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappEstadosCurso.findByUsuarioModificacion", query = "SELECT i FROM IappEstadosCurso i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappEstadosCurso.findByEstadoRegistro", query = "SELECT i FROM IappEstadosCurso i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappEstadosCurso implements Serializable {
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
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado_curso")
    private Integer idEstadoCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCurso")
    private Collection<IappCursos> iappCursosCollection;

    public IappEstadosCurso() {
    }

    public IappEstadosCurso(Integer idEstadoCurso) {
        this.idEstadoCurso = idEstadoCurso;
    }

    public IappEstadosCurso(Integer idEstadoCurso, String descripcion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idEstadoCurso = idEstadoCurso;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdEstadoCurso() {
        return idEstadoCurso;
    }

    public void setIdEstadoCurso(Integer idEstadoCurso) {
        this.idEstadoCurso = idEstadoCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @XmlTransient
    public Collection<IappCursos> getIappCursosCollection() {
        return iappCursosCollection;
    }

    public void setIappCursosCollection(Collection<IappCursos> iappCursosCollection) {
        this.iappCursosCollection = iappCursosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCurso != null ? idEstadoCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappEstadosCurso)) {
            return false;
        }
        IappEstadosCurso other = (IappEstadosCurso) object;
        if ((this.idEstadoCurso == null && other.idEstadoCurso != null) || (this.idEstadoCurso != null && !this.idEstadoCurso.equals(other.idEstadoCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.dto.IappEstadosCurso[ idEstadoCurso=" + idEstadoCurso + " ]";
    }

 
  
    
}
