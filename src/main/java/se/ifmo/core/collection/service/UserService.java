package se.ifmo.core.collection.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UserService {
    private final UserRepository userRepository;
    private final MessageDigest messageDigest;

    public User save(String username, String password) {
        return userRepository.save(
            User.builder()
                    .username(username)
                    .password(new String(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8))))
                    .build()
        );
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        return userRepository.existsByUsernameAndPassword(
                username,
                new String(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8)))
        );
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(
                username,
                new String(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8)))
        );
    }
}
