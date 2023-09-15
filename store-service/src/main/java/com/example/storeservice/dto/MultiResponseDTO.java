package com.example.storeservice.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponseDTO<T> {
    private final List<T> data;
    private final PageInfo pageInfo;

    public MultiResponseDTO(List<T> data, Page<?> page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber(),
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
