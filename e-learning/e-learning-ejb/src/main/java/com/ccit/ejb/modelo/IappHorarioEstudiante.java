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
@Table(name = "iapp_horario_estudiante",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappHorarioEstudiante.findAll", query = "SELECT i FROM IappHorarioEstudiante i"),
    @NamedQuery(name = "IappHorarioEstudiante.findByIdHorarioEstudiante", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.idHorarioEstudiante = :idHorarioEstudiante"),
    @NamedQuery(name = "IappHorarioEstudiante.findByFechaCreacion", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappHorarioEstudiante.findByFechaModificacion", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappHorarioEstudiante.findByUsuarioCreacion", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappHorarioEstudiante.findByUsuarioModificacion", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappHorarioEstudiante.findByEstadoRegistro", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "IappHorarioEstudiante.findByIdUsuarioIdHorario", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.idHorario.idHorario = :idHorario AND i.idusuario.idUsuario =:idusuario"),
    @NamedQuery(name = "IappHorarioEstudiante.findHorariosEstudianteSemana", query = "SELECT i FROM IappHorarioEstudiante i WHERE i.idHorario.fecha >= :fechaIni AND i.idHorario.fecha<= :fechaFIn AND i.idusuario.idUsuario = :idUsuario")
})
public class IappHorarioEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_horario_estudiante")
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_HORARIO_EST", schema = "general", sequenceName = "iapp_horario_estudiante_id_horario_estudiante_seq")
    @GeneratedValue(generator = "PGSSQL_HORARIO_EST", strategy = GenerationType.SEQUENCE)
    private Integer idHorarioEstudiante;
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
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne
    private IappHorario idHorario;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private IappUsuario idusuario;

    public IappHorarioEstudiante() {
    }

    public IappHorarioEstudiante(Integer idHorarioEstudiante) {
        this.idHorarioEstudiante = idHorarioEstudiante;
    }

    public IappHorarioEstudiante(Integer idHorarioEstudiante, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idHorarioEstudiante = idHorarioEstudiante;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdHorarioEstudiante() {
        return idHorarioEstudiante;
    }

    public void setIdHorarioEstudiante(Integer idHorarioEstudiante) {
        this.idHorarioEstudiante = idHorarioEstudiante;
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

    public IappHorario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(IappHorario idHorario) {
        this.idHorario = idHorario;
    }

    public IappUsuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(IappUsuario idusuario) {
        this.idusuario = idusuario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorarioEstudiante != null ? idHorarioEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappHorarioEstudiante)) {
            return false;
        }
        IappHorarioEstudiante other = (IappHorarioEstudiante) object;
        if ((this.idHorarioEstudiante == null && other.idHorarioEstudiante != null) || (this.idHorarioEstudiante != null && !this.idHorarioEstudiante.equals(other.idHorarioEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappHorarioEstudiante[ idHorarioEstudiante=" + idHorarioEstudiante + " ]";
    }
    
}
