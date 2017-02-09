package com.view.manager.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.controller.frameController;
import com.controller.orderController;
import com.controller.command.bussinessEvent;
import com.util.Common;
import com.util.Tools;

/**
 * Interfaz gráfica para eliminar productos del almacén.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class removeProductToOrder extends JFrame {

	// Controladores
	private orderController orderController;		// Controlador del subsistema de pedidos

	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	/* Panel de contenido */
	private JPanel contentPanel;

	private JLabel productIdLabel;
	private JLabel productAmountLabel;
	private JLabel whitespace;

	private JTextField productIdField;
	private JTextField productAmountField;

	/* Panel de acciones */
	private JPanel actionPanel;
	
	private JButton removeProductButton;

	/* Indicador del pedido actual al que eliminar */
	private int currentOrder;

	/**
     * Constructor de la interfaz gráfica para eliminar productos al pedido.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public removeProductToOrder(frameController cont) {
		super("Eliminar producto");
		this.orderController = (orderController) cont.getController(0);

		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new TitledBorder("Datos para eliminar producto"));

		/* Panel de contenido */
		contentPanel = new JPanel(new GridLayout(4, 0));
		
		productIdLabel = new JLabel("ID del producto");
		productAmountLabel = new JLabel("Cantidad");
		whitespace = new JLabel("                                   ");

		productIdField = new JTextField();
		productIdField.setPreferredSize(Tools.TEXTFIELDSIZE);
		productAmountField = new JTextField();
		productAmountField.setPreferredSize(Tools.TEXTFIELDSIZE);

		contentPanel.add(productIdLabel);
		contentPanel.add(productIdField);
		contentPanel.add(productAmountLabel);
		contentPanel.add(productAmountField);
		contentPanel.add(whitespace);

		/* Panel de acciones */
		actionPanel = new JPanel();
		
		removeProductButton = new JButton("Eliminar producto");
		
		removeProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// connect to controller
				try {
					int productId = Integer.parseInt(productIdField.getText());
					int productAmount = Integer.parseInt(productAmountField.getText());

					orderController.actionOrder(bussinessEvent.ELIMINAR_PRODUCTO_PEDIDO, productId + "_" + productAmount + "_" + currentOrder + "_");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog( null, Common.REMOVEPRODUCT_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos.");
				}

				setVisible(false);
				dispose();
			}

		});
		
		// Se añaden los botones al panel de acciones
		actionPanel.add(removeProductButton);

		mainPanel.add(contentPanel, BorderLayout.PAGE_START);
		mainPanel.add(actionPanel, BorderLayout.CENTER);
		this.add(mainPanel);

		this.setLocation(150, 150);
		this.setSize(Tools.ACTION_DIMENSION);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Método que inicializa el atributo currentOrder, es decir, para que la aplicación sepa cual es el pedido
	 * que se esta realizando en este mismo instante.
	 * @param currentOrder Identificador del pedido.
	 */

	public void currentOrder(int currentOrder) {
		this.currentOrder = currentOrder;
	}

}