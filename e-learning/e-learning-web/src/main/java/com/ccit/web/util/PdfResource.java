/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.util;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author marino
 */
public class PdfResource{
     private  byte[]buf = null;

        public PdfResource() {
        }
        
        public PdfResource( byte[]buf) {
            this.buf=buf;
        }
         
      
}
