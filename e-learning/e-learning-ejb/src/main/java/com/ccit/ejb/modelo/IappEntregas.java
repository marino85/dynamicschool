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
@Table(name = "iapp_entregas",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappEntregas.findAll", query = "SELECT i FROM IappEntregas i"),
    @NamedQuery(name = "IappEntregas.findByIdEntrega", query = "SELECT i FROM IappEntregas i WHERE i.idEntrega = :idEntrega"),
    @NamedQuery(name = "IappEntregas.findByNombre", query = "SELECT i FROM IappEntregas i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IappEntregas.findByDescripcion", query = "SELECT i FROM IappEntregas i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappEntregas.findByFile", query = "SELECT i FROM IappEntregas i WHERE i.file = :file"),
    @NamedQuery(name = "IappEntregas.findByFechaEntrega", query = "SELECT i FROM IappEntregas i WHERE i.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "IappEntregas.findByFechaCreacion", query = "SELECT i FROM IappEntregas i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappEntregas.findByFechaModificacion", query = "SELECT i FROM IappEntregas i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappEntregas.findByUsuarioCreacion", query = "SELECT i FROM IappEntregas i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappEntregas.findByUsuarioModificacion", query = "SELECT i FROM IappEntregas i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappEntregas.findByEstadoRegistro", query = "SELECT i FROM IappEntregas i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappEntregas implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
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
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_ENTREGA_SEQ", schema = "general", sequenceName = "iapp_entregas_id_entrega_seq")
    @GeneratedValue(generator = "PGSSQL_ENTREGA_SEQ", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_entrega")
    private Integer idEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
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
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne(optional = false)
    private IappMatriculas idMatricula;
    @JoinColumn(name = "id_asignacion", referencedColumnName = "id_asignacion")
    @ManyToOne(optional = false)
    private IappAsignaciones idAsignacion;
    @Transient
    private byte[] fileArray;

    
    public IappEntregas() {
    }

    public IappEntregas(Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public IappEntregas(Integer idEntrega, String nombre, String descripcion, Date fechaEntrega, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idEntrega = idEntrega;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFile() {
        return file;
    }
    
    public void setFile(String file) {
        this.file = file;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public IappMatriculas getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(IappMatriculas idMatricula) {
        this.idMatricula = idMatricula;
    }

    public IappAsignaciones getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(IappAsignaciones idAsignacion) {
        this.idAsignacion = idAsignacion;
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
        hash += (idEntrega != null ? idEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappEntregas)) {
            return false;
        }
        IappEntregas other = (IappEntregas) object;
        if ((this.idEntrega == null && other.idEntrega != null) || (this.idEntrega != null && !this.idEntrega.equals(other.idEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappEntregas[ idEntrega=" + idEntrega + " ]";
    }
}
