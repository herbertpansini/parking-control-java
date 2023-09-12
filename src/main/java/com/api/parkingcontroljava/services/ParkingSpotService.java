package com.api.parkingcontroljava.services;

import com.api.parkingcontroljava.services.dtos.ParkingSpotDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ParkingSpotService {
    ParkingSpotDto save(ParkingSpotDto parkingSpotDto);

    Page<ParkingSpotDto> findAll(Pageable pageable);

    ParkingSpotDto findById(UUID id);

    void deleteById(UUID id);

    ParkingSpotDto update(UUID id, ParkingSpotDto parkingSpotDto);
}