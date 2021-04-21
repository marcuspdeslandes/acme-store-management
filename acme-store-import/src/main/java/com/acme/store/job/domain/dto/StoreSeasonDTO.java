package com.acme.store.job.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
