package com.example.MathledgeAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MathledgeAPI.model.Score;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findTopByOrderByScoreDesc();
}
