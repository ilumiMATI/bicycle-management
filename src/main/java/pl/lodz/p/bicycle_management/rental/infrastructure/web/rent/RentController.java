package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.rental.application.RentService;
import pl.lodz.p.bicycle_management.rental.domain.Rent;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;
    private final RentDtoMapper rentDtoMapper;
    private final PageRentDtoMapper pageRentDtoMapper;

    @PostMapping
    ResponseEntity<RentDto> createRent(@RequestBody RentDto rentDto) {
        Rent rent = rentService.save(rentDtoMapper.toDomain(rentDto));
        return ResponseEntity.ok(rentDtoMapper.toDto(rent));
    }

    @DeleteMapping(path = "/{rentNumber}")
    ResponseEntity<Void> deleteRentByNumber(@PathVariable String rentNumber) {
        Rent rent = rentService.findByRentNumber(rentNumber);
        rentService.delete(rent.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{rentNumber}")
    ResponseEntity<RentDto> getRentByNumber(@PathVariable String rentNumber) {
        Rent rent = rentService.findByRentNumber(rentNumber);
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
