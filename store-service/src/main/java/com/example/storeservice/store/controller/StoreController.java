package com.example.storeservice.store.controller;

import com.example.storeservice.dto.MultiResponseDTO;
import com.example.storeservice.store.dto.StorePostDTO;
import com.example.storeservice.store.dto.StoreResponseDTO;
import com.example.storeservice.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody StorePostDTO storePostDTO) {
        StoreResponseDTO storeResponseDTO = storeService.createStore(storePostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeResponseDTO);
    }

    @GetMapping("/find/{storeCategoryId}")
    public ResponseEntity<MultiResponseDTO<StoreResponseDTO>> getStores(@PathVariable String storeCategoryId,
                                                                              @PageableDefault(
                                                                                      page = 1,
                                                                                      size = 10,
                                                                                      sort = "storeId",
                                                                                      direction = Sort.Direction.DESC
                                                                              ) Pageable pageable) {
        Page<StoreResponseDTO> storeResponseDTOPage = storeService.findStores(storeCategoryId, pageable);
        List<StoreResponseDTO> storeResponseDTOs = storeResponseDTOPage.getContent();
        return ResponseEntity.status(HttpStatus.OK).body(new MultiResponseDTO<>(storeResponseDTOs, storeResponseDTOPage));
    }
}
