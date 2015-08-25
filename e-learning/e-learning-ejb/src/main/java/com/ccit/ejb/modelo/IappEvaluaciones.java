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
@Table(name = "iapp_evaluaciones", schema = "general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappEvaluaciones.findAll", query = "SELECT i FROM IappEvaluaciones i"),
    @NamedQuery(name = "IappEvaluaciones.findByIdEvaluacion", query = "SELECT i FROM IappEvaluaciones i WHERE i.idEvaluacion = :idEvaluacion"),
    @NamedQuery(name = "IappEvaluaciones.findByNombre", query = "SELECT i FROM IappEvaluaciones i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IappEvaluaciones.findByDescripcion", query = "SELECT i FROM IappEvaluaciones i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappEvaluaciones.findByFechaInicio", query = "SELECT i FROM IappEvaluaciones i WHERE i.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "IappEvaluaciones.findByFechaLimite", query = "SELECT i FROM IappEvaluaciones i WHERE i.fechaLimite = :fechaLimite"),
    @NamedQuery(name = "IappEvaluaciones.findByFechaCreacion", query = "SELECT i FROM IappEvaluaciones i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappEvaluaciones.findByFechaModificacion", query = "SELECT i FROM IappEvaluaciones i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappEvaluaciones.findByUsuarioCreacion", query = "SELECT i FROM IappEvaluaciones i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappEvaluaciones.findByUsuarioModificacion", query = "SELECT i FROM IappEvaluaciones i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappEvaluaciones.findByEstadoRegistro", query = "SELECT i FROM IappEvaluaciones i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappEvaluaciones implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_limite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private int duracion;
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_TEST_SEQ", schema = "general", sequenceName = "iapp_evaluaciones_id_evaluacion_seq")
    @GeneratedValue(generator = "PGSSQL_TEST_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_evaluacion")
    private Integer idEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 500)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvaluacion", orphanRemoval = true)
    @OrderBy(value = "idPregunta ASC")
    private Collection<IappPreguntas> iappPreguntasCollection;
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne(optional = false)
    private IappModulos idModulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvaluacion", fetch = FetchType.EAGER)
    private Collection<IappCalEvaluacion> iappCalEvaluacionCollection;

    public IappEvaluaciones() {
    }

    public IappEvaluaciones(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public IappEvaluaciones(Integer idEvaluacion, String nombre, Date fechaInicio, Date fechaLimite, Integer duracion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idEvaluacion = idEvaluacion;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
        this.duracion = duracion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
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
    public Collection<IappPreguntas> getIappPreguntasCollection() {
        return iappPreguntasCollection;
    }

    public void setIappPreguntasCollection(Collection<IappPreguntas> iappPreguntasCollection) {
        this.iappPreguntasCollection = iappPreguntasCollection;
    }

    public IappModulos getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(IappModulos idModulo) {
        this.idModulo = idModulo;
    }

    public String getShortDescripcion() {
        return (descripcion.length() > 50) ? descripcion.substring(0, 49) + "..." : descripcion;
    }

    public boolean isEditable() {
        return !(new Date().before(fechaLimite) && new Date().after(fechaInicio));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappEvaluaciones)) {
            return false;
        }
        IappEvaluaciones other = (IappEvaluaciones) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappEvaluaciones[ idEvaluacion=" + idEvaluacion + " ]";
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @XmlTransient
    public Collection<IappCalEvaluacion> getIappCalEvaluacionCollection() {
        return iappCalEvaluacionCollection;
    }

    public void setIappCalEvaluacionCollection(Collection<IappCalEvaluacion> iappCalEvaluacionCollection) {
        this.iappCalEvaluacionCollection = iappCalEvaluacionCollection;
    }

    public IappCalEvaluacion getCalificacion(IappMatriculas matricula) {
        if (this.getIappCalEvaluacionCollection() != null) {
            for (IappCalEvaluacion cal : this.getIappCalEvaluacionCollection()) {
                if(matricula!=null){
                    
                if (cal.getIdMatricula().getIdMatricula() == matricula.getIdMatricula()) {
                    return cal;
                }
                }
            }
        }
        return null;
    }
}
