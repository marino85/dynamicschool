/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marino
 */
@Entity
@Table(name = "iapp_streaming_estudiante",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappStreamingEstudiante.findAll", query = "SELECT i FROM IappStreamingEstudiante i"),
    @NamedQuery(name = "IappStreamingEstudiante.findByEstudiantesCourse", query = "SELECT i FROM IappStreamingEstudiante i WHERE i.idStreaming.idCurso.idCurso = :idCurso AND i.idStreaming.idStreaming = :idStreaming"),
    @NamedQuery(name = "IappStreamingEstudiante.findByClasesEstudiantes", query = "SELECT i FROM IappStreamingEstudiante i WHERE i.idStreaming.idCurso.idCurso = :idCurso AND i.idUsuario.idUsuario = :idUsuario"),
    @NamedQuery(name = "IappStreamingEstudiante.findByAllClases", query = "SELECT i FROM IappStreamingEstudiante i WHERE i.idStreaming.idUsuario.idUsuario = :idUsuario AND i.idStreaming.idCurso.idCurso = :idCurso"),
    @NamedQuery(name = "IappStreamingEstudiante.findByIdStreamingEstudiante", query = "SELECT i FROM IappStreamingEstudiante i WHERE i.idStreamingEstudiante = :idStreamingEstudiante")})
public class IappStreamingEstudiante implements Serializable {
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private IappUsuario idUsuario;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_streaming_estudiante")
    
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_STREAMING_ESTUDIANTE_SEG", schema = "general", sequenceName = "iapp_streaming_estudiante_id_streaming_estudiante_seq")
    @GeneratedValue(generator = "PGSSQL_STREAMING_ESTUDIANTE_SEG", strategy = GenerationType.SEQUENCE)
    private Integer idStreamingEstudiante;
    @JoinColumn(name = "id_streaming", referencedColumnName = "id_streaming")
    @ManyToOne
    private IappStreaming idStreaming;

    public IappStreamingEstudiante() {
    }

    public IappStreamingEstudiante(Integer idStreamingEstudiante) {
        this.idStreamingEstudiante = idStreamingEstudiante;
    }

    public Integer getIdStreamingEstudiante() {
        return idStreamingEstudiante;
    }

    public void setIdStreamingEstudiante(Integer idStreamingEstudiante) {
        this.idStreamingEstudiante = idStreamingEstudiante;
    }

    public IappStreaming getIdStreaming() {
        return idStreaming;
    }

    public void setIdStreaming(IappStreaming idStreaming) {
        this.idStreaming = idStreaming;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStreamingEstudiante != null ? idStreamingEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappStreamingEstudiante)) {
            return false;
        }
        IappStreamingEstudiante other = (IappStreamingEstudiante) object;
        if ((this.idStreamingEstudiante == null && other.idStreamingEstudiante != null) || (this.idStreamingEstudiante != null && !this.idStreamingEstudiante.equals(other.idStreamingEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.other.sesion.IappStreamingEstudiante[ idStreamingEstudiante=" + idStreamingEstudiante + " ]";
    }

    public IappUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(IappUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
