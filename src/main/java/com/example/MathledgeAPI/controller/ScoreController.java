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
        return scoreService.getAllScores();
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
    public ResponseEntity<Score> updateScore(@PathVariable Long id, @RequestBody Score scoreDetails) {
        Optional<Score> score = scoreService.getScoreById(id);
        if (score.isPresent()) {
            Score existingScore = score.get();
            existingScore.setNombre(scoreDetails.getNombre());
            existingScore.setScore(scoreDetails.getScore());
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