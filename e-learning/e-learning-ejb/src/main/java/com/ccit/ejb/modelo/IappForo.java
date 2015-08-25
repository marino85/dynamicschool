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
@Table(name = "iapp_foro",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappForo.findAll", query = "SELECT i FROM IappForo i"),
    @NamedQuery(name = "IappForo.findByIdForo", query = "SELECT i FROM IappForo i WHERE i.idForo = :idForo"),
    @NamedQuery(name = "IappForo.findByTema", query = "SELECT i FROM IappForo i WHERE i.tema = :tema"),
    @NamedQuery(name = "IappForo.findByDescripcion", query = "SELECT i FROM IappForo i WHERE i.descripcion = :descripcion"),   
    @NamedQuery(name = "IappForo.findByFechaCreacion", query = "SELECT i FROM IappForo i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappForo.findByFechaModificacion", query = "SELECT i FROM IappForo i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappForo.findByUsuarioCreacion", query = "SELECT i FROM IappForo i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappForo.findByUsuarioModificacion", query = "SELECT i FROM IappForo i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappForo.findByEstadoRegistro", query = "SELECT i FROM IappForo i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappForo implements Serializable {
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
    @Column(name = "id_foro")
    @SequenceGenerator(allocationSize=1,name="PGSSQL_FORO_SEG",schema="general",sequenceName="iapp_foro_id_foro_seq")
    @GeneratedValue(generator="PGSSQL_FORO_SEG",strategy= GenerationType.SEQUENCE)
    private Integer idForo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tema")
    private String tema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 800)
    @Column(name = "descripcion")
    private String descripcion;    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 800)
    @Column(name = "usuario")
    private String usuario;    
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idForo")
    private Collection<IappMensajes> iappMensajesCollection;
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne(optional = false)
    private IappModulos idModulo;

    public IappForo() {
    }

    public IappForo(Integer idForo) {
        this.idForo = idForo;
    }

    public IappForo(Integer idForo, String tema, String descripcion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idForo = idForo;
        this.tema = tema;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdForo() {
        return idForo;
    }

    public void setIdForo(Integer idForo) {
        this.idForo = idForo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
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
    public Collection<IappMensajes> getIappMensajesCollection() {
        return iappMensajesCollection;
    }

    public void setIappMensajesCollection(Collection<IappMensajes> iappMensajesCollection) {
        this.iappMensajesCollection = iappMensajesCollection;
    }

    public IappModulos getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(IappModulos idModulo) {
        this.idModulo = idModulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idForo != null ? idForo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappForo)) {
            return false;
        }
        IappForo other = (IappForo) object;
        if ((this.idForo == null && other.idForo != null) || (this.idForo != null && !this.idForo.equals(other.idForo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappForo[ idForo=" + idForo + " ]";
    }
}
