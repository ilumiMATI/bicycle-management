package pl.lodz.p.bicycle_management.external.storage.bicycle;

import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.domain.bicycle.Bicycle;
import pl.lodz.p.bicycle_management.domain.bicycle.BicycleRepository;

import java.util.*;

@Repository
public class BicycleMemoryStorageAdapter implements BicycleRepository {
    final private Map<Integer, Bicycle> bicycles = new HashMap<>();
    private static int currentId = 0;

    @Override
    public Bicycle save(Bicycle bicycle) {
        bicycles.put(++currentId, bicycle);
        bicycle.setId(currentId);
        return bicycle;
    }

    @Override
    public Optional<Bicycle> findById(Integer id) {
        return Optional.ofNullable(bicycles.get(id));
    }

    @Override
    public List<Bicycle> findAll() {
        return new ArrayList<>(bicycles.values());
    }

    @Override
    public Bicycle update(Bicycle bicycle) {
        bicycles.put(bicycle.getId(), bicycle);
        return bicycle;
    }

    @Override
    public void delete(Integer id) {
        bicycles.remove(id);
    }
}
