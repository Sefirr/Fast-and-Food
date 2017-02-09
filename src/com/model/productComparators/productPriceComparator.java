package com.model.productComparators;

import java.util.Comparator;

import com.model.dto.TransferProduct;

/**
 * Comparamos los productos por su precio.
 * 
 * @author Borja
 */
public class productPriceComparator implements Comparator<TransferProduct> {

	@Override
	public int compare(TransferProduct o1, TransferProduct o2) {
		return Double.compare(o1.getPrice(), o2.getPrice());
	}
	
}