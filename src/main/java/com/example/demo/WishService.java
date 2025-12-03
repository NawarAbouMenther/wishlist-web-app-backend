package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WishService {

    @Autowired
    private WishEntryRepository repo;

    public WishEntry save(WishEntry wish) {
        return repo.save(wish);
    }

    public WishEntry get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Wish not found"));
    }
}



