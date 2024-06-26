package com.example.MathledgeAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.MathledgeAPI.model.Score;
import com.example.MathledgeAPI.service.ScoreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public List<Score> getAllScores() {
        List<Score> scores = scoreService.getAllScores();
        scores.sort((s1, s2) -> s2.getScore() - s1.getScore());
        return scores;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long id) {
        Optional<Score> score = scoreService.getScoreById(id);
        return score.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/highest")
    public ResponseEntity<Score> getHighestScore() {
        Optional<Score> score = scoreService.getHighestScore();
        return score.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Score createScore(@RequestBody Score score) {
        return scoreService.saveScore(score);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> updateScoreById(@PathVariable Long id, @RequestBody Score newScore) {
        Optional<Score> score = scoreService.getScoreById(id);
        if (score.isPresent()) {
            Score existingScore = score.get();
            existingScore.setScore(newScore.getScore());
            final Score updatedScore = scoreService.saveScore(existingScore);
            return ResponseEntity.ok(updatedScore);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
        return ResponseEntity.noContent().build();
    }
}