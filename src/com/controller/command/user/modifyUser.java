package com.controller.command.user;

import com.controller.command.bussinessEvent;
import com.model.exceptions.UserNotFoundException;
import com.util.Tools.UserPermission;

/**
 * Clase derivada de userCommandInterpreter que representa el comando modifyUser.
 * @author Javier
 *
 */

public class modifyUser extends userCommandInterpreter {

	private String username;
	private String password;
	private UserPermission permission;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * userCommandInterpreter.
	 */
	
	public modifyUser() {
		super();
	}

	/**
	 * Método que inicializa los atributos de modifyUser.
	 * @param userName Nombre del usuario.
	 * @param password Contraseña del usuario.
	 *  * @param permission Nivel de permiso del usuario (Vendedor/Encargado/Administrado/Ninguno).
	 */
	
	public modifyUser(String userName, String password, UserPermission permission) {
		super();
		this.username = userName;
		this.password = password;
		this.permission = permission;
	}

	/**
	 * Método de userCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando modifyUser.
	 * @return Devuelve el resultado de la ejecución del comando modifyUser.
	 */
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try {
			service.modifyUser(username, password, permission);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block

		}

		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
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
		try {
			permission = ((arg2.equalsIgnoreCase("ADMIN")) ? UserPermission.ADMIN
					: ((arg2.equalsIgnoreCase("MANAGER")) ? UserPermission.MANAGER
							: ((arg2.equalsIgnoreCase("VENDOR")) ? UserPermission.VENDOR
									: UserPermission.NONE)));
			return new modifyUser(arg0, arg1, permission);
		} catch (NumberFormatException e) {

		}

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
	 * @return Devuelve el evento de negocio correspondiente al comando modifyUser.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.MODIFICAR_USUARIO;
	}

}