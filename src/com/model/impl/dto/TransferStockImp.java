package com.model.impl.dto;

import java.util.ArrayList;
import java.util.Iterator;

import com.model.dto.TransferProduct;
import com.model.dto.TransferStock;

@SuppressWarnings("serial")
public class TransferStockImp implements TransferStock {

	private ArrayList<TransferProduct> _productList;

	public TransferStockImp() {

	}

	public ArrayList<TransferProduct> getProductList() {
		return _productList;
	}

	public void setProductList(ArrayList<TransferProduct> _productList) {
		this._productList = _productList;
	}

	public String displayContents() {
		Iterator<TransferProduct> it = _productList.iterator();
		String contents = "";

		while (it.hasNext()) {
			TransferProduct n = it.next();
			contents += n.getId() + "_" + n.getName() + "_" + n.getTag() + "_"
					+ n.getAmount() + "_" + n.getPrice() + "_";
		}

		return contents;

	}

	public String toFile() {
		Iterator<TransferProduct> it = _productList.iterator();

		if (!it.hasNext())
			return null;

		StringBuilder sb = new StringBuilder();

		do {
			TransferProduct n = it.next();
			sb.append(n.toString());
			sb.append("\n");
		} while (it.hasNext());

		return sb.toString();
	}

	@Override
	public String toString() {
		Iterator<TransferProduct> it = _productList.iterator();

		if (!it.hasNext())
			return "Vacío";

		StringBuilder sb = new StringBuilder();
		sb.append("Almacén:\n\n");

		do {
			TransferProduct n = it.next();
			sb.append(n.toString());
			sb.append("\n");
		} while (it.hasNext());

		return sb.toString();
	}

}