package com.model.impl;

import java.util.ArrayList;

import com.controller.command.bussinessEvent;
import com.model.orderService;
import com.model.dao.DaoFactory;
import com.model.dao.DaoOrder;
import com.model.dao.DaoStock;
import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;
import com.model.exceptions.ApproveOrderException;
import com.model.exceptions.DuplicateProductException;
import com.model.exceptions.OrderNotFoundException;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.model.impl.dto.TransferOrderImp;
import com.model.impl.dto.TransferProductImp;
import com.util.Tools;
import com.util.Watchable;
import com.util.Watcher;

public class orderServiceImp extends Watchable implements orderService {

	@Override
	public void addProduct(int id, String name, String Tag, int amount,
			double price, int orderIdentiffier) {
		// TODO Auto-generated method stub

		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();
		DaoStock stockDAO = factory.getDAOStock();

		try {
			TransferProduct orderById = new TransferProductImp();
			orderById.setId(id);
			orderById.setName(name);
			orderById.setTag(Tag);
			orderById.setAmount(amount);
			orderById.setPrice(price);

			TransferProduct buscadoEnStock = stockDAO.searchProduct(orderById);

			TransferOrder orderId = new TransferOrderImp();
			orderId.setOrderIdentifier(orderIdentiffier);

			TransferOrder currentOrder = orderDAO.searchOrder(orderId);

			// Condiciones para añadir producto - Si tienen un mismo nombre pero
			// las ids son distintas y si tienen un mismo id pero los nombres
			// son distintos - ERROR
			if (buscadoEnStock != null) {
				if ((buscadoEnStock.getName().equalsIgnoreCase(orderById
						.getName()))
						&& !(buscadoEnStock.getId() == orderById.getId()))
					throw new DuplicateProductException(
							"Ya existe un producto con este nombre asociado.");
				if (!(buscadoEnStock.getName().equalsIgnoreCase(orderById
						.getName()))
						&& (buscadoEnStock.getId() == orderById.getId()))
					throw new DuplicateProductException(
							"Ya existe un producto con este identificador asociado.");
				if(amount < 1)
					throw new ProductAmountException(
							"El producto con identificador " + id + 
							" no se puede añadir con una cantidad inferior a la unidad .");
			}
			

			orderDAO.addProduct(orderById, orderId);

			currentOrder = orderDAO.searchOrder(orderId);

			this.setChanged();
			this.notifyObservers(bussinessEvent.PEDIDO_ACTUAL + " " + orderIdentiffier + "," 
					+ currentOrder.displayContents());

			this.setChanged();
			this.notifyObservers(bussinessEvent.AÑADIR_PRODUCTO_PEDIDO + " ");
		} catch (ProductAmountException | DuplicateProductException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_ADDPRODUCT + " "
					+ e.getMessage());
			
		}

	}

	@Override
	public void removeProduct(int id, int amount, int orderIdentiffier) {
		// TODO Auto-generated method stub

		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		try {
			TransferProduct orderById = new TransferProductImp();
			orderById.setId(id);
			orderById.setAmount(amount);

			TransferOrder orderId = new TransferOrderImp();
			orderId.setOrderIdentifier(orderIdentiffier);

			TransferOrder currentOrder = orderDAO.searchOrder(orderId);

			TransferProduct buscado = orderDAO.searchProduct(currentOrder,
					orderById);

			if (!orderDAO.productFound(currentOrder, orderById))
				throw new ProductNotFoundException(
						"El producto con identificador " + id
								+ " no se encuentra en el pedido.");
			if (orderDAO.productFound(currentOrder, orderById)
					&& !Tools.availableProduct(buscado.getAmount(), amount))
				throw new ProductAmountException(
						"El producto con identificador "
								+ id
								+ " se encuentra en una cantidad inferior en el pedido.");
			if(amount < 1)
				throw new ProductAmountException(
						"El producto con identificador " + id + 
						" no se puede añadir con una cantidad inferior a la unidad .");

			orderDAO.removeProduct(orderById, currentOrder);

			currentOrder = orderDAO.searchOrder(orderId);

			this.setChanged();
			this.notifyObservers(bussinessEvent.PEDIDO_ACTUAL + " " + orderIdentiffier + ","
					+ currentOrder.displayContents());

			this.setChanged();
			this.notifyObservers(bussinessEvent.ELIMINAR_PRODUCTO_PEDIDO + " ");
		} catch (ProductNotFoundException | ProductAmountException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_REMOVEPRODUCT + " "
					+ e.getMessage());

		}

	}
	
	@Override
	public void addProductModify(int id, String name, String Tag, int amount,
			double price, int orderIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();
		DaoStock stockDAO = factory.getDAOStock();

		try {
			TransferProduct orderById = new TransferProductImp();
			orderById.setId(id);
			orderById.setName(name);
			orderById.setTag(Tag);
			orderById.setAmount(amount);
			orderById.setPrice(price);

			TransferProduct buscadoEnStock = stockDAO.searchProduct(orderById);

			TransferOrder orderId = new TransferOrderImp();
			orderId.setOrderIdentifier(orderIdentiffier);

			TransferOrder currentOrder = orderDAO.searchOrder(orderId);
			
			if(currentOrder.getSupplier().equalsIgnoreCase("null") || currentOrder.getDate().equalsIgnoreCase("null")) {
				throw new OrderNotFoundException("No se pueden modificar pedidos "
						+ "que aún no estén hechos.");
			}
			
			if(currentOrder.getFinished()) {
				throw new OrderNotFoundException("No se pueden modificar pedidos "
						+ "que estén finalizados.");
			}

			// Condiciones para añadir producto - Si tienen un mismo nombre pero
			// las ids son distintas y si tienen un mismo id pero los nombres
			// son distintos - ERROR
			if (buscadoEnStock != null) {
				if ((buscadoEnStock.getName().equalsIgnoreCase(orderById
						.getName()))
						&& !(buscadoEnStock.getId() == orderById.getId()))
					throw new DuplicateProductException(
							"Ya existe un producto con este nombre asociado.");
				if (!(buscadoEnStock.getName().equalsIgnoreCase(orderById
						.getName()))
						&& (buscadoEnStock.getId() == orderById.getId()))
					throw new DuplicateProductException(
							"Ya existe un producto con este identificador asociado.");
			}

			orderDAO.addProduct(orderById, orderId);
			
			this.setChanged();
			this.notifyObservers(bussinessEvent.AÑADIR_PRODUCTO_PEDIDO_MODIFY + " ");
		} catch (DuplicateProductException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_ADDPRODUCT + " "
					+ e.getMessage());
		} catch (OrderNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_MODIFYORDER + " "
					+ e.getMessage());
		}
	}

	@Override
	public void removeProductModify(int id, int amount, int orderIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		try {
			TransferProduct orderById = new TransferProductImp();
			orderById.setId(id);
			orderById.setAmount(amount);

			TransferOrder orderId = new TransferOrderImp();
			orderId.setOrderIdentifier(orderIdentiffier);

			TransferOrder currentOrder = orderDAO.searchOrder(orderId);
			
			if(currentOrder.getSupplier().equalsIgnoreCase("null") || currentOrder.getDate().equalsIgnoreCase("null")) {
				throw new OrderNotFoundException("No se pueden modificar pedidos "
						+ "que aún no estén hechos.");
			}
			
			if(currentOrder.getFinished()) {
				throw new OrderNotFoundException("No se pueden modificar pedidos "
						+ "que estén finalizados.");
			}

			TransferProduct buscado = orderDAO.searchProduct(currentOrder,
					orderById);

			if (!orderDAO.productFound(currentOrder, orderById))
				throw new ProductNotFoundException(
						"El producto con identificador " + id
								+ " no se encuentra en el pedido.");
			if (orderDAO.productFound(currentOrder, orderById)
					&& !Tools.availableProduct(buscado.getAmount(), amount))
				throw new ProductAmountException(
						"El producto con identificador "
								+ id
								+ " se encuentra en una cantidad inferior en el pedido.");

			orderDAO.removeProduct(orderById, currentOrder);

			currentOrder = orderDAO.searchOrder(orderId);

			this.setChanged();
			this.notifyObservers(bussinessEvent.ELIMINAR_PRODUCTO_PEDIDO_MODIFY + " ");
		} catch (ProductNotFoundException | ProductAmountException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_REMOVEPRODUCT + " "
					+ e.getMessage());

		} catch(OrderNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_MODIFYORDER + " "
					+ e.getMessage());
		}
		
	}


	@Override
	public void addOrder(int orderIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		TransferOrder newOrder = new TransferOrderImp();
		int identifier = new Integer(orderIdentiffier);
		
		// Comprobación de si existe ya el pedido. En ese caso, se le asigna una id nueva.
		while(searchOrderMethod(identifier) != null) {
			identifier++;
		}
		
		newOrder.setOrderIdentifier(identifier);

		orderDAO.addOrder(newOrder);

		this.setChanged();
		this.notifyObservers(bussinessEvent.AÑADIR_PEDIDO + " ");

	}

	@Override
	public void deleteOrder(int id) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		try {
			TransferOrder o = new TransferOrderImp();
			o.setOrderIdentifier(id);
			
			TransferOrder currentOrder = orderDAO.searchOrder(o);

			if (currentOrder == null) {
				throw new OrderNotFoundException("El pedido con identificador "
						+ o.getOrderIdentifier() + " no existe.");
			} else {
				if(!currentOrder.getFinished() || !currentOrder.getApproved()) {
					throw new OrderNotFoundException("No se pueden eliminar pedidos "
							+ "que no estén finalizados o confirmados.");
				}
			
			}

			orderDAO.deleteOrder(o);

			this.setChanged();
			this.notifyObservers(bussinessEvent.ELIMINAR_PEDIDO + " ");
		} catch (OrderNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_DELETEORDER + " "
					+ e.getMessage());

			throw e;
		}

	}

	@Override
	public void finishOrder(int orderIdentiffier, String supplier, String date, int orderState) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		TransferOrder o = new TransferOrderImp();
		o.setOrderIdentifier(orderIdentiffier);
		o.setSupplier(supplier);
		o.setDate(date);
		
		if(orderState == 1) {
			o.setFinished(true);
		} else if(orderState == 0) {
			o.setFinished(false);
		}

		orderDAO.finishOrder(o);

		this.setChanged();
		this.notifyObservers(bussinessEvent.FINALIZAR_PEDIDO + " ");

	}
	
	@Override
	public void cancelOrder(int orderIdentiffier) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		TransferOrder o = new TransferOrderImp();
		o.setOrderIdentifier(orderIdentiffier);

		orderDAO.cancelOrder(o);

		this.setChanged();
		this.notifyObservers(bussinessEvent.CANCELAR_PEDIDO + " ");
		
	}
	
	private TransferOrder searchOrderMethod(int id) {

		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		try {
			
			TransferOrder o = new TransferOrderImp();
			o.setOrderIdentifier(id);
			
			TransferOrder currentOrder = orderDAO.searchOrder(o);
			
			if(currentOrder == null)
				throw new OrderNotFoundException("El pedido con identificador " + o.getOrderIdentifier() + " no se existe.");
				
			return currentOrder;
		} catch(OrderNotFoundException e) {
		
		}
		return null;	

	}

	public TransferOrder searchOrder(int id) throws OrderNotFoundException {

		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		try {
			
			TransferOrder o = new TransferOrderImp();
			o.setOrderIdentifier(id);
			
			TransferOrder currentOrder = orderDAO.searchOrder(o);
			
			if(currentOrder == null)
				throw new OrderNotFoundException("El pedido con identificador " + o.getOrderIdentifier() + " no existe.");
			
			this.setChanged();
			this.notifyObservers(bussinessEvent.MOSTRAR_PEDIDO + " " + currentOrder.displayContents());
	
			return currentOrder;
		} catch(OrderNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_SEARCHORDER + " " + e.getMessage());
			
			throw e;
		
		}	

	}

	@Override
	public void modifyOrder(int orderIdentifier, String supplier,
			ArrayList<TransferProduct> productList, boolean finished,
			boolean approved) {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		try {
			TransferOrder o = new TransferOrderImp();
			o.setOrderIdentifier(orderIdentifier);
			o.setSupplier(supplier);
			o.setProductList(productList);
			o.setFinished(finished);
			o.setApproved(approved);

			TransferOrder currentOrder = orderDAO.searchOrder(o); 			
			if (currentOrder == null) {
				throw new OrderNotFoundException("El pedido con identificador "
						+ o.getOrderIdentifier() + " no existe.");
			} else {
				if(currentOrder.getSupplier().equalsIgnoreCase("null") || currentOrder.getDate().equalsIgnoreCase("null")) {
					throw new OrderNotFoundException("No se pueden modificar pedidos "
							+ "que aún no estén hechos.");
				}
				
				if(currentOrder.getFinished()) {
					throw new OrderNotFoundException("No se pueden modificar pedidos "
							+ "que estén finalizados.");
				}
				
				orderDAO.modifyOrder(o);

				this.setChanged();
				this.notifyObservers(bussinessEvent.MODIFICAR_PEDIDO + " ");
			}
			
		} catch (OrderNotFoundException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_MODIFYORDER + " "
					+ e.getMessage());
			
		}

	}

	@Override
	public void approve(int id) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		try {
			TransferOrder o = new TransferOrderImp();
			o.setOrderIdentifier(id);

			TransferOrder currentOrder = orderDAO.searchOrder(o);
			if(currentOrder != null) {
				if (!currentOrder.getFinished())
					throw new ApproveOrderException("El pedido con identificador "
							+ o.getOrderIdentifier() + " aún no ha sido finalizado.");
				if (currentOrder.getApproved())
					throw new ApproveOrderException("El pedido con identificador "
							+ o.getOrderIdentifier() + " ya ha sido confirmado.");
				
				orderDAO.approved(o);	
				
				this.setChanged();
				this.notifyObservers(bussinessEvent.CONFIRMAR_PEDIDO + " ");
			}
			
		} catch (ApproveOrderException e) {
			this.setChanged();
			this.notifyObservers(bussinessEvent.ERROR_APPROVEORDER + " "
					+ e.getMessage());

		}

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
	public void reloadOrders() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		ArrayList<TransferOrder> orderList = orderDAO.readOrders();
		String contents = String.valueOf(bussinessEvent.ACTUALIZAR_PEDIDOS)
				+ " ";

		for (TransferOrder o : orderList) {
			contents += o.resumeContents();
		}

		this.setChanged();
		this.notifyObservers(contents);

	}

	@Override
	public void reloadApprovedOrders() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		ArrayList<TransferOrder> orderList = orderDAO.readOrders();
		String contents = String
				.valueOf(bussinessEvent.ACTUALIZAR_PEDIDOSCONFIRMADOS) + " ";

		for (TransferOrder o : orderList) {
			if (!o.getApproved())
				contents += o.resumeContents();
		}

		this.setChanged();
		this.notifyObservers(contents);

	}

	@Override
	public int numOrders() {
		// TODO Auto-generated method stub
		DaoFactory factory = DaoFactory.getDAOFactory(DaoFactory.TXT);
		DaoOrder orderDAO = factory.getDAOOrder();

		return orderDAO.numOrders();

	}

}