package pl.lodz.p.bicycle_management.bicycle.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.bicycle.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Log
@RequiredArgsConstructor
@Repository
public class BicycleStorageAdapter implements BicycleRepository {
    final private JpaBicycleRepository jpaBicycleRepository;

    @Override
    public Bicycle save(final Bicycle bicycle) {
        try {
            Bicycle saved = jpaBicycleRepository.save(bicycle);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Bicycle with number " + bicycle.getBicycleNumber().asString() + " already exits in db");
            throw new BicycleAlreadyExistsException();
        }
    }



    @Override
    public Optional<Bicycle> findById(final Integer id) {
        return jpaBicycleRepository.findById(id);
    }

    @Override
    public Optional<Bicycle> findByBicycleNumber(final BicycleNumber bicycleNumber) {
        return jpaBicycleRepository.findByBicycleNumber(bicycleNumber);
    }

    @Override
    public PageBicycle findAll(final Pageable pageable) {
        Page<Bicycle> pageOfBicycles = jpaBicycleRepository.findAll(pageable);
        List<Bicycle> bicyclesOnCurrentPage = pageOfBicycles.getContent();
        return new PageBicycle(
                bicyclesOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfBicycles.getTotalPages(),
                pageOfBicycles.getTotalElements()
        );
    }

    @Override
    public Bicycle update(final Bicycle bicycle) {
        return jpaBicycleRepository.findByBicycleNumber(bicycle.getBicycleNumber())
                .map((found) -> jpaBicycleRepository.save(bicycle))
                .orElseThrow(BicycleNotFoundException::new);
    }

    @Override
    public void delete(final Integer id) {
        jpaBicycleRepository.deleteById(id);
    }
}
