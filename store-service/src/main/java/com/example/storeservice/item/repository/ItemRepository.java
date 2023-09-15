package com.example.storeservice.item.repository;

import com.example.storeservice.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
