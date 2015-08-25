/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "iapp_home_work",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappHomeWorks.findAll", query = "SELECT i FROM IappHomeWorks i"),
    @NamedQuery(name = "IappHomeWorks.findByIdAsignacion", query = "SELECT i FROM IappHomeWorks i WHERE i.idAsignacion = :idAsignacion"),
    @NamedQuery(name = "IappHomeWorks.findByNombre", query = "SELECT i FROM IappHomeWorks i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IappHomeWorks.findByDescripcion", query = "SELECT i FROM IappHomeWorks i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappHomeWorks.findByFechaInicial", query = "SELECT i FROM IappHomeWorks i WHERE i.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "IappHomeWorks.findByFechaFinal", query = "SELECT i FROM IappHomeWorks i WHERE i.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "IappHomeWorks.findByFechaCreacion", query = "SELECT i FROM IappHomeWorks i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IappHomeWorks.findByFechaModificacion", query = "SELECT i FROM IappHomeWorks i WHERE i.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "IappHomeWorks.findByUsuarioCreacion", query = "SELECT i FROM IappHomeWorks i WHERE i.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "IappHomeWorks.findByUsuarioModificacion", query = "SELECT i FROM IappHomeWorks i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "IappHomeWorks.findByEstadoRegistro", query = "SELECT i FROM IappHomeWorks i WHERE i.estadoRegistro = :estadoRegistro")})
public class IappHomeWorks implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicial;
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "file")
    private String file;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsignacion")
    private Collection<IappQualificationHomeWorks> iappCalAsignacionesCollection;
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne(optional = false)
    private IappSprints idModulo;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_asignacion")
    @SequenceGenerator(allocationSize = 1, name = "PGSSQL_WORKS_SEQ", schema = "general", sequenceName = "iapp_asignaciones_id_asignacion_seq")
    @GeneratedValue(generator = "PGSSQL_WORKS_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer idAsignacion;
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
    @Size(min = 1, max = 1)
    @Column(name = "estado_registro")
    private String estadoRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_file")
    private String nombreFile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsignacion")
    @OrderBy(value="idEntrega ASC")
    private Collection<IappSentFiles> iappEntregasCollection;
    @Transient
    private byte[] fileArray;

    public IappHomeWorks() {
    }

    public IappHomeWorks(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public IappHomeWorks(Integer idAsignacion, String nombre, String descripcion, Date fechaInicial, Date fechaFinal, Date fechaCreacion, Date fechaModificacion, String usuarioCreacion, String usuarioModificacion, String estadoRegistro) {
        this.idAsignacion = idAsignacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.estadoRegistro = estadoRegistro;
    }

    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
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

    public String getShortDescripcion() {
        return (descripcion.length()>50)?descripcion.substring(0,49)+"...":descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
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
    
    public IappSprints getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(IappSprints idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreFile() {
        return nombreFile;
    }

    public void setNombreFile(String nombreFile) {
        this.nombreFile = nombreFile;
    }
    
    @XmlTransient
    public boolean entregaHabilitada() {
        Date d=new Date();
        return (fechaInicial.before(d) && fechaFinal.after(d));
    }

    @XmlTransient
    public Collection<IappSentFiles> iappEntregasFromUserCollection(IappEnrollments matricula) {
        Collection <IappSentFiles> list=new ArrayList<IappSentFiles>();
        if(iappEntregasCollection!=null&&matricula!=null){
            for (IappSentFiles iappEntregas : iappEntregasCollection) {
                if(iappEntregas.getIdMatricula().getIdMatricula().intValue()==matricula.getIdMatricula().intValue()){
                    list.add(iappEntregas);
                }
            }
        }
        return list;
    }
    @XmlTransient
    public IappQualificationHomeWorks calificacion(IappEnrollments matricula) {
        for ( IappQualificationHomeWorks calificacion : this.getIappCalAsignacionesCollection()) {
            if(calificacion.getIdMatricula()==matricula){
                return calificacion;
            }
        }
        return null;
    }
       
    @XmlTransient
    public Collection<IappSentFiles> getIappEntregasCollection() {
        return iappEntregasCollection;
    }

    public void setIappEntregasCollection(Collection<IappSentFiles> iappEntregasCollection) {
        this.iappEntregasCollection = iappEntregasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacion != null ? idAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappHomeWorks)) {
            return false;
        }
        IappHomeWorks other = (IappHomeWorks) object;
        if ((this.idAsignacion == null && other.idAsignacion != null) || (this.idAsignacion != null && !this.idAsignacion.equals(other.idAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappHomeWorks[ idAsignacion=" + idAsignacion + " ]";
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public byte[] getFileArray() {
        return fileArray;
    }

    public void setFileArray(byte[] fileArray) {
        this.fileArray = fileArray;
    }

    @XmlTransient
    public Collection<IappQualificationHomeWorks> getIappCalAsignacionesCollection() {
        return iappCalAsignacionesCollection;
    }

    public void setIappCalAsignacionesCollection(Collection<IappQualificationHomeWorks> iappCalAsignacionesCollection) {
        this.iappCalAsignacionesCollection = iappCalAsignacionesCollection;
    }
}
