package com.model.impl;

import java.util.ArrayList;

import com.controller.command.bussinessEvent;
import com.model.saleService;
import com.model.dao.DaoFactory;
import com.model.dao.DaoSale;
import com.model.dao.DaoStock;
import com.model.dto.TransferProduct;
import com.model.dto.TransferSale;
import com.model.exceptions.ExchangeException;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.model.exceptions.SaleNotFoundException;
import com.model.impl.dto.TransferProductImp;
import com.model.impl.dto.TransferSaleImp;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

public class saleServiceImp extends Watchable implements saleService {
	
	@Override
	public void addProduct(int id, int amount, int saleIdentiffier) {
		// TODO Auto-generated method stub		
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		DaoStock stockDAO = factory.getDAOStock();
		
		try {
			TransferProduct saleById = new TransferProductImp();
			saleById.setId(id);
			saleById.setAmount(amount);
			
			TransferSale saleId = new TransferSaleImp();
			saleId.setSaleIdentifier(saleIdentiffier);
			
			TransferSale currentSale = saleDAO.searchSale(saleId);
			TransferProduct buscadoEnStock = stockDAO.searchProduct(saleById);
			TransferProduct buscadoEnVenta = saleDAO.searchProduct(currentSale, saleById);
			
			//Condiciones para añadir producto
			if(!stockDAO.productFound(id))
				throw new ProductNotFoundException("El producto con identificador " + id + " no se encuentra en el almacen.");
			if(stockDAO.productFound(id) && !Tools.availableProduct(buscadoEnStock.getAmount(), amount))
				throw new ProductAmountException("El producto con identificador " + id + " se encuentra en una cantidad inferior en el almacen.");
			if(stockDAO.productFound(id) && (buscadoEnVenta != null) && !Tools.availableProduct(buscadoEnStock.getAmount(), buscadoEnVenta.getAmount() + amount))
				throw new ProductAmountException("El producto con identificador " + id + " se encuentra en una cantidad inferior en el almacen.");
			buscadoEnStock.setAmount(amount);
			
			if(amount < 1)
				throw new ProductAmountException(
						"El producto con identificador " + id + 
						" no se puede añadir con una cantidad inferior a la unidad .");
			
		    saleDAO.addProduct(buscadoEnStock, saleId);
		    
		    currentSale = saleDAO.searchSale(saleId);
		    
		    this.setChanged();
		    this.notifyObservers(bussinessEvent.VENTA_ACTUAL + " " + saleIdentiffier + "," + currentSale.displayContents());
		    
		    this.setChanged();
		    this.notifyObservers(bussinessEvent.AÑADIR_PRODUCTO_VENTA + " ");
		} catch(ProductNotFoundException | ProductAmountException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_ADDPRODUCT + " " + e.getMessage());
		}
	    	
	}
	
	@Override
	public void removeProduct(int id, int amount, int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		try {
			TransferProduct saleById = new TransferProductImp();
			saleById.setId(id);
			saleById.setAmount(amount);
			
			TransferSale saleId = new TransferSaleImp();
			saleId.setSaleIdentifier(saleIdentiffier);
			
			TransferSale currentSale = saleDAO.searchSale(saleId);
			
			TransferProduct buscado = saleDAO.searchProduct(currentSale, saleById);
			
			
			if(!saleDAO.productFound(currentSale, saleById))
				throw new ProductNotFoundException("El producto con identificador " + id + " no se encuentra en la venta.");
			if(saleDAO.productFound(currentSale, saleById) && !Tools.availableProduct(buscado.getAmount(), amount))
				throw new ProductAmountException("El producto con identificador " + id + " se encuentra en una cantidad inferior en la venta.");
			if(amount < 1)
				throw new ProductAmountException(
						"El producto con identificador " + id + 
						" no se puede añadir con una cantidad inferior a la unidad .");
			
			buscado.setAmount(amount);
			
			saleDAO.removeProduct(buscado, saleId);
			
			currentSale = saleDAO.searchSale(saleId);
			
			this.setChanged();
		    this.notifyObservers(bussinessEvent.VENTA_ACTUAL + " " + saleIdentiffier + "," +currentSale.displayContents());
		    
		    this.setChanged();
		    this.notifyObservers(bussinessEvent.ELIMINAR_PRODUCTO_VENTA + " ");
		} catch(ProductNotFoundException | ProductAmountException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_REMOVEPRODUCT + " " + e.getMessage());
		}
	    
	}
	
	@Override
	public void addSale(int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		TransferSale  newSale = new TransferSaleImp();
		int identifier = new Integer(saleIdentiffier);
		
		// Comprobación de si existe ya la venta. En ese caso, se le asigna una id nueva.
		while(searchSaleMethod(identifier) != null) {
			identifier++;
		}
		
		newSale.setSaleIdentifier(identifier);
		
		saleDAO.addSale(newSale);
		
		this.setChanged();
	    this.notifyObservers(bussinessEvent.AÑADIR_VENTA + " ");
	
	}
	
	@Override
	public void deleteSale(int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		try {
			TransferSale s = new TransferSaleImp();
			s.setSaleIdentifier(saleIdentiffier);
			
			TransferSale currentSale = saleDAO.searchSale(s);
			if(currentSale == null) {
				throw new SaleNotFoundException("La venta con identificador " + s.getSaleIdentifier() + " no existe.");
			} else {
				if(!currentSale.getFinished()) {
					throw new SaleNotFoundException("No se pueden eliminar ventas "
							+ "que no estén finalizadas.");
				}
			}
				
			saleDAO.deleteSale(s);
			
			this.setChanged();
		    this.notifyObservers(bussinessEvent.ELIMINAR_VENTA + " ");
		} catch(SaleNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_DELETESALE + " " + e.getMessage());
			
			
		}
		
	}
	
	@Override
	public void saveSale(int currentSale) {
		// TODO Auto-generated method stub
		TransferSale s = new TransferSaleImp();
		s = this.searchSale(currentSale);
		
		this.setChanged();
		this.notifyObservers(bussinessEvent.FINALIZAR_VENTA_PROCESS + " " + currentSale + "," + s.getPrice());
	}
	
	@Override
	public void finishSale(int saleIdentiffier, String customer, String date) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		DaoStock stockDAO = factory.getDAOStock();
		
		TransferSale s = new TransferSaleImp();

		s = searchSale(saleIdentiffier);
		
		
		s.setSaleIdentifier(saleIdentiffier);
		s.setCustomer(customer);
		s.setDate(date);
		ArrayList<TransferProduct> productList = s.getProductList();
		
		for(TransferProduct p : productList) {
            	TransferProduct stockbyId = new TransferProductImp();
            	stockbyId.setId(p.getId());
            	stockbyId.setAmount(p.getAmount());
				try {
					stockDAO.removeProduct(stockbyId);
				} catch (ProductNotFoundException | ProductAmountException e) {
					// TODO Auto-generated catch block
					
				}		
		}
		
		
		saleDAO.finishSale(s);
		
		generateInvoicing(saleIdentiffier);
		
		this.setChanged();
	    this.notifyObservers(bussinessEvent.FINALIZAR_VENTA_NOTIFY + " " + saleIdentiffier);
		
	}
	
	@Override
	public void cancelSale(int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();

		TransferSale s = new TransferSaleImp();
		s.setSaleIdentifier(saleIdentiffier);
		
		saleDAO.cancelSale(s);
		
		this.setChanged();
	    this.notifyObservers(bussinessEvent.CANCELAR_VENTA + " ");
		
	}
	
	private TransferSale searchSaleMethod(int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		try {
			TransferSale s = new TransferSaleImp();
			s.setSaleIdentifier(saleIdentiffier);
			
			TransferSale currentSale = saleDAO.searchSale(s);
			
			if(currentSale == null)
				throw new SaleNotFoundException("La venta con identificador " + s.getSaleIdentifier() + " no existe.");
						
			return currentSale;
		} catch(SaleNotFoundException e) {
		  
		}
		return null;

	}
	
	@Override
	public TransferSale searchSale(int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		try {
			TransferSale s = new TransferSaleImp();
			s.setSaleIdentifier(saleIdentiffier);
			
			TransferSale currentSale = saleDAO.searchSale(s);
			
			if(currentSale == null)
				throw new SaleNotFoundException("La venta con identificador " + s.getSaleIdentifier() + " no existe.");
			
			this.setChanged();
			this.notifyObservers(bussinessEvent.MOSTRAR_VENTA + " " + currentSale.displayContents());
			
			return currentSale;
		} catch(SaleNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_SEARCHSALE + " " + e.getMessage());
		}
		return null;

	}
	
	@Override
	public void generateInvoicing(int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		TransferSale currentSale = new TransferSaleImp();
		currentSale.setSaleIdentifier(saleIdentiffier);
		
		saleDAO.generateInvoicing(currentSale);
		
	}
	
	@Override
	public TransferProduct searchProduct(int id, String productName, int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		TransferProduct p = new TransferProductImp();
		p.setId(id);
		p.setName(productName);
		
		TransferSale saleId = new TransferSaleImp();
		saleId.setSaleIdentifier(saleIdentiffier);
		
		TransferSale currentSale = saleDAO.searchSale(saleId);
		
		
		return saleDAO.searchProduct(currentSale, p);
		
	}
	
	@Override 
	public void showExchange(double pagado, int saleIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		try{	
			TransferSale s = new TransferSaleImp();
			s.setSaleIdentifier(saleIdentiffier);
			
			TransferSale currentSale = saleDAO.searchSale(s);
			
			if(pagado < currentSale.getPrice())
				throw new ExchangeException("La cantidad pagada es inferior a la que hay que pagar.");
			
			double exchange =  saleDAO.showExchange(pagado, currentSale);
			
			this.setChanged();
			this.notifyObservers(bussinessEvent.MOSTRAR_CAMBIO + " " + saleIdentiffier + "," + exchange + " ");
		} catch(ExchangeException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_MOSTRARCAMBIO + " " + e.getMessage());
		}
		
	}
	
	@Override
	public void showProfits() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		double profit =  saleDAO.showProfits();
		
		this.setChanged();
		this.notifyObservers(bussinessEvent.MOSTRAR_BALANCEDIA + " " + profit);
		
	}
	
	@Override
	public void showBalance() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		double balance = saleDAO.showBalance();
		
		this.setChanged();
		this.notifyObservers(bussinessEvent.MOSTRAR_BALANCE + " " + balance);
		
	}
	
	@Override
	public void dispatchWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		
		this.addWatcher(watcher);
		
	}
	
	@Override
	public void removeWatchers() {
		// TODO Auto-generated method stub
		
		this.deleteWatchers();
		
	}

	@Override
	public void reloadSales() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		ArrayList<TransferSale> saleList = saleDAO.readSales();
		
		String contents = String.valueOf(bussinessEvent.ACTUALIZAR_VENTAS) + " ";
		for(TransferSale s: saleList) {
			contents += s.resumeContents();
		}
		
		this.setChanged();
		this.notifyObservers(contents);
		
	}
	
	@Override 
	public int numSales() {
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoSale saleDAO = factory.getDAOSale();
		
		return saleDAO.numSales();
		
	}

	
		
}