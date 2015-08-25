/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author marino
 */
public class UtilidadesFecha {
    
    
    
     public static Date getDate(String date){
        try{
           return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        }catch(Exception e){
            return null;
        }
        
    }
    
    public static String getFormatDate(Calendar c, int day) {
        String fecha = "";
        c.set(Calendar.DAY_OF_WEEK, day);
        fecha = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
        return fecha;

    }
    
     public static String getFormatDate(Calendar c) {
        String fecha = "";      
        
        fecha = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
        return fecha;

    }
    
    
    
}
