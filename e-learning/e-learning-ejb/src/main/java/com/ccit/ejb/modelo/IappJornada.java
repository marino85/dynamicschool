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
@Table(name = "iapp_jornada",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappJornada.findAll", query = "SELECT i FROM IappJornada i"),
    @NamedQuery(name = "IappJornada.findByIdJornada", query = "SELECT i FROM IappJornada i WHERE i.idJornada = :idJornada"),
    @NamedQuery(name = "IappJornada.findByDescripcion", query = "SELECT i FROM IappJornada i WHERE i.descripcion = :descripcion")})
public class IappJornada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_jornada")
    private Integer idJornada;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJornada")
    private Collection<IappUser> iappUsuarioCollection;

    public IappJornada() {
    }

    public IappJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
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
        hash += (idJornada != null ? idJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappJornada)) {
            return false;
        }
        IappJornada other = (IappJornada) object;
        if ((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappJornada[ idJornada=" + idJornada + " ]";
    }
    
}
