package com.controller.command.stock;

import com.controller.command.bussinessEvent;
import com.model.stockService;

/**
 * Clase abstracta que representa los distintos comandos que podemos 
 * ejecutar sobre el subsistema de almacén.
 * @author Javier
 *
 */

public abstract class stockCommandInterpreter {

	protected static stockService service; // Servicio de aplicación de almacén

	/** 
	 * Método estático que configura el servicio de aplicación para poder ejecutar los comandos
	 * mediante el intérprete de comandos.
	 * @param s Servicio de aplicación de almacén.
	 */
	
	public static void configureCommandInterpreter(stockService s) {
		service = s;
	}

	/**
	 * Método que parsea los distintos comandos en función del evento de negocio y la cadena que recibe.
	 * @param event Evento de negocio del sistema.
	 * @param cadena Es la lista de parámetros de cada las funciones del modelo.
	 * @return Devuelve un objeto de la clase derivada de stockCommandInterpreter.
	 */
	
	public stockCommandInterpreter parseCommand(int event, String cadena) {
		String[] tok = cadena.split("_");
		stockCommandInterpreter Command = null;

		switch (event) {
		case bussinessEvent.AÑADIR_PRODUCTO_STOCK:
			Command = Command(tok[0], tok[1], tok[2], tok[3], tok[4]);
			break;
		case bussinessEvent.ELIMINAR_PRODUCTO_STOCK:
			Command = Command(tok[0], tok[1]);
			break;
		case bussinessEvent.MOSTRAR_PRODUCTO:
			Command = Command(tok[0], tok[1]);
			break;
		case bussinessEvent.CLASIFICAR_INVENTARIO:
			Command = Command(tok[0]);
			break;
		}

		return Command;
	}

	//Métodos abstractos
	public abstract Object execute();
	
	public abstract stockCommandInterpreter Command(String arg0);
	public abstract stockCommandInterpreter Command(String arg0, String arg1);
	public abstract stockCommandInterpreter Command(String arg0, String arg1, String arg2);
	public abstract stockCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3);
	public abstract stockCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4);
	public abstract stockCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5);

	public abstract int bussinessEvent();

}