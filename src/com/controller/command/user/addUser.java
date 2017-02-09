package com.controller.command.user;

import com.controller.command.bussinessEvent;
import com.model.exceptions.DuplicateUserException;
import com.util.Tools.UserPermission;

/**
 * Clase derivada de userCommandInterpreter que representa el comando addUser.
 * @author Javier
 *
 */

public class addUser extends userCommandInterpreter {

	private UserPermission permission;
	private String employeeName;
	private String username;
	private String password;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * userCommandInterpreter.
	 */
	
	public addUser() {
		super();
	}

	/**
	 * Método que inicializa los atributos de addUser.
	 * @param permission Nivel de permiso del usuario (Vendedor/Encargado/Administrado/Ninguno).
	 * @param employeeName Nombre del empleado.
	 * @param userName Nombre del usuario.
	 * @param password Contraseña del usuario.
	 */
	
	public addUser(UserPermission permission, String employeeName, String userName, String password) {
		super();
		this.permission = permission;
		this.employeeName = employeeName;
		this.username = userName;
		this.password = password;
	}

	/**
	 * Método de userCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando addUser.
	 * @return Devuelve el resultado de la ejecución del comando addUser.
	 */
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try {
			service.addUser(permission, employeeName, username, password);
		} catch (DuplicateUserException e) {
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
		return null;
	}

	@Override
	public userCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3) {
		// TODO Auto-generated method stub
		try {
			permission = ((arg0.equalsIgnoreCase("ADMIN")) ? UserPermission.ADMIN
					: ((arg0.equalsIgnoreCase("MANAGER")) ? UserPermission.MANAGER
							: ((arg0.equalsIgnoreCase("VENDOR")) ? UserPermission.VENDOR
									: UserPermission.NONE)));
			return new addUser(permission, arg1, arg2, arg3);
		} catch (NumberFormatException e) {

		}

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
	 * @return Devuelve el evento de negocio correspondiente al comando addUser.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.INSERTAR_USUARIO;
	}

}