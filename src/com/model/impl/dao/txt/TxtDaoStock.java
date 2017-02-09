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
import java.util.Collections;

import com.model.dao.DaoStock;
import com.model.dto.TransferProduct;
import com.model.dto.TransferStock;
import com.model.exceptions.FileErrorException;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;
import com.model.impl.dto.TransferProductImp;
import com.model.impl.dto.TransferStockImp;
import com.model.productComparators.productAmountComparator;
import com.model.productComparators.productNameComparator;
import com.model.productComparators.productPriceComparator;
import com.model.productComparators.productTagComparator;
import com.util.Common;
import com.util.Tools;
import com.util.Tools.SortMethod;


public class TxtDaoStock implements DaoStock {
	
	private static TxtDaoStock factory;
	
	public static TxtDaoStock getInstance() {
		if(factory == null)
			factory = new TxtDaoStock();
		
		return factory;
	}
	
	private TxtDaoStock() {
		
	}
	
	@Override
	public TransferStock readStock() {
		// TODO Auto-generated method stub
		BufferedReader bf = null;
        Path input = Paths.get(Common.STOCK_FILE);
        ArrayList<TransferProduct> stockList = new ArrayList<TransferProduct>();

        int productID;
        String productName;
        String productTag;
        double productPrice;
        int productAmount;

        try {
            bf = Files.newBufferedReader(input, Charset.defaultCharset());
            String line = bf.readLine();

            if(line != null && Tools.isInteger(line))
                Tools.NUMPRODUCTS = Integer.parseInt(line.trim());

            bf.readLine(); // white line
            line = bf.readLine();

            while(line != null) {

                productID = (Tools.isInteger(line.trim())) ? Integer.parseInt(line) : -1;
                line = bf.readLine();

                productName = (!line.isEmpty()) ? line.trim() : null;
                line = bf.readLine();

                productTag = (!line.isEmpty()) ? line.trim() : null;
                line = bf.readLine();

                productPrice = (Tools.isDouble(line.trim())) ? Double.parseDouble(line) : -1.0;
                line = bf.readLine();

                productAmount = (Tools.isInteger(line.trim())) ? Integer.parseInt(line) : -1;
                TransferProduct product = new TransferProductImp(productID, productName, productTag, productPrice, productAmount);
                stockList.add(product);

                bf.readLine(); // white line
                line = bf.readLine();
            } 

	        if(stockList.size() != Tools.NUMPRODUCTS)
	            throw new FileErrorException("El número de productos indicado no es correcto.");
	        
        } catch (IOException ioe) {
            System.exit(2);

        } catch (FileErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                bf.close();
            } catch (IOException ioe) {
                System.exit(1);
            }
        }  

        TransferStock  stock = new TransferStockImp();
        stock.setProductList(stockList);
        return stock;
	}
	
	@Override
	public void writeStock(TransferStock stock) {
		Path writeFilePath = Paths.get(Common.STOCK_FILE);
        FileWriter writer = null;
        String newLine = System.getProperty("line.separator");
        
        ArrayList<TransferProduct> productList = stock.getProductList();

        if(Files.exists(writeFilePath)) {
            try {

                writer = new FileWriter(writeFilePath.toString());

                if(stock == null || productList.size() == 0) {
                    writer.write("0");
                    return;
                }

                writer.write(productList.size() + newLine + newLine);
                writer.write(stock.toFile());

            } catch (IOException e1) {
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

                File file = new File(Common.STOCK_FILE);
                writer = new FileWriter(file.toString());

                if(stock == null || productList.size() == 0) {
                    writer.write("0");
                    return;
                }

                writer.write(productList.size() + newLine + newLine);
                writer.write(stock.toFile());

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
	public void addProduct(TransferProduct p) {
		// TODO Auto-generated method stub
		TransferStock stock = readStock();
		ArrayList<TransferProduct> productList = stock.getProductList();
		if(productFound(p.getId(), p.getName())) {
			TransferProduct product = searchProduct(stock, p);
			product.setAmount(product.getAmount() + p.getAmount());
			product.setPrice(p.getPrice());
		} else {
			Tools.NUMPRODUCTS++;
			productList.add(p);
		}
		
		writeStock(stock);
	}

	@Override
	public void removeProduct(TransferProduct p) throws ProductNotFoundException, ProductAmountException {
		// TODO Auto-generated method stub
		TransferStock stock = readStock();
		TransferProduct product = searchProduct(stock, p);
		product.setAmount(product.getAmount() - p.getAmount());
	
		writeStock(stock);
		
	}

	@Override
	public TransferProduct searchProduct(TransferStock stock, TransferProduct p) {
		// TODO Auto-generated method stub
		ArrayList<TransferProduct> currentList = stock.getProductList();
		for(TransferProduct product: currentList) {
			if(product.getId() == p.getId()) {
				return product;
			}
			if(product.getName().equalsIgnoreCase(p.getName()))
				return product;
		}
		
		return null;
	}
	
	@Override
	public TransferProduct searchProduct(TransferProduct p) {
		// TODO Auto-generated method stub
		TransferStock stock = readStock();
		ArrayList<TransferProduct> currentList = stock.getProductList();
		for(TransferProduct product: currentList) {
			if(product.getName().equalsIgnoreCase(p.getName())) 
				return product;
			if(product.getId() == p.getId()) {
				return product;
			}
		}
		
		return null;
	}

	@Override
	public TransferStock sortStock(SortMethod method) {
		// TODO Auto-generated method stub
		TransferStock stock = readStock();
		ArrayList<TransferProduct> productList = stock.getProductList();
		switch(method) {
        case NOMBRE:
            Collections.sort(productList, new productNameComparator());
            break;
        case CANTIDAD:
            Collections.sort(productList, new productAmountComparator());
            Collections.reverse(productList);
            break;
        case TAG:
            Collections.sort(productList, new productTagComparator());
            break;
        case PRECIO:
            Collections.sort(productList, new productPriceComparator());
            break;
        }
		
		stock.setProductList(productList);
		writeStock(stock);
		
		return stock;

	}

	@Override
	public boolean productFound(int id) {
		// TODO Auto-generated method stub
		TransferStock stock = readStock();
		ArrayList<TransferProduct> currentList = stock.getProductList();
		for(TransferProduct p: currentList) {
			if((p.getId() == id)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean productFound(int id, String name) {
		// TODO Auto-generated method stub
		TransferStock stock = readStock();
		ArrayList<TransferProduct> currentList = stock.getProductList();
		for(TransferProduct p: currentList) {
			if((p.getId() == id) && (p.getName().equalsIgnoreCase(name))) 
				return true;
		}
		
		return false;
	}

}