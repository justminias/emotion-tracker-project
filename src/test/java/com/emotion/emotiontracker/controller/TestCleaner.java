package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestCleaner {

    @Autowired
    EmotionLogRepository emotionLogRepository;

    @Autowired
    EmotionRepository emotionRepository;

    @Autowired
    ReasonRepository reasonRepository;

    @Autowired
    SolutionRepository solutionRepository;

    @Autowired
    UserRepository userRepository;

    public void cleanAllRepositories() {
        emotionLogRepository.deleteAll();
        emotionRepository.deleteAll();
        reasonRepository.deleteAll();
        solutionRepository.deleteAll();
        userRepository.deleteAll();
    }
}
