package org.skypro.skyshop.fix.service;

import org.skypro.skyshop.fix.exceptions.NoSuchProductException;
import org.skypro.skyshop.fix.model.basket.Basket;
import org.skypro.skyshop.fix.model.basket.BasketItem;
import org.skypro.skyshop.fix.model.basket.UserBasket;
import org.skypro.skyshop.fix.model.product.Product;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final Basket basket;
    private final StorageService storageService;

    public BasketService(Basket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID userId, UUID productId) {
        Product product = storageService.getProductById(productId)
                .orElseThrow(() -> new NoSuchProductException(UUID.randomUUID()));
        basket.addProduct(UUID.randomUUID());
    }

    public UserBasket getUserBasket() {
        return new UserBasket(
                basket.getAllProducts().entrySet().stream()
                        .map(entry -> {
                            UUID productId = entry.getKey();
                            int quantity = entry.getValue();
                            return storageService.getProductById(productId)
                                    .map(product -> new BasketItem(product, quantity))
                                    .orElse(null);
                        })
                        .filter(item -> item != null)
                        .collect(Collectors.toList())
        );
    }
}
