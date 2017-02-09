package com.controller.command.sale;

import com.controller.command.bussinessEvent;
import com.model.saleService;
import com.model.exceptions.SaleNotFoundException;

/**
 * Clase abstracta que representa los distintos comandos que podemos 
 * ejecutar sobre el subsistema de ventas.
 * @author Javier
 *
 */

public abstract class saleCommandInterpreter {

	protected static saleService service; // Servicio de aplicación de ventas
	
	/** 
	 * Método estático que configura el servicio de aplicación para poder ejecutar los comandos
	 * mediante el intérprete de comandos.
	 * @param s Servicio de aplicación de ventas.
	 */

	public static void configureCommandInterpreter(saleService s) {
		service = s;
	}

	/**
	 * Método que parsea los distintos comandos en función del evento de negocio y la cadena que recibe.
	 * @param event Evento de negocio del sistema.
	 * @param cadena Es la lista de parámetros de cada las funciones del modelo.
	 * @return Devuelve un objeto de la clase derivada de saleCommandInterpreter.
	 */
	
	public saleCommandInterpreter parseCommand(int event, String cadena) {
		String[] tok = cadena.split("_");
		saleCommandInterpreter Command = null;

		switch (event) {
		case bussinessEvent.AÑADIR_PRODUCTO_VENTA:
			Command = Command(tok[0], tok[1], tok[2]);
			break;
		case bussinessEvent.ELIMINAR_PRODUCTO_VENTA:
			Command = Command(tok[0], tok[1], tok[2]);
			break;
		case bussinessEvent.MOSTRAR_PRODUCTO:
			Command = Command(tok[0], tok[1], tok[2]);
			break;
		case bussinessEvent.AÑADIR_VENTA:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.ELIMINAR_VENTA:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.FINALIZAR_VENTA:
			Command = Command(tok[0], tok[1], tok[2]);
			break;
		case bussinessEvent.CANCELAR_VENTA:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.MOSTRAR_VENTA:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.GENERAR_FACTURA:
			Command = Command(tok[0]);
			break;
		case bussinessEvent.MOSTRAR_CAMBIO:
			Command = Command(tok[0], tok[1]);
			break;
		case bussinessEvent.MOSTRAR_BALANCEDIA:
			Command = Command();
			break;
		case bussinessEvent.MOSTRAR_BALANCE:
			Command = Command();
			break;
		}

		return Command;
	}

	
	// Métodos abstractos
	public abstract Object execute() throws SaleNotFoundException;

	public abstract saleCommandInterpreter Command();
	public abstract saleCommandInterpreter Command(String arg0);
	public abstract saleCommandInterpreter Command(String arg0, String arg1);
	public abstract saleCommandInterpreter Command(String arg0, String arg1, String arg2);
	public abstract saleCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3);
	public abstract saleCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4);
	public abstract saleCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5);

	public abstract int bussinessEvent();

}