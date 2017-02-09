package com.controller.command.user;

import com.controller.command.bussinessEvent;
import com.model.exceptions.UserNotFoundException;

/**
 * Clase derivada de userCommandInterpreter que representa el comando searchUser.
 * @author Javier
 *
 */

public class searchUser extends userCommandInterpreter {
	
	private String username;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * userCommandInterpreter.
	 */
	
	public searchUser() {
		super();
	}

	/**
	 * Método que inicializa los atributos de searchUser.
	 * @param userName Nombre del usuario.
	 */
	
	public searchUser(String userName) {
		super();
		this.username = userName;
	}

	/**
	 * Método de userCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando searchUser.
	 * @return Devuelve el resultado de la ejecución del comando searchUser.
	 */
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try {
			return service.searchUser(username);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
		
	}

	@Override
	public userCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		try {
			return new searchUser(arg0);
		} catch(NumberFormatException e) {
			
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
	public userCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método de CommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando searchUser.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.MOSTRAR_USUARIO;
	}

}
