package com.example.demo;

import jakarta.persistence.*;

@Entity
public class WishCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;     // z.B. "birthday"
    private String label;   // z.B. "Geburtstag"

    public WishCategory() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
