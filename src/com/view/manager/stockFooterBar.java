package com.view.manager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.controller.frameController;
import com.util.Tools;
import com.view.manager.actions.addProductToStock;
import com.view.manager.actions.removeProductToStock;
import com.view.manager.actions.tagProducts;

/**
 * Interfaz gráfica del menú del encargado en lo referente al almacén.
 * @author Javier
 *
 */

@SuppressWarnings("serial")
public class stockFooterBar extends JPanel {

	/* Controladores */
	private frameController frameController;		// Controlador de la interfaz gráfica
	
	/* Panel principal de la interfaz */
    private JPanel mainPanel;
	
	/* Panel de botones de acción sobre Pedidos */
    private JPanel actionPanel;
    
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton tagProductButton;
    
    tagProducts tagProducts;
    
    /* Link a todas aquella ventanas que se abren individualmente por separado de la aplicación */
    private ArrayList<addProductToStock> addProductLink;
    private ArrayList<removeProductToStock> removeProductLink;
    
    /**
     * Constructor de la interfaz gráfica del menú del encargado situado en el pie de la aplicación.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public stockFooterBar(frameController cont) {
    	this.frameController = cont;
    	
    	initUI();
    	
    }

    /**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */
    
	private void initUI() {
		/* Panel principal de la interfaz */
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(Tools.MENU_DIMENSION);
		
		/* Aqui se inicializan todos aquellos atributos que necesitan ser 
		 * inicializados y que no son controladores. 
		 * */
		this.tagProducts = new tagProducts(frameController);
    	
    	this.addProductLink = new ArrayList<addProductToStock>();
    	this.removeProductLink = new ArrayList<removeProductToStock>();
		
		/* Panel de acciones del encargado en lo referente al almacén */
        actionPanel = new JPanel();
        actionPanel.setBorder(new TitledBorder("Opciones de encargado - Almacén"));

        addProductButton = new JButton("Agregar producto al almacén");
        removeProductButton = new JButton("Eliminar producto al almacén");
        tagProductButton = new JButton("Clasificar inventario");
        
        addProductButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addProductToStock addProduct = new addProductToStock(frameController);
				addProductLink.add(addProduct);
				addProduct.setVisible(true);
				
			}
        	
        });
        
        removeProductButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeProductToStock removeProduct = new removeProductToStock(frameController);
				removeProductLink.add(removeProduct);
				removeProduct.setVisible(true);
			}
        	
        });
        
        tagProductButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tagProducts.clear();
				tagProducts.setVisible(true);
			}
        	
        });
        
        /* Se añaden los botones al panel de acciones del encargado */
        actionPanel.add(addProductButton);
        actionPanel.add(removeProductButton);
        actionPanel.add(tagProductButton);

        
        mainPanel.add(actionPanel);
        this.add(mainPanel);
        
	}
	
	/**
	 * Método que cierra todas las ventanas cuando un usuario cierra la aplicación. Se llama desde el stockPanel. 
	 */
	
	public void dispose() {
		for(addProductToStock o: addProductLink) {
			o.dispose();
		}
		
		for(removeProductToStock o: removeProductLink) {
			o.dispose();
		}
		
		tagProducts.dispose();
	}
	
}