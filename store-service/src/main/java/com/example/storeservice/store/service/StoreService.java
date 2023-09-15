package com.example.storeservice.store.service;


import com.example.storeservice.exception.BusinessLogicException;
import com.example.storeservice.exception.ExceptionCode;
import com.example.storeservice.store.dto.StorePostDTO;
import com.example.storeservice.store.dto.StoreResponseDTO;
import com.example.storeservice.store.entity.Store;
import com.example.storeservice.store.entity.StoreCategory;
import com.example.storeservice.store.mapper.StoreMapper;
import com.example.storeservice.store.repository.StoreCategoryRepository;
import com.example.storeservice.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreCategoryRepository storeCategoryRepository;
    private final StoreMapper storeMapper;

    public StoreResponseDTO createStore(StorePostDTO storePostDTO) {
        Store store = storeMapper.storePostDTOToStore(storePostDTO);
        StoreCategory foundStoreCategory = getVerifiedStoreCategory(storePostDTO.getStoreCategoryId());
        store.setStoreCategory(foundStoreCategory);
        Store savedStore = storeRepository.save(store);
        return storeMapper.storeToStoreResponseDTO(savedStore);
    }

    public  Page<StoreResponseDTO> findStores(String categoryId, Pageable pageable) {
        StoreCategory foundStoreCategory = getVerifiedStoreCategory(categoryId);
        Page<Store> storePage = storeRepository.findAllByStoreCategoryStoreCategoryIdStartingWith(categoryId, pageable);
        return storePage.map(storeMapper::storeToStoreResponseDTO);
    }

    private StoreCategory getVerifiedStoreCategory(String storeCategoryId) {
        return storeCategoryRepository.findById(storeCategoryId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.STORE_CATEGORY_NOT_FOUND));
    }
}
