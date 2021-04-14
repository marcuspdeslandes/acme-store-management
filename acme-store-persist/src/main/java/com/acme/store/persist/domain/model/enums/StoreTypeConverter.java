package com.acme.store.persist.domain.model.enums;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class StoreTypeConverter implements AttributeConverter<StoreTypeEnum, String> {

  @Override
  public String convertToDatabaseColumn(StoreTypeEnum storeTypeEnum) {
    if (storeTypeEnum == null) {
      return null;
    }
    return storeTypeEnum.getName();
  }

  @Override
  public StoreTypeEnum convertToEntityAttribute(String storeType) {
    if (storeType == null) {
      return null;
    }

    return Stream.of(StoreTypeEnum.values())
      .filter(c -> c.getName().equals(storeType))
      .findFirst()
      .orElseThrow(IllegalArgumentException::new);
  }
}