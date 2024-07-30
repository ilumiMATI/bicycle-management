package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleType;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleTypeNotFoundException;
import pl.lodz.p.bicycle_management.bicycle.application.BicycleTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bicycleType")
@RequiredArgsConstructor
public class BicycleTypeController {
    final private BicycleTypeService bicycleTypeService;
    final private BicycleTypeDtoMapper bicycleTypeDtoMapper;

    @PostMapping
    ResponseEntity<BicycleTypeDto> addBicycleType(@RequestBody BicycleTypeDto bicycleTypeDto) {
        BicycleType bicycleType = bicycleTypeService.addBicycleType(bicycleTypeDtoMapper.toDomain(bicycleTypeDto));
        return ResponseEntity.ok(bicycleTypeDtoMapper.toDto(bicycleType));
    }

    @GetMapping
    ResponseEntity<List<BicycleTypeDto>> findAllBicycleTypes() {
        return ResponseEntity.ok(bicycleTypeService.findAllBicycleTypes().stream().map(bicycleTypeDtoMapper::toDto).toList());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<BicycleTypeDto> findBicycleTypeById(@PathVariable Integer id) {
        Optional<BicycleType> bicycleType = bicycleTypeService.findBicycleTypeById(id);
        if (bicycleType.isPresent())
            return ResponseEntity.ok(bicycleTypeDtoMapper.toDto(bicycleType.get()));
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    ResponseEntity<BicycleTypeDto> updateBicycleType(@RequestBody BicycleTypeDto bicycleTypeDto) {
        try {
            BicycleType updatedBicycleType = bicycleTypeService.updateBicycleType(bicycleTypeDtoMapper.toDomain(bicycleTypeDto));
            return ResponseEntity.ok(bicycleTypeDtoMapper.toDto(updatedBicycleType));
        } catch (BicycleTypeNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    void deleteBicycleType(@PathVariable Integer id) {
        bicycleTypeService.deleteBicycleType(id);
    }
}

