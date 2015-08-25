/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marino
 */
@Entity
@Table(name = "iapp_cal_curso",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappCalCurso.findAll", query = "SELECT i FROM IappCalCurso i"),
    @NamedQuery(name = "IappCalCurso.findByIdMatricula", query = "SELECT i FROM IappCalCurso i WHERE i.idMatricula = :idMatricula"),
    @NamedQuery(name = "IappCalCurso.findByCalTrabajos", query = "SELECT i FROM IappCalCurso i WHERE i.calTrabajos = :calTrabajos"),
    @NamedQuery(name = "IappCalCurso.findByCalEvaluaciones", query = "SELECT i FROM IappCalCurso i WHERE i.calEvaluaciones = :calEvaluaciones"),
    @NamedQuery(name = "IappCalCurso.findByCalAdicional", query = "SELECT i FROM IappCalCurso i WHERE i.calAdicional = :calAdicional"),
    @NamedQuery(name = "IappCalCurso.findByCalTotal", query = "SELECT i FROM IappCalCurso i WHERE i.calTotal = :calTotal")})
public class IappCalCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_matricula")
    private Integer idMatricula;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cal_trabajos")
    private BigDecimal calTrabajos;
    @Column(name = "cal_evaluaciones")
    private BigDecimal calEvaluaciones;
    @Column(name = "cal_adicional")
    
    private BigDecimal calAdicional;
    @Column(name = "cal_total")
    private BigDecimal calTotal;
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private IappMatriculas iappMatriculas;

    public IappCalCurso() {
    }

    public IappCalCurso(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public BigDecimal getCalTrabajos() {
        return calTrabajos;
    }

    public void setCalTrabajos(BigDecimal calTrabajos) {
        this.calTrabajos = calTrabajos;
    }

    public BigDecimal getCalEvaluaciones() {
        return calEvaluaciones;
    }

    public void setCalEvaluaciones(BigDecimal calEvaluaciones) {
        this.calEvaluaciones = calEvaluaciones;
    }

    public BigDecimal getCalAdicional() {
        return calAdicional;
    }

    public void setCalAdicional(BigDecimal calAdicional) {
        this.calAdicional = calAdicional;
    }

    public BigDecimal getCalTotal() {
        return calTotal;
    }

    public void setCalTotal(BigDecimal calTotal) {
        this.calTotal = calTotal;
    }

    public IappMatriculas getIappMatriculas() {
        return iappMatriculas;
    }

    public void setIappMatriculas(IappMatriculas iappMatriculas) {
        this.iappMatriculas = iappMatriculas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappCalCurso)) {
            return false;
        }
        IappCalCurso other = (IappCalCurso) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappCalCurso[ idMatricula=" + idMatricula + " ]";
    }
    
}
