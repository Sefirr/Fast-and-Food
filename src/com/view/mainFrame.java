package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controller.frameController;
import com.controller.command.bussinessEvent;
import com.util.Watchable;
import com.util.Watcher;
import com.view.admin.adminPanel;
import com.view.login.loginPanel;
import com.view.manager.stockPanel;
import com.view.vendor.vendorPanel;

/**
 * Frame principal de la aplicacion.
 * @author Borja
 *
 */

@SuppressWarnings("serial")
public class mainFrame extends JFrame implements Watcher {

	/* Enumerado que sirve para acceder al loguearse a los distintos paneles a los que el usuario puede acceder en función del 
	 * nivel de permisos que tiene. */
	public enum Panels {
		LOGIN, SALES, STOCK, ADMIN
	}

	/* Controladores */
	private frameController controller;		// Controlador de la interfaz gráfica
	
	private JFrame holderFrame;				// Frame principal	 
	private JPanel switcherPanel;
	private CardLayout switcherLayout;
	
	private loginPanel loginPanel;		// Panel de login
	private adminPanel adminPanel;		// Panel de administración
	private vendorPanel vendorPanel;	// Panel de vendedor
	private stockPanel managerPanel;	// Panel de encargado
	
	/**
     * Constructor de la interfaz gráfica del frame principal.
     * @param cont 	Controlador de la interfaz gráfica.
     */
	
	public mainFrame(frameController cont) {
	    this.controller = cont;
	    
	    this.controller.addWatcherToUser(this);
	
	    initUI();
	    
	}

	/**
     * Método que inicializa todos los componentes de la interfaz gráfica.
     */
	
	private void initUI() {
		holderFrame =      new JFrame();
	    holderFrame.setTitle("Fast and Food Inc.");
	    holderFrame.setLayout(new BorderLayout());
	    
	    switcherLayout =   new CardLayout();
	    switcherPanel =    new JPanel(switcherLayout);
	    
	    loginPanel =       new loginPanel(controller);
	    adminPanel =       new adminPanel(controller);
	    vendorPanel =      new vendorPanel(controller);
	    managerPanel =     new stockPanel(controller);
	
	    switcherPanel.add(this.loginPanel, Panels.LOGIN.name());
	    switcherPanel.add(this.adminPanel, Panels.ADMIN.name());
	    switcherPanel.add(this.vendorPanel, Panels.SALES.name());
	    switcherPanel.add(this.managerPanel, Panels.STOCK.name());
	    
	    holderFrame.add(switcherPanel, BorderLayout.CENTER);
	    holderFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    
	}

	/**
	 * Método que sirve para cambiar de panel al loguesrse
	 * @param panelToShow Panel al que se va a cambiar al loguearse.
	 */
	
	private void switchCards(Panels panelToShow) {
	    switcherLayout.show(switcherPanel, panelToShow.name());
	}

	/**
	 * Método que configura la vista al inicializarse. 
	 */
	
	public void enable() {
	    controller.init();
	    holderFrame.pack();
	    holderFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
	    holderFrame.setVisible(true);
	}
	
	public void loggedUser(String username) {
		adminPanel.loggedUser(username);
        vendorPanel.loggedUser(username);
        managerPanel.loggedUser(username);
	}
	
	/**
	 * Método que sirve para mostrar un mensaje en el centro del frame.
	 * @param message Mensaje a mostrar.
	 */
	public void show(String message) {
	    JOptionPane.showMessageDialog(holderFrame, message);
	}
	
	/**
	 * Método que sirve para cerrar la aplicación con un mensaje de confirmación.
	 */
	public void quit() {
	    if(JOptionPane.showConfirmDialog(null, "¿Estás seguro de querer salir?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	        System.exit(0);
	        controller.shutdown();
	    }
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
    	cadena = cadena.substring(contenido[0].length()+1);
    	
		if((PETICION == bussinessEvent.LOGINEVENT) || (PETICION == bussinessEvent.LOGOUTEVENT)) {
			switch(cadena) {
			case "NONE":
				switchCards(Panels.LOGIN); 
				break;
		    case "ADMIN":
		    	switchCards(Panels.ADMIN); 
		    	break;
		    case "MANAGER":
		    	switchCards(Panels.STOCK); 
		    	break;
		    case "VENDOR":
		    	switchCards(Panels.SALES);
		    	break;
			}
		}
		
	}
	
}