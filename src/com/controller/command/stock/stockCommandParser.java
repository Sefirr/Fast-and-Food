package com.controller.command.stock;

/**
 * Una clase para parsear la cadena correspondiente al comando que quiere ejecutar el usuario. 
 * @author Javier
 */

public class stockCommandParser {
	
	private static stockCommandInterpreter[] command = {
		new addProduct(), new removeProduct(), new searchProduct(), new sortStock(),
	};

	/**
	 * Recorre el conjunto de posibles comandos. Si la instancia del comando
	 * que se va a ejecutar corresponde con alguno de ellos.
	 * @param event Evento de negocio correspondiente al comando.
	 * @param s Es la de parámetros correspondientes al comando en cuestión.
	 * @return Devuelve una clase derivada de stockCommandInterpreter.
	 */
	
	public static stockCommandInterpreter parseCommand(int event, String cadena) {
		for(stockCommandInterpreter op : command) {
			stockCommandInterpreter command = op.parseCommand(event, cadena);
			if ((command != null) && command.bussinessEvent() == event){
				return command;
			}
		}
		return null;
	}

}