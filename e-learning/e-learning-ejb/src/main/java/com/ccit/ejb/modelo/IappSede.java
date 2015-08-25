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
@Table(name = "iapp_sede",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappSede.findAll", query = "SELECT i FROM IappSede i"),
    @NamedQuery(name = "IappSede.findByIdSede", query = "SELECT i FROM IappSede i WHERE i.idSede = :idSede"),
    @NamedQuery(name = "IappSede.findByDescripcion", query = "SELECT i FROM IappSede i WHERE i.descripcion = :descripcion")})
public class IappSede implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sede")
    private Integer idSede;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idSede")
    private Collection<IappAulas> iappAulasCollection;

    public IappSede() {
    }

    public IappSede(Integer idSede) {
        this.idSede = idSede;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<IappAulas> getIappAulasCollection() {
        return iappAulasCollection;
    }

    public void setIappAulasCollection(Collection<IappAulas> iappAulasCollection) {
        this.iappAulasCollection = iappAulasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSede != null ? idSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappSede)) {
            return false;
        }
        IappSede other = (IappSede) object;
        if ((this.idSede == null && other.idSede != null) || (this.idSede != null && !this.idSede.equals(other.idSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappSede[ idSede=" + idSede + " ]";
    }
    
}
