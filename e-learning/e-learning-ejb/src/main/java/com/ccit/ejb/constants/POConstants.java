/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.constants;

/**
 *
 * @author marino
 */
public class POConstants {

    public static final Integer ESTADO_POOL_SIN_ABRIR = 1;
    public static final Integer ESTADO_POOL_ABIERTA = 2;
    public static final Integer ESTADO_POOL_PAGADA_Y_ABONADA = 4;
    public static final Integer ESTADO_POOL_CANCELADA = 5;
    public static final Integer ESTADO_POOL_RECHAZADA = 6;
    public static final Integer ESTADO_POOL_EN_VALIDACION = 7;
    public static final Integer ESTADO_POOL_REVERSADA = 8;
    public static final Integer ESTADO_POOL_REVERSADA_FRAUDULENTA = 9;
    public static final Integer ESTADO_POOL_ENVIADA_ENT_FINANCIERA = 10;
    public static final Integer ESTADO_POOL_CAPTURANDO_DATOS_TARJETA_CREDITO = 11;
    public static final Integer ESTADO_POOL_ESPERANDO_CONFIRMACION_PSE = 12;
    public static final Integer ESTADO_POOL_ACTIVA_DEBITOS_ACH = 13;
    public static final Integer ESTADO_POOL_CONFIRMANDO_PAGO_EFECTY = 14;
    public static final Integer ESTADO_POOL_IMPRESO = 15;
    public static final Integer ESTADO_POOL_DEBITO_ACH_REGISTRADO = 16;
    public static final String VALOR_ESTADO_POOL_SIN_ABRIR = "Sin abrir";
    public static final String VALOR_ESTADO_POOL_ABIERTA = "Abierta";
    public static final String VALOR_ESTADO_POOL_PAGADA_Y_ABONADA = "Pagada y abonada";
    public static final String VALOR_ESTADO_POOL_CANCELADA = "Cancelada";
    public static final String VALOR_ESTADO_POOL_RECHAZADA = "Rechazada";
    public static final String VALOR_ESTADO_POOL_EN_VALIDACION = "En validación";
    public static final String VALOR_ESTADO_POOL_REVERSADA = "Reversada";
    public static final String VALOR_ESTADO_POOL_REVERSADA_FRAUDULENTA = "Reversada fraudulenta";
    public static final String VALOR_ESTADO_POOL_ENVIADA_ENT_FINANCIERA = "Enviada ent. Financiera";
    public static final String VALOR_ESTADO_POOL_CAPTURANDO_DATOS_TARJETA_CREDITO = "Capturando datos tarjeta de crédito";
    public static final String VALOR_ESTADO_POOL_ESPERANDO_CONFIRMACION_PSE = "Esperando confirmación sistema PSE";
    public static final String VALOR_ESTADO_POOL_ACTIVA_DEBITOS_ACH = "Activa Débitos ACH";
    public static final String VALOR_ESTADO_POOL_CONFIRMANDO_PAGO_EFECTY = "Confirmando pago Efecty";
    public static final String VALOR_ESTADO_POOL_IMPRESO = "Impreso";
    public static final String VALOR_ESTADO_POOL_DEBITO_ACH_REGISTRADO = "Debito ACH Registrado";
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCION_APROBADA = 1;
    public static final Integer CODIGO_RESPUESTA_POOL_PAGO_CANCELADO_POR_EL_USUARIO = 2;
    public static final Integer CODIGO_RESPUESTA_POOL_PAGO_CANCELADO_POR_EL_USUARIO_DURANTE_VALIDACION = 3;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCION_RECHAZADA_POR_LA_ENTIDAD = 4;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCIÓN_DECLINADA_POR_LA_ENTIDAD = 5;
    public static final Integer CODIGO_RESPUESTA_POOL_FONDOS_INSUFICIENTES = 6;
    public static final Integer CODIGO_RESPUESTA_POOL_TARJETA_INVALIDA = 7;
    public static final Integer CODIGO_RESPUESTA_POOL_ACUDA_A_SU_ENTIDAD = 8;
    public static final Integer CODIGO_RESPUESTA_POOL_TARJETA_VENCIDA = 9;
    public static final Integer CODIGO_RESPUESTA_POOL_TARJETA_RESTRINGIDA = 10;
    public static final Integer CODIGO_RESPUESTA_POOL_DISCRECIONAL_POL = 11;
    public static final Integer CODIGO_RESPUESTA_POOL_FECHA_DE_EXPIRACION_O_CAMPO_SEG_INVALIDOS = 12;
    public static final Integer CODIGO_RESPUESTA_POOL_REPITA_TRANSACCION = 13;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCION_INVALIDA = 14;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCION_EN_PROCESO_DE_VALIDACION = 15;
    public static final Integer CODIGO_RESPUESTA_POOL_COMBINACION_USUARIO_CONTRASE_A_INVALIDOS = 16;
    public static final Integer CODIGO_RESPUESTA_POOL_MONTO_EXCEDE_MÁXIMO_PERMITIDO_POR_ENTIDAD = 17;
    public static final Integer CODIGO_RESPUESTA_POOL_DOCUMENTO_DE_IDENTIFICACION_INVALIDO = 18;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCI_N_ABANDONADA_CAPTURANDO_DATOS_TC = 19;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCION_ABANDONADA = 20;
    public static final Integer CODIGO_RESPUESTA_POOL_IMPOSIBLE_REVERSAR_TRANSACCION = 21;
    public static final Integer CODIGO_RESPUESTA_POOL_TARJETA_NO_AUTORIZADA_PARA_REALIZAR_COMPRAS_POR_INTERNET = 22;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCION_RECHAZADA = 23;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCION_PARCIAL_APROBADA = 24;
    public static final Integer CODIGO_RESPUESTA_POOL_RECHAZADA_POR_NO_CONFIRMACION = 25;
    public static final Integer CODIGO_RESPUESTA_POOL_COMPROBANTE_GENERADO_ESPERANDO_PAGO_EN_BANCO = 26;
    public static final Integer CODIGO_RESPUESTA_POOL_TRANSACCIPN_PENDIENTE_POR_CONFIRMAR = 9994;
    public static final Integer CODIGO_RESPUESTA_POOL_CERTIFICADO_DIGITAL_NO_ENCONTRADO = 9995;
    public static final Integer CODIGO_RESPUESTA_POOL_ENTIDAD_NO_RESPONDE = 9996;
    public static final Integer CODIGO_RESPUESTA_POOL_ERROR_DE_MENSAJERIA_CON_LA_ENTIDAD_FINANCIERA = 9997;
    public static final Integer CODIGO_RESPUESTA_POOL_ERROR_EN_LA_ENTIDAD_FINANCIERA = 9998;
    public static final Integer CODIGO_RESPUESTA_POOL_ERROR_NO_ESPECIFICADO = 9999;
    
       
}
