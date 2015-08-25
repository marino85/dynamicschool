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
@Table(name = "iapp_estado_usuario",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappEstadoUsuario.findAll", query = "SELECT i FROM IappEstadoUsuario i"),
    @NamedQuery(name = "IappEstadoUsuario.findByIdEstadoUsuario", query = "SELECT i FROM IappEstadoUsuario i WHERE i.idEstadoUsuario = :idEstadoUsuario"),
    @NamedQuery(name = "IappEstadoUsuario.findByDescripcion", query = "SELECT i FROM IappEstadoUsuario i WHERE i.descripcion = :descripcion")})
public class IappEstadoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "id_estado_usuario")
    private String idEstadoUsuario;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoUsuario")
    private Collection<IappUsuario> iappUsuarioCollection;

    public IappEstadoUsuario() {
    }

    public IappEstadoUsuario(String idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public String getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(String idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<IappUsuario> getIappUsuarioCollection() {
        return iappUsuarioCollection;
    }

    public void setIappUsuarioCollection(Collection<IappUsuario> iappUsuarioCollection) {
        this.iappUsuarioCollection = iappUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoUsuario != null ? idEstadoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappEstadoUsuario)) {
            return false;
        }
        IappEstadoUsuario other = (IappEstadoUsuario) object;
        if ((this.idEstadoUsuario == null && other.idEstadoUsuario != null) || (this.idEstadoUsuario != null && !this.idEstadoUsuario.equals(other.idEstadoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappEstadoUsuario[ idEstadoUsuario=" + idEstadoUsuario + " ]";
    }
    
}
