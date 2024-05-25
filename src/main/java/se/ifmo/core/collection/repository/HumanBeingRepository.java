package se.ifmo.core.collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.core.collection.model.HumanBeing;
import se.ifmo.core.collection.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface HumanBeingRepository extends JpaRepository<HumanBeing, Long> {
    List<HumanBeing> findAll();
    List<HumanBeing> findAllByOwner(User owner);

    Optional<HumanBeing> findByIdAndOwner(Long id, User owner);

    void removeAllByOwner(User owner);
    boolean removeByIdAndOwner(Long id, User owner);
}
