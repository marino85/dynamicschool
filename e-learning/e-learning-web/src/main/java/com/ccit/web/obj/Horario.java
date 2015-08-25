/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.obj;

import java.util.Date;
import java.util.List;

/**
 *
 * @author marino
 */
public class Horario {
    
    public String hora;
        public String lunes;
        public String martes;
        public String miercoles;
        public String jueves;
        public String viernes;
        public String sabado;

        public Horario(String hora, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado) {
            this.hora = hora;
            this.lunes = lunes;
            this.martes = martes;
            this.miercoles = miercoles;
            this.jueves = jueves;
            this.viernes = viernes;
            this.sabado = sabado;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getJueves() {
            return jueves;
        }

        public void setJueves(String jueves) {
            this.jueves = jueves;
        }

        public String getLunes() {
            return lunes;
        }

        public void setLunes(String lunes) {
            this.lunes = lunes;
        }

        public String getMartes() {
            return martes;
        }

        public void setMartes(String martes) {
            this.martes = martes;
        }

        public String getMiercoles() {
            return miercoles;
        }

        public void setMiercoles(String miercoles) {
            this.miercoles = miercoles;
        }

        public String getSabado() {
            return sabado;
        }

        public void setSabado(String sabado) {
            this.sabado = sabado;
        }

        public String getViernes() {
            return viernes;
        }

        public void setViernes(String viernes) {
            this.viernes = viernes;
        }
    }

   