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
@Table(name = "iapp_cursos", schema = "general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappCursos.findAll", query = "SELECT i FROM IappCursos i"),
    @NamedQuery(name = "IappCursos.findByIdCurso", query = "SELECT i FROM IappCursos i WHERE i.idCurso = :idCurso"),
    @NamedQuery(name = "IappCursos.findByNombre", query = "SELECT i FROM IappCursos i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IappCursos.findByDescripcion", query = "SELECT i FROM IappCursos i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappCursos.findByFechaCreacion", query = "SELECT i FROM IappCursos i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappCursos.findByFechaModificacion", query = "SELECT i FROM IappCursos i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappCursos.findByUsuarioCreacion", query = "SELECT i FROM IappCursos i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappCursos.findByUsuarioModificacion", query = "SELECT i FROM IappCursos i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappCursos.findByEstadoRegistro", query = "SELECT i FROM IappCursos i WHERE i.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "IappCursos.findByTipoCurso", query = "SELECT i FROM IappCursos i WHERE i.tipoCurso = :tipoCurso")})
public class IappCursos implements Serializable {

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
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false)
    @OrderBy
    private IappNiveles idNivel;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private IappUsuario idProfesor;
    @JoinColumn(name = "id_estado_curso", referencedColumnName = "id_estado_curso")
    @ManyToOne(optional = false)
    private IappEstadosCurso idEstadoCurso;
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
    private Collection<IappMatriculas> iappMatriculasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso", orphanRemoval = true,fetch= FetchType.LAZY)
    @OrderBy(value = "idModulo ASC")
    private Collection<IappModulos> iappModulosCollection;

    public IappCursos() {
    }

    public IappCursos(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public IappCursos(Integer idCurso, String nombre, String descripcion, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
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
    public Collection<IappMatriculas> getIappMatriculasCollection() {
        return iappMatriculasCollection;
    }

    public void setIappMatriculasCollection(Collection<IappMatriculas> iappMatriculasCollection) {
        this.iappMatriculasCollection = iappMatriculasCollection;
    }

    @XmlTransient
    public Collection<IappModulos> getIappModulosCollection() {
        return iappModulosCollection;
    }

    public void setIappModulosCollection(Collection<IappModulos> iappModulosCollection) {
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
        if (!(object instanceof IappCursos)) {
            return false;
        }
        IappCursos other = (IappCursos) object;
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

    public IappEstadosCurso getIdEstadoCurso() {
        return idEstadoCurso;
    }

    public void setIdEstadoCurso(IappEstadosCurso idEstadoCurso) {
        this.idEstadoCurso = idEstadoCurso;
    }

    public IappUsuario getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(IappUsuario idProfesor) {
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

    public IappNiveles getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(IappNiveles idNivel) {
        this.idNivel = idNivel;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappCursos[ idCurso=" + idCurso + " ]";
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
