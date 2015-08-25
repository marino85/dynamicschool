/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marino
 */
@Entity
@Table(name = "iapp_noticias",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappNoticias.findAll", query = "SELECT i FROM IappNoticias i"),
    @NamedQuery(name = "IappNoticias.findByIdNoticias", query = "SELECT i FROM IappNoticias i WHERE i.idNoticias = :idNoticias"),
    @NamedQuery(name = "IappNoticias.findByTitulo", query = "SELECT i FROM IappNoticias i WHERE i.titulo = :titulo"),
    @NamedQuery(name = "IappNoticias.findByContenido", query = "SELECT i FROM IappNoticias i WHERE i.contenido = :contenido"),
@NamedQuery(name = "IappNoticias.findByLastPublication", query = "SELECT i FROM IappNoticias i WHERE i.estado = 'A' ORDER BY i.fechaCreacion DESC ")})
public class IappNoticias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_noticias")
    @SequenceGenerator(allocationSize=1,name="PGSSQL_NOTI_SEG",schema="general",sequenceName="iapp_noticias_id_noticias_seq")
    @GeneratedValue(generator="PGSSQL_NOTI_SEG",strategy= GenerationType.SEQUENCE)
    private Integer idNoticias;
    @Size(max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 2147483647)
    @Column(name = "contenido")
    private String contenido;    
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;    
    @Column(name = "estado")
    private String estado;

    public IappNoticias() {
    }

    public IappNoticias(Integer idNoticias) {
        this.idNoticias = idNoticias;
    }

    public Integer getIdNoticias() {
        return idNoticias;
    }

    public void setIdNoticias(Integer idNoticias) {
        this.idNoticias = idNoticias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNoticias != null ? idNoticias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappNoticias)) {
            return false;
        }
        IappNoticias other = (IappNoticias) object;
        if ((this.idNoticias == null && other.idNoticias != null) || (this.idNoticias != null && !this.idNoticias.equals(other.idNoticias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappNoticias[ idNoticias=" + idNoticias + " ]";
    }
    
}
