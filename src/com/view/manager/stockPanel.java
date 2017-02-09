package com.view.manager;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.controller.frameController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Notification;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

/**
 * Interfaz gráfica del panel del almacén.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class stockPanel extends JPanel implements Watcher {

	/* Controladores */
	private frameController controller;		// Controlador de la interfaz gráfica

	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	/* Menús de administración de stock */
	stockHeaderBar headerBar;
	stockFooterBar footerBar;

	/* Thread que permite la concurrencia en todas las ventanas que se abran de la aplicación para
	 * mantener bien informados a todos los usuarios que utilicen la aplicación */
	private Thread concurrentThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(2000);
					controller.reloadStock();
					controller.reloadOrders();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	});

	/* Panel de Contenido */
	private JPanel contentPanel;

	/* Tabla de Productos */
	private JPanel stockTableHolder;
	private DefaultTableModel stockTableModel;
	private JTable stockTable;
	private JScrollPane stockScrollable;

	/* Tabla de Pedidos */
	private JPanel orderTableHolder;
	private DefaultTableModel orderListTableModel;
	private JTable orderListTable;
	private JScrollPane orderListScrollable;

	/**
     * Constructor de la interfaz gráfica del panel de administración.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public stockPanel(frameController cont) {
		this.controller = cont;
		
		this.controller.addWatcherToOrder(this);
		this.controller.addWatcherToStock(this);
		this.controller.addWatcherToUser(this);

		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Comienza la ejecución del hilo de concurrrencia */
		concurrentThread.start();

		/*Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(Tools.SCREEN_DIMENSION);

		/* Aqui se inicializan todos aquellos atributos que necesitan ser 
		 * inicializados y que no son controladores. 
		 * */
		headerBar = new stockHeaderBar(controller);
		footerBar = new stockFooterBar(controller);

		/* Panel de Contenido */
		contentPanel = new JPanel(new GridLayout(0, 2));

		/* Tabla de Productos */
		stockTableHolder = new JPanel(new BorderLayout());
		stockTableHolder.setBorder(new TitledBorder("Almacén"));

		stockTableModel = new DefaultTableModel(new Object[] { "ID",
				"Producto", "Etiqueta", "Cantidad", "Precio" }, 0);
		stockTable = new JTable(stockTableModel) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			};
		};

		stockScrollable = new JScrollPane(stockTable);
		stockTableHolder.add(stockScrollable);

		/* Tabla de Pedidos */
		orderTableHolder = new JPanel(new BorderLayout());
		orderTableHolder.setBorder(new TitledBorder("Pedidos"));

		orderListTableModel = new DefaultTableModel(new Object[] { "ID",
				"Proveedor", "Fecha", "Estado", "Confirmado" }, 0);
		orderListTable = new JTable(orderListTableModel) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			};
		};

		orderListScrollable = new JScrollPane(orderListTable);
		orderTableHolder.add(orderListScrollable);

		// Se añaden al panel de contenido la tabla del almacén y la de pedidos.
		contentPanel.add(stockTableHolder);
		contentPanel.add(orderTableHolder);

		mainPanel.add(headerBar, BorderLayout.PAGE_START);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(footerBar, BorderLayout.PAGE_END);
		this.add(mainPanel);
	}

	/**
	 * Método que inicializa el atributo _loggedUser, es decir, para que la aplicación sepa cual es el usuario
	 * que está logueado.
	 * @param username Usuario logueado en la aplcación.
	 */
	
	public void loggedUser(String username) {
		headerBar.loggedUser(username);
	}

	/**
	 * Método de notificación de los observadores, que son notificados por el modelo para refrescar todos
	 * aquellos componentes que necesitan ser actualizados, así como lanzar notifcaciones de éxito o error.
	 */
	
	@Override
	public void update(Watchable o, Object arg) {
		String cadena = String.valueOf(arg);
		String[] contenido = cadena.split(" ");
		int PETICION = Integer.parseInt(contenido[0]);
		cadena = cadena.substring(contenido[0].length() + 1);

		Notification nt;

		switch (PETICION) {
		case bussinessEvent.ACTUALIZAR_STOCK:
			updateStockTable(cadena);
			break;
		case bussinessEvent.ACTUALIZAR_PEDIDOS:
			updateOrdersTable(cadena);
			break;
		case bussinessEvent.AÑADIR_PEDIDO:
			nt = new Notification(Common.SUCCESFULL_ADDORDER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ELIMINAR_PEDIDO:
			nt = new Notification(Common.SUCCESFULL_DELETEORDER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_DELETEORDER:
			JOptionPane.showMessageDialog(null, Common.DELETEORDER_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.FINALIZAR_PEDIDO:
			nt = new Notification(Common.SUCCESFULL_FINISHORDER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.MODIFICAR_PEDIDO:
			nt = new Notification(Common.SUCCESFULL_MODIFYORDER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_MODIFYORDER:
			JOptionPane.showMessageDialog(null, Common.MODIFYORDER_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.CLASIFICAR_INVENTARIO:
			nt = new Notification(Common.SUCCESFULL_SORTPRODUCTS_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.AÑADIR_PRODUCTO_STOCK:
			nt = new Notification(Common.SUCCESFULL_ADDPRODUCT_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.AÑADIR_PRODUCTO_PEDIDO:
			nt = new Notification(Common.SUCCESFULL_ADDPRODUCT_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_ADDPRODUCT:
			JOptionPane.showMessageDialog(null, Common.ADDPRODUCT_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.ELIMINAR_PRODUCTO_STOCK:
			nt = new Notification(Common.SUCCESFULL_REMOVEPRODUCT_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ELIMINAR_PRODUCTO_PEDIDO:
			nt = new Notification(Common.SUCCESFULL_REMOVEPRODUCT_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_REMOVEPRODUCT:
			JOptionPane.showMessageDialog(null,
					Common.REMOVEPRODUCT_ERRORMESSAGE + System.lineSeparator()
							+ cadena);
			break;
		case bussinessEvent.ERROR_SEARCHPRODUCT:
			JOptionPane.showMessageDialog(null,
					Common.SEARCHPRODUCT_ERRORMESSAGE + System.lineSeparator()
							+ cadena);
			break;
		case bussinessEvent.ERROR_SEARCHORDER:
			JOptionPane.showMessageDialog(null, Common.SEARCHORDER_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.LOGOUTEVENT:
			footerBar.dispose();
			headerBar.dispose();
			break;
		}

	}
	
	/**
	 * Método de actualización de la tabla del almacén.
	 * @param stockContents	Contenido de la tabla del almacén.
	 */

	private void updateStockTable(String stockContents) {
		String[] contents = null;

		if (!stockContents.equals(""))
			contents = stockContents.split("_");

		stockTableModel.setRowCount(0);
		
		if (contents != null) {	
			for (int i = 0; i < contents.length - 1; i++)
				if ((i % 5) == 0)
					stockTableModel
							.addRow(new Object[] { contents[i],
									contents[i + 1], contents[i + 2],
									contents[i + 3], contents[i + 4] });
		}

	}
	
	/**
	 * Método de actualización de la tabla de pedidos.
	 * @param orderContents Contenido de la tabla de pedidos.
	 */

	private void updateOrdersTable(String orderContents) {
		String[] contents = null;

		if (!orderContents.equals(""))
			contents = orderContents.split("_");

		orderListTableModel.setRowCount(0);

		if (contents != null) {
			for (int i = 0; i < contents.length - 1; i++)
				if ((i % 5) == 0)
					orderListTableModel
							.addRow(new Object[] {
									contents[i],
									contents[i + 1].equalsIgnoreCase("null") ? "Sin determinar" : contents[i + 1],
									contents[i + 2].equalsIgnoreCase("null") ? "Sin determinar" : contents[i + 2],
									(contents[i + 3].equalsIgnoreCase("true")) ? "Finalizado"
											: "No finalizado",
									(contents[i + 4].equalsIgnoreCase("true")) ? "Confirmado"
											: "No confirmado" });
		}

	}

}