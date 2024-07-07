package pl.lodz.p.bicycle_management.external.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleDto;

@RestController
@RequestMapping("/api/v1/bicycle")
public class BicycleController {

    @GetMapping
    ResponseEntity<BicycleDto> getBicycle() {

        BicycleDto bicycle = new BicycleDto("Tesla","Model B", 2077,60, 60000, 50000);
        return ResponseEntity.ok(bicycle);
    }
}
