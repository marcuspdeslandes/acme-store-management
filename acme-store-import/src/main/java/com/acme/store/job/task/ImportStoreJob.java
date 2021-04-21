package com.acme.store.job.task;

import com.acme.store.job.service.ImportStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportStoreJob {

  private final ImportStoreService importStoreService;

  public void importStore() {
      importStoreService.importStore();
  }

  public void importStoreAndSeasons() {
    importStoreService.importStoreAndSeasons();
  }

  @Scheduled(fixedDelayString = "${store.import.fixed-delay-milliseconds}")
  public void importExtraData() {
    importStoreService.importExtraData();
  }
}
