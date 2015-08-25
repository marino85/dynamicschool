/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.constants;

/**
 *
 * @author innovasoft
 */
public class Constants {
    public static Integer PERFIL_ESTUDIANTE=new Integer(1);
    public static Integer PERFIL_ADMINISTRADOR=new Integer(2);
    public static Integer PERFIL_SECRETARIO=new Integer(3);
    public static Integer PERFIL_PROFESOR=new Integer(4);
    public static String  REGISTRO_ACTIVO ="A";
    public static String  REGISTRO_BORRADO="B";
    public static String  CURSO_VIRTUAL="V";
    public static String  CURSO_PRESENCIAL="P";
    public static Integer CURSO_ACTIVO=new Integer(2);
    public static Integer CURSO_INACTIVO=new Integer(1);
    public static Integer CURSO_CERRADO=new Integer(3);
    public static String ESTADO_ONLINE_CREADO = "CREADA";
    public static String ESTADO_ONLINE_INICIADO ="INICIADA";
    public static String ESTADO_ONLINE_FINALIZADO = "FINALIZADA";
     
        

    public static Integer getPERFIL_ADMINISTRADOR() {
        return PERFIL_ADMINISTRADOR;
    }

    public static Integer getPERFIL_ESTUDIANTE() {
        return PERFIL_ESTUDIANTE;
    }

    public static Integer getPERFIL_PROFESOR() {
        return PERFIL_PROFESOR;
    }

    public static Integer getPERFIL_SECRETARIO() {
        return PERFIL_SECRETARIO;
    }

    public static String getREGISTRO_ACTIVO() {
        return REGISTRO_ACTIVO;
    }

    public static String getREGISTRO_BORRADO() {
        return REGISTRO_BORRADO;
    }

    public static String getCURSO_PRESENCIAL() {
        return CURSO_PRESENCIAL;
    }

    public static String getCURSO_VIRTUAL() {
        return CURSO_VIRTUAL;
    }

    public static Integer getCURSO_ACTIVO() {
        return CURSO_ACTIVO;
    }

    public static Integer getCURSO_CERRADO() {
        return CURSO_CERRADO;
    }

    public static Integer getCURSO_INACTIVO() {
        return CURSO_INACTIVO;
    }
}
