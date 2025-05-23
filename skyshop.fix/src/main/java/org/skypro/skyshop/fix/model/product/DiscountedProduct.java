package org.skypro.skyshop.fix.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private final int basePrice;
    private final int discount;
    private final int discountPrice;

    public DiscountedProduct(UUID id, String name, int basePrice, int discount) {
        super(id, name);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена продукта должна быть строго больше 0.");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в диапазоне от 0 до 100 включительно.");
        }
        this.basePrice = basePrice;
        this.discount = discount;
        this.discountPrice = calculateDiscountedPrice();
    }

    private int calculateDiscountedPrice() {
        double discountAmount = (double) basePrice * discount / 100;
        return (int) (basePrice - discountAmount);
    }

    @Override
    public int getPrice() {
        return discountPrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }
}
