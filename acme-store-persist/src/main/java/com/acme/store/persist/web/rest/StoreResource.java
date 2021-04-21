package com.acme.store.persist.web.rest;

import com.acme.store.persist.domain.exception.BusinessException;
import com.acme.store.persist.domain.model.StoreDTO;
import com.acme.store.persist.domain.model.ResultDTO;
import com.acme.store.persist.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/acme/store/v1/persist/store")
@Slf4j
@RequiredArgsConstructor
public class StoreResource {

  private final StoreService service;

  @GetMapping("/")
  @Operation(description = "To fetch the stores")
  @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"})
  public ResponseEntity<ResultDTO> fetch(
      @RequestParam(defaultValue = "0") final int page, @RequestParam(defaultValue = "5") final int pageSize) {
    return ResponseEntity.ok(service.fetch(page, pageSize));
  }

  @GetMapping("/name/{name}")
  @Operation(description = "To search the store by name.")
  @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"})
  public ResponseEntity<ResultDTO> find(
      @Parameter(description = "Store name", required = true) @PathVariable final String name,
      @RequestParam(defaultValue = "0") final int page, @RequestParam(defaultValue = "5") final int pageSize)
      throws BusinessException {
    return ResponseEntity.ok(service.findByName(name, page, pageSize));
  }

  @PostMapping(value = "/", consumes = "application/json")
  @Operation(description = "To save store.")
  public ResponseEntity<Void> create(
      @Parameter(description = "The store that will be saved on database.") @RequestBody
          final StoreDTO store) {

    service.save(store);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping(value = "/id/{id}", consumes = "application/json")
  @Operation(description = "To update information about the name store.")
  @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"})
  public ResponseEntity<Void> update(
      @PathVariable Long id,
      @Parameter(description = "The name that will be updated on database.") @RequestBody
          final String name)
      throws BusinessException {
    service.update(id, name);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/id/{id}")
  @Operation(description = "To delete the store by id.")
  public ResponseEntity<Void> delete(
      @Parameter(description = "Identifier of the store.", required = true) @PathVariable
          final Long id)
      throws BusinessException {

    service.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
