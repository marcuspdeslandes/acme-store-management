package com.acme.store.job.client;

import com.acme.store.job.domain.dto.ExtraDataDTO;
import com.acme.store.job.domain.dto.StoreDTO;
import com.acme.store.job.domain.dto.StoreSeasonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "store", url = "${api.integration.persist.url}")
public interface PersistClient {

  @PostMapping("/store/")
  void createStore(@RequestBody() StoreDTO store);

  @PostMapping("/store-seasons/")
  void createStoreSeasons(@RequestBody() StoreSeasonDTO store);

  @PostMapping("/extra-data/")
  void createExtraData(@RequestBody() ExtraDataDTO extraData);
}
