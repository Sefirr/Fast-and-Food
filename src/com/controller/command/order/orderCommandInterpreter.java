package com.controller.command.order;

import com.controller.command.bussinessEvent;
import com.model.orderService;

/**
 * Clase abstracta que representa los distintos comandos que podemos 
 * ejecutar sobre el subsistema de pedidos.
 * @author Javier
 *
 */

public abstract class orderCommandInterpreter {

	protected static orderService service; // Servicio de aplicación de pedidos

	/** 
	 * Método estático que configura el servicio de aplicación para poder ejecutar los comandos
	 * mediante el intérprete de comandos.
	 * @param s Servicio de aplicación de pedidos.
	 */
	
	public static void configureCommandInterpreter(orderService s) {
		service = s;
	}

	/**
	 * Método que parsea los distintos comandos en función del evento de negocio y la cadena que recibe.
	 * @param event Evento de negocio del sistema.
	 * @param cadena Es la lista de parámetros de cada las funciones del modelo.
	 * @return Devuelve un objeto de la clase derivada de orderCommandInterpreter.
	 */
	
	public orderCommandInterpreter parseCommand(int event, String cadena) {
		String[] tok = cadena.split("_");
		orderCommandInterpreter Command = null;

		switch (event) {
		case bussinessEvent.AÑADIR_PRODUCTO_PEDIDO:
			Command = Command(tok[0], tok[1], tok[2], tok[3], tok[4], tok[5]);
			break;
		case bussinessEvent.ELIMINAR_PRODUCTO_PEDIDO:
			Command = Command(tok[0], tok[1], tok[2]);
			break;
		case bussinessEvent.AÑADIR_PRODUCTO_PEDIDO_MODIFY:
			Command = Command(tok[0], tok[1], tok[2], tok[3], tok[4], tok[5]);
			break;
		case bussinessEvent.ELIMINAR_PRODUCTO_PEDIDO_MODIFY:
			Command = Command(tok[0], tok[1], tok[2]);
			break;
		case bussinessEvent.AÑADIR_PEDIDO:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.ELIMINAR_PEDIDO:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.FINALIZAR_PEDIDO:
			Command = Command(tok[0], tok[1], tok[2], tok[3]);
			break;
		case bussinessEvent.CANCELAR_PEDIDO:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.MOSTRAR_PEDIDO:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.MODIFICAR_PEDIDO:
			Command = Command(tok[0], tok[1], tok[2], tok[3], tok[4]);
			break;
		case bussinessEvent.CONFIRMAR_PEDIDO:
			Command = Command(tok[0]);
			break;
		}

		return Command;
	}

	
	//Métodos abstractos
	public abstract Object execute();
	
	public abstract orderCommandInterpreter Command(String arg0);
	public abstract orderCommandInterpreter Command(String arg0, String arg1);
	public abstract orderCommandInterpreter Command(String arg0, String arg1, String arg2);
	public abstract orderCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3);
	public abstract orderCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4);
	public abstract orderCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5);
	
	public abstract int bussinessEvent();

}