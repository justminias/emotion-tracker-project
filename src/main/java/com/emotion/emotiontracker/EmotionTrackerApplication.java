package com.emotion.emotiontracker;

import com.emotion.emotiontracker.entity.EmotionEntity;
import com.emotion.emotiontracker.repository.EmotionRepository;
import com.emotion.emotiontracker.repository.EmotionLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@SpringBootApplication
public class EmotionTrackerApplication implements CommandLineRunner {

    EmotionRepository emotionRepository;

    EmotionLogRepository entryRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmotionTrackerApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        emotionRepository.save(new EmotionEntity("5", "Anger"));
    }
}
