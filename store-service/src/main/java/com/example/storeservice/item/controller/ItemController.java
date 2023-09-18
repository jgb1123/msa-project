package com.example.storeservice.item.controller;

import com.example.storeservice.dto.MultiResponseDTO;
import com.example.storeservice.item.dto.ItemPostDTO;
import com.example.storeservice.item.dto.ItemResponseDTO;
import com.example.storeservice.item.service.ItemService;
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
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create/{memberId}/{storeId}")
    public ResponseEntity<ItemResponseDTO> createItem(@PathVariable String memberId,
                                                      @PathVariable Long storeId,
                                                      @RequestBody ItemPostDTO itemPostDTO) {
        ItemResponseDTO itemResponseDTO = itemService.createItem(itemPostDTO, memberId, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponseDTO);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<MultiResponseDTO<ItemResponseDTO>> findItems(@PathVariable Long storeId,
                                                                       @PageableDefault(
                                                                               page = 1,
                                                                               size = 10,
                                                                               sort = "itemId",
                                                                               direction = Sort.Direction.ASC
                                                                       ) Pageable pageable) {
        Page<ItemResponseDTO> itemResponseDTOPage = itemService.findItems(storeId, pageable);
        List<ItemResponseDTO> itemResponseDTOs = itemResponseDTOPage.getContent();
        return ResponseEntity.status(HttpStatus.OK).body(new MultiResponseDTO<>(itemResponseDTOs, itemResponseDTOPage));
    }
}
