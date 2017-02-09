package com.controller.command.order;

import java.util.ArrayList;

import com.controller.command.bussinessEvent;
import com.model.dto.TransferProduct;
import com.model.exceptions.OrderNotFoundException;
import com.model.impl.dto.TransferProductImp;

/**
 * Clase derivada de orderCommandInterpreter que representa el comando modifyOrder.
 * @author Javier
 *
 */

public class modifyOrder extends orderCommandInterpreter {

	private int orderIdentifier;
	private String supplier;
	private ArrayList<TransferProduct> productList;
	private boolean finished;
	private boolean approved;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * orderCommandInterpreter.
	 */
	
	public modifyOrder() {
		super();
	}
	
	/**
	 * Método constructor que inicializa los atributos de modifyOrder.
	 * @param orderIdentiffier Identificador del pedido que se va a modificar.
	 * @param supplier Proveedor del pedido.
	 * @param productList Lista de productos del pedido.
	 * @param finished Indica si está finalizado o no el pedido.
	 * @param approved Indica si está confirmado o no el pedido.
	 */

	public modifyOrder(int orderIdentiffier, String supplier, ArrayList<TransferProduct> productList, boolean finished, boolean approved) {
		super();
		this.orderIdentifier = orderIdentiffier;
		this.supplier = supplier;
		this.productList = productList;
		this.finished = finished;
		this.approved = approved;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando modifyOrder.
	 * @return Devuelve el resultado de la ejecución del comando modifyOrder.
	 */

	@Override
	public Object execute() {
		try {
			service.modifyOrder(orderIdentifier, supplier, productList, finished, approved);
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block

		}

		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		try {
			int orderIdentiffier = Integer.parseInt(arg0);
			ArrayList<TransferProduct> productList = new ArrayList<TransferProduct>();

			String[] info = arg2.split("\n");
			for (int i = 0; i < info.length - 1; i++) {
				if (i % 5 == 0)
					productList.add(new TransferProductImp(Integer
							.parseInt(info[i]), info[i + 1], info[i + 2],
							Double.valueOf(info[i + 3]), Integer
									.parseInt(info[i + 4])));
			}
			boolean finalizado;
			if(arg3.equalsIgnoreCase("1")) {
				finalizado = true;
			} else {
				finalizado = false;
			}
				
			return new modifyOrder(orderIdentiffier, arg1, productList,
					finalizado, Boolean.valueOf(arg4));
		} catch (NumberFormatException e) {

		}

		return null;

	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando modifyOrder.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.MODIFICAR_PEDIDO;
	}

}