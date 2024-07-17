package pl.lodz.p.bicycle_management.bicycle.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNotFoundException;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleService;
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
    ResponseEntity<BicycleMinimalDto> addBicycle(@RequestBody BicycleMinimalDto bicycleMinimalDto) {
        Bicycle bicycle = bicycleService.addBicycle(bicycleDtoMapper.toDomain(bicycleMinimalDto));
        return ResponseEntity.ok(bicycleDtoMapper.toMinimalDto(bicycle));
    }

    @GetMapping
    ResponseEntity<List<BicycleDto>> findBicycles() {
        return ResponseEntity.ok(bicycleService.findAllBicycles().stream().map(bicycleDtoMapper::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<BicycleDto> findBicycleById(@PathVariable Integer id) {
        Optional<Bicycle> bicycle = bicycleService.findBicycleById(id);
        if (bicycle.isPresent())
            return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle.get()));
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    ResponseEntity<BicycleMinimalDto> updateBicycle(@RequestBody BicycleMinimalDto bicycleMinimalDto) {
        try {
            Bicycle updatedBicycle = bicycleService.updateBicycle(bicycleDtoMapper.toDomain(bicycleMinimalDto));
            return ResponseEntity.ok(bicycleDtoMapper.toMinimalDto(updatedBicycle));
        } catch (BicycleNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    void deleteBicycle(@PathVariable Integer id) {
        bicycleService.deleteBicycle(id);
    }

}
