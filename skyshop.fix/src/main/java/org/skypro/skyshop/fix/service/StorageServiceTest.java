package org.skypro.skyshop.fix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.fix.model.product.Product;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageServiceTest {
    private StorageService storageService;

    @BeforeEach
    void setUp() {
        storageService = new StorageService();
        storageService.initTestData(); // Инициализируем тестовые данные
    }

    @Test
    void getProductById_shouldReturnProduct_whenExists() {
        // Подготовка: берем первый продукт из хранилища
        UUID existingId = storageService.getAllProducts().iterator().next().getId();

        // Действие
        Optional<Product> result = storageService.getProductById(existingId);

        // Проверка
        assertTrue(result.isPresent(), "Продукт должен существовать");
        assertEquals(existingId, result.get().getId(), "ID должны совпадать");
    }

    @Test
    void getProductById_shouldReturnEmpty_whenNotExists() {
        // Подготовка
        UUID randomId = UUID.randomUUID();

        // Действие
        Optional<Product> result = storageService.getProductById(randomId);

        // Проверка
        assertTrue(result.isEmpty(), "Продукт не должен существовать");
    }
}
