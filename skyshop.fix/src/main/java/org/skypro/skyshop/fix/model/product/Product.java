package org.skypro.skyshop.fix.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.fix.model.search.Searchable;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String name;

    public Product(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID getId() { return id; }
    public String getName() { return name; }

    @JsonIgnore
    @Override
    public String getSearchTerm() { return name; }

    public abstract int getPrice();
    public abstract boolean isSpecial();
}