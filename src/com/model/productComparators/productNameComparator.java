package com.model.productComparators;

import java.util.Comparator;

import com.model.dto.TransferProduct;

/**
 * Comparamos los Productos por su nombre.
 * 
 * @author Borja
 */
public class productNameComparator implements Comparator<TransferProduct> {

    @Override
    public int compare(TransferProduct o1, TransferProduct o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
    
}