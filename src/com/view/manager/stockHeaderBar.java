package com.view.manager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.controller.frameController;
import com.controller.orderController;
import com.controller.userController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferUser;
import com.util.Tools;
import com.view.manager.actions.*;

/**
 * Interfaz gráfica del menú del encargado en lo referente a los pedidos.
 * @author Javier
 *
 */

@SuppressWarnings("serial")
public class stockHeaderBar extends JPanel {
	
	/* Controladores */
	private frameController frameController;		// Controlador de la interfaz gráfica
	private orderController orderController;		// Controlador del subsistema de pedidos
	private userController userController;			// Controlador del subsistema de usuarios

	/* Panel principal de la interfaz */
    private JPanel mainPanel;
	
	/* Panel de acciones sobre Pedidos */
    private JPanel actionPanel;
    
    private JButton addOrderButton;
    private JButton deleteOrderButton;
    private JButton editAndSearchOrderButton;
    private JButton searchProductButton;
    private JButton logoutButton;
    
    private ArrayList<addOrder> addOrderLink;
    private ArrayList<deleteOrder> deleteOrderLink;
    private ArrayList<editAndSearchOrder> editAndSearchOrderLink;
    private ArrayList<searchProduct> searchProductLink;
    
    /* Usuario logueado en la aplicación */
    private String _loggedUser;
    
    /* Indicador de pedido actual */
    private int currentOrder;
    
    /**
     * Constructor de la interfaz gráfica del menú de encargado en lo referente a los pedidos.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public stockHeaderBar(frameController cont) {
    	this.frameController = cont;
    	this.orderController = (orderController) cont.getController(0);
    	this.userController = (com.controller.userController) cont.getController(3);
    	
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
		this.addOrderLink = new ArrayList<addOrder>();
    	this.deleteOrderLink = new ArrayList<deleteOrder>();
    	this.editAndSearchOrderLink = new ArrayList<editAndSearchOrder>();
    	this.searchProductLink = new ArrayList<searchProduct>();
		
		/* Panel de acciones del encargado en lo referente a los pedidos. */
        actionPanel = new JPanel();
        actionPanel.setBorder(new TitledBorder("Opciones de encargado - Pedidos"));

        addOrderButton = new JButton("Agregar pedido");
        deleteOrderButton = new JButton("Eliminar pedido");
        searchProductButton = new JButton("Buscar producto");
        editAndSearchOrderButton = new JButton("Modificar y/o buscar pedido");
        logoutButton = new JButton("Logout");
        
        addOrderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					addOrder addOrder = new addOrder(frameController);
					addOrderLink.add(addOrder);
					
					currentOrder = frameController.numOrders();
					addOrder.currentOrder(currentOrder);
					
					orderController.actionOrder(bussinessEvent.AÑADIR_PEDIDO, currentOrder + "_");
					addOrder.clear();
					addOrder.setVisible(true);
		
			}
        	
        });
        
        deleteOrderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteOrder deleteOrder = new deleteOrder(frameController);
				deleteOrderLink.add(deleteOrder);

				deleteOrder.clear();
				deleteOrder.setVisible(true);
			}
        	
        });
        
        editAndSearchOrderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub		
				editAndSearchOrder editAndSearchOrder = new editAndSearchOrder(frameController);
				editAndSearchOrderLink.add(editAndSearchOrder);
				
				editAndSearchOrder.clear();
				editAndSearchOrder.setVisible(true);
			}
        	
        });
        
        searchProductButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchProduct searchProduct = new searchProduct(frameController);
				searchProductLink.add(searchProduct);
				
				searchProduct.clear();
				searchProduct.setVisible(true);
			}
        	
        });
        
        logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				TransferUser buscado;
				buscado = (TransferUser) userController.actionUser(bussinessEvent.MOSTRAR_USUARIO, _loggedUser + "_");
				frameController.logoutEvent(buscado.getUserName(), buscado.getPassword());	
			}
        	
        });
        
        /* Se añaden los botones al panel de acciones de encargado */
        actionPanel.add(addOrderButton);
        actionPanel.add(deleteOrderButton);
        actionPanel.add(editAndSearchOrderButton);
        actionPanel.add(searchProductButton);
        actionPanel.add(logoutButton);
        
        mainPanel.add(actionPanel);
        this.add(mainPanel);
        
	}
	
	/**
	 * Método que inicializa el atributo _loggedUser, es decir, para que la aplicación sepa cual es el usuario
	 * que está logueado.
	 * @param username Usuario logueado en la aplcación.
	 */
	
	public void loggedUser(String username) {
    	this._loggedUser = username;
    }
	
	/**
	 * Método que inicializa el atributo currentOrder, es decir, para que la aplicación sepa cual es el pedido
	 * que se esta realizando en este mismo instance.
	 * @param currentOrder Identificador del pedido.
	 */
	
	public void currentOrder(int currentOrder) {
    	this.currentOrder = currentOrder;
    }

	/**
	 * Método que cierra todas las ventanas cuando un usuario cierra la aplicación. Se llama desde el stockPanel. 
	 */
	
	public void dispose() {
		for(addOrder e: addOrderLink) {
			e.dispose();
		}
		
		for(deleteOrder e: deleteOrderLink) {
			e.dispose();
		}
		
		for(editAndSearchOrder e: editAndSearchOrderLink) {
			e.dispose();
		}
		
		for(searchProduct e: searchProductLink) {		
			e.dispose();
		}
	}

}