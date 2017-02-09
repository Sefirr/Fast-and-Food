package com.model.dao;

import java.util.ArrayList;

import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;

public interface DaoOrder {
	public ArrayList<TransferOrder> readOrders();
	public void writeOrders(ArrayList<TransferOrder> orderList);
	public void addProduct(TransferProduct p, TransferOrder s);
	public void removeProduct(TransferProduct p, TransferOrder s) throws ProductAmountException, ProductNotFoundException;
	public void addOrder(TransferOrder o);
	public void deleteOrder(TransferOrder o);
	public void finishOrder(TransferOrder o);
	public void cancelOrder(TransferOrder o);
	public boolean productFound(TransferOrder currentOrder, TransferProduct p);
	public TransferProduct searchProduct(TransferOrder currentOrder, TransferProduct p);
	public void modifyOrder(TransferOrder o);
	public TransferOrder searchOrder(TransferOrder o);
	public void approved(TransferOrder o);
	public int numOrders();
	
}