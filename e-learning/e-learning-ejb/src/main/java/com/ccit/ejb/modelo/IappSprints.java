/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "iapp_sprints",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappSprints.findAll", query = "SELECT i FROM IappSprints i"),
    @NamedQuery(name = "IappSprints.findByIdModulo", query = "SELECT i FROM IappSprints i WHERE i.idModulo = :idModulo"),
    @NamedQuery(name = "IappSprints.findByNombre", query = "SELECT i FROM IappSprints i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IappSprints.findByDescripcion", query = "SELECT i FROM IappSprints i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappSprints.findByPorcentaje", query = "SELECT i FROM IappSprints i WHERE i.porcentaje = :porcentaje"),
    @NamedQuery(name = "IappSprints.findByFechaCreacion", query = "SELECT i FROM IappSprints i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappSprints.findByFechaModificacion", query = "SELECT i FROM IappSprints i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappSprints.findByUsuarioCreacion", query = "SELECT i FROM IappSprints i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappSprints.findByUsuarioModificacion", query = "SELECT i FROM IappSprints i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappSprints.findByEstadoRegistro", query = "SELECT i FROM IappSprints i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappSprints implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo",orphanRemoval=true)
    @OrderBy(value="idAsignacion ASC")
    private Collection<IappHomeWorks> iappAsignacionesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(allocationSize=1,name="PGSSQL_MODULE_SEQ",schema="general",sequenceName="iapp_modulos_id_modulo_seq")
    @GeneratedValue(generator="PGSSQL_MODULE_SEQ",strategy= GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modulo")
    private Integer idModulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo")
    private Collection<IappContent> iappContenidosCollection;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private IappCourses idCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo")
    private Collection<IappTests> iappEvaluacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo")
    private Collection<IappForo> iappForoCollection;

    public IappSprints() {
    }

    public IappSprints(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public IappSprints(Integer idModulo, String nombre, String descripcion, BigDecimal porcentaje, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idModulo = idModulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
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

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
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
    public Collection<IappContent> getIappContenidosCollection() {
        return iappContenidosCollection;
    }

    public void setIappContenidosCollection(Collection<IappContent> iappContenidosCollection) {
        this.iappContenidosCollection = iappContenidosCollection;
    }

    public IappCourses getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(IappCourses idCurso) {
        this.idCurso = idCurso;
    }

    @XmlTransient
    public Collection<IappTests> getIappEvaluacionesCollection() {
        return iappEvaluacionesCollection;
    }

    public void setIappEvaluacionesCollection(Collection<IappTests> iappEvaluacionesCollection) {
        this.iappEvaluacionesCollection = iappEvaluacionesCollection;
    }

    @XmlTransient
    public Collection<IappForo> getIappForoCollection() {
        return iappForoCollection;
    }

    public void setIappForoCollection(Collection<IappForo> iappForoCollection) {
        this.iappForoCollection = iappForoCollection;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappSprints[ idModulo=" + idModulo + " ]";
    }

    @XmlTransient
    public Collection<IappHomeWorks> getIappAsignacionesCollection() {
        return iappAsignacionesCollection;
    }

    public void setIappAsignacionesCollection(Collection<IappHomeWorks> iappAsignacionesCollection) {
        this.iappAsignacionesCollection = iappAsignacionesCollection;
    }
}
