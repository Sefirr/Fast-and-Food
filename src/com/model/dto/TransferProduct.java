package com.model.dto;

import java.io.Serializable;

public interface TransferProduct extends Serializable {
	public int getId();
	public String getName();
	public String getTag();
	public double getPrice();
	public int getAmount();
	public void setId(int _productIdentifier);
	public void setName(String _name);
	public void setTag(String _tag);
	public void setAmount(int _amount);
	public void setPrice(double _price);
	public String toString();
	
}