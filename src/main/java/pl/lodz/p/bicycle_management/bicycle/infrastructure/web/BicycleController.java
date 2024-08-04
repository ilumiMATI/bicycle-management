package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.bicycle.application.BicycleService;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNotFoundException;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bicycle")
@RequiredArgsConstructor
public class BicycleController {
    final private BicycleService bicycleService;
    final private BicycleDtoMapper bicycleDtoMapper;

    @PostMapping
    ResponseEntity<BicycleDto> addBicycle(@RequestBody BicycleDto bicycleMinimalDto) {
        Bicycle bicycle = bicycleService.addBicycle(bicycleDtoMapper.toDomain(bicycleMinimalDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle));
    }

    @GetMapping
    ResponseEntity<List<BicycleDto>> findBicycles() {
        return ResponseEntity.ok(bicycleService.findAllBicycles().stream().map(bicycleDtoMapper::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<BicycleDto> findBicycleById(@PathVariable Integer id) {
        Bicycle bicycle = bicycleService.findBicycleById(id);
        return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle));
    }

    @PutMapping
    ResponseEntity<BicycleDto> updateBicycle(@RequestBody BicycleDto bicycleMinimalDto) {
        Bicycle updatedBicycle = bicycleService.updateBicycle(bicycleDtoMapper.toDomain(bicycleMinimalDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(updatedBicycle));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deleteBicycle(@PathVariable Integer id) {
        bicycleService.deleteBicycle(id);
        return ResponseEntity.ok().build();
    }

}
