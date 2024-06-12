package com.example.MathledgeAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MathledgeAPI.model.Score;
import com.example.MathledgeAPI.repository.ScoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Optional<Score> getScoreById(Long id) {
        return scoreRepository.findById(id);
    }

    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);
    }

    public Optional<Score> getHighestScore() {
        return scoreRepository.findTopByOrderByScoreDesc();
    }
}
