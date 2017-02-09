package com.controller;

import java.util.ArrayList;

import com.model.Shop;
import com.model.dto.TransferProduct;
import com.model.exceptions.InvalidLoginException;
import com.model.exceptions.ProductNotFoundException;
import com.util.Watcher;
import com.view.mainFrame;

/**
 * Controlador de la interfaz gráfica, es decir, aqui es donde se añaden los observadores para enterarse de
 * las actualizaciones que tiene el modelo.
 * @author Borja y Javier
 *
 */

public class frameController {

	private Shop model;
	private mainFrame frame;
    
    public frameController(Shop core) {
		// TODO Auto-generated constructor stub
    	model = core;
	}

	public void addView(mainFrame frame) {
    	this.frame = frame;
    }
	
	public void show(String msg) {
		frame.show(msg);
	}
    
    public void loginEvent(String username, String password) {
        try {
            model.login(username, password);
            frame.loggedUser(username);
        } catch (InvalidLoginException e) {
            frame.show(e.getMessage());
        }
    }
	
    public void logoutEvent(String username, String password) {
        model.logout(username, password);
    }
    
    public void quitEvent() {
        frame.quit();
    }

	public void init() {
		model.initStock();
		model.initSales();
		model.initUsers();
		model.initOrders();
		model.initApprovedOrders();
	}
	
	public void addWatcherToSale(Watcher watcher) {
		model.dispatchWatchersToSale(watcher);
	}
	
	public void addWatcherToOrder(Watcher watcher) {
		model.dispatchWatcherToOrder(watcher);
	}
	
	public void addWatcherToUser(Watcher watcher) {
		model.dispatchWatcherToUser(watcher);
	}
	
	public void addWatcherToStock(Watcher watcher) {
		model.dispatchWatcherToStock(watcher);
	}
	
	public void reloadSales() {
		model.initSales();
	}
	
	public void reloadUsers() {
		model.initUsers();
	}
	
	public void reloadStock() {
		model.initStock();
	}
	
	public void reloadOrders() {
		model.initOrders();
	}
	
	public void reloadApprovedOrders() {
		model.initApprovedOrders();
	}
	
	public void shutdown() {
		model.removeWatchersToStock();
		model.removeWatchersToSale();
		model.removeWatchersToUser();
		model.removeWatchersToOrder();
	}
	
	public Object getController(int event) {
		
		return model.getController(event);
	}
	
	public int numOrders() {
		return model.numOrders();
	}
	
	public int numSales() {
		return model.numSales();
	}
	
	public void saleEvent(int currentSale) {
       model.saveSale(currentSale); 
                
       /* for(TransferProduct p : productList) {
            try {
				model.removeProductToStock(p.getId(), p.getAmount());
			} catch (ProductNotFoundException | ProductAmountException e) {
				// TODO Auto-generated catch block
				frame.show(e.getMessage());
			}
        }*/

    }
	
	public void orderEvent(ArrayList<TransferProduct> productList) {        
                
        for(TransferProduct p : productList) {
            try {
				model.addProductToStock(p.getId(), p.getName(), p.getTag(), p.getAmount(), p.getPrice());
			} catch (ProductNotFoundException e) {
				// TODO Auto-generated catch block
				frame.show(e.getMessage());
			}
        }
	}
    
}