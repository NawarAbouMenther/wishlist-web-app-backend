package com.example.demo;

import com.example.demo.WishEntry;
import com.example.demo.WishEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WishEntryController {

    @Autowired
    private WishEntryRepository wishEntryRepository;

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
        return (List<WishEntry>) wishEntryRepository.findAll();
    }

    // Speichert einen neuen Wunsch
    @PostMapping("/wishes")
    public WishEntry addWish(@RequestBody WishEntry wishEntry) {
        return wishEntryRepository.save(wishEntry);
    }

    @DeleteMapping("/wishes/{id}")
    public void deleteWish(@PathVariable Long id) {
        wishEntryRepository.deleteById(id);
    }

}
