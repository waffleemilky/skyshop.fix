package org.skypro.skyshop.fix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.fix.model.search.SearchResult;
import org.skypro.skyshop.fix.model.search.Searchable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    // Тестовые данные
    private Searchable product1;
    private Searchable product2;
    private Searchable product3;
    private List<Searchable> testData;

    @BeforeEach
    void setUp() {
        // Создаем тестовые объекты
        product1 = createTestSearchable("Ноутбук Dell XPS", "Мощный игровой ноутбук");
        product2 = createTestSearchable("iPhone 13 Pro", "Флагманский смартфон Apple");
        product3 = createTestSearchable("Клавиатура Logitech MX", "Беспроводная механическая клавиатура");
        testData = Arrays.asList(product1, product2, product3);
    }

    private Searchable createTestSearchable(String name, String description) {
        return new Searchable() {
            @Override
            public String getName() { return name; }
            @Override
            public String getDescription() { return description; }
            @Override
            public UUID getId() { return UUID.randomUUID(); }
            @Override
            public String getSearchTerm() { return name + " " + description; }
        };
    }

    // Тест 1: Поиск при пустом хранилище
    @Test
    void search_shouldReturnEmptyList_whenStorageEmpty() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        // Действие
        List<SearchResult> results = new ArrayList<>(searchService.search("ноутбук"));

        // Проверка
        assertTrue(results.isEmpty(), "Должен вернуть пустой список");
        verify(storageService, times(1)).getAllSearchables();
    }

    // Тест 2: Поиск без совпадений
    @Test
    void search_shouldReturnEmptyList_whenNoMatches() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(testData);

        // Действие
        List<SearchResult> results = new ArrayList<>(searchService.search("Samsung TV"));

        // Проверка
        assertTrue(results.isEmpty(), "Не должно быть совпадений");
    }

    // Тест 3: Поиск с одним совпадением
    @Test
    void search_shouldReturnOneResult_whenSingleMatch() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(testData);

        // Действие
        List<SearchResult> results = new ArrayList<>(searchService.search("iPhone"));

        // Проверка
        assertEquals(1, results.size(), "Должен найти 1 товар");
        assertEquals("iPhone 13 Pro", results.get(0).getName(), "Название должно совпадать");
    }

    // Тест 4: Поиск с несколькими совпадениями
    @Test
    void search_shouldReturnMultipleResults_whenMultipleMatches() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(testData);

        // Действие
        List<SearchResult> results = new ArrayList<>(searchService.search("ноутбук"));

        // Проверка
        assertEquals(2, results.size(), "Должен найти 2 товара");
        assertTrue(results.stream().anyMatch(r -> r.getName().contains("Dell")));
        assertTrue(results.stream().anyMatch(r -> r.getDescription().contains("ноутбук")));
    }

    // Тест 5: Регистронезависимый поиск
    @Test
    void search_shouldBeCaseInsensitive() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(testData);

        // Действие
        List<SearchResult> results = new ArrayList<>(searchService.search("DELL"));

        // Проверка
        assertEquals(1, results.size());
        assertEquals("Ноутбук Dell XPS", results.get(0).getName());
    }

    // Тест 6: Поиск по части слова
    @Test
    void search_shouldMatchPartialWords() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(testData);

        // Действие
        List<SearchResult> results = new ArrayList<>(searchService.search("logi"));

        // Проверка
        assertEquals(1, results.size());
        assertEquals("Клавиатура Logitech MX", results.get(0).getName());
    }

    // Тест 7: Пустой запрос
    @Test
    void search_shouldReturnEmptyList_whenQueryIsBlank() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(testData);

        // Действие
        List<SearchResult> results = new ArrayList<>(searchService.search("   "));

        // Проверка
        assertTrue(results.isEmpty());
    }
}