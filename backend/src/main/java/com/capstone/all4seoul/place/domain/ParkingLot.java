package com.capstone.all4seoul.place.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class ParkingLot extends Place {

    private int capacity;
    private int price;
    private int dues;
    private String etc;
}
