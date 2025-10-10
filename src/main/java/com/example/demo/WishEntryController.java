package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishEntryController {

    @GetMapping("/wishes")
    public String getWishes() {
        return "Hello World";
    }
}

