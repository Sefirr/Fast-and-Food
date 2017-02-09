package com.model.dto;

import java.io.Serializable;
import java.util.ArrayList;

public interface TransferSale extends Serializable {
	public int getSaleIdentifier();
	public void setSaleIdentifier(int saleIdentifier);
	public String getCustomer();
	public void setCustomer(String _customer);
	public ArrayList<TransferProduct> getProductList();
	public void setProductList(ArrayList<TransferProduct> productList);
	public String getDate();
	public void setDate(String date);
	public double getPrice();
	public void setPrice(double price);
	public boolean getFinished();
	public void setFinished(boolean finished);
	public void finished();
	public String displayContents();
	public String resumeContents();
	public String toFile();
	public String toString();

}