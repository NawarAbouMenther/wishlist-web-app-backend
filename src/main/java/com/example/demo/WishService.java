package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishService {

    @Autowired
    private WishEntryRepository repo;

    public List<WishEntry> getAllForList(Long listId) {
        return repo.findByListId(listId);
    }

    public WishEntry save(WishEntry entry) {
        return repo.save(entry);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public WishEntry update(Long id, WishEntry updated) {
        WishEntry e = repo.findById(id).orElseThrow();

        e.setTitle(updated.getTitle());
        e.setName(updated.getName());
        e.setDescription(updated.getDescription());
        e.setStatus(updated.getStatus());
        e.setPriority(updated.getPriority());
        e.setPrice(updated.getPrice());
        e.setFulfilled(updated.isFulfilled());

        return repo.save(e);
    }
}
