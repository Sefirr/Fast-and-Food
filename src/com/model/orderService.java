package com.model;

import java.util.ArrayList;

import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;
import com.model.exceptions.OrderNotFoundException;
import com.util.Watcher;

public interface orderService {
	public void addProduct(int id, String name, String Tag, int amount, double price, int orderIdentiffier);
	public void removeProduct(int id, int amount, int orderIdentiffier);
	public void addProductModify(int id, String name, String Tag, int amount, double price, int orderIdentiffier);
	public void removeProductModify(int id, int amount, int orderIdentiffier);
	public void addOrder(int orderIdentiffier);
	public void deleteOrder(int orderIdentiffier) throws OrderNotFoundException;
	public void finishOrder(int orderIdentiffier, String supplier, String date, int orderState);
	public void cancelOrder(int orderIdentiffier);
	public TransferOrder searchOrder(int id) throws OrderNotFoundException;
	public void modifyOrder(int orderIdentifier, String supplier, ArrayList<TransferProduct> productList, boolean finished, boolean approved) throws OrderNotFoundException;
	public void approve(int id) throws OrderNotFoundException;
	public void reloadOrders();
	public void reloadApprovedOrders();
	public int numOrders();
	
	public void dispatchWatcher(Watcher watcher);
	public void removeWatchers();
	
}