package com.model;

import com.model.dto.TransferProduct;
import com.model.dto.TransferSale;
import com.model.exceptions.SaleNotFoundException;
import com.util.Watcher;

public interface saleService {
	public void addProduct(int id, int amount, int saleIdentiffier);
	public void removeProduct(int id, int amount, int saleIdentiffier);
	public TransferProduct searchProduct(int id, String productName, int saleIdentiffier);
	public void addSale(int id);
	public void deleteSale(int id) throws SaleNotFoundException;
	public void saveSale(int currentSale);
	public void finishSale(int saleIdentiffier, String customer, String date);
	public void cancelSale(int saleIdentiffier);
	public TransferSale searchSale(int saleIdentiffier);
	public void generateInvoicing(int saleIdentiffier);
	public void showExchange(double pagado, int saleIdentiffier);
	public void showProfits();
	public void showBalance();
	public void reloadSales();
	public int numSales();
	
	public void dispatchWatcher(Watcher watcher);
	public void removeWatchers();	
	
}
