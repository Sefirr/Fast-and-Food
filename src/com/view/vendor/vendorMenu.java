package com.view.vendor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.controller.frameController;
import com.controller.saleController;
import com.controller.userController;
import com.controller.command.bussinessEvent;
import com.model.dto.TransferUser;
import com.util.Tools;
import com.view.vendor.actions.addSale;
import com.view.vendor.actions.deleteSale;
import com.view.vendor.actions.searchSale;
import com.view.vendor.actions.showBalance;
import com.view.vendor.actions.showProfit;

/**
 * Interfaz gráfica del menú de ventas
 * @author Javier
 *
 */

@SuppressWarnings("serial")
public class vendorMenu extends JPanel {

	/* Controladores */
	private frameController frameController;		// Controlador de la interfaz gráfica
    private userController userController;			// Controlador del subsistema de usuarios		
    private saleController saleController;			// Controlador del subsistema de ventas
	
    /* Panel principal de la interfaz */
	private JPanel mainPanel;    
        
    /* Panel de acciones de un vendedor */
    private JPanel saleActions; 
    
    private JButton addSaleBtn;
    private ArrayList<addSale> addSaleLink;
    
    private JButton deleteSaleBtn;
    private ArrayList<deleteSale> deleteSaleLink;
    
    private JButton searchSaleBtn;
    private ArrayList<searchSale> searchSaleLink;
    
    private JButton showProfitBtn;
    private ArrayList<showProfit> showProfitLink;
    
    private JButton showBalanceBtn;
    private ArrayList<showBalance> showBalanceLink;
    
    private JButton logoutButton;
    
    /* Usuario logueado en la aplicación */
    private String _loggedUser;
    
    /* Atributos de venta actual y para averiguar si se puede realizar otra venta */
    private int currentSale;

    /**
     * Constructor de la interfaz gráfica del menú de ventas.
     * @param cont 	Controlador de la interfaz gráfica.
     */
    
    public vendorMenu(frameController cont) {
        this.frameController = cont;
        this.userController = (userController) cont.getController(3);
        this.saleController = (saleController) cont.getController(1);
   
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
    	this.addSaleLink = new ArrayList<addSale>();
	    this.deleteSaleLink = new ArrayList<deleteSale>();
	    this.searchSaleLink = new ArrayList<searchSale>();
	    this.showProfitLink = new ArrayList<showProfit>();
	    this.showBalanceLink = new ArrayList<showBalance>();
    	
    	/* Acciones del panel de ventas */
    	saleActions = new JPanel(new FlowLayout());
    	saleActions.setBorder(new TitledBorder("Opciones de vendedor - Ventas"));
    
    	this.addSaleBtn = new JButton("Añadir venta");
    	this.deleteSaleBtn = new JButton("Eliminar venta");
    	this.searchSaleBtn = new JButton("Buscar venta");
    	this.showProfitBtn = new JButton("Ver caja");
    	this.showBalanceBtn = new JButton ("Mostrar balance");
    	this.logoutButton = new JButton("Logout"); 
    	
    	addSaleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addSale addSale = new addSale(frameController);
				addSaleLink.add(addSale);
				
				currentSale = frameController.numSales();
				addSale.currentSale(currentSale);
				
				saleController.actionSale(bussinessEvent.AÑADIR_VENTA, currentSale + "_");
				addSale.clear();
				addSale.setVisible(true);
			}
        	
        });
    	
    	deleteSaleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				deleteSale deleteSale = new deleteSale(frameController);
				deleteSaleLink.add(deleteSale);
				
				deleteSale.clear();
				deleteSale.setVisible(true);
			}
        	
        });
    	
    	searchSaleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchSale searchSale = new searchSale(frameController);
				searchSaleLink.add(searchSale);
				
				searchSale.clear();
				searchSale.setVisible(true);
			}
        	
        });
    	
    	showProfitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showProfit showProfit = new showProfit(frameController);
				showProfitLink.add(showProfit);
				
				saleController.actionSale(bussinessEvent.MOSTRAR_BALANCEDIA, "_");
				showProfit.setVisible(true);
			}
    		
    	});
    	
    	showBalanceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showBalance showBalance = new showBalance(frameController);
				showBalanceLink.add(showBalance);
				
				saleController.actionSale(bussinessEvent.MOSTRAR_BALANCE, "_");
				showBalance.setVisible(true);
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
    	
    	// Se añaden los botones al panel de acciones del vendedor.
    	saleActions.add(addSaleBtn);
    	saleActions.add(deleteSaleBtn);
    	saleActions.add(searchSaleBtn);
    	saleActions.add(showProfitBtn);
    	saleActions.add(showBalanceBtn);
    	saleActions.add(logoutButton);
    	
    	mainPanel.add(saleActions);
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
	 * Método que inicializa el atributo currentSale, es decir, para que la aplicación sepa cual es la venta
	 * que se esta realizando en este mismo instante.
	 * @param currentSale Identificador de la venta.
	 */
    
    public void currentSale(int currentSale) {
    	this.currentSale = currentSale;
    }
 
    /**
	 * Método que cierra todas las ventanas cuando un usuario cierra la aplicación. Se llama desde el vendorPanel. 
	 */
    
    public void dispose() {
    	for(addSale e: addSaleLink) {
    		e.dispose();
    	}
    	
    	for(deleteSale e: deleteSaleLink) {
    		e.dispose();
    	}
    	
    	for(searchSale e: searchSaleLink) {
    		e.dispose();
    	}
    	
    	for(showProfit e: showProfitLink) {
    		e.dispose();
    	}
    	
    	for(showBalance e: showBalanceLink) {
    		e.dispose();
    	}
    }
    
}