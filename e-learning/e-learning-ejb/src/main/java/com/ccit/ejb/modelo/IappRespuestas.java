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
@Table(name = "iapp_respuestas", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappRespuestas.findAll", query = "SELECT i FROM IappRespuestas i"),
    @NamedQuery(name = "IappRespuestas.findByIdRespuesta", query = "SELECT i FROM IappRespuestas i WHERE i.idRespuesta = :idRespuesta"),
    @NamedQuery(name = "IappRespuestas.findByDescripcion", query = "SELECT i FROM IappRespuestas i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappRespuestas.findByCorrecta", query = "SELECT i FROM IappRespuestas i WHERE i.correcta = :correcta"),
    @NamedQuery(name = "IappRespuestas.findByFechaCreacion", query = "SELECT i FROM IappRespuestas i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappRespuestas.findByFechaModificacion", query = "SELECT i FROM IappRespuestas i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappRespuestas.findByUsuarioCreacion", query = "SELECT i FROM IappRespuestas i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappRespuestas.findByUsuarioModificacion", query = "SELECT i FROM IappRespuestas i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappRespuestas.findByEstadoRegistro", query = "SELECT i FROM IappRespuestas i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappRespuestas implements Serializable {
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
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_ANSW_SEQ", schema = "general", sequenceName = "iapp_respuestas_id_respuesta_seq")
    @GeneratedValue(generator = "PGSSQL_ANSW_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_respuesta")
    private Integer idRespuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correcta")
    private boolean correcta;
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
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private IappPreguntas idPregunta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRespuesta")
    private Collection<IappSoluciones> iappSolucionesCollection;

    public IappRespuestas() {
    }

    public IappRespuestas(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public IappRespuestas(Integer idRespuesta, String descripcion, boolean correcta, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idRespuesta = idRespuesta;
        this.descripcion = descripcion;
        this.correcta = correcta;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
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

    public IappPreguntas getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(IappPreguntas idPregunta) {
        this.idPregunta = idPregunta;
    }

    @XmlTransient
    public Collection<IappSoluciones> getIappSolucionesCollection() {
        return iappSolucionesCollection;
    }

    public void setIappSolucionesCollection(Collection<IappSoluciones> iappSolucionesCollection) {
        this.iappSolucionesCollection = iappSolucionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuesta != null ? idRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappRespuestas[ idRespuesta=" + idRespuesta + " ]";
    }
}
