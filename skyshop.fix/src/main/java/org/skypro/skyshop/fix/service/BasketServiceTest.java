package org.skypro.skyshop.fix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.fix.exceptions.NoSuchProductException;
import org.skypro.skyshop.fix.model.basket.Basket;
import org.skypro.skyshop.fix.model.product.Product;
import org.skypro.skyshop.fix.model.product.SimpleProduct;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private Basket basket; // Мок корзины (не BasketItem!)

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    private UUID userId;
    private UUID productId;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        productId = UUID.randomUUID();
        testProduct = new SimpleProduct(productId, "Ноутбук", 999);
    }

    @Test
    void addProduct_shouldThrowException_whenItemNotExists() {

        when(storageService.getProductById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> {
            basketService.addProduct(userId, productId);
        });

        verify(basket, never()).addProduct(any(UUID.class));
    }

    @Test
    void addProduct_shouldAddItem_whenProductExists() {
        when(storageService.getProductById(productId)).thenReturn(Optional.of(testProduct));

        basketService.addProduct(userId, productId);

        verify(basket, times(1)).addProduct(any(UUID.class));
    }
}