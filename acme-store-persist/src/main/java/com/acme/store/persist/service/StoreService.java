package com.acme.store.persist.service;

import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.exception.BusinessException;
import com.acme.store.persist.domain.model.ResultDTO;
import com.acme.store.persist.domain.model.StoreDTO;
import com.acme.store.persist.repository.StoreRepository;
import com.acme.store.persist.service.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

  private static final String STORE_NOT_FOUND = "Store not found";

  @Autowired private final StoreMapper storeMapper;
  @Autowired private final StoreRepository storeRepository;

  public ResultDTO fetch(final int page, final int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));

    final Page<Store> stores = storeRepository.findAll(pageable);
    if (CollectionUtils.isEmpty(stores.getContent())) {
      return new ResultDTO();
    }

    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(
        stores.getContent().stream().map(storeMapper::fromEntity).collect(Collectors.toList()));
    resultDTO.setTotalElements(stores.getTotalPages());

    return resultDTO;
  }

  public ResultDTO findByName(final String name, final int page, final int pageSize)
      throws BusinessException {
    Pageable pageable = PageRequest.of(page, pageSize);

    final Page<Store> stores =
        Optional.ofNullable(storeRepository.findByNameLike(name, pageable))
            .orElseThrow(() -> new BusinessException(STORE_NOT_FOUND, HttpStatus.NOT_FOUND));

    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(
        stores.getContent().stream().map(storeMapper::fromEntity).collect(Collectors.toList()));
    resultDTO.setTotalElements(stores.getTotalPages());
    return resultDTO;
  }

  public void save(StoreDTO vo) {
    storeRepository.save(storeMapper.toEntity(vo));
  }

  public void update(final Long id, final String name) throws BusinessException {

    if (name == null) {
      throw new BusinessException("No name changed to be updated", HttpStatus.BAD_REQUEST);
    }

    final Store store =
        storeRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(STORE_NOT_FOUND, HttpStatus.NOT_FOUND));
    store.setName(name);
    storeRepository.save(store);
  }

  public void delete(final Long id) throws BusinessException {
    storeRepository
        .findById(id)
        .orElseThrow(() -> new BusinessException(STORE_NOT_FOUND, HttpStatus.NOT_FOUND));
    storeRepository.deleteById(id);
  }
}
