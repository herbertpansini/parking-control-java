package com.api.parkingcontroljava.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingSpotModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(nullable = false, unique = true, length = 10)
    String parkingSpotNumber;

    @Column(nullable = false, unique = true, length = 7)
    String licensePlateCar;

    @Column(nullable = false, length = 70)
    String brandCar;

    @Column(nullable = false, length = 70)
    String modelCar;

    @Column(nullable = false, length = 70)
    String colorCar;

    @Column(nullable = false)
    LocalDateTime registrationDate;

    @Column(nullable = false, length = 130)
    String responsibleName;

    @Column(nullable = false, length = 30)
    String apartment;

    @Column(nullable = false, length = 30)
    String block;

    @PrePersist
    public void onPrePersist() {
        this.registrationDate = LocalDateTime.now();
    }
}