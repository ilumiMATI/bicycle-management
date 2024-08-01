package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.rental.application.RentService;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rents")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;
    private final RentDtoMapper rentDtoMapper;
    private final PageRentDtoMapper pageRentDtoMapper;

    @PostMapping(path = "/create")
    ResponseEntity<Void> createRents(@RequestBody RentCommand cmd) {
        rentService.createRents(
                new UserId(cmd.userId()),
                cmd.bicycleIds()
                        .stream()
                        .map(BicycleId::new)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/my")
    ResponseEntity<List<RentDto>> getMyRents() {
        List<Rent> rents = rentService.getMyRents();
        return ResponseEntity.ok(
                rents.stream().map(rentDtoMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping(path = "/{rentNumber}/return")
    ResponseEntity<Void> returnRent(@PathVariable("rentNumber") String rentNumber) {
        rentService.returnRent(new RentNumber(rentNumber));
        return ResponseEntity.ok().build();
    }

    // CRUD BELOW

    @PostMapping
    ResponseEntity<RentDto> saveRent(@RequestBody RentDto rentDto) {
        Rent rent = rentService.save(rentDtoMapper.toDomain(rentDto));
        return ResponseEntity.ok(rentDtoMapper.toDto(rent));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        rentService.delete(new RentId(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{rentNumber}")
    ResponseEntity<RentDto> getRentByNumber(@PathVariable String rentNumber) {
        Rent rent = rentService.findByRentNumber(new RentNumber(rentNumber));
        return ResponseEntity.ok(rentDtoMapper.toDto(rent));
    }

    @GetMapping
    public ResponseEntity<PageRentDto> getRents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageRentDto pageRentDtos = pageRentDtoMapper.toPageDto(rentService.findAll(pageable));

        return ResponseEntity.ok(pageRentDtos);
    }

}
