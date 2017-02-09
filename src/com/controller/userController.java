package com.controller;

import com.controller.command.user.userCommandInterpreter;
import com.controller.command.user.userCommandParser;
import com.model.userService;

/**
 * Controlador del subsistema de usuarios.
 * Es el especialista en creación de comandos en los referente a usuarios.
 * 
 * @author JAVIER
 */

public class userController {

	private userService model; // Modelo del subsistema de usuarios
	
	/**
	 * Constructor de un parámetro del controlador del subsistema de usuarios.
	 * @param order Modelo del subsistema de usuarios.
	 */
	
	public userController(userService staff) {
		model = staff;
		userCommandInterpreter.configureCommandInterpreter(model);
		
	}
	
	/**
	 * Método encargado de generar un comando u otro en función del evento y del argumento que se pasan como parámetros.
	 * @param evento Evento del negocio que se quiere ejecutar.
	 * @param arg Parámetros del evento del negocio que se pasan como comando.
	 * @return Lo que devuelve el comando al ser ejecutado.
	 */
	
	public Object actionUser(int evento, Object arg) {

		userCommandInterpreter command = userCommandParser.parseCommand(evento, (String) arg);
		
		return command.execute();

	}
		
}