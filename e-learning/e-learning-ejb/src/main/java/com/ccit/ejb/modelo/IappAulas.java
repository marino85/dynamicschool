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
@Table(name = "iapp_aulas", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappAulas.findAll", query = "SELECT i FROM IappAulas i"),
    @NamedQuery(name = "IappAulas.findByIdAula", query = "SELECT i FROM IappAulas i WHERE i.idAula = :idAula"),
    @NamedQuery(name = "IappAulas.findByDescripcion", query = "SELECT i FROM IappAulas i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappAulas.findByFechaCreacion", query = "SELECT i FROM IappAulas i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappAulas.findByFechaModificacion", query = "SELECT i FROM IappAulas i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappAulas.findByUsuarioCreacion", query = "SELECT i FROM IappAulas i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappAulas.findByUsuarioModificacion", query = "SELECT i FROM IappAulas i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappAulas.findByEstadoRegistro", query = "SELECT i FROM IappAulas i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappAulas implements Serializable {
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
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    @ManyToOne
    private IappSede idSede;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_aula")
    private Integer idAula;
    @Size(max = 100)
    @OrderBy
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAula")
    private Collection<IappHorario> iappHorarioCollection;

    public IappAulas() {
    }

    public IappAulas(Integer idAula) {
        this.idAula = idAula;
    }

    public IappAulas(Integer idAula, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idAula = idAula;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
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
    public Collection<IappHorario> getIappHorarioCollection() {
        return iappHorarioCollection;
    }

    public void setIappHorarioCollection(Collection<IappHorario> iappHorarioCollection) {
        this.iappHorarioCollection = iappHorarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAula != null ? idAula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappAulas)) {
            return false;
        }
        IappAulas other = (IappAulas) object;
        if ((this.idAula == null && other.idAula != null) || (this.idAula != null && !this.idAula.equals(other.idAula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappAulas[ idAula=" + idAula + " ]";
    }

    

    public IappSede getIdSede() {
        return idSede;
    }

    public void setIdSede(IappSede idSede) {
        this.idSede = idSede;
    }
    
}
