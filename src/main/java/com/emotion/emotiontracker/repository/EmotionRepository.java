package com.emotion.emotiontracker.repository;

import com.emotion.emotiontracker.entity.EmotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<EmotionEntity, String> {
}
