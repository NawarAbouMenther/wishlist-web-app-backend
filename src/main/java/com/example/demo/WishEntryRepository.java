package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishEntryRepository extends JpaRepository<WishEntry, Long> {
}
