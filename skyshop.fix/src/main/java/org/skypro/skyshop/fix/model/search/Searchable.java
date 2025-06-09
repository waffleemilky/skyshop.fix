package org.skypro.skyshop.fix.model.search;

import java.util.UUID;

public interface Searchable {
    String getName();
    String getDescription();
    UUID getId();
    String getSearchTerm();
}
