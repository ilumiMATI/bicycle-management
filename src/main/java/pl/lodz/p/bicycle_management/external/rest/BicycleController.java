package pl.lodz.p.bicycle_management.external.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.domain.bicycle.BicycleService;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleDto;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleDtoMapper;
import pl.lodz.p.bicycle_management.domain.bicycle.Bicycle;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bicycle")
@RequiredArgsConstructor
public class BicycleController {
    final private BicycleService bicycleService;
    final private BicycleDtoMapper bicycleDtoMapper;

    @PostMapping
    ResponseEntity<BicycleDto> addBicycle(@RequestBody BicycleDto bicycleDto) {
        Bicycle bicycle = bicycleService.addBicycle(bicycleDtoMapper.toDomain(bicycleDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle));
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

    // TODO: NotFound logic should be inside repository and here it should only be catched
    @PutMapping(path = "/{id}")
    ResponseEntity<BicycleDto> updateBicycle(@PathVariable Integer id, @RequestBody BicycleDto bicycleDto) {
        Optional<Bicycle> bicycle = bicycleService.findBicycleById(id);
        if (bicycle.isEmpty())
            return ResponseEntity.notFound().build();

        Bicycle updatedBicycle = bicycleService.updateBicycle(id, bicycleDtoMapper.toDomain(bicycleDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(updatedBicycle));
    }

    @DeleteMapping(path = "/{id}")
    void deleteBicycle(@PathVariable Integer id) {
        bicycleService.deleteBicycle(id);
    }

}
