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
@Table(name = "iapp_preguntas", schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappPreguntas.findAll", query = "SELECT i FROM IappPreguntas i"),
    @NamedQuery(name = "IappPreguntas.findByIdPregunta", query = "SELECT i FROM IappPreguntas i WHERE i.idPregunta = :idPregunta"),
    @NamedQuery(name = "IappPreguntas.findByDescripcion", query = "SELECT i FROM IappPreguntas i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappPreguntas.findByFechaCreacion", query = "SELECT i FROM IappPreguntas i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappPreguntas.findByFechaModificacion", query = "SELECT i FROM IappPreguntas i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappPreguntas.findByUsuarioCreacion", query = "SELECT i FROM IappPreguntas i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappPreguntas.findByUsuarioModificacion", query = "SELECT i FROM IappPreguntas i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappPreguntas.findByEstadoRegistro", query = "SELECT i FROM IappPreguntas i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappPreguntas implements Serializable {
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
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_QST_SEQ", schema = "general", sequenceName = "iapp_preguntas_id_pregunta_seq")
    @GeneratedValue(generator = "PGSSQL_QST_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pregunta")
    private Integer idPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta", orphanRemoval=true)
    private Collection<IappRespuestas> iappRespuestasCollection;
    @JoinColumn(name = "id_evaluacion", referencedColumnName = "id_evaluacion")
    @ManyToOne(optional = false)
    private IappEvaluaciones idEvaluacion;
    @Transient
    private String []respuestas;
    @Transient
    private String respuesta;
    public IappPreguntas() {
    }

    public IappPreguntas(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public IappPreguntas(Integer idPregunta, String descripcion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idPregunta = idPregunta;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
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
    public Collection<IappRespuestas> getIappRespuestasCollection() {
        return iappRespuestasCollection;
    }

    public void setIappRespuestasCollection(Collection<IappRespuestas> iappRespuestasCollection) {
        this.iappRespuestasCollection = iappRespuestasCollection;
    }

    public IappEvaluaciones getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(IappEvaluaciones idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }
    
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public boolean multiRespuesta(){
        int i=0;
        for(IappRespuestas resp:this.getIappRespuestasCollection()){
            if(resp.getCorrecta())
                i++;
        }
        return (i>1)?true:false;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappPreguntas[ idPregunta=" + idPregunta + " ]";
    }
}
