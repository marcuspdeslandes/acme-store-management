package com.acme.store.persist.service;

import com.acme.store.persist.domain.entity.ExtraData;
import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.model.ExtraDataDTO;
import com.acme.store.persist.repository.ExtraDataRepository;
import com.acme.store.persist.repository.StoreRepository;
import com.acme.store.persist.service.mapper.ExtraDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExtraDataService {

  @Autowired private final ExtraDataMapper extraDataMapper;
  @Autowired private final ExtraDataRepository extraDataRepository;
  @Autowired private final StoreRepository storesRepository;

  public List<ExtraDataDTO> fetch() {
    Pageable pageable = PageRequest.of(0, 2);
    final Page<ExtraData> stores = extraDataRepository.findAll(pageable);
    if (CollectionUtils.isEmpty(stores.getContent())) {
      return new ArrayList<>();
    }

    return stores.getContent().stream()
        .map(extraDataMapper::fromEntity)
        .collect(Collectors.toList());
  }

  public void save(ExtraDataDTO vo) {
    final Optional<Store> store =
      storesRepository.findById(Long.parseLong(vo.getStoreId()));
    ExtraData extraData = extraDataMapper.toEntity(vo, store.get());
    store.get().setExtraData(extraData);
    storesRepository.save(store.get());
  }
}
