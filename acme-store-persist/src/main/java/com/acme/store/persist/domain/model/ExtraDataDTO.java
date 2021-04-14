package com.acme.store.persist.domain.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDataDTO {

  @NotEmpty private String storeId;

  private String specialField1;

  private String specialField2;
}
