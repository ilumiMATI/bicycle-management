package pl.lodz.p.bicycle_management.external.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleApiService;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleDto;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleDtoMapper;
import pl.lodz.p.bicycle_management.domain.Bicycle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bicycle")
@RequiredArgsConstructor
public class BicycleController {
    final private BicycleApiService bicycleApiService;
    final private BicycleDtoMapper bicycleDtoMapper;

    @PostMapping
    ResponseEntity<BicycleDto> addBicycle(@RequestBody BicycleDto bicycleDto) {
        Bicycle bicycle = bicycleApiService.addBicycle(bicycleDtoMapper.toDomain(bicycleDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle));
    }

    @GetMapping
    ResponseEntity<List<BicycleDto>> findBicycles() {
        return ResponseEntity.ok(bicycleApiService.findAllBicycles().stream().map(bicycleDtoMapper::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<BicycleDto> findBicycleById(@PathVariable Integer id) {
        Optional<Bicycle> bicycle = bicycleApiService.findBicycleById(id);
        if (bicycle.isPresent())
            return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle.get()));
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<BicycleDto> updateBicycle(@PathVariable Integer id, @RequestBody BicycleDto bicycleDto) {
        Optional<Bicycle> bicycle = bicycleApiService.findBicycleById(id);
        if (!bicycle.isPresent())
            return ResponseEntity.notFound().build();

        Bicycle updatedBicycle = bicycleApiService.updateBicycle(id, bicycleDtoMapper.toDomain(bicycleDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(updatedBicycle));
    }

    @DeleteMapping(path = "/{id}")
    void deleteBicycle(@PathVariable Integer id) {
        bicycleApiService.deleteBicycle(id);
    }

}
