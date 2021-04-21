package com.acme.store.persist.web.rest;

import com.acme.store.persist.domain.model.ExtraDataDTO;
import com.acme.store.persist.service.ExtraDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/acme/store/v1/persist/extra-data")
@Slf4j
@RequiredArgsConstructor
public class ExtraDataResource {

  private final ExtraDataService service;

  @GetMapping("/")
  @Operation(description = "To fetch the extra data")
  public ResponseEntity<List<ExtraDataDTO>> fetch() {
    return ResponseEntity.ok(service.fetch());
  }

  @PostMapping(value = "/", consumes = "application/json")
  @Operation(description = "To save extra data.")
  public ResponseEntity<Void> create(
      @Parameter(description = "The item that will be saved on database.") @RequestBody
          final ExtraDataDTO extraData) {

    service.save(extraData);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
