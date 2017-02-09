package com.model.impl;

import com.controller.command.bussinessEvent;
import com.model.stockService;
import com.model.dao.DaoFactory;
import com.model.dao.DaoStock;
import com.model.dto.TransferProduct;
import com.model.dto.TransferStock;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.model.impl.dto.TransferProductImp;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;
import com.util.Tools.SortMethod;

public class stockServiceImp extends Watchable implements stockService {
	
	@Override
	public void addProduct(int id, String name, String tag, int amount, double price) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoStock stockDAO = factory.getDAOStock();
		
		try {
			TransferProduct p = new TransferProductImp();
			p.setId(id);
			p.setName(name);
			p.setTag(tag);
			p.setAmount(amount);
			p.setPrice(price);
		

			if(stockDAO.productFound(id, name) || !stockDAO.productFound(id)) {
				stockDAO.addProduct(p);
				
				this.setChanged();
			    this.notifyObservers(bussinessEvent.AÑADIR_PRODUCTO_STOCK + " ");			
			
			} else {
				throw new ProductNotFoundException("El identificador de producto ya es de un producto existente en el almacén.");
			}
		} catch(ProductNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_ADDPRODUCT + " " + e.getMessage());
			
			throw e;
		}
		
	}

	@Override
	public void removeProduct(int id, int amount) throws ProductNotFoundException, ProductAmountException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoStock stockDAO = factory.getDAOStock();
		
		try {
			TransferProduct stockById = new TransferProductImp();
			stockById.setId(id);
			stockById.setAmount(amount);
			TransferProduct buscado = stockDAO.searchProduct(stockById);
			
			if(!stockDAO.productFound(id))
				throw new ProductNotFoundException("El producto con identificador " + id + " no se encuentra en el almacen.");
			if(stockDAO.productFound(id) && !Tools.availableProduct(buscado.getAmount(), amount))
				throw new ProductAmountException("El producto con identificador " + id + " se encuentra en una cantidad inferior en el almacen.");
			
			stockDAO.removeProduct(stockById);	
			
			this.setChanged();
		    this.notifyObservers(bussinessEvent.ELIMINAR_PRODUCTO_STOCK + " ");
		} catch(ProductNotFoundException | ProductAmountException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_REMOVEPRODUCT + " " + e.getMessage());
			
			throw e;
		}
		
	}

	@Override
	public TransferProduct searchProduct(int id, String productName) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoStock stockDAO = factory.getDAOStock();
		
		try {
		TransferProduct p = new TransferProductImp();
		p.setName(productName);
		p.setId(id);
		
		TransferProduct currentProduct = stockDAO.searchProduct(p);
		
		if(currentProduct == null)
			throw new ProductNotFoundException("El producto con identificador" + p.getId() + " no existe." );
		
		this.setChanged();
		this.notifyObservers(bussinessEvent.MOSTRAR_PRODUCTO + " " + currentProduct.toString());
		
		return currentProduct;
		} catch(ProductNotFoundException e) {
			this.setChanged(); 
			this.notifyObservers(bussinessEvent.ERROR_SEARCHPRODUCT + " ");
			
			throw e;
		}
		
	}
	
	@Override
	public void sortStock(SortMethod method) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoStock stockDAO = factory.getDAOStock();
		
		TransferStock currentStock = stockDAO.sortStock(method);
			
		this.setChanged();
		this.notifyObservers(String.valueOf(bussinessEvent.CLASIFICAR_INVENTARIO) + " " + currentStock);
		
	}
	
	@Override
	public void reloadStock() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoStock stockDAO = factory.getDAOStock();
		
		TransferStock stock = stockDAO.readStock();
		String contents = String.valueOf(bussinessEvent.ACTUALIZAR_STOCK) + " " + stock.displayContents();
		
		this.setChanged();
		this.notifyObservers(contents);
	}

	@Override
	public void dispatchWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		this.addWatcher(watcher);
	}
	
	@Override
	public void removeObservers() {
		// TODO Auto-generated method stub
		this.deleteWatchers();
	}

}
