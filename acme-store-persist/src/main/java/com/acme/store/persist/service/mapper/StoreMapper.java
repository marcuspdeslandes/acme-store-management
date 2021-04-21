package com.acme.store.persist.service.mapper;

import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.model.StoreDTO;
import com.acme.store.persist.domain.model.enums.StoreTypeConverter;
import org.springframework.stereotype.Service;

@Service
public class StoreMapper {

  StoreTypeConverter converter = new StoreTypeConverter();

  public StoreDTO fromEntity(Store entity) {
    return StoreDTO.builder()
        .id(entity.getId())
        .code(entity.getCode())
        .description(entity.getDescription())
        .code(entity.getCode())
        .name(entity.getName())
        .openingDate(entity.getOpeningDate())
        .storeType(converter.convertToDatabaseColumn(entity.getStoreType()))
        .storeSeason(entity.getStoreSeason() != null ? entity.getStoreSeason().getSeason() : "")
        .specialField1(entity.getExtraData() != null ? entity.getExtraData().getSpecialField1() : "")
        .specialField2(entity.getExtraData() != null ? entity.getExtraData().getSpecialField2() : "")
        .build();
  }

  public Store toEntity(StoreDTO vo) {
    Store entity = new Store();
    entity.setId(vo.getId());
    entity.setCode(vo.getCode() != null ? vo.getCode().trim(): vo.getCode());
    entity.setDescription(vo.getDescription() != null ? vo.getDescription().trim(): vo.getDescription());
    entity.setName(vo.getName() != null ? vo.getName().trim(): vo.getName());
    entity.setOpeningDate(vo.getOpeningDate());
    entity.setStoreType(converter.convertToEntityAttribute(vo.getStoreType()));
    return entity;
  }
}
