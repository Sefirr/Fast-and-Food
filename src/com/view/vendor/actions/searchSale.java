package com.view.vendor.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.controller.frameController;
import com.controller.saleController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferSale;
import com.util.Common;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

/**
 * Interfaz gráfica para la búsqueda de ventas.
 * @author Javier
 *
 */

@SuppressWarnings("serial")
public class searchSale extends JFrame implements Watcher {

	/* Controladores */
	private saleController saleController;		// Controlador del subsistema de ventas

	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	/* Panel de contenido */
	private JPanel contentPanel;

	/* Panel de búsqueda de ventas */
	private JPanel findPanel;
	
	private JLabel idLabel;

	private JTextField idTextField;

	/* Panel de acciones */
	private JPanel actionPanel;
	
	private JButton findButton;

	/* Panel de lista de productos de la venta */
	private JPanel saleProductListPanel;
	private DefaultTableModel saleProductsTableModel;
	private JTable saleProductsTable;
	private JScrollPane saleProductListScrollable;

	/* Panel del cliente */
	private JPanel customerPanel;
	private JLabel customerNameLabel;
	private JTextField customerTextField;

	/**
     * Constructor de la interfaz gráfica para búsqueda de ventas.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public searchSale(frameController cont) {
		super("Buscar venta");
		this.saleController = (saleController) cont.getController(1);
		
		cont.addWatcherToSale(this);

		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(Tools.SCREEN_DIMENSION);

		/* Panel de contenido */
		contentPanel = new JPanel(new GridLayout(2, 0));

		/* Panel de búsqueda de ventas */
		findPanel = new JPanel();
		
		idLabel = new JLabel("ID de la venta: ");
		
		idTextField = new JTextField();
		idTextField.setPreferredSize(Tools.TEXTFIELDSIZE);

		/* Panel de acciones */
		actionPanel = new JPanel();

		findButton = new JButton("Buscar");

		findButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Connect to controller
				try {
					int id = Integer.parseInt(idTextField.getText());
					TransferSale currentSale;

					if (saleController.actionSale(bussinessEvent.MOSTRAR_VENTA,
							id + "_") != null) {
						currentSale = (TransferSale) saleController.actionSale(
								bussinessEvent.MOSTRAR_VENTA, id + "_");
						customerTextField.setText(currentSale.getCustomer());
					}
				} catch (NumberFormatException e) {
					JOptionPane
							.showMessageDialog(
									null,
									Common.SEARCHSALE_ERRORMESSAGE
											+ System.lineSeparator()
											+ "Los datos que has introducido no son correctos.");
				}

			}

		});

		// Se añaden los botones al panel de acciones
		actionPanel.add(findButton);

		findPanel.add(idLabel);
		findPanel.add(idTextField);
		findPanel.add(actionPanel);

		/* Panel del cliente */
		customerPanel = new JPanel();
		
		customerNameLabel = new JLabel("Nombre del cliente: ");
		
		customerTextField = new JTextField();
		customerTextField.setPreferredSize(Tools.TEXTFIELDSIZE);
		customerTextField.setEditable(false);
		
		customerPanel.add(customerNameLabel);
		customerPanel.add(customerTextField);

		contentPanel.add(findPanel);
		contentPanel.add(customerPanel);

		/* Tabla de productos de la venta */
		saleProductListPanel = new JPanel();

		saleProductsTableModel = new DefaultTableModel(new Object[] {
				"Producto", "Cantidad" }, 0);
		saleProductsTable = new JTable(saleProductsTableModel) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			};
		};
		saleProductsTable.setEnabled(true);
		saleProductsTable.setRowSelectionAllowed(true);

		saleProductListScrollable = new JScrollPane(saleProductsTable);
		saleProductListPanel.add(saleProductListScrollable);

		mainPanel.add(contentPanel, BorderLayout.PAGE_START);
		mainPanel.add(saleProductListPanel, BorderLayout.CENTER);
		this.add(mainPanel, BorderLayout.CENTER);

		this.setLocation(50, 100);
		this.setSize(Tools.WINDOW_DIMENSION);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

		switch (PETICION) {
		case bussinessEvent.MOSTRAR_VENTA:
			updateSaleTable(cadena);
			break;
		}

	}

	/**
	 * Método de actualización de la tabla de ventas.
	 * @param saleContents Contenido de la tabla de ventas.
	 */
	
	private void updateSaleTable(String saleContents) {
		String[] contents = null;

		if (!saleContents.equals(""))
			contents = saleContents.split("_");

		saleProductsTableModel.setRowCount(0);

		if (contents != null) {
			for (int i = 0; i < contents.length - 1; i++)
				if ((i % 3) == 0) {
					saleProductsTableModel.addRow(new Object[] { contents[i], contents[i+2] });
				}
		}
	}

	/**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */
	
	public void clear() {
		idTextField.setText("");
		customerTextField.setText("");
		saleProductsTableModel.setRowCount(0);
	}

}