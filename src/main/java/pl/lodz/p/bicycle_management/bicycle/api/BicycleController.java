package pl.lodz.p.bicycle_management.bicycle.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleService;

@RestController
@RequestMapping("/api/v1/bicycles")
@RequiredArgsConstructor
public class BicycleController {
    private final BicycleService bicycleService;
    private final BicycleDtoMapper bicycleDtoMapper;
    private final PageBicycleDtoMapper pageBicycleDtoMapper;

    @PostMapping
    ResponseEntity<BicycleDto> saveBicycle(@RequestBody BicycleDto bicycleDto) {
        Bicycle bicycle = bicycleService.save(bicycleDtoMapper.toDomain(bicycleDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<BicycleDto> getBicycle(@PathVariable Integer id) {
        Bicycle bicycle = bicycleService.findById(id);
        return ResponseEntity.ok(bicycleDtoMapper.toDto(bicycle));
    }

    @GetMapping
    ResponseEntity<PageBicycleDto> getBicycles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageBicycleDto pageBicycles = pageBicycleDtoMapper.toPageDto(bicycleService.findAll(pageable));

        return ResponseEntity.ok(pageBicycles);
    }

    @PutMapping
    ResponseEntity<BicycleDto> updateBicycle(@RequestBody BicycleDto bicycleDto) {
        Bicycle updatedBicycle = bicycleService.update(bicycleDtoMapper.toDomain(bicycleDto));
        return ResponseEntity.ok(bicycleDtoMapper.toDto(updatedBicycle));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> removeBicycle(@PathVariable Integer id) {
        bicycleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
