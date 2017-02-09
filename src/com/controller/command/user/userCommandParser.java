package com.controller.command.user;

/**
 * Una clase para parsear la cadena correspondiente al comando que quiere ejecutar el usuario. 
 * @author Javier
 */

public class userCommandParser {
	
	private static userCommandInterpreter[] command = {
		new addUser(), new removeUser(), new modifyUser(), new searchUser(),
	};

	/**
	 * Recorre el conjunto de posibles comandos. Si la instancia del comando
	 * que se va a ejecutar corresponde con alguno de ellos.
	 * @param event Evento de negocio correspondiente al comando.
	 * @param s Es la de parámetros correspondientes al comando en cuestión.
	 * @return Devuelve una clase derivada de userCommandInterpreter.
	 */
	
	public static userCommandInterpreter parseCommand(int event, String cadena) {
		for(userCommandInterpreter op : command) {
			userCommandInterpreter command = op.parseCommand(event, cadena);
			if ((command != null) && command.bussinessEvent() == event){
				return command;
			}
		}
		return null;
	}

}
