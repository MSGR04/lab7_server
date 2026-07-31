package se.ifmo.core.collection.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import se.ifmo.core.collection.model.HumanBeing;
import se.ifmo.core.collection.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class HumanBeingService implements Serializable {
    public HumanBeing save(HumanBeing humanBeing) {
        return null;
    }

    public long count() {
        return 00;
    }

    public Optional<HumanBeing> findByIdAndOwner(Long id, User owner) {
        return null;
    }

    public boolean removeByIdAndOwner(Long id, User owner) {
    }

    public void removeAllByOwner(User owner) {
    }

    public List<HumanBeing> findAll() {
        return null;
    }

    public List<HumanBeing> findAllByOwner(User owner) {
        return null;
    }
}
