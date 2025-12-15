package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
public class WishService {

    @Autowired
    private WishEntryRepository wishEntryRepository;

    public List<WishEntry> getAll() {
        return StreamSupport
                .stream(wishEntryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());    }

    public WishEntry save(WishEntry wishEntry) {
        return wishEntryRepository.save(wishEntry);
    }

    public void deleteById(Long id) {
        wishEntryRepository.deleteById(id);
    }

    public Optional<WishEntry> getById(Long id) {
        return wishEntryRepository.findById(id);
    }
}
