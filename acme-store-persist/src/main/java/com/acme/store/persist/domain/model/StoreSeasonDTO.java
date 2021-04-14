package com.acme.store.persist.domain.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreSeasonDTO {
  @NotEmpty
  private Long storeId;
  private String season;

}
