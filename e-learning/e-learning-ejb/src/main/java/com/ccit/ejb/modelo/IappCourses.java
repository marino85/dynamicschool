/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import com.ccit.ejb.constants.Constants;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "iapp_courses", schema = "general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappCourses.findAll", query = "SELECT i FROM IappCourses i"),
    @NamedQuery(name = "IappCourses.findByIdCurso", query = "SELECT i FROM IappCourses i WHERE i.idCurso = :idCurso"),
    @NamedQuery(name = "IappCourses.findByNombre", query = "SELECT i FROM IappCourses i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IappCourses.findByDescripcion", query = "SELECT i FROM IappCourses i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappCourses.findByFechaCreacion", query = "SELECT i FROM IappCourses i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappCourses.findByFechaModificacion", query = "SELECT i FROM IappCourses i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappCourses.findByUsuarioCreacion", query = "SELECT i FROM IappCourses i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappCourses.findByUsuarioModificacion", query = "SELECT i FROM IappCourses i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappCourses.findByEstadoRegistro", query = "SELECT i FROM IappCourses i WHERE i.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "IappCourses.findByTipoCurso", query = "SELECT i FROM IappCourses i WHERE i.tipoCurso = :tipoCurso")})
public class IappCourses implements Serializable {

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
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "porcentajetrabajos")
    private BigDecimal porcentajetrabajos;
    @Column(name = "porcentajefinal")
    private BigDecimal porcentajefinal;
    @Column(name = "porcentajeevaluaciones")
    private BigDecimal porcentajeevaluaciones;
    @Size(max = 1)
    @Column(name = "tipo_curso")
    private String tipoCurso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;  
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private IappUser idProfesor;
  
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_COURSE_SEQ", schema = "general", sequenceName = "iapp_curso_id_curso_seq")
    @GeneratedValue(generator = "PGSSQL_COURSE_SEQ", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso")
    private Integer idCurso;
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
    @Size(min = 1, max = 25)
    @Column(name = "estado_registro")
    private String estadoRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso",fetch= FetchType.LAZY)
    private Collection<IappEnrollments> iappMatriculasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso", orphanRemoval = true,fetch= FetchType.LAZY)
    @OrderBy(value = "idModulo ASC")
    private Collection<IappSprints> iappModulosCollection;

    public IappCourses() {
    }

    public IappCourses(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public IappCourses(Integer idCurso, String nombre, String descripcion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
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
    public Collection<IappEnrollments> getIappMatriculasCollection() {
        return iappMatriculasCollection;
    }

    public void setIappMatriculasCollection(Collection<IappEnrollments> iappMatriculasCollection) {
        this.iappMatriculasCollection = iappMatriculasCollection;
    }

    @XmlTransient
    public Collection<IappSprints> getIappModulosCollection() {
        return iappModulosCollection;
    }

    public void setIappModulosCollection(Collection<IappSprints> iappModulosCollection) {
        this.iappModulosCollection = iappModulosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappCourses)) {
            return false;
        }
        IappCourses other = (IappCourses) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    public boolean isCursoVirtual() {
        return (this.tipoCurso.equals(Constants.CURSO_VIRTUAL));
    }

    public boolean isCursoPresencial() {
        return (this.tipoCurso.equals(Constants.CURSO_PRESENCIAL));

    }

    

    public IappUser getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(IappUser idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappCourses[ idCurso=" + idCurso + " ]";
    }

  

    public BigDecimal getPorcentajeevaluaciones() {
        return porcentajeevaluaciones;
    }

    public void setPorcentajeevaluaciones(BigDecimal porcentajeevaluaciones) {
        this.porcentajeevaluaciones = porcentajeevaluaciones;
    }

   

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getPorcentajetrabajos() {
        return porcentajetrabajos;
    }

    public void setPorcentajetrabajos(BigDecimal porcentajetrabajos) {
        this.porcentajetrabajos = porcentajetrabajos;
    }

    public BigDecimal getPorcentajefinal() {
        return porcentajefinal;
    }

    public void setPorcentajefinal(BigDecimal porcentajefinal) {
        this.porcentajefinal = porcentajefinal;
    }

   

   
}
