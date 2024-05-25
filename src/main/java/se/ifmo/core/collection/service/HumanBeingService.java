package se.ifmo.core.collection.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import se.ifmo.core.collection.model.HumanBeing;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.repository.HumanBeingRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class HumanBeingService {
    HumanBeingRepository repository;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public HumanBeing save(HumanBeing humanBeing) {
        readWriteLock.writeLock().lock();
        try {
            return repository.save(humanBeing);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public long count() {
        readWriteLock.readLock().lock();
        try {
            return repository.count();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Optional<HumanBeing> findByIdAndOwner(Long id, User owner) {
        readWriteLock.readLock().lock();
        try {
            return repository.findByIdAndOwner(id, owner);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public boolean removeByIdAndOwner(Long id, User owner) {
        readWriteLock.writeLock().lock();
        try {
            return repository.removeByIdAndOwner(id, owner);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void removeAllByOwner(User owner) {
        readWriteLock.writeLock().lock();
        try {
            repository.removeAllByOwner(owner);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public List<HumanBeing> findAll() {
        readWriteLock.readLock().lock();
        try {
            return repository.findAll();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public List<HumanBeing> findAllByOwner(User owner) {
        readWriteLock.readLock().lock();
        try {
            return repository.findAllByOwner(owner);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
