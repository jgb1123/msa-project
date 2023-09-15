package com.example.storeservice.store.repository;

import com.example.storeservice.store.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, String> {
}
