package com.acme.store.job.domain.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDataDTO {

  @CsvBindByPosition(position = 0)
  @NotEmpty
  private String storeId;

  @CsvBindByPosition(position = 1)
  private String specialField1;

  @CsvBindByPosition(position = 2)
  private String specialField2;
}
