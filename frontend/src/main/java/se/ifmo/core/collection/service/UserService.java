package se.ifmo.core.collection.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.ifmo.core.collection.model.User;

import java.io.Serializable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UserService implements Serializable {
    public User save(String username, String password) {
        return null;
    }

    public boolean existsByUsername(String username) {
        return false;
    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        return false;
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return null;
    }
}
