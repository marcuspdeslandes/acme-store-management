package com.acme.store.job.domain.dto;

import com.acme.store.job.domain.dto.enums.StoreType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
  @NotEmpty
  private Long id;
  private String code;
  private String description;
  private String name;
  @NotEmpty
  private Date openingDate;
  private String storeType;
  private String storeSeason;
  private String specialField1;
  private String specialField2;
}
