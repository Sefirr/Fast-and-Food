package com.util;

import java.awt.Dimension;
import java.util.ArrayList;

import com.model.dao.DaoFactory;
import com.model.dao.DaoOrder;
import com.model.dao.DaoSale;
import com.model.dao.DaoStock;
import com.model.dao.DaoUser;
import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;
import com.model.dto.TransferSale;
import com.model.dto.TransferStock;
import com.model.dto.TransferUser;
import com.model.impl.dto.TransferStockImp;

/**
 * Clase encargada de las herramientas que se utilizan en la aplicación.
 * @author JAVIER
 *
 */
public class Tools {

	// Número de pedidos, ventas, usuarios y usuarios logueados
	public static int NUMORDERS;
	public static int NUMSALES;
	public static int NUMUSERS;
	public static int NUMLOGGEDUSERS;
	public static int NUMPRODUCTS;

	
	// Enumerado que sirve para designar las distintas formas de clasificar el inventario
	public enum SortMethod {
		NOMBRE, CANTIDAD, TAG, PRECIO
	}

	// Enumerado que sirve para designar los distintos permisos de usuario que hay en la aplicación
	public enum UserPermission {
		NONE, ADMIN, MANAGER, VENDOR
	}

	/**
	 * Función que sirve para averiguar si hay stock disponible o no en el almacén.
	 * @param arg0 Cantidad disponible en el almacen.
	 * @param arg1 Cantidad a quitar del almacén.
	 * @return true si hay stock disponible en el almacén.
	 */
	public static boolean availableProduct(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return (arg0 >= arg1);
	}

	/**
	 * Función que comprueba si es entero el número que se le pasa por argumento como String.
	 * @param line El número que se va a parsear.
	 * @return true si el argumento es entero.
	 */
	public static boolean isInteger(String line) {
		try {
			Integer.parseInt(line);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Función que comprueba si es double el número que se le pasa por argumento como String.
	 * @param line El número que se va a parsear.
	 * @return true si el argumento es double.
	 */
	public static boolean isDouble(String line) {
		try {
			Double.parseDouble(line);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Función que hace tantos simbolos como se puedan hacer resultado de la resta de num-regex.lenght().
	 * @param content Cadena a la que se le quieren añadir símbolos.
	 * @param num Número de símbolos que se quieren añadir.
	 * @param symbol Símbolo que se quiere añadir.
	 * @return La cadena con el número de símbolos a poner.
	 */
	public static String makeSymbol(String content, int num, String symbol) {

		String[] regex = content.split("");

		num = num - regex.length;

		String contents = "";

		for (int i = 0; i <= num; i++) {
			contents += symbol;
		}

		return contents;
	}
	
	/**
	 * Función que testea el subsistema de usuarios.
	 */

	// Test de usuarios
	public static void userTest() {

		DaoFactory factoria = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoUser userDAO = factoria.getDAOUser();

		ArrayList<TransferUser> userList = new ArrayList<TransferUser>();
		userList = userDAO.readUsers();

		String line = "SUBSISTEMA DE USUARIOS" + System.lineSeparator()
				+ System.lineSeparator();
		for (TransferUser o : userList) {
			line += o.getEmployeeName() + System.lineSeparator()
					+ o.getUserName() + System.lineSeparator()
					+ o.getPassword() + System.lineSeparator() + "--"
					+ System.lineSeparator();
			line += System.lineSeparator();

		}
		System.out.println(line + System.lineSeparator()
				+ "Número de usuarios: " + NUMUSERS);
	}
	
	/**
	 * Función que testea el subsistema de ventas.
	 */

	// Test de ventas
	public static void saleTest() {

		DaoFactory factoria = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factoria.getDAOSale();

		ArrayList<TransferSale> saleList = new ArrayList<TransferSale>();
		saleList = saleDAO.readSales();

		String line = "SUBSISTEMA DE VENTAS" + System.lineSeparator()
				+ System.lineSeparator();
		for (TransferSale o : saleList) {
			line += o.getSaleIdentifier() + System.lineSeparator()
					+ o.getCustomer() + System.lineSeparator() + "--"
					+ System.lineSeparator();
			for (TransferProduct p : o.getProductList()) {
				line += p.getName() + " Cantidad:" + p.getAmount()
						+ System.lineSeparator();
			}
			line += "--" + System.lineSeparator();
			if (o.getFinished())
				line += "true";
			else
				line += "false";
			line += System.lineSeparator();

			line += System.lineSeparator() + o.getPrice()
					+ System.lineSeparator() + System.lineSeparator();

		}
		System.out.println(line + System.lineSeparator() + "Número de ventas: "
				+ NUMSALES);
	}
	
	/**
	 * Función que testea el subsistema de pedidos.
	 */
	
	// Test de pedidos
	public static void orderTest() {

		DaoFactory factoria = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factoria.getDAOOrder();

		ArrayList<TransferOrder> orderList = new ArrayList<TransferOrder>();
		orderList = orderDAO.readOrders();

		String line = "SUBSISTEMA DE PEDIDOS" + System.lineSeparator()
				+ System.lineSeparator();
		for (TransferOrder o : orderList) {
			line += o.getOrderIdentifier() + System.lineSeparator()
					+ o.getSupplier() + System.lineSeparator() + "--"
					+ System.lineSeparator();
			for (TransferProduct p : o.getProductList()) {
				line += p.getName() + " Cantidad:" + p.getAmount()
						+ System.lineSeparator();
			}
			line += "--" + System.lineSeparator();
			if (o.getFinished())
				line += "true";
			else
				line += "false";
			line += System.lineSeparator();
			if (o.getApproved())
				line += "true";
			else
				line += "false";
			line += System.lineSeparator() + o.getDate()
					+ System.lineSeparator() + System.lineSeparator();

		}
		System.out.println(line + System.lineSeparator()
				+ "Número de pedidos: " + NUMORDERS);
	}
	
	/**
	 * Función que testea el subsistema de almacén.
	 */

	// Test de almacén
	public static void stockTest() {

		DaoFactory factoria = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoStock stockDAO = factoria.getDAOStock();

		TransferStock stock = new TransferStockImp();
		stock = stockDAO.readStock();
		ArrayList<TransferProduct> productList = new ArrayList<TransferProduct>();
		productList = stock.getProductList();

		String line = "SUBSISTEMA DE ALMACÉN" + System.lineSeparator()
				+ System.lineSeparator();
		for (TransferProduct p : productList) {
			line += p.getId() + System.lineSeparator() + p.getName()
					+ System.lineSeparator() + p.getPrice()
					+ System.lineSeparator() + p.getAmount()
					+ System.lineSeparator() + "--" + System.lineSeparator();
		}

		System.out.println(line + System.lineSeparator()
				+ "Número de productos: " + NUMPRODUCTS);
	}

	// Dimensiones principales de los elementos de Swing
	public static Dimension SCREEN_DIMENSION = new Dimension(getScreenWorkingWidth() - 100, getScreenWorkingHeight() - 35);
	public static Dimension ACTION_DIMENSION = new Dimension(600, 300);
	public static Dimension ACTION_DIMENSION_MIN = new Dimension(600, 125);
	public static Dimension WINDOW_DIMENSION = new Dimension(800, 600);
	public static Dimension MENU_DIMENSION = new Dimension(getScreenWorkingWidth() - 100, 60);
	public static Dimension TEXTFIELDSIZE = new Dimension(160, 20);

	/**
	 * Función que devuelve la máxima dimensión que se puede mostrar por pantalla de largo.
	 * @return
	 */
	
	private static int getScreenWorkingWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	/**
	 * Función que devuelve la máxima dimensión que se puede mostrar por pantalla de ancho.
	 * @return
	 */
	private static int getScreenWorkingHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
