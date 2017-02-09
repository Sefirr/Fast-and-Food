package com.model.impl;

import java.util.ArrayList;

import com.controller.orderController;
import com.controller.saleController;
import com.controller.stockController;
import com.controller.userController;
import com.model.Shop;
import com.model.orderService;
import com.model.saleService;
import com.model.userService;
import com.model.stockService;
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

public class shopImp implements Shop {
	
	private orderService _servicioDePedidos;
	private saleService _servicioDeVentas;
	private stockService _servicioDeStock;
	private userService _servicioDeUsuarios;
	
	public shopImp() {
		this._servicioDePedidos = new orderServiceImp();
		this._servicioDeVentas = new saleServiceImp();
		this._servicioDeStock = new stockServiceImp();
		this._servicioDeUsuarios = new userServiceImp();
	}
	
	@Override
	public void addProductToOrder(int id, String name, String Tag, int amount, double price, int orderIdentiffier) throws ProductAmountException {
		// TODO Auto-generated method stub
		_servicioDePedidos.addProduct(id, name, Tag, amount, price, orderIdentiffier);
	}

	@Override
	public void removeProductToOrder(int id, int amount, int orderIdentiffier) {
		// TODO Auto-generated method stub
		_servicioDePedidos.removeProduct(id, amount, orderIdentiffier);
	}
	
	@Override
	public void addProductToOrderModify(int id, String name, String Tag,
			int amount, double price, int orderIdentiffier) throws DuplicateProductException {
		// TODO Auto-generated method stub
		_servicioDePedidos.addProductModify(id, name, Tag, amount, price, orderIdentiffier);
		
	}

	@Override
	public void removeProductToOrderModify(int id, int amount,
			int orderIdentiffier) throws ProductNotFoundException,
			ProductAmountException {
		// TODO Auto-generated method stub
		_servicioDePedidos.removeProductModify(id, amount, orderIdentiffier);
	}
	
	@Override
	public int numOrders() {
		// TODO Auto-generated method stub
		return _servicioDePedidos.numOrders();
	}

	@Override
	public void addOrder(int orderIdentiffier) {
		// TODO Auto-generated method stub
		_servicioDePedidos.addOrder(orderIdentiffier);
	}

	@Override
	public void deleteOrder(int orderIdentiffier) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		_servicioDePedidos.deleteOrder(orderIdentiffier);
	}

	@Override
	public void finishOrder(int orderIdentiffier, String supplier, String fecha, int orderState) {
		// TODO Auto-generated method stub
		_servicioDePedidos.finishOrder(orderIdentiffier, supplier, fecha, orderState);
	}
	
	@Override
	public void cancelOrder(int orderIdentiffier) {
		// TODO Auto-generated method stub
		_servicioDePedidos.cancelOrder(orderIdentiffier);
	}


	@Override
	public TransferOrder searchOrder(int id) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		return _servicioDePedidos.searchOrder(id);
	}

	@Override
	public void modifyOrder(int orderIdentifier, String supplier, ArrayList<TransferProduct> productList, boolean finished, boolean approved) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		_servicioDePedidos.modifyOrder(orderIdentifier, supplier, productList, finished, approved);
	}

	@Override
	public void approveOrder(int id) throws OrderNotFoundException, ApproveOrderException {
		// TODO Auto-generated method stub
		_servicioDePedidos.approve(id);
	}

	@Override
	public void addProductToSale(int id, int amount, int saleIdentiffier) {
		// TODO Auto-generated method stub
		_servicioDeVentas.addProduct(id, amount, saleIdentiffier);
	}

	@Override
	public void removeProductToSale(int id, int amount, int saleIdentiffier) {
		// TODO Auto-generated method stub
		_servicioDeVentas.removeProduct(id, amount, saleIdentiffier);
	}
	
	@Override 
	public int numSales() {
		return _servicioDeVentas.numSales();
	}

	@Override
	public void addSale(int id) {
		// TODO Auto-generated method stub
		_servicioDeVentas.addSale(id);
	}

	@Override
	public void deleteSale(int id) throws SaleNotFoundException {
		// TODO Auto-generated method stub
		_servicioDeVentas.deleteSale(id);
	}
	
	@Override
	public void saveSale(int currentSale) {
		// TODO Auto-generated method stub
		_servicioDeVentas.saveSale(currentSale);
	}

	@Override
	public void finishSale(int saleIdentiffier, String customer, String date) {
		// TODO Auto-generated method stub
		_servicioDeVentas.finishSale(saleIdentiffier, customer, date);
	}

	@Override
	public void cancelSale(int saleIdentiffier) {
		// TODO Auto-generated method stub
		_servicioDeVentas.cancelSale(saleIdentiffier);
	}

	@Override
	public TransferSale searchSale(int id) throws SaleNotFoundException {
		// TODO Auto-generated method stub
		return _servicioDeVentas.searchSale(id);
	}

	@Override
	public void generateInvoicing(int saleIdentiffier) {
		// TODO Auto-generated method stub
		_servicioDeVentas.generateInvoicing(saleIdentiffier);
	}
	
	@Override
	public void showExchange(double pagado, int saleIdentiffier) throws ExchangeException {
		// TODO Auto-generated method stub
		_servicioDeVentas.showExchange(pagado, saleIdentiffier);
	}

	@Override
	public void showProfits() {
		// TODO Auto-generated method stub
		_servicioDeVentas.showProfits();
	}

	@Override
	public void showBalance() {
		// TODO Auto-generated method stub
		_servicioDeVentas.showBalance();
	}

	@Override
	public void addProductToStock(int id, String name, String tag, int amount, double price) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		_servicioDeStock.addProduct(id, name, tag, amount, price);
	}

	@Override
	public void removeProductToStock(int id, int amount) throws ProductNotFoundException, ProductAmountException {
		// TODO Auto-generated method stub
		_servicioDeStock.removeProduct(id, amount);
	}

	@Override
	public TransferProduct searchProductToStock(int id, String productName) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return _servicioDeStock.searchProduct(id, productName);
	}

	@Override
	public void sortStock(SortMethod method) {
		// TODO Auto-generated method stub
		_servicioDeStock.sortStock(method);
	}

	@Override
	public void login(String username, String password) throws InvalidLoginException {
		// TODO Auto-generated method stub
		_servicioDeUsuarios.login(username, password);
	}

	@Override
	public void logout(String username, String password) {
		// TODO Auto-generated method stub
		_servicioDeUsuarios.logout(username, password);
	}

	@Override
	public void addUser(UserPermission permission, String employeeName, String userName, String password) throws DuplicateUserException {
		// TODO Auto-generated method stub
		_servicioDeUsuarios.addUser(permission, employeeName, userName, password);
	}

	@Override
	public void removeUser(String userName) throws UserNotFoundException {
		// TODO Auto-generated method stub
		_servicioDeUsuarios.removeUser(userName);
	}

	@Override
	public void modifyUser(String userName, String password, UserPermission permission) throws UserNotFoundException {
		// TODO Auto-generated method stub
		_servicioDeUsuarios.modifyUser(userName, password, permission);
	}

	@Override
	public TransferUser searchUser(String userName) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return _servicioDeUsuarios.searchUser(userName);
	}
	
	@Override
	public void dispatchWatcherToStock(Watcher watcher) {
		// TODO Auto-generated method stub
		_servicioDeStock.dispatchWatcher(watcher);
	}
	
	@Override
	public void removeWatchersToStock() {
		// TODO Auto-generated method stub
		_servicioDeStock.removeObservers();
	}

	@Override
	public void dispatchWatcherToUser(Watcher watcher) {
		// TODO Auto-generated method stub
		_servicioDeUsuarios.dispatchWatcher(watcher);
		
	}

	@Override
	public void removeWatchersToUser() {
		// TODO Auto-generated method stub
		_servicioDeUsuarios.removeWatchers();
	}

	@Override
	public void dispatchWatchersToSale(Watcher watcher) {
		// TODO Auto-generated method stub
		_servicioDeVentas.dispatchWatcher(watcher);
	}

	@Override
	public void removeWatchersToSale() {
		// TODO Auto-generated method stub
		_servicioDeVentas.removeWatchers();
	}
	
	@Override
	public void dispatchWatcherToOrder(Watcher watchers) {
		// TODO Auto-generated method stub
		_servicioDePedidos.dispatchWatcher(watchers);
	}

	@Override
	public void removeWatchersToOrder() {
		// TODO Auto-generated method stub
		_servicioDePedidos.removeWatchers();
	}
	
	@Override
	public void initStock() {
		// TODO Auto-generated method stub
		_servicioDeStock.reloadStock();
	}
	
	@Override
	public void initSales() {
		// TODO Auto-generated method stub
		_servicioDeVentas.reloadSales();
	}
	
	@Override
	public void initUsers() {
		_servicioDeUsuarios.reloadUsers();
	}

	@Override
	public void initOrders() {
		// TODO Auto-generated method stub
		_servicioDePedidos.reloadOrders();
	}

	@Override
	public void initApprovedOrders() {
		// TODO Auto-generated method stub
		_servicioDePedidos.reloadApprovedOrders();
	}

	@Override
	public Object getController(int event) {
		// TODO Auto-generated method stub
		if(event == 0) {
			return new orderController(_servicioDePedidos);
		} else if(event == 1) {
			return new saleController(_servicioDeVentas);
		} else if(event == 2) {
			return new stockController(_servicioDeStock);
		} else if(event == 3) {
			return new userController(_servicioDeUsuarios);
		}
		
		return null;
	}	

}