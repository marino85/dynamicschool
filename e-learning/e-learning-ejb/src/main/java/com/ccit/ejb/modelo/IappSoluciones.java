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
@Table(name = "iapp_soluciones", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappSoluciones.findAll", query = "SELECT i FROM IappSoluciones i"),
    @NamedQuery(name = "IappSoluciones.findByIdSolucion", query = "SELECT i FROM IappSoluciones i WHERE i.idSolucion = :idSolucion"),
    @NamedQuery(name = "IappSoluciones.findByFechaCreacion", query = "SELECT i FROM IappSoluciones i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappSoluciones.findByFechaModificacion", query = "SELECT i FROM IappSoluciones i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappSoluciones.findByUsuarioCreacion", query = "SELECT i FROM IappSoluciones i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappSoluciones.findByUsuarioModificacion", query = "SELECT i FROM IappSoluciones i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappSoluciones.findByEstadoRegistro", query = "SELECT i FROM IappSoluciones i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappSoluciones implements Serializable {
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
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_SOL_SEQ", schema = "general", sequenceName = "iapp_soluciones_id_solucion_seq")
    @GeneratedValue(generator = "PGSSQL_SOL_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_solucion")
    private Integer idSolucion;
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
    @JoinColumn(name = "id_respuesta", referencedColumnName = "id_respuesta")
    @ManyToOne(optional = false)
    private IappRespuestas idRespuesta;
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    @ManyToOne(optional = false)
    private IappMatriculas idMatricula;

    public IappSoluciones() {
    }

    public IappSoluciones(Integer idSolucion) {
        this.idSolucion = idSolucion;
    }

    public IappSoluciones(Integer idSolucion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idSolucion = idSolucion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdSolucion() {
        return idSolucion;
    }

    public void setIdSolucion(Integer idSolucion) {
        this.idSolucion = idSolucion;
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

    public IappRespuestas getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(IappRespuestas idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public IappMatriculas getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(IappMatriculas idMatricula) {
        this.idMatricula = idMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolucion != null ? idSolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappSoluciones)) {
            return false;
        }
        IappSoluciones other = (IappSoluciones) object;
        if ((this.idSolucion == null && other.idSolucion != null) || (this.idSolucion != null && !this.idSolucion.equals(other.idSolucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappSoluciones[ idSolucion=" + idSolucion + " ]";
    }
}
