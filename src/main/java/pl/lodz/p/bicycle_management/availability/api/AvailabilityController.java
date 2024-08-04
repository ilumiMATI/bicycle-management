package pl.lodz.p.bicycle_management.availability.api;

import pl.lodz.p.bicycle_management.availability.command.application.AvailabilityService;
import pl.lodz.p.bicycle_management.availability.command.application.CreateCommand;
import pl.lodz.p.bicycle_management.availability.command.application.LockCommand;
import pl.lodz.p.bicycle_management.availability.command.application.UnlockCommand;
import pl.lodz.p.bicycle_management.availability.query.facade.BicycleAvailabilityDto;
import pl.lodz.p.bicycle_management.availability.query.facade.BicycleAvailabilityFacade;
import pl.lodz.p.bicycle_management.availability.query.facade.PageBicycleAvailabilityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/availabilities",
        produces = "application/json",
        consumes = "application/json"
)
class AvailabilityController {

//    private final AvailabilityService availabilityService;
//
//    @PostMapping
//    public ResponseEntity<Void> createReservation(@RequestBody CreateCommand createCommand){
//        availabilityService.create(createCommand);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("lock")
//    public ResponseEntity<Void> lockBicycle(@RequestBody LockCommand lockCommand){
//        availabilityService.lockBicycle(lockCommand);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("unlock")
//    public ResponseEntity<Void> unlockBicycle(@RequestBody UnlockCommand unlockCommand){
//        availabilityService.unlockBicycle(unlockCommand);
//        return ResponseEntity.ok().build();
//    }

    private final BicycleAvailabilityFacade availabilityFacade;

    @GetMapping( path = "/{bicycleNumber}")
    public ResponseEntity<BicycleAvailabilityDto> getAvailability(@PathVariable String bicycleNumber) {
        return ResponseEntity.ok(availabilityFacade.findByBicycleNumber(bicycleNumber));
    }

    @GetMapping
    public ResponseEntity<PageBicycleAvailabilityDto> getAvailabilities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(availabilityFacade.findAll(pageable));
    }

}
