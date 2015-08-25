<%@page import="com.innovasoft.web.bean.PagosOnlineComfirm"%>

<%
    String usuario_id;
    String estado_pol;
    String riesgo;
    String codigo_respuesta_pol;
    String ref_venta;
    String ref_pol;
    String firma;
    String mensaje;
    String medio_pago;
    String tipo_medio_pago;
    String valor;
    String cus;
    String banco_pse;
    String fecha_procesamiento;
    String emailComprador;


    usuario_id = request.getParameter("usuario_id");
    estado_pol = request.getParameter("estado_pol");
    riesgo = request.getParameter("riesgo");
    codigo_respuesta_pol = request.getParameter("codigo_respuesta_pol");
    ref_venta = request.getParameter("ref_venta");
    ref_pol = request.getParameter("ref_pol");
    firma = request.getParameter("firma");
    mensaje = request.getParameter("mensaje_respuesta_pol");
    medio_pago = request.getParameter("medio_pago");
    tipo_medio_pago = request.getParameter("tipo_medio_pago");
    valor = request.getParameter("valor");
    cus = request.getParameter("cus");
    banco_pse = request.getParameter("banco_pse");
    emailComprador = request.getParameter("email_comprador");
    fecha_procesamiento = request.getParameter("fecha_transaccion");

    PagosOnlineComfirm comfirm = new PagosOnlineComfirm();
    comfirm.setUsuario_id(usuario_id);
    comfirm.setEstado_pol(estado_pol);
    comfirm.setRiesgo(riesgo);
    comfirm.setCodigo_respuesta_pol(codigo_respuesta_pol);
    comfirm.setRef_venta(ref_venta);
    comfirm.setRef_pol(ref_pol);
    comfirm.setFirma(firma);
    comfirm.setMensaje(mensaje);
    comfirm.setMedio_pago(medio_pago);
    comfirm.setTipo_medio_pago(tipo_medio_pago);
    comfirm.setValor(valor);
    comfirm.setCus(cus);
    comfirm.setBanco_pse(banco_pse);
    comfirm.setEmailComprador(emailComprador);
    comfirm.setFecha_procesamiento(fecha_procesamiento);
    comfirm.sendWellcomeEmail();
    comfirm.sendWellcomeEmailAdministrador();
     
%>
