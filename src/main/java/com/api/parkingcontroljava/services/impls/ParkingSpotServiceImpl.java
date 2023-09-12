package com.api.parkingcontroljava.services.impls;

import com.api.parkingcontroljava.repositories.ParkingSpotRepository;
import com.api.parkingcontroljava.services.ParkingSpotService;
import com.api.parkingcontroljava.services.dtos.ParkingSpotDto;
import com.api.parkingcontroljava.services.dtos.ParkingSpotView;
import com.api.parkingcontroljava.services.mappers.ParkingSpotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSpotMapper parkingSpotMapper;

    @Override
    public ParkingSpotDto save(ParkingSpotDto parkingSpotDto) {
        this.validate(parkingSpotDto);

        return this.parkingSpotMapper.toDto(
                this.parkingSpotRepository.save(
                        this.parkingSpotMapper.toEntity(parkingSpotDto)
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParkingSpotDto> findAll(Pageable pageable) {
        return this.parkingSpotRepository.findAll(pageable).map(this.parkingSpotMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ParkingSpotDto findById(UUID id) {
        return this.parkingSpotMapper.toDto(
                this.parkingSpotRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public void deleteById(UUID id) {
        this.parkingSpotRepository.deleteById(id);
    }

    @Override
    public ParkingSpotDto update(UUID id, ParkingSpotDto parkingSpotDto) {
        ParkingSpotDto parkingSpot = this.findById(id);
        BeanUtils.copyProperties(parkingSpotDto, parkingSpot, "id", "registrationDate");
        return this.save(parkingSpot);
    }

    private void validate(ParkingSpotDto parkingSpotDto) {
        if (this.validateLicensePlateCar(parkingSpotDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (this.validateParkingSpotNumber(parkingSpotDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (this.validateApartmentAndBlock(parkingSpotDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validateLicensePlateCar(ParkingSpotDto parkingSpotDto) {
        ParkingSpotView view = this.parkingSpotRepository.findByLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        return !(view == null || view.getId().equals(parkingSpotDto.getId()));
    }

    private boolean validateParkingSpotNumber(ParkingSpotDto parkingSpotDto) {
        ParkingSpotView view = this.parkingSpotRepository.findByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        return !(view == null || view.getId().equals(parkingSpotDto.getId()));
    }

    private boolean validateApartmentAndBlock(ParkingSpotDto parkingSpotDto) {
        ParkingSpotView view = this.parkingSpotRepository.findByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock());
        return !(view == null || view.getId().equals(parkingSpotDto.getId()));
    }
}