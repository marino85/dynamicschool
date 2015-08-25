/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.exception;

import java.io.Serializable;

/**
 *
 * @author marino
 */
public class IappException extends Exception{
    
    private String codigo;
    

    public IappException(String message, Throwable cause) {
        super(message, cause);
    }

    public IappException(String message) {
        super(message);
    }

    public IappException() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    
    
    
    
}
