package com.acme.store.job.domain.dto.enums;

public enum StoreType {
    STORE_FRONT("STORE FRONT"),
    OTHER("OTHER"),
    RETAIL("RETAIL");

    private String name;

    StoreType(String name) {
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