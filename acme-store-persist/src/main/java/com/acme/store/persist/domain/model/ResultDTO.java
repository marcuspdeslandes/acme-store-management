package com.acme.store.persist.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
  private List<?> data;
  private int totalElements;
}
