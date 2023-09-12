package com.api.parkingcontroljava.services.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingSpotDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    UUID id;

    @NotBlank
    String parkingSpotNumber;

    @NotBlank
    @Size(max = 7)
    String licensePlateCar;

    @NotBlank
    String brandCar;

    @NotBlank
    String modelCar;

    @NotBlank
    String colorCar;

    LocalDateTime registrationDate;

    @NotBlank
    String responsibleName;

    @NotBlank
    String apartment;

    @NotBlank
    String block;
}