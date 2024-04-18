package com.capstone.all4seoul.place.domain;

public enum Category {
    PARKING_LOT("PK6", "주차장"),
    GAS_STATION("OL7", "주유소"),
    CULTURE_FACILITY("CT1", "문화시설"),
    TOURIST_ATTRACTION("AT4", "관광명소"),
    RESTAURANT("FD6", "음식점"),
    CAFE("CE7", "카페");

    private final String code;
    private final String name;

    Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
