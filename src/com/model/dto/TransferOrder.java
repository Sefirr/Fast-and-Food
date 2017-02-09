package com.model.dto;

import java.io.Serializable;
import java.util.ArrayList;

public interface TransferOrder extends Serializable {
	public int getOrderIdentifier();
	public void setOrderIdentifier(int orderIdentifier);
	public String getSupplier();
	public void setSupplier(String supplier);
	public ArrayList<TransferProduct> getProductList();
	public void setProductList(ArrayList<TransferProduct> productList);
	public boolean getFinished();
	public void setFinished(boolean finished);
	public boolean getApproved();
	public void setApproved(boolean approved);
	public void finished();
	public void approved();
	public String getDate();
	public void setDate(String date);
	public String displayContents();
	public String displayContentsMVC();
	public String resumeContents();
	public String toFile();
	public String toString();
	
}