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
 * @author marino
 */
@Entity
@Table(name = "iapp_clase",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappClase.findAll", query = "SELECT i FROM IappClase i"),
    @NamedQuery(name = "IappClase.findByIdClase", query = "SELECT i FROM IappClase i WHERE i.idClase = :idClase"),
    @NamedQuery(name = "IappClase.findByDescripcion", query = "SELECT i FROM IappClase i WHERE i.descripcion = :descripcion")})
public class IappClase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_clase")
    private Integer idClase;
    @Size(max = 100)
    @Column(name = "descripcion")
    @OrderBy
    private String descripcion;
    @OneToMany(mappedBy = "idClase")
    private Collection<IappHorario> iappHorarioCollection;

    public IappClase() {
    }

    public IappClase(Integer idClase) {
        this.idClase = idClase;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idClase != null ? idClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappClase)) {
            return false;
        }
        IappClase other = (IappClase) object;
        if ((this.idClase == null && other.idClase != null) || (this.idClase != null && !this.idClase.equals(other.idClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappClase[ idClase=" + idClase + " ]";
    }
    
}
