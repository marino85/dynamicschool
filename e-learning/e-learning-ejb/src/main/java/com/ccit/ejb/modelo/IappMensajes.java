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
 * @author innovasoft
 */
@Entity
@Table(name = "iapp_mensajes",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappMensajes.findAll", query = "SELECT i FROM IappMensajes i"),
    @NamedQuery(name = "IappMensajes.findByIdMensaje", query = "SELECT i FROM IappMensajes i WHERE i.idMensaje = :idMensaje"),
    @NamedQuery(name = "IappMensajes.findByTitulo", query = "SELECT i FROM IappMensajes i WHERE i.titulo = :titulo"),
    @NamedQuery(name = "IappMensajes.findByMensaje", query = "SELECT i FROM IappMensajes i WHERE i.mensaje = :mensaje"),   
    @NamedQuery(name = "IappMensajes.findByFechaCreacion", query = "SELECT i FROM IappMensajes i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappMensajes.findByFechaModificacion", query = "SELECT i FROM IappMensajes i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappMensajes.findByUsuarioCreacion", query = "SELECT i FROM IappMensajes i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappMensajes.findByUsuarioModificacion", query = "SELECT i FROM IappMensajes i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappMensajes.findByEstadoRegistro", query = "SELECT i FROM IappMensajes i WHERE i.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "IappMensajes.ListByForumId", query = "SELECT i FROM IappMensajes i WHERE i.idForo.idForo = :idForo AND i.idMensajeReply IS NULL"),
    @NamedQuery(name = "IappMensajes.ListByRepplyMessageId", query = "SELECT i FROM IappMensajes i WHERE i.idForo.idForo = :idForo AND i.idMensajeReply = :idMensaje ")})
public class IappMensajes implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mensaje")
    @SequenceGenerator(allocationSize=1,name="PGSSQL_MENAJE_SEG",schema="general",sequenceName="iapp_mensajes_id_mensaje_seq")
    @GeneratedValue(generator="PGSSQL_MENAJE_SEG",strategy= GenerationType.SEQUENCE)
    private Integer idMensaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "mensaje")
    private String mensaje;   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 800)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "id_mensaje_reply")
    private Integer idMensajeReply;
    
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
    @JoinColumn(name = "id_foro", referencedColumnName = "id_foro")
    @ManyToOne(optional = false)
    private IappForo idForo;

    public IappMensajes() {
    }

    public IappMensajes(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public IappMensajes(Integer idMensaje, String titulo, String mensaje, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idMensaje = idMensaje;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

   

    public IappForo getIdForo() {
        return idForo;
    }

    public void setIdForo(IappForo idForo) {
        this.idForo = idForo;
    }

    public Integer getIdMensajeReply() {
        return idMensajeReply;
    }

    public void setIdMensajeReply(Integer idMensajeReply) {
        this.idMensajeReply = idMensajeReply;
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
        hash += (idMensaje != null ? idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappMensajes)) {
            return false;
        }
        IappMensajes other = (IappMensajes) object;
        if ((this.idMensaje == null && other.idMensaje != null) || (this.idMensaje != null && !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappMensajes[ idMensaje=" + idMensaje + " ]";
    }
}
