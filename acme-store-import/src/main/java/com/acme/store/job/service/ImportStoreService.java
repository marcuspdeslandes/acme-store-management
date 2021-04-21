package com.acme.store.job.service;

import com.acme.store.job.client.AcmeClient;
import com.acme.store.job.client.PersistClient;
import com.acme.store.job.domain.dto.ExtraDataDTO;
import com.acme.store.job.domain.dto.StoreDTO;
import com.acme.store.job.domain.dto.StoreSeasonDTO;
import com.acme.store.job.domain.exception.BusinessException;
import com.opencsv.bean.CsvToBeanBuilder;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImportStoreService {

  private @Value("${api.integration.acme.key}") String apiKey;
  @Autowired private final AcmeClient acmeClient;
  @Autowired private final PersistClient persistClient;

  public void importStore() {
    List<StoreDTO> storeList = null;
    int page = 0;
    do {
      try {
        storeList = acmeClient.getStore(apiKey, page);
        storeList.forEach(store -> persistClient.createStore(store));
        log.info("Importing store (Page {})", page);
        page++;
      } catch (Exception e) {
        log.error("Error importing store (Page {})", page);
      }
      // When get error from server or get some content continue...
    } while (storeList == null || storeList.size() > 0);
  }

  public void importStoreAndSeasons() {
    try {
      List<StoreSeasonDTO> storeSeasonsList = acmeClient.getStoreSeasons(apiKey);
      storeSeasonsList.forEach(storeSeasons -> persistClient.createStoreSeasons(storeSeasons));
      log.info("Importing store season");
    } catch (Exception e) {
      throw new BusinessException("Error importing store season", e);
    }
  }

  public void importExtraData() {
    try {
      Response fileName = acmeClient.getExtraData(apiKey);

      final Response.Body body = fileName.body();
      final InputStream inputStream = body.asInputStream();

      List<ExtraDataDTO> extraDataList =
          new CsvToBeanBuilder(new InputStreamReader(inputStream))
              .withSkipLines(1)
              .withType(ExtraDataDTO.class)
              .build()
              .parse();

      extraDataList.forEach(extraData -> persistClient.createExtraData(extraData));
      log.info("Importing extra data");
    } catch (Exception e) {
      throw new BusinessException("Error importing extra data", e);
    }
  }
}
