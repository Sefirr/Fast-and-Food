package com.model.dao;

import com.model.dto.TransferProduct;
import com.model.dto.TransferStock;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.util.Tools.SortMethod;


public interface DaoStock {
	public TransferStock readStock();
	public void writeStock(TransferStock stockList);
	public void addProduct(TransferProduct p) throws ProductNotFoundException;
	public void removeProduct(TransferProduct p) throws ProductNotFoundException, ProductAmountException;
	public TransferProduct searchProduct(TransferStock stock, TransferProduct p);
	public TransferProduct searchProduct(TransferProduct p);
	public boolean productFound(int id);
	public TransferStock sortStock(SortMethod method);
	boolean productFound(int id, String name);
	
}