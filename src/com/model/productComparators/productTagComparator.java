package com.model.productComparators;

import java.util.Comparator;

import com.model.dto.TransferProduct;

/**
 * Comparamos los Productos por su etiqueta.
 * 
 * @author Borja
 */
public class productTagComparator implements Comparator<TransferProduct> {

	@Override
	public int compare(TransferProduct o1, TransferProduct o2) {
		return o1.getTag().compareToIgnoreCase(o2.getTag());
	}
	
}