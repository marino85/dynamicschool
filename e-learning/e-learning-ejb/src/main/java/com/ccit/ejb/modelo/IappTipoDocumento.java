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
@Table(name = "iapp_tipo_documento",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappTipoDocumento.findAll", query = "SELECT i FROM IappTipoDocumento i"),
    @NamedQuery(name = "IappTipoDocumento.findByIdTipoDoc", query = "SELECT i FROM IappTipoDocumento i WHERE i.idTipoDoc = :idTipoDoc"),
    @NamedQuery(name = "IappTipoDocumento.findByDescripcion", query = "SELECT i FROM IappTipoDocumento i WHERE i.descripcion = :descripcion")})
public class IappTipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "id_tipo_doc")
    private String idTipoDoc;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDoc")
    private Collection<IappUsuario> iappUsuarioCollection;

    public IappTipoDocumento() {
    }

    public IappTipoDocumento(String idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public String getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(String idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
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
        hash += (idTipoDoc != null ? idTipoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappTipoDocumento)) {
            return false;
        }
        IappTipoDocumento other = (IappTipoDocumento) object;
        if ((this.idTipoDoc == null && other.idTipoDoc != null) || (this.idTipoDoc != null && !this.idTipoDoc.equals(other.idTipoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappTipoDocumento[ idTipoDoc=" + idTipoDoc + " ]";
    }
    
}
