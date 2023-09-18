package com.example.storeservice.item.repository;

import com.example.storeservice.item.entity.Item;
import com.example.storeservice.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByStore(Store store, Pageable pageable);
}
