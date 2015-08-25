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
@Table(name = "iapp_niveles", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappNiveles.findAll", query = "SELECT i FROM IappNiveles i"),
    @NamedQuery(name = "IappNiveles.findByIdNivel", query = "SELECT i FROM IappNiveles i WHERE i.idNivel = :idNivel"),
    @NamedQuery(name = "IappNiveles.findByDescripcion", query = "SELECT i FROM IappNiveles i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappNiveles.findByFechaCreacion", query = "SELECT i FROM IappNiveles i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappNiveles.findByFechaModificacion", query = "SELECT i FROM IappNiveles i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappNiveles.findByUsuarioCreacion", query = "SELECT i FROM IappNiveles i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappNiveles.findByUsuarioModificacion", query = "SELECT i FROM IappNiveles i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappNiveles.findByEstadoRegistro", query = "SELECT i FROM IappNiveles i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappNiveles implements Serializable {
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
    @OneToMany(mappedBy = "idNivel")
    private Collection<IappHorario> iappHorarioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_nivel")
    @OrderBy
    private Integer idNivel;
    @Size(max = 100)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivel")
    private Collection<IappCursos> iappCursosCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivel")
    private Collection<IappUsuario> iappUsuarioCollection;
    public IappNiveles() {
    }

    public IappNiveles(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public IappNiveles(Integer idNivel, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idNivel = idNivel;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
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

    public Collection<IappUsuario> getIappUsuarioCollection() {
        return iappUsuarioCollection;
    }

    public void setIappUsuarioCollection(Collection<IappUsuario> iappUsuarioCollection) {
        this.iappUsuarioCollection = iappUsuarioCollection;
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
        hash += (idNivel != null ? idNivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappNiveles)) {
            return false;
        }
        IappNiveles other = (IappNiveles) object;
        if ((this.idNivel == null && other.idNivel != null) || (this.idNivel != null && !this.idNivel.equals(other.idNivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappNiveles[ idNivel=" + idNivel + " ]";
    }

    

    @XmlTransient
    public Collection<IappHorario> getIappHorarioCollection() {
        return iappHorarioCollection;
    }

    public void setIappHorarioCollection(Collection<IappHorario> iappHorarioCollection) {
        this.iappHorarioCollection = iappHorarioCollection;
    }

   

  
    
}
