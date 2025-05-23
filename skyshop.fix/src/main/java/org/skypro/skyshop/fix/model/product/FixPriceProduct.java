package org.skypro.skyshop.fix.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final int FIXPRICE = 25;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    @Override
    public int getPrice() {
        return FIXPRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXPRICE;
    }
}
