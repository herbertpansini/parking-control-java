package com.api.parkingcontroljava.services.mappers;

import com.api.parkingcontroljava.models.ParkingSpotModel;
import com.api.parkingcontroljava.services.dtos.ParkingSpotDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper extends EntityMapper<ParkingSpotModel, ParkingSpotDto> {
}