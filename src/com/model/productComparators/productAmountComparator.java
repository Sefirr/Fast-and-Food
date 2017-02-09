package com.model.productComparators;

import java.util.Comparator;

import com.model.dto.TransferProduct;

/**
 * Comparamos los Productos por su cantidad.
 * 
 * @author Borja
 */
public class productAmountComparator implements Comparator<TransferProduct> {

    @Override
    public int compare(TransferProduct o1, TransferProduct o2) {
        return o1.getAmount() - o2.getAmount();
    }
    
}