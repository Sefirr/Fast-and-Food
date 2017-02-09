package com.controller.command.order;

/**
 * Una clase para parsear la cadena correspondiente al comando que quiere ejecutar el usuario. 
 * @author Javier
 */

public class orderCommandParser {
	
	private static orderCommandInterpreter[] command = {
		new addOrder(), new searchOrder(), new addProduct(), new removeProduct(), new addProductModify(), 
		new removeProductModify(), new approveOrder(), new deleteOrder(), new finishOrder(), new cancelOrder(), 
		new modifyOrder(),	
	};

	/**
	 * Recorre el conjunto de posibles comandos. Si la instancia del comando
	 * que se va a ejecutar corresponde con alguno de ellos.
	 * @param event Evento de negocio correspondiente al comando.
	 * @param s Es la de parámetros correspondientes al comando en cuestión.
	 * @return Devuelve una clase derivada de orderCommandInterpreter.
	 */
	
	public static orderCommandInterpreter parseCommand(int event, String cadena) {
		for(orderCommandInterpreter op : command) {
			orderCommandInterpreter command = op.parseCommand(event, cadena);
			if ((command != null) && command.bussinessEvent() == event){
				return command;
			}
		}
		return null;
	}

}
