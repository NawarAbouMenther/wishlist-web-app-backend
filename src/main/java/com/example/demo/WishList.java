package com.example.demo;

import jakarta.persistence.*;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId; // ❗ Kategorie-Zuordnung
    private String title;

    public WishList() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
