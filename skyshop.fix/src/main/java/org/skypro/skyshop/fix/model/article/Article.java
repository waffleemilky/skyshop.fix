package org.skypro.skyshop.fix.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.fix.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String content;

    public Article(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public UUID getId() { return id; }
    public String getTitle() { return title; }

    @JsonIgnore
    @Override
    public String getSearchTerm() { return title + " " + content; }
}