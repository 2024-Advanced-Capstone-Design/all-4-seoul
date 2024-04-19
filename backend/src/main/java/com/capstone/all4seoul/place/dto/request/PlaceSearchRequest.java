package com.capstone.all4seoul.place.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchRequest {
    private List<String> includedTypes;
    private int maxResultCount;
    private LocationRestriction locationRestriction;
}

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationRestriction {
        private Circle circle;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Circle {
        private Center center;
        private double radius;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Center {
        private double latitude;
        private double longitude;
    }
}