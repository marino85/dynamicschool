/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "iapp_perfiles",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappPerfiles.findAll", query = "SELECT i FROM IappPerfiles i"),
    @NamedQuery(name = "IappPerfiles.findByIdPerfil", query = "SELECT i FROM IappPerfiles i WHERE i.idPerfil = :idPerfil"),
    @NamedQuery(name = "IappPerfiles.findByDescripcion", query = "SELECT i FROM IappPerfiles i WHERE i.descripcion = :descripcion")})
public class IappPerfiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil")
    private Integer idPerfil;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
    @OrderBy(value="nombres ASC")
    private Collection<IappUser> iappUsuarioCollection;

    public IappPerfiles() {
    }

    public IappPerfiles(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<IappUser> getIappUsuarioCollection() {
        return iappUsuarioCollection;
    }

    public void setIappUsuarioCollection(Collection<IappUser> iappUsuarioCollection) {
        this.iappUsuarioCollection = iappUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappPerfiles)) {
            return false;
        }
        IappPerfiles other = (IappPerfiles) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappPerfiles[ idPerfil=" + idPerfil + " ]";
    }
    
}
