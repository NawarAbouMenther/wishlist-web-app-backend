package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface WishListRepository extends CrudRepository<WishList, Long> {
    List<WishList> findByCategoryId(Long categoryId);
}
