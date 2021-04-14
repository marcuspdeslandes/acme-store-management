package com.acme.store.persist.service.mapper;

import com.acme.store.persist.domain.entity.ExtraData;
import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.model.ExtraDataDTO;
import org.springframework.stereotype.Service;

@Service
public class ExtraDataMapper {

  public ExtraDataDTO fromEntity(ExtraData entity) {
    return ExtraDataDTO.builder()
        .storeId(entity.getStore().getId().toString())
        .specialField1(entity.getSpecialField1())
        .specialField2(entity.getSpecialField2())
        .build();
  }

  public ExtraData toEntity(ExtraDataDTO vo, Store store) {
    ExtraData entity = new ExtraData();
    entity.setId(Long.parseLong(vo.getStoreId()));
    entity.setStore(store);
    entity.setSpecialField1(vo.getSpecialField1().trim());
    entity.setSpecialField2(vo.getSpecialField2().trim());
    return entity;
  }

}
