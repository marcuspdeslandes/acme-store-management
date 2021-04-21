package com.acme.store.persist.domain.model.enums;

public enum StoreTypeEnum {
    STORE_FRONT("STORE FRONT"),
    OTHER("OTHER"),
    RETAIL("RETAIL");

  private String name;

  StoreTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return name;
  }

}
