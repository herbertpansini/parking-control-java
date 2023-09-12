package com.api.parkingcontroljava.repositories;

import com.api.parkingcontroljava.models.ParkingSpotModel;
import com.api.parkingcontroljava.services.dtos.ParkingSpotView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    ParkingSpotView findByLicensePlateCar(String licensePlateCar);

    ParkingSpotView findByParkingSpotNumber(String parkingSpotNumber);

    ParkingSpotView findByApartmentAndBlock(String apartment, String block);
}