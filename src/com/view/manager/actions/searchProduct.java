package com.view.manager.actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.util.Common;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;
import com.controller.frameController;
import com.controller.stockController;
import com.controller.command.bussinessEvent;

/**
 * Interfaz gráfica para la búsqueda de pedidos.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class searchProduct extends JFrame implements Watcher {

	/* Controladores */
	private stockController stockController;		// Controlador del subsistema de alamacén

	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	/* Panel de contenido */
	private JPanel contentPanel;
	
	/* Panel de búsqueda de productos */
	private JPanel findPanel;

	private JLabel productIdLabel;
	private JLabel productNameLabel;

	private JTextField productIdField;
	private JTextField productNameField;

	/* Panel de información del producto */
	private JPanel productPanel;

	private JTextArea productInfo;

	/* Panel de acciones */
	private JPanel actionPanel;
	
	private JButton findButton;

	/**
     * Constructor de la interfaz gráfica para la búsqueda de productos.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public searchProduct(frameController cont) {
		// TODO Auto-generated constructor stub
		super("Buscar producto");
		this.stockController = (stockController) cont.getController(2);
		
		cont.addWatcherToStock(this);

		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());

		/* Panel de contenido */
		contentPanel = new JPanel(new GridLayout(2, 0));

		/* Panel de búsqueda de productos */
		findPanel = new JPanel();
		productIdLabel = new JLabel("ID del producto ");
		productNameLabel = new JLabel("Nombre del producto");

		productIdField = new JTextField();
		productIdField.setPreferredSize(Tools.TEXTFIELDSIZE);
		productNameField = new JTextField();
		productNameField.setPreferredSize(Tools.TEXTFIELDSIZE);

		/* Panel de acciones */
		actionPanel = new JPanel();

		findButton = new JButton("Buscar");

		findButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Connect to controller
				try {
					int productId = Integer.parseInt(productIdField.getText());
					String productName = productNameField.getText();

					if (productName.isEmpty())
						throw new NumberFormatException();

					if (stockController.actionStock(bussinessEvent.MOSTRAR_PRODUCTO, productId + "_" + productName + "_") != null)
						stockController.actionStock(bussinessEvent.MOSTRAR_PRODUCTO, productId + "_" + productName + "_");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog( null, Common.SEARCHPRODUCT_ERRORMESSAGE + System.lineSeparator() + "Los datos que has introducido no son correctos." + " Además, debes rellenar los dos campos.");
				}
			}

		});

		// Se añaden los botones al panel de acciones
		actionPanel.add(findButton);

		findPanel.add(productIdLabel);
		findPanel.add(productIdField);
		findPanel.add(productNameLabel);
		findPanel.add(productNameField);
		findPanel.add(new JLabel("  "));
		findPanel.add(actionPanel);

		contentPanel.add(findPanel);
		contentPanel.add(new JLabel("   "));

		/* Panel de información del producto */
		productPanel = new JPanel();
		productPanel.setAlignmentX(FlowLayout.CENTER);
		productInfo = new JTextArea();
		productInfo.setEditable(false);
		productInfo.setPreferredSize(new Dimension(600, 400));

		JScrollPane scrollPanel = new JScrollPane(productInfo);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanel.setPreferredSize(new Dimension(600, 400));

		productPanel.add(scrollPanel);

		mainPanel.add(contentPanel, BorderLayout.PAGE_START);
		mainPanel.add(productPanel, BorderLayout.CENTER);
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
		case bussinessEvent.MOSTRAR_PRODUCTO:
			productInfo.setText(cadena);
			break;
		}

	}
	
	/**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */

	public void clear() {
		productIdField.setText("");
		productNameField.setText("");
		productInfo.setText("");

	}

}