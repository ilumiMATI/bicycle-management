package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.rental.application.RentService;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.RentNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;
    private final RentDtoMapper rentDtoMapper;

    @PostMapping
    ResponseEntity<RentDto> addRent(@RequestBody RentDto rentMinimalDto) {
        RentDto rentMinimalDtoWithTime = new RentDto(null,
                rentMinimalDto.userId(), rentMinimalDto.bicycleId(), LocalDateTime.now());
        Rent rent = rentService.addRent(rentDtoMapper.toDomain(rentMinimalDtoWithTime));
        return ResponseEntity.ok(rentDtoMapper.toDto(rent));
    }

    @GetMapping
    ResponseEntity<List<RentDto>> getAllRents() {
        return ResponseEntity.ok(
                StreamSupport.stream(rentService.findAllRents().spliterator(), false)
                        .map(rentDtoMapper::toDto)
                        .toList());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<RentDto> getRentById(@PathVariable Integer id) {
        Optional<Rent> rent = rentService.findRentById(id);
        if(rent.isPresent()) {
            return ResponseEntity.ok(rentDtoMapper.toDto(rent.get()));
        }
        return ResponseEntity.notFound().build();
    }

    // Updating rent info shouldn't allow everything
    @PutMapping
    ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentMinimalDto) {
        try {
            Rent updatedRent = rentService.updateRent(rentDtoMapper.toDomain(rentMinimalDto));
            return ResponseEntity.ok(rentDtoMapper.toDto(updatedRent));
        } catch (RentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deleteRent(@PathVariable Integer id) {
        rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }
}