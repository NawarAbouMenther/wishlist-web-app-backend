package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WishEntryController {

    @Autowired
    private WishService wishService;

    @GetMapping("/")
    public String index() {
        return "Backend läuft ✅";
    }

    // Testroute – nützlich für erste Überprüfung
    @GetMapping("/hello")
    public String getHello() {
        return "Hello World";
    }

    // Gibt alle Wünsche zurück
    @GetMapping("/wishes")
    public List<WishEntry> getAllWishes() {
        return (List<WishEntry>) wishService.getAll();
    }

    // Speichert einen neuen Wunsch
    @PostMapping("/wishes")
    public WishEntry addWish(@RequestBody WishEntry wishEntry) {
        return wishService.save(wishEntry);
    }

    @DeleteMapping("/wishes/{id}")
    public void deleteWish(@PathVariable Long id) {
        wishService.deleteById(id);
    }

    @PutMapping("/wishes/{id}/fulfilled")
    public WishEntry markWishAsFulfilled(@PathVariable Long id) {
        return wishService.markAsFulfilled(id);
    }


}
