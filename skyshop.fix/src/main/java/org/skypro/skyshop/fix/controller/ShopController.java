package org.skypro.skyshop.fix.controller;

import org.skypro.skyshop.fix.model.article.Article;
import org.skypro.skyshop.fix.model.product.Product;
import org.skypro.skyshop.fix.model.search.SearchResult;
import org.skypro.skyshop.fix.service.SearchService;
import org.skypro.skyshop.fix.service.StorageService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }
}