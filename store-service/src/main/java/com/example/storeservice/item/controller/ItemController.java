package com.example.storeservice.item.controller;

import com.example.storeservice.item.dto.ItemPostDTO;
import com.example.storeservice.item.dto.ItemResponseDTO;
import com.example.storeservice.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class ItemController {

    private final ItemService itemService;

//    @PostMapping("/create/{memberId}/{storeId}")
//    public ResponseEntity<ItemResponseDTO> createItem(@PathVariable Long storeId,
//                                                      @RequestBody ItemPostDTO itemPostDTO) {
//    }
}
