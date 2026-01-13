package com.example.demo;

import jakarta.persistence.*;

@Entity
public class WishEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long listId;        // ❗ Jeder Wunsch gehört zu einer Liste
    private Long categoryId;    // ❗ Kategorie → sorgt für saubere Trennung

    private String title;
    private String name;
    private String description;
    private String status;
    private int price;
    private boolean fulfilled;
    private String priority;

    public WishEntry() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getListId() { return listId; }
    public void setListId(Long listId) { this.listId = listId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public boolean isFulfilled() { return fulfilled; }
    public void setFulfilled(boolean fulfilled) { this.fulfilled = fulfilled; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}
