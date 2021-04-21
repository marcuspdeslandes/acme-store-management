package com.acme.store.persist.service;

import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.entity.StoreSeason;
import com.acme.store.persist.domain.exception.BusinessException;
import com.acme.store.persist.domain.model.StoreDTO;
import com.acme.store.persist.domain.model.StoreSeasonDTO;
import com.acme.store.persist.repository.StoreRepository;
import com.acme.store.persist.repository.StoreSeasonsRepository;
import com.acme.store.persist.service.mapper.StoreSeasonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreSeasonsService {

  private static final String STORE_SEASON_NOT_FOUND = "Store season not found";

  @Autowired
  private final StoreSeasonMapper storeSeasonsMapper;
  @Autowired
  private final StoreSeasonsRepository storeSeasonsRepository;
  @Autowired
  private final StoreRepository storesRepository;

  public List<StoreSeasonDTO> fetch() {
    Pageable pageable = PageRequest.of(0, 2);
    final Page<StoreSeason> stores =
      storeSeasonsRepository.findAll(pageable);
    if (CollectionUtils.isEmpty(stores.getContent())) {
      return new ArrayList<>();
    }

    return stores.getContent().stream().map(storeSeasonsMapper::fromEntity).collect(Collectors.toList());
  }

  public List<StoreSeasonDTO> findBySeason(final String season) throws BusinessException {
    Pageable pageable = PageRequest.of(0, 2);
    final Page<StoreSeason> stores =
        Optional.ofNullable(storeSeasonsRepository.findBySeason(season, pageable))
            .orElseThrow(() -> new BusinessException(STORE_SEASON_NOT_FOUND, HttpStatus.NOT_FOUND));
    return stores.getContent().stream().map(storeSeasonsMapper::fromEntity).collect(Collectors.toList());
  }

  public void save(StoreSeasonDTO vo) {
    final Optional<Store> store =
      storesRepository.findById(vo.getStoreId());
    StoreSeason storeSeason = storeSeasonsMapper.toEntity(vo, store.get());
    store.get().setStoreSeason(storeSeason);
    storesRepository.save(store.get());
  }

  public void update(Long id, String season) throws BusinessException {

    if (season==null) {
      throw new BusinessException("No season changed to be updated", HttpStatus.BAD_REQUEST);
    }

    final StoreSeason store =
      storeSeasonsRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(STORE_SEASON_NOT_FOUND, HttpStatus.NOT_FOUND));

    store.setSeason(season);

    storeSeasonsRepository.save(store);
  }
}
