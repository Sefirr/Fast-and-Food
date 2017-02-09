package com.model;
import com.model.dto.TransferProduct;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.util.Watcher;
import com.util.Tools.SortMethod;

public interface stockService {	
	public void addProduct(int id, String name, String tag, int amount, double price) throws ProductNotFoundException;
	public void removeProduct(int id, int amount) throws ProductNotFoundException, ProductAmountException;
	public TransferProduct searchProduct(int id, String productName) throws ProductNotFoundException;
	public void sortStock(SortMethod method); 
	public void reloadStock();
	
	public void dispatchWatcher(Watcher watcher);
	public void removeObservers();
	
}
