package com.view.admin;

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
 * Interfaz gráfica del panel de administración.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class adminPanel extends JPanel implements Watcher {

	/* Controladores */
	private frameController controller;		// Controlador de la interfaz gráfica

	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	/* Menú de administración */
	private adminMenu menu;

	/* Thread que permite la concurrencia en todas las ventanas que se abran de la aplicación para
	 * mantener bien informados a todos los usuarios que utilicen la aplicación */
	private Thread concurrentThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(2000);
					controller.reloadUsers();
					controller.reloadApprovedOrders();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	});

	/* Panel de Contenido */
	private JPanel contentPanel;

	/* Tabla de Usuarios */
	private JPanel staffTableHolder;
	private DefaultTableModel staffTableModel;
	private JTable staffTable;
	private JScrollPane staffTableScrollable;

	/* Tabla de Pedidos */
	private JPanel orderTableHolder;
	private DefaultTableModel orderTableModel;
	private JTable orderTable;
	private JScrollPane orderTableScrollable;

	/**
     * Constructor de la interfaz gráfica del panel de administración.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public adminPanel(frameController cont) {
		this.controller = cont;
		
		this.controller.addWatcherToOrder(this);
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
		menu = new adminMenu(controller);

		/* Panel de Contenido */
		contentPanel = new JPanel(new GridLayout(0, 2));

		/* Tabla de Empleados */
		staffTableHolder = new JPanel(new BorderLayout());
		staffTableHolder.setBorder(new TitledBorder("Panel de empleados"));

		staffTableModel = new DefaultTableModel(new Object[] {
				"Nombre de empleado", "Nick de usuario", "Nivel de permisos" },
				0);
		staffTable = new JTable(staffTableModel) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			};
		};

		staffTableScrollable = new JScrollPane(staffTable);
		staffTableHolder.add(staffTableScrollable);

		/* Tabla de Pedidos */
		orderTableHolder = new JPanel(new BorderLayout());
		orderTableHolder.setBorder(new TitledBorder("Panel de Pedidos"));

		orderTableModel = new DefaultTableModel(new Object[] { "Pedido",
				"Proveedor", "Si/No confirmado" }, 0);
		orderTable = new JTable(orderTableModel) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			};
		};

		orderTableScrollable = new JScrollPane(orderTable);
		orderTableHolder.add(orderTableScrollable);

		// Se añaden al panel de contenidos la tabla de empleados y de pedidos.
		contentPanel.add(staffTableHolder);
		contentPanel.add(orderTableHolder);

		mainPanel.add(menu, BorderLayout.PAGE_START);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		this.add(mainPanel);

	}

	/**
	 * Método que inicializa el atributo _loggedUser, es decir, para que la aplicación sepa cual es el usuario
	 * que está logueado.
	 * @param username Usuario logueado en la aplcación.
	 */
	
	public void loggedUser(String username) {
		menu.loggedUser(username);
	}
	
	/**
	 * Método de notificación de los observadores, que son notificados por el modelo para refrescar todos
	 * aquellos componentes que necesitan ser actualizados, así como lanzar notifcaciones de éxito o error.
	 */
	
	@Override
	public void update(Watchable o, Object arg) {
		// TODO Auto-generated method stub
		String cadena = String.valueOf(arg);
		String[] contenido = cadena.split(" ");
		int PETICION = Integer.parseInt(contenido[0]);
		cadena = cadena.substring(contenido[0].length() + 1);

		Notification nt;

		switch (PETICION) {
		case bussinessEvent.ACTUALIZAR_USUARIOS:
			updateAdminTable(cadena);
			break;
		case bussinessEvent.ACTUALIZAR_PEDIDOSCONFIRMADOS:
			updateOrdersTable(cadena);
			break;
		case bussinessEvent.INSERTAR_USUARIO:
			nt = new Notification(Common.SUCCESFULL_ADDUSER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_ADDUSER:
			JOptionPane.showMessageDialog(null, Common.ADDUSER_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.ELIMINAR_USUARIO:
			nt = new Notification(Common.SUCCESFULL_DELETEUSER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_DELETEUSER:
			JOptionPane.showMessageDialog(null, Common.DELETEUSER_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.ERROR_SEARCHUSER:
			JOptionPane.showMessageDialog(null, Common.SEARCHUSER_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.MODIFICAR_USUARIO:
			nt = new Notification(Common.SUCCESFULL_MODIFYUSER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_MODIFYUSER:
			JOptionPane.showMessageDialog(null, Common.MODIFYUSER_ERRORMESSAGE
					+ System.lineSeparator() + cadena);
			break;
		case bussinessEvent.CONFIRMAR_PEDIDO:
			nt = new Notification(Common.SUCCESFULL_APPROVEORDER_MESSAGE);
			nt.start();
			break;
		case bussinessEvent.ERROR_APPROVEORDER:
			JOptionPane.showMessageDialog(null,
					Common.APPROVEORDER_ERRORMESSAGE + System.lineSeparator()
							+ cadena);
			break;
		case bussinessEvent.LOGOUTEVENT:
			menu.dispose();
			break;
		}

	}

	/**
	 * Método de actualización de la tabla de empleados.
	 * @param staffContents	Contenido de la tabla de empleados.
	 */
	
	private void updateAdminTable(String staffContents) {
		String[] contents = null;

		if (!staffContents.equals(""))
			contents = staffContents.split("_");

		staffTableModel.setRowCount(0);

		if (contents != null) {
			for (int i = 0; i < contents.length - 1; i++)
				if ((i % 4) == 0) {
					if (contents[i + 2].equalsIgnoreCase("1"))
						contents[i + 2] = "ADMININISTRADOR";
					else if (contents[i + 2].equalsIgnoreCase("2"))
						contents[i + 2] = "ENCARGADO";
					else if (contents[i + 2].equalsIgnoreCase("3"))
						contents[i + 2] = "VENDEDOR";
					else
						contents[i + 2] = "NONE";

					staffTableModel.addRow(new Object[] { contents[i],
							contents[i + 1], contents[i + 2] });
				}
		}

	}

	/**
	 * Método de actualización de la tabla de pedidos.
	 * @param orderContents Contenido de la tabla de pedidos confirmados.
	 */
	
	private void updateOrdersTable(String orderContents) {
		String[] contents = null;

		if (!orderContents.equals(""))
			contents = orderContents.split("_");

		orderTableModel.setRowCount(0);

		if (contents != null) {
			for (int i = 0; i < contents.length - 1; i++)
				if ((i % 5) == 0) {
					String finalizado = (contents[i + 3].equalsIgnoreCase("true")) ? "Finalizado" : "No finalizado";
					if(finalizado.equalsIgnoreCase("Finalizado")) {
						orderTableModel
								.addRow(new Object[] {
										contents[i],
										contents[i + 1].equalsIgnoreCase("null") ? "Sin determinar" : contents[i + 1],
										(contents[i + 4].equalsIgnoreCase("true")) ? "Confirmado"
												: "No confirmado" });
					}
				}
		}
	}

}