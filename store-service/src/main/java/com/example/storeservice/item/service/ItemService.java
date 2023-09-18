package com.example.storeservice.item.service;

import com.example.storeservice.client.MemberServiceClient;
import com.example.storeservice.dto.MemberResponseDTO;
import com.example.storeservice.exception.BusinessLogicException;
import com.example.storeservice.exception.ExceptionCode;
import com.example.storeservice.item.dto.ItemPostDTO;
import com.example.storeservice.item.dto.ItemResponseDTO;
import com.example.storeservice.item.entity.Item;
import com.example.storeservice.item.mapper.ItemMapper;
import com.example.storeservice.item.repository.ItemRepository;
import com.example.storeservice.store.entity.Store;
import com.example.storeservice.store.repository.StoreRepository;
import com.example.storeservice.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final StoreService storeService;
    private final ItemMapper itemMapper;
    private final MemberServiceClient memberServiceClient;

    public ItemResponseDTO createItem(ItemPostDTO itemPostDTO, String memberId, Long storeId) {
        MemberResponseDTO foundMemberResponseDTO = memberServiceClient.getMember(memberId);
        Store foundStore = storeService.getVerifiedStore(storeId);
        if(!foundMemberResponseDTO.getMemberId().equals(foundStore.getOwnerMemberId())) {
            throw new BusinessLogicException(ExceptionCode.ITEM_CANNOT_CREATE);
        }
        Item item = itemMapper.itemPostDTOToItem(itemPostDTO);
        item.setStore(foundStore);
        Item savedItem = itemRepository.save(item);
        return itemMapper.itemToItemResponseDTO(savedItem);
    }

    public Page<ItemResponseDTO> findItems(Long storeId, Pageable pageable) {
        Store foundStore = storeService.getVerifiedStore(storeId);
        Page<Item> itemPage = itemRepository.findAllByStore(foundStore, pageable);
        return itemPage.map(itemMapper::itemToItemResponseDTO);
    }
}
