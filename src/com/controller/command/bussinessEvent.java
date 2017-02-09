package com.controller.command;

/**
 * Clase que representa los distintos eventos del negocio en función del subsistema al que estén referidos.
 * @author JAVIER
 *
 */
public class bussinessEvent {
	//PETICIONES DEL SISTEMA
	public static final int LOGINEVENT 						= 101;
	public static final int LOGOUTEVENT						= 102;
	
	public static final int AÑADIR_PRODUCTO_PEDIDO			= 501;
    public static final int ELIMINAR_PRODUCTO_PEDIDO		= 502;
	public static final int AÑADIR_PEDIDO 					= 201;
	public static final int ELIMINAR_PEDIDO 				= 202;
	public static final int CANCELAR_PEDIDO					= 203;
	public static final int FINALIZAR_PEDIDO 				= 204;
    public static final int MOSTRAR_PEDIDO 					= 205;
    public static final int MODIFICAR_PEDIDO 				= 206;
    public static final int CONFIRMAR_PEDIDO 				= 207;
    public static final int PEDIDO_ACTUAL					= 208;
    public static final int AÑADIR_PRODUCTO_PEDIDO_MODIFY   = 209;
    public static final int ELIMINAR_PRODUCTO_PEDIDO_MODIFY	= 210;
    public static final int ACTUALIZAR_PEDIDOS 				= 601;
    public static final int ACTUALIZAR_PEDIDOSCONFIRMADOS 	= 603;
    
    public static final int AÑADIR_PRODUCTO_VENTA			= 503;
    public static final int ELIMINAR_PRODUCTO_VENTA			= 504;
    public static final int AÑADIR_VENTA 					= 301;
    public static final int ELIMINAR_VENTA 					= 302;
    public static final int CANCELAR_VENTA					= 303;
    public static final int MOSTRAR_VENTA 					= 304;
    public static final int MOSTRAR_BALANCEDIA 				= 305;
    public static final int MOSTRAR_BALANCE 				= 306;
    public static final int FINALIZAR_VENTA					= 307;
    public static final int GENERAR_FACTURA					= 308;
    public static final int VENTA_ACTUAL					= 309;
    public static final int MOSTRAR_CAMBIO					= 310;
    public static final int FINALIZAR_VENTA_PROCESS			= 311;
    public static final int FINALIZAR_VENTA_NOTIFY			= 312;
    public static final int ACTUALIZAR_VENTAS 				= 602;
    
    public static final int INSERTAR_USUARIO 				= 401;
    public static final int ELIMINAR_USUARIO 				= 402;
    public static final int MOSTRAR_USUARIO 				= 403;
    public static final int MODIFICAR_USUARIO 				= 404;
    public static final int ACTUALIZAR_USUARIOS 			= 604;
    
    public static final int AÑADIR_PRODUCTO_STOCK			= 505;
    public static final int ELIMINAR_PRODUCTO_STOCK			= 506;
    public static final int MOSTRAR_PRODUCTO 				= 507;
    public static final int CLASIFICAR_INVENTARIO 			= 508;
    public static final int ACTUALIZAR_STOCK 				= 605;
    
    //EVENTOS DE ERROR
    public static final int ERROR_LOGIN 					= 900;
    public static final int ERROR_ADDPRODUCT 				= 901;
    public static final int ERROR_REMOVEPRODUCT 			= 902;
    public static final int ERROR_SEARCHPRODUCT 			= 903;
    public static final int ERROR_DELETEORDER 				= 904;
    public static final int ERROR_MODIFYORDER 				= 905;
    public static final int ERROR_APPROVEORDER 				= 906;
    public static final int ERROR_SEARCHORDER 				= 907;
    public static final int ERROR_DELETESALE 				= 908;
    public static final int ERROR_SEARCHSALE 				= 909;
    public static final int ERROR_ADDUSER 					= 910;
    public static final int ERROR_DELETEUSER 				= 911;
    public static final int ERROR_MODIFYUSER 				= 912;
    public static final int ERROR_SEARCHUSER 				= 913;
	public static final int ERROR_MOSTRARCAMBIO 			= 914;
	
}