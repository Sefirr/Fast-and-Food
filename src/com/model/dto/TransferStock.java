package com.model.dto;

import java.io.Serializable;
import java.util.ArrayList;

public interface TransferStock extends Serializable {
	public ArrayList<TransferProduct> getProductList();
	public void setProductList(ArrayList<TransferProduct> _productList);
	public String displayContents();
	public String toFile();
	public String toString();
	
}