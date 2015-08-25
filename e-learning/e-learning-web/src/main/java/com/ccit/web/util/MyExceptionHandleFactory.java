/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author marino
 */
public class MyExceptionHandleFactory extends ExceptionHandlerFactory {

    ExceptionHandlerFactory delegateFactory;

    public MyExceptionHandleFactory(ExceptionHandlerFactory delegateFactory) {
        this.delegateFactory = delegateFactory;
    }
    
    
    
 @Override
    public ExceptionHandler getExceptionHandler() {
        return new MyExceptionHandler(delegateFactory.getExceptionHandler());
    }
    
}
