package org.skypro.skyshop.fix.service;

import org.skypro.skyshop.fix.model.article.Article;
import org.skypro.skyshop.fix.model.product.DiscountedProduct;
import org.skypro.skyshop.fix.model.product.FixPriceProduct;
import org.skypro.skyshop.fix.model.product.Product;
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

    private void initTestData() {
        products.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "iPhone", 100_000, 10));
        products.put(UUID.randomUUID(), new FixPriceProduct(UUID.randomUUID(), "Книга"));

        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Новости", "Spring Boot — это просто!"));
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
}
