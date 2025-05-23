package org.skypro.skyshop.fix.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Component
public class Basket {
    private final Map<UUID, Integer> products = new HashMap<>();

    public void addProduct(UUID id) {
        products.merge(id, 1, Integer::sum);
    }

    public Map<UUID, Integer> getAllProducts() {
        return Collections.unmodifiableMap(products);
    }
}
