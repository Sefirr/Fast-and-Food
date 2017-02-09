package com.view.manager.actions;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.controller.frameController;
import com.controller.orderController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferOrder;
import com.util.Common;
import com.util.Tools;

/**
 * Interfaz gráfica para la búsqueda y modificación de pedidos.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class editAndSearchOrder extends JFrame {

	// Controladores
	private frameController frameController;		// Controlador de interfaz gráfica
	private orderController orderController;		// Controlador del subsistema de pedidos

	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	/* Panel de contenido */
	private JPanel contentPanel;
	
	/* Panel de búsqueda de pedidos */
	private JPanel searchOrderPanel;

	private JLabel searchOrderLabel;

	private JTextField searchOrderField;

	/* Boton de búsqueda de pedidos por identificador */
	private JButton searchOrderButton;

	/* Panel de Acciones */
	private JPanel actionPanelHeader;

	private JButton addProductButton;
	private JButton removeProductButton;
	private JButton modifyOrderButton;

	/* Panel de lista de productos del pedido */
	private JPanel orderProductListPanel;
	private DefaultTableModel orderProductsTableModel;
	private JTable orderProductsTable;
	private JScrollPane orderProductListScrollable;
	
	 /* Panel de acciones del pie*/
    private JPanel actionPanelFooter;
    
    private JPanel stateOrderPanel;
    private JLabel stateOrderLabel;
    private Choice stateOrderChoice;

	/* Panel del cliente */
	private JPanel supplierPanel;
	private JLabel supplierNameLabel;
	private JTextField supplierTextField;
	
	/* Thread que permite la concurrencia en todas las ventanas que se abran de la aplicación para
	 * mantener bien informados a todos los usuarios que utilicen la aplicación */
	private Thread concurrentThread;
	private int orderIdentiffier;
	
	/**
     * Constructor de la interfaz gráfica para la búsqueda y modificación de pedidos.
     * @param cont 	Controlador de la interfaz gráfica.
     */

	public editAndSearchOrder(frameController cont) {
		super("Buscar y editar pedido");
		this.frameController = cont;
		this.orderController = (orderController) cont.getController(0);
		
		//this.frameController.addWatcherToOrder(this);

		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Comienza la ejecución del hilo de concurrrencia */
		
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());

		/* Panel de contenido */
		contentPanel = new JPanel(new GridLayout(2,0));

		/* Panel de búsqueda de pedido */
		searchOrderPanel = new JPanel();
		searchOrderLabel = new JLabel("Introduzca la ID del pedido ");
		searchOrderField = new JTextField();
		searchOrderField.setPreferredSize(Tools.TEXTFIELDSIZE);

		searchOrderButton = new JButton("Buscar");

		searchOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (orderController.actionOrder(
							bussinessEvent.MOSTRAR_PEDIDO,
							Integer.parseInt(searchOrderField.getText()) + "_") != null) {
						TransferOrder currentOrder = (TransferOrder) orderController
								.actionOrder(
										bussinessEvent.MOSTRAR_PEDIDO,
										Integer.parseInt(searchOrderField
												.getText()) + "_");
						supplierTextField.setText(currentOrder.getSupplier());
						addProductButton.setEnabled(true);
						removeProductButton.setEnabled(true);
						modifyOrderButton.setEnabled(true);
						orderIdentiffier = currentOrder.getOrderIdentifier();
						concurrentThread = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								while(true) {
									try {
											Thread.sleep(500);
											TransferOrder currentOrder = (TransferOrder) orderController
													.actionOrder(
															bussinessEvent.MOSTRAR_PEDIDO,
															Integer.parseInt(searchOrderField
																	.getText()) + "_");
											if(orderIdentiffier == currentOrder.getOrderIdentifier())  {
												updateOrderTable(currentOrder.displayContents());
												if(currentOrder.getFinished())
													stateOrderChoice.select("FINALIZADO");
												else {
													stateOrderChoice.select("NO FINALIZADO");
												}
											}
									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}});
						concurrentThread.start();
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, Common.SEARCHORDER_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
				}
			}
		});

		searchOrderPanel.add(searchOrderLabel);
		searchOrderPanel.add(searchOrderField);
		searchOrderPanel.add(searchOrderButton);

		/* Panel de acciones */
		actionPanelHeader = new JPanel();
		actionPanelHeader.setBorder(new TitledBorder("Acciones de la venta"));

		addProductButton = new JButton("Agregar producto");
		removeProductButton = new JButton("Eliminar producto");
		modifyOrderButton = new JButton("Modificar pedido");

		addProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductToOrderModify addProduct = new addProductToOrderModify(frameController);
				addProduct.currentOrder(Integer.parseInt(searchOrderField.getText()));
				
				addProduct.setVisible(true);
			}
		});

		removeProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeProductToOrderModify removeProduct = new removeProductToOrderModify(frameController);
				removeProduct.currentOrder(Integer.parseInt(searchOrderField.getText()));
				
				removeProduct.setVisible(true);
			}
		});

		modifyOrderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TransferOrder currentOrder = (TransferOrder) orderController.actionOrder(bussinessEvent.MOSTRAR_PEDIDO, Integer.parseInt(searchOrderField.getText()) + "_");
				orderController.actionOrder(bussinessEvent.MODIFICAR_PEDIDO, Integer.parseInt(searchOrderField.getText()) + "_" + supplierTextField.getText() + "_" + currentOrder.displayContentsMVC() + "_" + stateOrderChoice.getSelectedIndex() + "_" + currentOrder.getApproved() + "_");
				stateOrderChoice.select(0);
				setVisible(false);
				dispose();
			}

		});

		// Se añaden los botones al panel de acciones
		actionPanelHeader.add(addProductButton);
		actionPanelHeader.add(removeProductButton);
		actionPanelHeader.add(modifyOrderButton);
		
		contentPanel.add(searchOrderPanel);
		contentPanel.add(actionPanelHeader);

		/* Tabla de productos de la venta */
		orderProductListPanel = new JPanel();
		orderProductListPanel.setBorder(new TitledBorder(
				"Lista de productos del pedido"));

		orderProductsTableModel = new DefaultTableModel(new Object[] {
				"Producto", "Cantidad" }, 0);
		orderProductsTable = new JTable(orderProductsTableModel) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			};
		};
		orderProductsTable.setEnabled(true);
		orderProductsTable.setRowSelectionAllowed(true);

		orderProductListPanel.add(orderProductsTable);

		orderProductListScrollable = new JScrollPane(orderProductsTable);
		orderProductListScrollable.setPreferredSize(new Dimension(750, 350));

		orderProductListPanel.add(orderProductListScrollable);
		
		 /* Panel de acciones del pie */
        actionPanelFooter = new JPanel(new GridLayout(1,2));
        
        /* Panel del estado del pedido */
        stateOrderPanel = new JPanel();
        stateOrderLabel = new JLabel("Estado del pedido: ");
        stateOrderChoice = new Choice();
        
        stateOrderChoice.add("NO FINALIZADO");
        stateOrderChoice.add("FINALIZADO");
        
        stateOrderPanel.add(stateOrderLabel);
        stateOrderPanel.add(stateOrderChoice);
		
        /* Panel de clientes */
		supplierPanel = new JPanel();
		supplierNameLabel = new JLabel("Nombre del proveedor: ");
		supplierTextField = new JTextField();
		supplierTextField.setPreferredSize(Tools.TEXTFIELDSIZE);
		supplierTextField.setEditable(false);

		supplierPanel.add(supplierNameLabel);
		supplierPanel.add(supplierTextField);
		
		actionPanelFooter.add(stateOrderPanel);
        actionPanelFooter.add(supplierPanel);

		/* Panel principal */
		mainPanel.add(contentPanel, BorderLayout.PAGE_START);
		mainPanel.add(orderProductListPanel, BorderLayout.CENTER);
		mainPanel.add(actionPanelFooter, BorderLayout.PAGE_END);

		this.add(mainPanel, BorderLayout.CENTER);

		this.setLocation(50, 100);
		this.setSize(Tools.WINDOW_DIMENSION);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Método de actualización de la tabla de pedidos.
	 * @param orderContents Contenido de la tabla de pedidos.
	 */

	private void updateOrderTable(final String orderContents) {
		String[] contents = null;

		if (!orderContents.equals(""))
			contents = orderContents.split("_");

		orderProductsTableModel.setRowCount(0);

		if (contents != null) {
			for (int i = 0; i < contents.length - 1; i++)
				if ((i % 3) == 0) {
					orderProductsTableModel.addRow(new Object[] { contents[i], contents[i+2] });
				}
		}
	}

	/**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */
	
	public void clear() {
		orderProductsTableModel.setRowCount(0);
		supplierTextField.setText("");
		searchOrderField.setText("");
		addProductButton.setEnabled(false);
		removeProductButton.setEnabled(false);
		modifyOrderButton.setEnabled(false);
	}

}