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
    ResponseEntity<RentDto> addRent(@RequestBody RentDto rentDto) {
        RentDto rentDtoWithTime = new RentDto(null, rentDto.rentNumber(),
                rentDto.userId(), rentDto.bicycleId(), LocalDateTime.now());
        Rent rent = rentService.create(rentDtoMapper.toDomain(rentDtoWithTime));
        return ResponseEntity.ok(rentDtoMapper.toDto(rent));
    }

    @GetMapping(path = "/{rentNumber}")
    ResponseEntity<RentDto> getRentById(@PathVariable String rentNumber) {
        Optional<Rent> rent = rentService.findByRentNumber(rentNumber);
        if(rent.isPresent()) {
            return ResponseEntity.ok(rentDtoMapper.toDto(rent.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<RentDto>> getAllRents() {
        return ResponseEntity.ok(
                StreamSupport.stream(rentService.findAll().spliterator(), false)
                        .map(rentDtoMapper::toDto)
                        .toList());
    }

    // Updating rent info shouldn't allow everything
    @PutMapping
    ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto) {
        try {
            Rent updatedRent = rentService.update(rentDtoMapper.toDomain(rentDto));
            return ResponseEntity.ok(rentDtoMapper.toDto(updatedRent));
        } catch (RentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deleteRent(@PathVariable Integer id) {
        rentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
