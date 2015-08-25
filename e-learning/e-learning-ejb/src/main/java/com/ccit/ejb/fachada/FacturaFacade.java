/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.fachada.impl.IappFacturaFacade;
import com.ccit.ejb.modelo.IappFactura;
import com.ccit.exception.IappException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class FacturaFacade {

    @EJB
    private IappFacturaFacade factura;
    
    
    public void createInvoice(IappFactura invoice) throws IappException{
        factura.create(invoice);
        
    }
    
}
