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
@Table(name = "iapp_content",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappContent.findAll", query = "SELECT i FROM IappContent i"),
    @NamedQuery(name = "IappContent.findByIdContenido", query = "SELECT i FROM IappContent i WHERE i.idContenido = :idContenido"),
    @NamedQuery(name = "IappContent.findByTitulo", query = "SELECT i FROM IappContent i WHERE i.titulo = :titulo"),
    @NamedQuery(name = "IappContent.findByDescripcion", query = "SELECT i FROM IappContent i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappContent.findByNombre", query = "SELECT i FROM IappContent i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IappContent.findByFile", query = "SELECT i FROM IappContent i WHERE i.file = :file"),
    @NamedQuery(name = "IappContent.findByFechaCreacion", query = "SELECT i FROM IappContent i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappContent.findByFechaModificacion", query = "SELECT i FROM IappContent i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappContent.findByUsuarioCreacion", query = "SELECT i FROM IappContent i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappContent.findByUsuarioModificacion", query = "SELECT i FROM IappContent i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappContent.findByEstadoRegistro", query = "SELECT i FROM IappContent i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappContent implements Serializable {
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
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_CONTENIDO_SEQ", schema = "general", sequenceName = "iapp_contenidos_id_contenido_seq")
    @GeneratedValue(generator = "PGSSQL_CONTENIDO_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_contenido")
    private Integer idContenido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "file")
    private String file;
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
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne(optional = false)
    private IappSprints idModulo;
    @Transient
    private byte[] fileArray;
    
    public IappContent() {
    }

    public IappContent(Integer idContenido) {
        this.idContenido = idContenido;
    }

    public IappContent(Integer idContenido, String titulo, String descripcion, String nombre, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Integer idContenido) {
        this.idContenido = idContenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public IappSprints getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(IappSprints idModulo) {
        this.idModulo = idModulo;
    }

    public String getShortDescripcion() {
        return (descripcion.length()>50)?descripcion.substring(0,49)+"...":descripcion;
    }

    public byte[] getFileArray() {
        return fileArray;
    }

    public void setFileArray(byte[] fileArray) {
        this.fileArray = fileArray;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContenido != null ? idContenido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappContent)) {
            return false;
        }
        IappContent other = (IappContent) object;
        if ((this.idContenido == null && other.idContenido != null) || (this.idContenido != null && !this.idContenido.equals(other.idContenido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappContent[ idContenido=" + idContenido + " ]";
    }
}
