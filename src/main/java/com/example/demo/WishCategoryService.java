package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishCategoryService {

    @Autowired
    private WishCategoryRepository repo;

    public Iterable<WishCategory> getAllCategories() {
        return repo.findAll();
    }
}
