package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class WishEntryController {

    private final WishCategoryService categoryService;
    private final WishListService listService;
    private final WishService entryService;

    public WishEntryController(
            WishCategoryService categoryService,
            WishListService listService,
            WishService entryService
    ) {
        this.categoryService = categoryService;
        this.listService = listService;
        this.entryService = entryService;
    }

    // ---------- Kategorien ----------
    @GetMapping("/categories")
    public Iterable<WishCategory> getCategories() {
        return categoryService.getAllCategories();
    }

    // ---------- Listen einer Kategorie ----------
    @GetMapping("/categories/{catId}/lists")
    public List<WishList> getLists(@PathVariable Long catId) {
        return listService.getLists(catId);
    }

    @PostMapping("/categories/{catId}/lists")
    public WishList createList(@PathVariable Long catId, @RequestBody WishList list) {
        list.setCategoryId(catId);
        return listService.create(list);
    }

    // ---------- Wünsche einer Liste ----------
    @GetMapping("/lists/{listId}/wishes")
    public List<WishEntry> getWishes(@PathVariable Long listId) {
        return entryService.getAllForList(listId);
    }

    @PostMapping("/lists/{listId}/wishes")
    public WishEntry createWish(@PathVariable Long listId, @RequestBody WishEntry entry) {
        entry.setListId(listId);
        return entryService.save(entry);
    }

    @PutMapping("/wishes/{wishId}")
    public WishEntry updateWish(@PathVariable Long wishId, @RequestBody WishEntry updated) {
        return entryService.update(wishId, updated);
    }

    @DeleteMapping("/wishes/{wishId}")
    public void deleteWish(@PathVariable Long wishId) {
        entryService.delete(wishId);
    }
}
