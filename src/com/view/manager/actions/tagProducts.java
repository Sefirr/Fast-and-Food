package com.view.manager.actions;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.util.Tools;
import com.util.Tools.SortMethod;
import com.controller.frameController;
import com.controller.stockController;
import com.controller.command.bussinessEvent;

/**
 * Interfaz gráfica para clasificar inventario.
 * @author Borja y Javier
 *
 */

@SuppressWarnings("serial")
public class tagProducts extends JFrame {

	/* Controladores */
	private stockController stockController;		// Controlador del subsistema de almacén
	
	/* Panel principal de la interfaz */
	private JPanel mainPanel;

	private Choice tagSelect;
	private JButton tagButton;
	
	/**
     * Constructor de la interfaz gráfica para la clasificación de inventario.
     * @param cont 	Controlador de la interfaz gráfica.
     */

	public tagProducts(frameController cont) {
		// TODO Auto-generated constructor stub
		super("Clasificar inventario");
		this.stockController = (stockController) cont.getController(2);
		
		initUI();
		
	}
	
	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */

	private void initUI() {
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new FlowLayout());
		
		tagSelect = new Choice();
		tagSelect.add("Nombre");
		tagSelect.add("Cantidad");
		tagSelect.add("Etiqueta");
		tagSelect.add("Precio");
		
		tagButton = new JButton("Ordenar");
		
		tagButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(tagSelect.getSelectedItem().equalsIgnoreCase("Nombre")) {
					stockController.actionStock(bussinessEvent.CLASIFICAR_INVENTARIO, SortMethod.NOMBRE.name());
				} else if(tagSelect.getSelectedItem().equalsIgnoreCase("Cantidad")) {
					stockController.actionStock(bussinessEvent.CLASIFICAR_INVENTARIO, SortMethod.CANTIDAD.name());
				} else if(tagSelect.getSelectedItem().equalsIgnoreCase("Etiqueta")) {
					stockController.actionStock(bussinessEvent.CLASIFICAR_INVENTARIO, SortMethod.TAG.name());
				} else {
					stockController.actionStock(bussinessEvent.CLASIFICAR_INVENTARIO, SortMethod.PRECIO.name());
				}
				
				setVisible(false);
				dispose();
			}
			
		});
		
		mainPanel.add(tagSelect);
		mainPanel.add(tagButton);
		this.add(mainPanel);
		
		this.setLocation(50, 100);
		this.setSize(Tools.ACTION_DIMENSION_MIN);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
	}
	
	/**
	 * Método que sirve para "limpiar" las ventanas de aplicación una vez han sido utilizadas.
	 */
	
	public void clear() {
		tagSelect.select(0);
	}

}