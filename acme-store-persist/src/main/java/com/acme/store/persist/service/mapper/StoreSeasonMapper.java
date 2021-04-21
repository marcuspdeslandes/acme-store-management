package com.acme.store.persist.service.mapper;

import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.entity.StoreSeason;
import com.acme.store.persist.domain.model.StoreSeasonDTO;
import org.springframework.stereotype.Service;

@Service
public class StoreSeasonMapper {

  public StoreSeasonDTO fromEntity(StoreSeason entity) {
    return StoreSeasonDTO.builder().storeId(entity.getStore().getId()).season(entity.getSeason()).build();
  }

  public StoreSeason toEntity(StoreSeasonDTO vo, Store store) {
    StoreSeason entity = new StoreSeason();
    entity.setId(vo.getStoreId());
    entity.setStore(store);
    entity.setSeason(vo.getSeason().trim());
    return entity;
  }
}
