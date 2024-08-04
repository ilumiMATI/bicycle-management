package pl.lodz.p.bicycle_management.bicycle.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.bicycle.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@RequiredArgsConstructor
@Repository
@Log
public class BicycleStorageAdapter implements BicycleRepository {
    final private JpaBicycleRepository jpaBicycleRepository;

    @Override
    public Bicycle save(Bicycle bicycle) {
        try {
            Bicycle saved = jpaBicycleRepository.save(bicycle);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Bicycle with number " + bicycle.getBicycleId().asString() + " already exits in db");
            throw new BicycleAlreadyExistsException();
        }
    }



    @Override
    public Optional<Bicycle> findById(Integer id) {
        return jpaBicycleRepository.findById(id);
    }

    @Override
    public Optional<Bicycle> findByBicycleId(BicycleId bicycleId) {
        return jpaBicycleRepository.findByBicycleId(bicycleId);
    }

    @Override
    public List<Bicycle> findAll() {
        return jpaBicycleRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @Override
    public Bicycle update(Bicycle bicycle) {
        return jpaBicycleRepository.findByBicycleId(bicycle.getBicycleId())
                .map((found) -> jpaBicycleRepository.save(bicycle))
                .orElseThrow(BicycleNotFoundException::new);
    }

    @Override
    public void delete(Integer id) {
        jpaBicycleRepository.deleteById(id);
    }
}
