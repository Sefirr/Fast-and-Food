package com.model.impl.dto;

import java.util.ArrayList;
import java.util.Iterator;

import com.model.dto.TransferProduct;
import com.model.dto.TransferSale;

@SuppressWarnings("serial")
public class TransferSaleImp implements TransferSale {

	private int _saleIdentifier;
	private String _customer;
    private ArrayList<TransferProduct> _productList;
    private String _date;
    private double _price;
    private boolean _finished;
    
    public TransferSaleImp() {
    	this._price = 0;
    	this._productList = new ArrayList<TransferProduct>();
		this._finished = false;
    }

	public TransferSaleImp(int saleIdentifier, String customer, ArrayList<TransferProduct> saleProductList, String date, double salePrice, boolean state) {
		// TODO Auto-generated constructor stub
		this._saleIdentifier = saleIdentifier;
		this._customer = customer;
		this._productList = saleProductList;
		this._date = date;
		this._price = salePrice;
		this._finished = state;
	}



	public int getSaleIdentifier() {
		return _saleIdentifier;
	}

	public void setSaleIdentifier(int saleIdentifier) {
		this._saleIdentifier = saleIdentifier;
	}

	public String getCustomer() {
		return _customer;
	}



	public void setCustomer(String _customer) {
		this._customer = _customer;
	}



	public ArrayList<TransferProduct> getProductList() {
		return _productList;
	}

	public void setProductList(ArrayList<TransferProduct> productList) {
		this._productList = productList;
	}
	
	public String getDate() {
		return _date;
	}

	public void setDate(String date) {
		this._date = date;
	}

	public double getPrice() {
		return this._price;
	}

	public void setPrice(double price) {
		this._price = price;
	}

	public boolean getFinished() {
		return _finished;
	}

	public void setFinished(boolean finished) {
		this._finished = finished;
	}
	
	public void finished() {
		this._finished = true;
	}
	
	public String displayContents() {
        Iterator<TransferProduct> it = _productList.iterator();
        String contents = "";

        while(it.hasNext()) {
            TransferProduct n = it.next();
            contents += n.getName() + "_" + n.getPrice() + "_" + n.getAmount() + "_"; 
        } 
        
        return contents;
        
    }
	
	public String resumeContents() {
       return _saleIdentifier + "_" + _customer + "_" + _price + "_" + _finished + "_" + _date + "_";
        
    }
	
	public String toFile() {
        String productList;
        int productNumber = this._productList.size();
        Iterator<TransferProduct> it = this._productList.iterator();

        StringBuilder sb = new StringBuilder();

        while(it.hasNext()) {
            TransferProduct p = it.next();
            sb.append(p.toString());
            sb.append('\n');
        }

        productList = productNumber + "\n\n" +sb.toString();
        
        if(this._finished) {
	        return  this._saleIdentifier + "\n" + _customer + "\n" + "--\n" +
	        productList + "--\n" + _date + System.lineSeparator() + this._price + "\n" + "true" + "\n"; 
        } else {
        	return  this._saleIdentifier + "\n" + _customer + "\n" + "--\n" +
        	productList + "--\n" + _date + System.lineSeparator() + this._price + "\n" + "false" + "\n";
        }
    } 

    @Override
    public String toString() {
        String productList;
        Iterator<TransferProduct> it = this._productList.iterator();

        if(!it.hasNext())
            return "Venta: Lista de productos vacía";

        StringBuilder sb = new StringBuilder();

        do {
            TransferProduct p = it.next();
            sb.append(p.toString());
        } while(it.hasNext());

        productList = sb.toString();
        
        if(this._finished) {
	        return  this._saleIdentifier + "\n" + _customer + "\n" + "--\n" +
	        productList + "--\n" + this. _date + System.lineSeparator() + this._price + "\n" + "true" + System.lineSeparator(); 
        } else {
        	return  this._saleIdentifier + "\n" + _customer + "\n" + "--\n" +
        	productList + "--\n" + this. _date + System.lineSeparator() + this._price + "\n" + "false" + System.lineSeparator();
        }
        
    }
    	
}