package com.Yere.Gamehub.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Yere.Gamehub.model.GameEntity;

public interface JpaGameRepository extends JpaRepository<GameEntity, Long> {
    
}
