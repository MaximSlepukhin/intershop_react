package com.github.maximslepukhin.intershop.dto;


public class ItemsResult {
    private Paging pageInfo;

    public ItemsResult(boolean hasNextPage, int page, int pageSize) {
        boolean hasPreviousPage = page > 1;
        Paging info = new Paging(page, pageSize, hasNextPage, hasPreviousPage);
        this.pageInfo = info;
    }

    public Paging getPageInfo() {
        return pageInfo;
    }
}
