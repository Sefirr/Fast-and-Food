package com.model.impl.dto;

import com.model.dto.TransferProduct;

@SuppressWarnings("serial")
public class TransferProductImp implements TransferProduct {
	private int _productIdentifier;

	private String _name;
	private String _tag;

	private int _amount;

	private double _price;

	public TransferProductImp() {
		this._name = new String();
		this._tag = new String();
		this._amount = 0;
		this._price = 0;
	}

	public TransferProductImp(int id, String name, String tag, double price,
			int amount) {
		this._productIdentifier = id;
		this._name = name;
		this._tag = tag;
		this._price = price;
		this._amount = amount;

	}

	public int getId() {
		return this._productIdentifier;

	}

	public String getName() {
		return this._name;

	}

	public String getTag() {
		return this._tag;

	}

	public double getPrice() {
		return this._price;

	}

	public int getAmount() {
		return this._amount;

	}

	public void setId(int _productIdentifier) {
		this._productIdentifier = _productIdentifier;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public void setTag(String _tag) {
		this._tag = _tag;
	}

	public void setAmount(int _amount) {
		this._amount = _amount;
	}

	public void setPrice(double _price) {
		this._price = _price;
	}

	@Override
	public String toString() {
		return this._productIdentifier + "\n" + this._name + "\n"
				+ this._tag.toString() + "\n" + this._price + "\n"
				+ this._amount + "\n";
	}
}
