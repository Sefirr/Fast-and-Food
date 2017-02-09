package com.model.impl.dto;
import java.util.ArrayList;
import java.util.Iterator;

import com.model.dto.TransferOrder;
import com.model.dto.TransferProduct;

@SuppressWarnings("serial")
public class TransferOrderImp implements TransferOrder {
	private int _orderIdentifier;
    private String _supplier;
    private ArrayList<TransferProduct> _productList;
    private boolean _finished;
    private boolean _approved;
    private String _date;
    
    public TransferOrderImp() {
    	this._productList = new ArrayList<TransferProduct>();
    	 this._finished = false;
         this._approved = false;
    }
    
    public TransferOrderImp(int id, String supplier, ArrayList<TransferProduct> productList, String date, boolean finished, boolean approved) {
        this._orderIdentifier = id;
        this._supplier = supplier;
        this._productList = productList;
        this._finished = finished;
        this._approved = approved;
        this._date = date;
    }

	public int getOrderIdentifier() {
		return _orderIdentifier;
	}

	public void setOrderIdentifier(int orderIdentifier) {
		this._orderIdentifier = orderIdentifier;
	}

	public String getSupplier() {
		return _supplier;
	}

	public void setSupplier(String supplier) {
		this._supplier = supplier;
	}

	public ArrayList<TransferProduct> getProductList() {
		return _productList;
	}

	public void setProductList(ArrayList<TransferProduct> productList) {
		this._productList = productList;
	}

	public boolean getFinished() {
		return _finished;
	}
	
	public void setFinished(boolean finished) {
		this._finished = finished;
	}

	public boolean getApproved() {
		return _approved;
	}
	
	public void setApproved(boolean approved) {
		this._approved = approved;
	}
	
	public void finished() {
		this._finished = true;
	}

	public void approved() {
		this._approved = true;
	}

	public String getDate() {
		return _date;
	}

	public void setDate(String date) {
		this._date = date;
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
	
	@Override
	public String displayContentsMVC() {
        Iterator<TransferProduct> it = _productList.iterator();
        String contents = "";

        while(it.hasNext()) {
            TransferProduct n = it.next();
            contents += n.toString(); 
        } 
        
        return contents;
        
    }
	
	public String resumeContents() {
          
        return _orderIdentifier + "_" + _supplier + "_" + _date + "_" + _finished + "_" + _approved + "_";
        
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

        productList = productNumber + "\n\n" + sb.toString();

        return  this._orderIdentifier + "\n" +
        this._supplier + "\n" +
        "--\n" +
        productList + 
        "--\n" +
        this._date + "\n" + this._finished + System.lineSeparator() + this._approved + System.lineSeparator();
    }

    @Override
    public String toString() {
        String productList;
        Iterator<TransferProduct> it = this._productList.iterator();

        if(!it.hasNext())
            return "Pedido: Lista de productos vacía";

        StringBuilder sb = new StringBuilder();

        do {
            TransferProduct p = it.next();
            sb.append(p.toString());
        } while(it.hasNext());

        productList = sb.toString();

        return  this._orderIdentifier + "\n" +
        this._supplier + "\n" +
        "--\n" +
        productList + 
        "--\n" +
        this._date + "\n" + this._finished + "/n" + this._approved + "/n";
    }
       
}