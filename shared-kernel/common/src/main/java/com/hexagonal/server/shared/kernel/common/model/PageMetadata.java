package com.hexagonal.server.shared.kernel.common.model;

public class PageMetadata {

    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;

    public PageMetadata(int page, int size, long totalElements) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

}

