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
 * @author marino
 */
@Entity
@Table(name = "iapp_streaming",schema = "general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappStreaming.findAll", query = "SELECT i FROM IappStreaming i"),
    @NamedQuery(name = "IappStreaming.findByIdStreaming", query = "SELECT i FROM IappStreaming i WHERE i.idStreaming = :idStreaming"),
    @NamedQuery(name = "IappStreaming.findByIdMeeting", query = "SELECT i FROM IappStreaming i WHERE i.idMeeting = :idMeeting"),
    @NamedQuery(name = "IappStreaming.findByIdUsuario", query = "SELECT i FROM IappStreaming i WHERE i.idUsuario.idUsuario = :idusuario AND i.idCurso.idCurso = :idCurso ORDER BY i.fechaCreacion DESC"),
    @NamedQuery(name = "IappStreaming.findByNombreMeeting", query = "SELECT i FROM IappStreaming i WHERE i.nombreMeeting = :nombreMeeting"),
    @NamedQuery(name = "IappStreaming.findByUrlJoinModerator", query = "SELECT i FROM IappStreaming i WHERE i.urlJoinModerator = :urlJoinModerator"),
    @NamedQuery(name = "IappStreaming.findByUrlJoinEstudiantes", query = "SELECT i FROM IappStreaming i WHERE i.urlJoinEstudiantes = :urlJoinEstudiantes")})
public class IappStreaming implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_streaming")
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_STREAMING_SEG", schema = "general", sequenceName = "iapp_streaming_seq")
    @GeneratedValue(generator = "PGSSQL_STREAMING_SEG", strategy = GenerationType.SEQUENCE)
    private Integer idStreaming;
    @Size(max = 200)
    @Column(name = "id_meeting")
    private String idMeeting;
    @Size(max = 200)
    @Column(name = "nombre_meeting")
    private String nombreMeeting;
    @Size(max = 2147483647)
    @Column(name = "url_join_moderator")
    private String urlJoinModerator;
    @Size(max = 2147483647)
    @Column(name = "url_join_estudiantes")
    private String urlJoinEstudiantes;
    @OneToMany(mappedBy = "idStreaming")
    private Collection<IappStreamingEstudiante> iappStreamingEstudianteCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private IappUsuario idUsuario;
    @Column(name = "estado")
    private String estado;    
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private IappCursos idCurso;    
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_sesion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSesion;
    
    

    public IappStreaming() {
    }

    public IappStreaming(Integer idStreaming) {
        this.idStreaming = idStreaming;
    }

    public Integer getIdStreaming() {
        return idStreaming;
    }

    public void setIdStreaming(Integer idStreaming) {
        this.idStreaming = idStreaming;
    }

    public String getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(String idMeeting) {
        this.idMeeting = idMeeting;
    }

    public String getNombreMeeting() {
        return nombreMeeting;
    }

    public void setNombreMeeting(String nombreMeeting) {
        this.nombreMeeting = nombreMeeting;
    }

    public String getUrlJoinModerator() {
        return urlJoinModerator;
    }

    public void setUrlJoinModerator(String urlJoinModerator) {
        this.urlJoinModerator = urlJoinModerator;
    }

    public String getUrlJoinEstudiantes() {
        return urlJoinEstudiantes;
    }

    public void setUrlJoinEstudiantes(String urlJoinEstudiantes) {
        this.urlJoinEstudiantes = urlJoinEstudiantes;
    }

    public IappUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(IappUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    @XmlTransient
    public Collection<IappStreamingEstudiante> getIappStreamingEstudianteCollection() {
        return iappStreamingEstudianteCollection;
    }

    public void setIappStreamingEstudianteCollection(Collection<IappStreamingEstudiante> iappStreamingEstudianteCollection) {
        this.iappStreamingEstudianteCollection = iappStreamingEstudianteCollection;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public IappCursos getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(IappCursos idCurso) {
        this.idCurso = idCurso;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(Date fechaSesion) {
        this.fechaSesion = fechaSesion;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStreaming != null ? idStreaming.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappStreaming)) {
            return false;
        }
        IappStreaming other = (IappStreaming) object;
        if ((this.idStreaming == null && other.idStreaming != null) || (this.idStreaming != null && !this.idStreaming.equals(other.idStreaming))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.other.sesion.IappStreaming[ idStreaming=" + idStreaming + " ]";
    }
    
}
