package com.model.productComparators;

import java.util.Comparator;

import com.model.dto.TransferProduct;



/**
 * Comparamos los Productos por su identificador de producto.
 * 
 * @author Borja
 */
public class productIdComparator implements Comparator<TransferProduct> {

    @Override
    public int compare(TransferProduct o1, TransferProduct o2) {
        return o1.getId() - o2.getId();
    }
    
}