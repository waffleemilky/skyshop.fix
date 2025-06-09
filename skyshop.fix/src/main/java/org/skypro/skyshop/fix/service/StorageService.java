package org.skypro.skyshop.fix.service;

import org.skypro.skyshop.fix.model.article.Article;
import org.skypro.skyshop.fix.model.product.Product;
import org.skypro.skyshop.fix.model.product.SimpleProduct;
import org.skypro.skyshop.fix.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public StorageService() {
        initTestData();
    }

    public Collection<Product> getAllProducts() { return products.values(); }
    public Collection<Article> getAllArticles() { return articles.values(); }
    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public void initTestData() {
        products.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(),"Ноутбук Dell XPS", 1500));
        products.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(),"iPhone 13 Pro", 999));
        products.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(),"Клавиатура Logitech MX", 120));

        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Обзор ноутбуков", "..."));
        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Новости Apple", "ююю"));
    }
}
