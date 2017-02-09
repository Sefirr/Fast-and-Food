package com.controller.command.user;

import com.controller.command.bussinessEvent;
import com.model.userService;

/**
 * Clase abstracta que representa los distintos comandos que podemos 
 * ejecutar sobre el subsistema de usuarios.
 * @author Javier
 *
 */

public abstract class userCommandInterpreter {
	
	protected static userService service; // Servicio de aplicación de usuarios
	
	/** 
	 * Método estático que configura el servicio de aplicación para poder ejecutar los comandos
	 * mediante el intérprete de comandos.
	 * @param s Servicio de aplicación de usuarios.
	 */
	
	public static void configureCommandInterpreter(userService s){
		service = s;
	}

	/**
	 * Método que parsea los distintos comandos en función del evento de negocio y la cadena que recibe.
	 * @param event Evento de negocio del sistema.
	 * @param cadena Es la lista de parámetros de cada las funciones del modelo.
	 * @return Devuelve un objeto de la clase derivada de userCommandInterpreter.
	 */
	
	public userCommandInterpreter parseCommand(int event, String cadena) {
		String [] tok = cadena.split("_");
		userCommandInterpreter Command = null;
		
		switch(event) {
		case bussinessEvent.INSERTAR_USUARIO:
			Command = Command(tok[0], tok[1], tok[2], tok[3]);
		break;
		case bussinessEvent.ELIMINAR_USUARIO:
			Command = Command(tok[0]);
		break;
		case bussinessEvent.MOSTRAR_USUARIO:
			Command = Command(tok[0]);
		break;
		case bussinessEvent.MODIFICAR_USUARIO:
			Command = Command(tok[0], tok[1], tok[2]);
		break;
		}
		
				
		return Command;
	}

	//Métodos abstractos
	public abstract Object execute();
	
	public abstract userCommandInterpreter Command(String arg0);
	public abstract userCommandInterpreter Command(String arg0, String arg1);
	public abstract userCommandInterpreter Command(String arg0, String arg1, String arg2);
	public abstract userCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3);
	public abstract userCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4);
	public abstract userCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5);		
	
	public abstract int bussinessEvent();

}