package com.controller.command.user;

import com.controller.command.bussinessEvent;
import com.model.exceptions.UserNotFoundException;

/**
 * Clase derivada de userCommandInterpreter que representa el comando removeUser.
 * @author Javier
 *
 */

public class removeUser extends userCommandInterpreter {

	private String username;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * userCommandInterpreter.
	 */
	
	public removeUser() {
		super();
	}

	/**
	 * Método que inicializa los atributos de removeUser.
	 * @param userName Nombre del usuario.
	 */
	
	public removeUser(String username) {
		super();
		this.username = username;
	}

	/**
	 * Método de userCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando removeUser.
	 * @return Devuelve el resultado de la ejecución del comando removeUser.
	 */
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try {
			service.removeUser(username);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block

		}

		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		try {
			return new removeUser(arg0);
		} catch (NumberFormatException e) {

		}

		return null;

	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método de CommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando removeUser.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.ELIMINAR_USUARIO;
	}

}