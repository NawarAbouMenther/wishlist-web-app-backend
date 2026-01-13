package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface WishEntryRepository extends CrudRepository<WishEntry, Long> {
    List<WishEntry> findByListId(Long listId);
}
