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

    //구글 -> 카카오 카테고리 변환 메서드
    public Category convertCategoryGoogleToKakao(String category) {
        if (category.equals("cafe")) {
            return Category.CAFE;
        }
        if (category.equals("restaurant")) {
            return Category.RESTAURANT;
        }
        if (category.equals("tourist_attraction")) {
            return Category.TOURIST_ATTRACTION;
        }
        if (isCultureFacility(category)) {
            return Category.CULTURE_FACILITY;
        }
        if (category.equals("gas_station")) {
            return Category.GAS_STATION;
        }
        if (category.equals("parking")) {
            return Category.PARKING_LOT;
        }
        throw new IllegalArgumentException("해당하는 카테고리는 존재하지 않습니다.");
    }
    //주어진 카테고리가 문화시설 하위에 속하는지 여부 확인
    private static boolean isCultureFacility(String category) {
        return category.equals("amusement_park")
                || category.equals("aquarium")
                || category.equals("art_gallery")
                || category.equals("campground")
                || category.equals("library")
                || category.equals("movie_theater")
                || category.equals("museum")
                || category.equals("department_store")
                || category.equals("stadium")
                || category.equals("shopping_mall")
                || category.equals("spa");
    }
}
