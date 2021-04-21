package com.acme.store.persist.web.rest;

import com.acme.store.persist.domain.entity.Store;
import com.acme.store.persist.domain.exception.BusinessException;
import com.acme.store.persist.domain.model.StoreDTO;
import com.acme.store.persist.domain.model.StoreSeasonDTO;
import com.acme.store.persist.service.StoreSeasonsService;
import com.acme.store.persist.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/acme/store/v1/persist/store-seasons")
@Slf4j
@RequiredArgsConstructor
public class StoreSeasonsResource {

  private final StoreSeasonsService service;
  private final StoreService storeService;

  @GetMapping("/")
  @Operation(description = "To fetch the store seasons")
  public ResponseEntity<List<StoreSeasonDTO>> fetch() {
    return ResponseEntity.ok(service.fetch());
  }

  @GetMapping("/season/{season}")
  @Operation(description = "To search the store seasons by season.")
  public ResponseEntity<List<StoreSeasonDTO>> find(
      @Parameter(description = "Store season", required = true) @PathVariable final String season)
      throws BusinessException {
    return ResponseEntity.ok(service.findBySeason(season));
  }

  @PostMapping(value = "/", consumes = "application/json")
  @Operation(description = "To save store season.")
  public ResponseEntity<Void> create(
      @Parameter(description = "The item that will be saved on database.") @RequestBody
          final StoreSeasonDTO storeSeason) {
    service.save(storeSeason);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping(value = "/id/{id}", consumes = "application/json")
  @Operation(description = "To update information about the season store.")
  public ResponseEntity<Void> update(
      @PathVariable Long id,
      @Parameter(description = "The season that will be updated on database.") @RequestBody
          final String season)
      throws BusinessException {
    service.update(id, season);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
