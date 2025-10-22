package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WishEntryController {

    @Autowired
    private WishEntryRepository wishEntryRepository;

    @GetMapping("/hello")
    public String getWishes() {
        return "Hello World";
    }
    @GetMapping("/wishes")
    public List<WishEntry> getAllWishes() {
        return wishEntryRepository.findAll();
    }

    @PostMapping("/wishes")
    public WishEntry addWish(@RequestBody WishEntry wishEntry) {
        return wishEntryRepository.save(wishEntry);
    }
}

