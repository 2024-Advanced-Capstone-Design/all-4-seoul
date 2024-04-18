package com.capstone.all4seoul.place.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchRequest {
    private List<String> includedTypes;
    private int maxResultCount;
    private LocationRestriction locationRestriction;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class LocationRestriction {
    private Circle circle;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class Circle {
    private Center center;
    private double radius;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class Center {
    private double latitude;
    private double longitude;
}
