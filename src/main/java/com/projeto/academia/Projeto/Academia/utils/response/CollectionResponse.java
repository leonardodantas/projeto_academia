package com.projeto.academia.Projeto.Academia.utils.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter

public class CollectionResponse<D,E> {
    
    private List<D> items;
    private boolean hasNext;
    private int size;
    private int page;
    private int totalPages;
    
    public CollectionResponse(Page<E> page, List<D> items){
        this.items = items;
        this.hasNext = page.hasNext();
        this.size = page.getSize();
        this.page = page.getNumber();
        this.totalPages = page.getTotalPages();
    }
    
}
