/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.util;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;


/**
 *
 * @author marino
 */
public class MyExceptionHandler extends ExceptionHandlerWrapper {
private ExceptionHandler wrapped;

    public MyExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }


    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
    
   public void handle() throws FacesException {
        Iterator events = getUnhandledExceptionQueuedEvents().iterator();

        //Iterate through the queued exceptions
        while (events.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();

//            //See if it's an exception I'm interested in
//            if (t instanceof SessionExpiredException) {
//                try {
//                    context.getContext().release();
//                } finally {
//                    //remove it if you processed it
//                    events.remove();
//                }
//            }

            //Let the next ExceptionHandler(s) deal with the others
            getWrapped().handle();
        }
    }
    
}
