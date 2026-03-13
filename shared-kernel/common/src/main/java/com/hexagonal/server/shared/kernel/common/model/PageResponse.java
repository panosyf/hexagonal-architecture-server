package com.hexagonal.server.shared.kernel.common.model;

import java.util.List;

public class PageResponse<T> {

    private final List<T> items;
    private final PageMetadata page;

    public PageResponse(List<T> items, PageMetadata page) {
        this.items = items;
        this.page = page;
    }

    public List<T> getItems() {
        return items;
    }

    public PageMetadata getPage() {
        return page;
    }

}

