package com.Yere.Gamehub.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GameEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public GameEntity(String name) {
        this.name = name;
    }

    protected GameEntity() {
    }

    public Long getId() {
        return id;
    }

    private String name;

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
