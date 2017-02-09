package com.model.dao;
import java.util.ArrayList;

import com.model.dto.TransferProduct;
import com.model.dto.TransferSale;
import com.model.exceptions.ExchangeException;


public interface DaoSale {
	public ArrayList<TransferSale> readSales();
	public TransferSale searchSale(TransferSale s);
	public void writeSales(ArrayList<TransferSale> saleList);
	public void addProduct(TransferProduct p, TransferSale s);
	public void removeProduct(TransferProduct p, TransferSale s);
	public void addSale(TransferSale s);
	public void deleteSale(TransferSale s);
	public void finishSale(TransferSale s);
	public void cancelSale(TransferSale s);
	public boolean productFound(TransferSale currentSale, TransferProduct p);
	public TransferProduct searchProduct(TransferSale currentSale, TransferProduct p);
	public void generateInvoicing(TransferSale s);
	public double showExchange(double pagado, TransferSale s) throws ExchangeException;
	public double showProfits();
	public double showBalance();
	public int numSales();
	
}