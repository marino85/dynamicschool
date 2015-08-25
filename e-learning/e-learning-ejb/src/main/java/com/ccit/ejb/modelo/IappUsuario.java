/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author innovasoft
 */
@Entity
@Table(name = "iapp_usuario",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappUsuario.findAll", query = "SELECT i FROM IappUsuario i"),
    @NamedQuery(name = "IappUsuario.findByNumeroDoc", query = "SELECT i FROM IappUsuario i WHERE i.numeroDoc = :numeroDoc"),
    @NamedQuery(name = "IappUsuario.findByNumeroDocTipoDoc", query = "SELECT i FROM IappUsuario i WHERE i.numeroDoc = :numeroDoc AND i.tipoDoc.idTipoDoc = :tipoDoc"),
    @NamedQuery(name = "IappUsuario.findByNombres", query = "SELECT i FROM IappUsuario i WHERE i.nombres = :nombres"),
    @NamedQuery(name = "IappUsuario.findByApellidos", query = "SELECT i FROM IappUsuario i WHERE i.apellidos = :apellidos"),
    @NamedQuery(name = "IappUsuario.findByDireccion", query = "SELECT i FROM IappUsuario i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "IappUsuario.findByTelefono", query = "SELECT i FROM IappUsuario i WHERE i.telefono = :telefono"),
    @NamedQuery(name = "IappUsuario.findByEmail", query = "SELECT i FROM IappUsuario i WHERE i.email = :email"),
    @NamedQuery(name = "IappUsuario.findByFechaNacimiento", query = "SELECT i FROM IappUsuario i WHERE i.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "IappUsuario.findBySexo", query = "SELECT i FROM IappUsuario i WHERE i.sexo = :sexo"),
    @NamedQuery(name = "IappUsuario.findByNivelEducacion", query = "SELECT i FROM IappUsuario i WHERE i.nivelEducacion = :nivelEducacion"),
    @NamedQuery(name = "IappUsuario.findByCodigo", query = "SELECT i FROM IappUsuario i WHERE i.codigo = :codigo"),
   
    @NamedQuery(name = "IappUsuario.findByPasswd", query = "SELECT i FROM IappUsuario i WHERE i.passwd = :passwd"),
    @NamedQuery(name = "IappUsuario.findByCont", query = "SELECT i FROM IappUsuario i WHERE i.cont = :cont"),
    @NamedQuery(name = "IappUsuario.findByFechaInicio", query = "SELECT i FROM IappUsuario i WHERE i.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "IappUsuario.findByFechaFin", query = "SELECT i FROM IappUsuario i WHERE i.fechaFin = :fechaFin"),
    @NamedQuery(name = "IappUsuario.findByIdUsuario", query = "SELECT i FROM IappUsuario i WHERE i.idUsuario = :idUsuario"),
    @NamedQuery(name = "IappUsuario.findByIdPerfil", query = "SELECT i FROM IappUsuario i WHERE i.idPerfil.idPerfil = :idPerfil")})
public class IappUsuario implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name =     "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name =     "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<IappHorario> iappHorarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfesor")
    @OrderBy(value="nombre ASC")
    private Collection<IappCursos> iappCursosCollection;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_doc")
    private String numeroDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "nivel_educacion")
    private String nivelEducacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 5,message="El código debe entre 4 y 5 digitos")
    @Column(name = "codigo")
    private String codigo;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "passwd")
    private String passwd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cont")
    private int cont;
    @Id
    @SequenceGenerator(allocationSize=1,name="PGSSQL_USER_SEG",schema="general",sequenceName="iapp_usuario_id_usuario_seq")
    @GeneratedValue(generator="PGSSQL_USER_SEG",strategy= GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @JoinColumn(name = "tipo_doc", referencedColumnName = "id_tipo_doc")
    @ManyToOne(optional = false)
    private IappTipoDocumento tipoDoc;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(optional = false)
    private IappPerfiles idPerfil;
    @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada")
    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    private IappJornada idJornada;
    @JoinColumn(name = "estado_usuario", referencedColumnName = "id_estado_usuario")
    @ManyToOne(optional = false)
    private IappEstadoUsuario estadoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    @OrderBy(value="idMatricula ASC")
    private Collection<IappMatriculas> iappMatriculasCollection;    
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    private IappNiveles idNivel;
    @OneToMany(cascade= CascadeType.REMOVE,mappedBy="idUsuario")
    private Collection<IappFactura> facturas;
    @Column(name = "id_profesor")
    private Integer idProfesor;    
    @Column(name = "asistencia")
    private String asistencia;

    public IappUsuario() {
    }

    public IappUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public IappUsuario(Integer idUsuario, String numeroDoc, String nombres, String apellidos, String direccion, String telefono, String email, Date fechaNacimiento, String sexo, String nivelEducacion, String codigo, String nivelCursado, String passwd, int cont) {
        this.idUsuario = idUsuario;
        this.numeroDoc = numeroDoc;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.nivelEducacion = nivelEducacion;
        this.codigo = codigo;
       
        this.passwd = passwd;
        this.cont = cont;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNivelEducacion() {
        return nivelEducacion;
    }

    public void setNivelEducacion(String nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

   

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public IappTipoDocumento getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(IappTipoDocumento tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public IappPerfiles getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(IappPerfiles idPerfil) {
        this.idPerfil = idPerfil;
    }

    public IappJornada getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(IappJornada idJornada) {
        this.idJornada = idJornada;
    }

    public IappEstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(IappEstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public IappNiveles getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(IappNiveles idNivel) {
        this.idNivel = idNivel;
    }

    public Collection<IappFactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Collection<IappFactura> facturas) {
        this.facturas = facturas;
    }
    
    

    @XmlTransient
    public Collection<IappMatriculas> getIappMatriculasCollection() {
        return iappMatriculasCollection;
    }

    public void setIappMatriculasCollection(Collection<IappMatriculas> iappMatriculasCollection) {
        this.iappMatriculasCollection = iappMatriculasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(object==this){
            return true;
        }
        if (!(object instanceof IappUsuario)) {
            return false;
        }
        IappUsuario other = (IappUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappUsuario[ idUsuario=" + idUsuario + " ]";
    }


    @XmlTransient
    public Collection<IappCursos> getIappCursosCollection() {
        return iappCursosCollection;
    }

    public void setIappCursosCollection(Collection<IappCursos> iappCursosCollection) {
        this.iappCursosCollection = iappCursosCollection;
    }

    

    @XmlTransient
    public Collection<IappHorario> getIappHorarioCollection() {
        return iappHorarioCollection;
    }

    public void setIappHorarioCollection(Collection<IappHorario> iappHorarioCollection) {
        this.iappHorarioCollection = iappHorarioCollection;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    
  
    
}