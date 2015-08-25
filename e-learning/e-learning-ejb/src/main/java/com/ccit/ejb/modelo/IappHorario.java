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
@Table(name = "iapp_horario", schema = "general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappHorario.findAll", query = "SELECT i FROM IappHorario i"),
    @NamedQuery(name = "IappHorario.findByIdHorario", query = "SELECT i FROM IappHorario i WHERE i.idHorario = :idHorario"),
    @NamedQuery(name = "IappHosorario.findByHora", query = "SELECT i FROM IappHorario i WHERE i.hora = :hora"),
    @NamedQuery(name = "IappHorario.findByFecha", query = "SELECT i FROM IappHorario i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "IappHorario.findByCupoTotal", query = "SELECT i FROM IappHorario i WHERE i.cupoTotal = :cupoTotal"),
    @NamedQuery(name = "IappHorario.findByFechaCreacion", query = "SELECT i FROM IappHorario i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappHorario.findByFechaModificacion", query = "SELECT i FROM IappHorario i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappHorario.findByUsuarioCreacion", query = "SELECT i FROM IappHorario i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappHorario.findByUsuarioModificacion", query = "SELECT i FROM IappHorario i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappHorario.findByEstadoRegistro", query = "SELECT i FROM IappHorario i WHERE i.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "IappHorario.findByUniqueKeyHoraFecha", query = "SELECT i FROM IappHorario i WHERE i.hora = :hora AND i.fecha = :fecha"),
    @NamedQuery(name = "IappHorario.findByUniqueKeyHoraFechaAula", query = "SELECT i FROM IappHorario i WHERE i.hora = :hora AND i.fecha = :fecha AND i.idAula.idAula = :idAula"),
    @NamedQuery(name = "IappHorario.findByFechasNivel", query = "SELECT i FROM IappHorario i WHERE ( i.fecha >= :fechaIni OR i.fecha <= :fechaFin) AND i.idNivel.idNivel = :idNivel order by i.fecha asc"),
    @NamedQuery(name = "IappHorario.findByFechaHoraNivel", query = "SELECT i FROM IappHorario i WHERE  i.fecha = :fecha AND i.hora = :hora AND i.idNivel.idNivel = :idNivel "),
    
    @NamedQuery(name = "IappHorario.findByFechaNivelSede", query = "SELECT i FROM IappHorario i WHERE   i.fecha >= :fechaIni AND i.fecha <= :fechaFin AND i.idNivel.idNivel = :idNivel AND i.idAula.idSede.idSede = :idSede  ORDER BY i.hora2 ASC"),
    @NamedQuery(name = "IappHorario.findByFechaNivelSedeClase", query = "SELECT i FROM IappHorario i WHERE   i.fecha >= :fechaIni AND i.fecha <= :fechaFin AND i.idNivel.idNivel = :idNivel AND i.idAula.idSede.idSede = :idSede AND  i.idClase.idClase = :idClase  ORDER BY i.hora2 ASC")
        
})
public class IappHorario implements Serializable {
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
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private IappUsuario idUsuario;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne
    private IappNiveles idNivel;
    @JoinColumn(name = "id_clase", referencedColumnName = "id_clase")
    @ManyToOne
    private IappClase idClase;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_HORARIO_SEG", schema = "general", sequenceName = "iapp_horario_id_horario_seq")
    @GeneratedValue(generator = "PGSSQL_HORARIO_SEG", strategy = GenerationType.SEQUENCE)
    @NotNull
    @Column(name = "id_horario")
    private Integer idHorario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "hora")
    private String hora;
    @Column(name = "cupo_total")
    private Integer cupoTotal;
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
    @OneToMany(mappedBy = "idHorario")
    private Collection<IappHorarioEstudiante> iappHorarioEstudianteCollection;   
    @JoinColumn(name = "id_aula", referencedColumnName = "id_aula")
    @ManyToOne(optional = false)
    private IappAulas idAula;
    @Column(name="hora2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora2;

    public IappHorario() {
    }

    public IappHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public IappHorario(Integer idHorario, String hora, Date fecha, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idHorario = idHorario;
        this.hora = hora;
        this.fecha = fecha;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(Integer cupoTotal) {
        this.cupoTotal = cupoTotal;
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
    public Collection<IappHorarioEstudiante> getIappHorarioEstudianteCollection() {
        return iappHorarioEstudianteCollection;
    }

    public void setIappHorarioEstudianteCollection(Collection<IappHorarioEstudiante> iappHorarioEstudianteCollection) {
        this.iappHorarioEstudianteCollection = iappHorarioEstudianteCollection;
    }

   

    public IappAulas getIdAula() {
        return idAula;
    }

    public void setIdAula(IappAulas idAula) {
        this.idAula = idAula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappHorario)) {
            return false;
        }
        IappHorario other = (IappHorario) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappHorario[ idHorario=" + idHorario + " ]";
    }

   

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public IappUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(IappUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public IappNiveles getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(IappNiveles idNivel) {
        this.idNivel = idNivel;
    }

    public IappClase getIdClase() {
        return idClase;
    }

    public void setIdClase(IappClase idClase) {
        this.idClase = idClase;
    }

    public Date getHora2() {
        return hora2;
    }

    public void setHora2(Date hora2) {
        this.hora2 = hora2;
    }
    
    
}
