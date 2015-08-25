/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marino
 */
@Entity
@Table(name = "iapp_factura",schema="general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IappFactura.findAll", query = "SELECT i FROM IappFactura i"),
    @NamedQuery(name = "IappFactura.findByRefVenta", query = "SELECT i FROM IappFactura i WHERE i.refVenta = :refVenta"),
    @NamedQuery(name = "IappFactura.findByDescripcion", query = "SELECT i FROM IappFactura i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IappFactura.findByValor", query = "SELECT i FROM IappFactura i WHERE i.valor = :valor"),
    @NamedQuery(name = "IappFactura.findByIva", query = "SELECT i FROM IappFactura i WHERE i.iva = :iva"),
    @NamedQuery(name = "IappFactura.findByBaseiva", query = "SELECT i FROM IappFactura i WHERE i.baseiva = :baseiva")})
public class IappFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull    
    @SequenceGenerator(allocationSize=1,name="FAC_SEG",schema="general",sequenceName="iapp_factura_id_factura_seq   ")
    @GeneratedValue(generator="FAC_SEG",strategy= GenerationType.SEQUENCE)
    @Column(name = "id_factura")
    private Integer refVenta;   
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "iva")
    private BigDecimal iva;
    @Column(name = "baseiva")
    private BigDecimal baseiva;
    
    @JoinColumn(name="id_usuario",referencedColumnName="id_usuario")
    @ManyToOne
    private IappUser idUsuario;

    public IappFactura() {
    }

    public IappFactura(Integer refVenta) {
        this.refVenta = refVenta;
    }

    public Integer getRefVenta() {
        return refVenta;
    }

    public void setRefVenta(Integer refVenta) {
        this.refVenta = refVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getBaseiva() {
        return baseiva;
    }

    public void setBaseiva(BigDecimal baseiva) {
        this.baseiva = baseiva;
    }

    public IappUser getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(IappUser idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refVenta != null ? refVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IappFactura)) {
            return false;
        }
        IappFactura other = (IappFactura) object;
        if ((this.refVenta == null && other.refVenta != null) || (this.refVenta != null && !this.refVenta.equals(other.refVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.innovasoft.ejb.modelo.IappFactura[ refVenta=" + refVenta + " ]";
    }
    
}
