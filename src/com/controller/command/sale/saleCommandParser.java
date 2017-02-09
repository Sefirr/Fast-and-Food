package com.controller.command.sale;

/**
 * Una clase para parsear la cadena correspondiente al comando que quiere ejecutar el usuario. 
 * @author Javier
 */

public class saleCommandParser {
	
	private static saleCommandInterpreter[] command = {
		new addSale(), new addProduct(), new removeProduct(), new searchProduct(), new cancelSale(), 
		new deleteSale(), new finishSale(), new generateInvoicing(), new searchSale(), new showBalance(),
		new showExchange(), new showProfits(),	
	};
	
	/**
	 * Recorre el conjunto de posibles comandos. Si la instancia del comando
	 * que se va a ejecutar corresponde con alguno de ellos.
	 * @param event Evento de negocio correspondiente al comando.
	 * @param s Es la de parámetros correspondientes al comando en cuestión.
	 * @return Devuelve una clase derivada de saleCommandInterpreter.
	 */
	
	public static saleCommandInterpreter parseCommand(int event, String cadena) {
		for(saleCommandInterpreter op : command) {
			saleCommandInterpreter command = op.parseCommand(event, cadena);
			if ((command != null) && command.bussinessEvent() == event){
				return command;
			}
		}
		return null;
	}

}
