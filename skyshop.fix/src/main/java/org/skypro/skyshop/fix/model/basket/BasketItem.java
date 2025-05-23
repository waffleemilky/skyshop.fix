package org.skypro.skyshop.fix.model.basket;

import org.skypro.skyshop.fix.model.product.Product;

public class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
