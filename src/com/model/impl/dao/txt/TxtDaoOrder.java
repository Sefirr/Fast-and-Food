package com.model.impl.dao.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.model.dao.DaoOrder;
import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;
import com.model.exceptions.FileErrorException;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.model.impl.dto.TransferOrderImp;
import com.model.impl.dto.TransferProductImp;
import com.util.Common;
import com.util.Tools;

public class TxtDaoOrder implements DaoOrder {
	
	private static TxtDaoOrder factory;
	
	public static TxtDaoOrder getInstance() {
		if(factory == null)
			factory = new TxtDaoOrder();
		
		return factory;
	}
	
	private TxtDaoOrder() {
		
	}
	
	@Override
	public ArrayList<TransferOrder> readOrders() {
		BufferedReader bf = null;
        Path input = Paths.get(Common.ORDERS_FILE);

        ArrayList<TransferOrder> orderList = new ArrayList<TransferOrder>();
        
        int orderIdentifier;
        String orderSupplier;
        ArrayList<TransferProduct> orderProductList;
        String orderDate;

        int productNumber = -1;
        int productID;
        String productName;
        String productTag;
        double productPrice;
        int productAmount = -1;
        boolean finished;
        boolean approved;

        try {
        	bf = Files.newBufferedReader(input, Charset.defaultCharset());
            String line = bf.readLine();

            if(line != null && Tools.isInteger(line))
                Tools.NUMORDERS = Integer.parseInt(line);

            bf.readLine(); // white line
            line = bf.readLine();
            
            while(line != null) {

                orderProductList = new ArrayList<TransferProduct>();

                orderIdentifier = (Tools.isInteger(line.trim())) ? Integer.parseInt(line) : -1;
                line = bf.readLine();

                orderSupplier = (!line.isEmpty()) ? line.trim() : null;
                bf.readLine(); // -- indicador de inicio de lista de productos
                line = bf.readLine(); // Número de productos

                if(Tools.isInteger(line))
                    productNumber = Integer.parseInt(line);

                bf.readLine(); // white line
                line = bf.readLine(); // Identificador de producto

                while(!line.equalsIgnoreCase("--")) {
                    productID = (Tools.isInteger(line.trim())) ? Integer.parseInt(line) : -1;
                    line = bf.readLine();

                    productName = (!line.isEmpty()) ? line.trim() : null;
                    line = bf.readLine();

                    productTag = (!line.isEmpty()) ? line.trim() : null;
                    line = bf.readLine();

                    productPrice = (Tools.isDouble(line.trim())) ? Double.parseDouble(line) : -1.0;
                    line = bf.readLine();

                    productAmount = (Tools.isInteger(line)) ? Integer.parseInt(line) : -1;

                    orderProductList.add(new TransferProductImp(productID, productName, productTag, productPrice, productAmount));
             
                    bf.readLine(); // white line
                    line = bf.readLine();
                };
            
            	line = bf.readLine(); 
            	orderDate = (!line.isEmpty()) ? line.trim() : null;
            	
            	line = bf.readLine();
            	finished = (line.equalsIgnoreCase("true")) ? true : false;
            	
            	line = bf.readLine();
            	approved = (line.equalsIgnoreCase("true")) ? true : false;
            	

            	orderList.add(new TransferOrderImp(orderIdentifier, orderSupplier, orderProductList, orderDate, finished, approved));


            	if(orderProductList.size() != productNumber)
            		throw new FileErrorException("El número de productos indicado en los pedidos no es correcto.");

            	bf.readLine(); // white line
            	line = bf.readLine();
            }

            if(orderList.size() != Tools.NUMORDERS)
            	throw new FileErrorException("El número de pedidos indicado no es correcto.");
        } catch (IOException e) {
            System.exit(2);
        } catch (FileErrorException e) {
				// TODO Auto-generated catch block
        	e.printStackTrace();
		} finally {
            try {
                bf.close();
            } catch (IOException e) {
                System.exit(1);
            }
        }
        
        return orderList;
		
	}
	
	@Override
	public void writeOrders(ArrayList<TransferOrder> orderList) {
		 Path writeFilePath = Paths.get(Common.ORDERS_FILE);
	        FileWriter writer = null;
	        String newLine = System.getProperty("line.separator");

	        if(Files.exists(writeFilePath)) {
	            try {

	                writer = new FileWriter(writeFilePath.toString());

	                if(orderList == null || orderList.size() == 0) {
	                    writer.write("0");
	                    return;
	                }

	                writer.write(orderList.size() + newLine + newLine);

	                for(TransferOrder o: orderList) {
	                    if(o.toString() != null)
	                        writer.write(o.toFile() + newLine);
	                    else
	                        writer.write(newLine);
	                }

	            } catch (IOException e) {
	                System.exit(2);

	            } finally {
	                try {
	                    writer.close();
	                } catch (IOException e) {
	                    System.exit(2);
	                }
	            }
	        }

	        else {
	            try {

	                File file = new File(Common.ORDERS_FILE);
	                writer = new FileWriter(file.toString());

	                if(orderList == null || orderList.size() == 0) {
	                    writer.write("0");
	                    return;
	                }

	                writer.write(orderList.size() + newLine + newLine);

	                for(TransferOrder o: orderList) {
	                    if(o.toString() != null)
	                        writer.write(o.toFile() + newLine);
	                    else
	                        writer.write(newLine);
	                }

	            } catch (IOException e) {
	                System.exit(2);

	            } finally {
	                try {
	                    writer.close();
	                } catch (IOException e) {
	                    System.exit(2);
	                }
	            }
	        }
	}
	
	@Override 
	public int numOrders() {
		ArrayList<TransferOrder> orderList = readOrders();
		
		return orderList.size();
	}
	
	@Override
	public void addProduct(TransferProduct p, TransferOrder o) {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		TransferOrder currentOrder = orderList.get(o.getOrderIdentifier());
		if(productFound(currentOrder, p)) {
			TransferProduct product = searchProduct(currentOrder, p);
			product.setAmount(product.getAmount() + p.getAmount());
		} else {
			ArrayList<TransferProduct> currentList = currentOrder.getProductList();
			currentList.add(p);
		}
		
		writeOrders(orderList);
		
	}

	@Override
	public void removeProduct(TransferProduct p, TransferOrder o) throws ProductAmountException,
			ProductNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		TransferOrder currentOrder = orderList.get(o.getOrderIdentifier());
		if(productFound(currentOrder, p)) {
			TransferProduct product = searchProduct(currentOrder, p);
			if(p.getAmount() == product.getAmount()) {
				ArrayList<TransferProduct> currentList = currentOrder.getProductList();
				int id = currentList.indexOf(p)+1;
				currentList.remove(id);
			} else {
				product.setAmount(product.getAmount() - p.getAmount());
			}
		}
		
		writeOrders(orderList);
		
	}

	@Override
	public void addOrder(TransferOrder o) {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		orderList.add(o);
		
		Tools.NUMORDERS++;
		writeOrders(orderList);
		
	}

	@Override
	public void deleteOrder(TransferOrder o) {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		
		for (int pos = 0; pos < orderList.size(); pos++) {
            if(orderList.get(pos).getOrderIdentifier() == o.getOrderIdentifier())
            	orderList.remove(pos);
            
        }

		Tools.NUMORDERS--;
		writeOrders(orderList);
		
	}

	@Override
	public void finishOrder(TransferOrder o) {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		TransferOrder currentOrder = orderList.get(o.getOrderIdentifier());
		
		if(o.getFinished()) {
			currentOrder.setFinished(true);
		} else {
			currentOrder.setFinished(false);
		}
		currentOrder.setSupplier(o.getSupplier());
		currentOrder.setDate(o.getDate());
		writeOrders(orderList);
		
	}
	
	public void cancelOrder(TransferOrder o) {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		
		for (int pos = 0; pos < orderList.size(); pos++) {
            if(orderList.get(pos).getOrderIdentifier() == o.getOrderIdentifier())
            	orderList.remove(pos);
            
        }

		Tools.NUMORDERS--;
		writeOrders(orderList);
	}
	
	@Override
	public boolean productFound(TransferOrder currentOrder, TransferProduct p) {
		// TODO Auto-generated method stub
		ArrayList<TransferProduct> currentList = currentOrder.getProductList();
		for(TransferProduct product: currentList) {
			if(product.getId() == p.getId()) {
				return true;
			}
		}
		
		return false;
		
	}

	@Override
	public TransferProduct searchProduct(TransferOrder currentOrder, TransferProduct p) {
		// TODO Auto-generated method stub
		ArrayList<TransferProduct> currentList = currentOrder.getProductList();
		for(TransferProduct product: currentList) {
			if(product.getId() == p.getId()) {
				return product;
			}
			if(product.getName().equalsIgnoreCase(p.getName())) {
				return product;
			}
		}
		
		return null;
		
	}

	@Override
	public void modifyOrder(TransferOrder o) {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		TransferOrder currentOrder = orderList.get(o.getOrderIdentifier());
		currentOrder.setOrderIdentifier(o.getOrderIdentifier());
		currentOrder.setSupplier(o.getSupplier());
		currentOrder.setProductList(o.getProductList());
		currentOrder.setFinished(o.getFinished());
		currentOrder.setApproved(o.getApproved());
		writeOrders(orderList);
		
	}
	
	@Override
	public TransferOrder searchOrder(TransferOrder o) {
		ArrayList<TransferOrder> orderList = readOrders();
	
		for (TransferOrder order: orderList) {
            if(order.getOrderIdentifier() == o.getOrderIdentifier())
            	return order;
        }
		
		return null;
		
	}

	@Override
	public void approved(TransferOrder o) {
		// TODO Auto-generated method stub
		ArrayList<TransferOrder> orderList = readOrders();
		TransferOrder currentOrder = orderList.get(o.getOrderIdentifier());	
		currentOrder.approved();
		writeOrders(orderList);
		
	}

}