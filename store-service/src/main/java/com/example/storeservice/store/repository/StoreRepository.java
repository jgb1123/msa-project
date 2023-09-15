package com.example.storeservice.store.repository;

import com.example.storeservice.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findAllByStoreCategoryStoreCategoryIdStartingWith(String categoryId, Pageable pageable);
}
