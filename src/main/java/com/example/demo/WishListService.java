package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository repo;

    public List<WishList> getLists(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }

    public WishList create(WishList list) {
        return repo.save(list);
    }
}
