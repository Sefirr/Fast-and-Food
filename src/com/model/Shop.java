package com.model;

import java.util.ArrayList;

import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;
import com.model.dto.TransferSale;
import com.model.dto.TransferUser;
import com.model.exceptions.ApproveOrderException;
import com.model.exceptions.DuplicateProductException;
import com.model.exceptions.DuplicateUserException;
import com.model.exceptions.ExchangeException;
import com.model.exceptions.InvalidLoginException;
import com.model.exceptions.OrderNotFoundException;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.model.exceptions.SaleNotFoundException;
import com.model.exceptions.UserNotFoundException;
import com.util.Watcher;
import com.util.Tools.SortMethod;
import com.util.Tools.UserPermission;

public interface Shop {
	// Servicio de aplicación de pedidos
	public void addProductToOrder(int id, String name, String Tag, int amount, double price, int orderIdentiffier) throws ProductAmountException;
	public void removeProductToOrder(int id, int amount, int orderIdentiffier);
	public void addProductToOrderModify(int id, String name, String Tag, int amount, double price, int orderIdentiffier) throws DuplicateProductException;
	public void removeProductToOrderModify(int id, int amount, int orderIdentiffier) throws ProductNotFoundException, ProductAmountException;
	public void addOrder(int orderIdentiffier);
	public void deleteOrder(int orderIdentiffier) throws OrderNotFoundException;
	public void finishOrder(int orderIdentiffier, String supplier, String date, int orderState);
	public void cancelOrder(int orderIdentiffier);
	public TransferOrder searchOrder(int id) throws OrderNotFoundException;
	public void modifyOrder(int orderIdentiffier, String supplier, ArrayList<TransferProduct> productList, boolean finished, boolean approved) throws OrderNotFoundException;
	public void approveOrder(int id) throws OrderNotFoundException, ApproveOrderException;
	public int numOrders();
	public void initOrders();
	public void initApprovedOrders();
	
	public void dispatchWatcherToOrder(Watcher watcher);
	public void removeWatchersToOrder();

	// Servicio de aplicación de ventas
	public void addProductToSale(int id, int amount, int saleIdentiffier);
	public void removeProductToSale(int id, int amount, int saleIdentiffier);
	public void addSale(int id);
	public void deleteSale(int id) throws SaleNotFoundException;
	public void saveSale(int currentSale);
	public void finishSale(int saleIdentiffier, String customer, String date);
	public void cancelSale(int saleIdentiffier);
	public TransferSale searchSale(int id) throws SaleNotFoundException;
	public void generateInvoicing(int saleIdentiffier);
	public void showExchange(double pagado, int saleIdentiffier) throws ExchangeException;
	public void showProfits();
	public void showBalance();
	public int numSales();
	public void initSales();
	
	public void dispatchWatchersToSale(Watcher watcher);
	public void removeWatchersToSale();

	// Servicio de aplicación de Stock
	public void addProductToStock(int id, String name, String Tag, int amount, double price) throws ProductNotFoundException;
	public void removeProductToStock(int id, int amount) throws ProductNotFoundException, ProductAmountException;
	public TransferProduct searchProductToStock(int id, String productName) throws ProductNotFoundException;
	public void sortStock(SortMethod method);
	public void initStock();
	
	public void dispatchWatcherToStock(Watcher watcher);
	public void removeWatchersToStock();

	// Servicio de aplicación de Staff
	public void login(String username, String password) throws InvalidLoginException;
	public void logout(String username, String password);
	public void addUser(UserPermission permission, String employeeName, String userName, String password) throws DuplicateUserException;
	public void removeUser(String userName) throws UserNotFoundException;
	public void modifyUser(String userName, String password, UserPermission permisssion) throws UserNotFoundException;
	public TransferUser searchUser(String userName) throws UserNotFoundException;
	public void initUsers();

	public void dispatchWatcherToUser(Watcher watcher);
	public void removeWatchersToUser();

	// Controladores del modelo
	public Object getController(int event);

}