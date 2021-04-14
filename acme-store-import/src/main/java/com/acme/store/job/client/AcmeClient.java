package com.acme.store.job.client;

import com.acme.store.job.domain.dto.StoreDTO;
import com.acme.store.job.domain.dto.StoreSeasonDTO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "acme", url = "${api.integration.acme.url}")
public interface AcmeClient {

  @GetMapping("/v1/stores/")
  List<StoreDTO> getStore(
      @RequestHeader(name = "apiKey") String apiKey, @RequestParam(name = "page") int page);

  @GetMapping("/other/stores_and_seasons")
  List<StoreSeasonDTO> getStoreSeasons(
      @RequestHeader(name = "apiKey") String apiKey);

  @GetMapping("/extra_data.csv")
  Response  getExtraData(
      @RequestHeader(name = "apiKey") String apiKey);
}
