package com.emotion.emotiontracker.repository;

import com.emotion.emotiontracker.entity.EmotionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmotionLogRepository extends JpaRepository<EmotionLogEntity, String> {

    List<EmotionLogEntity> findAllByUserId(String userId);
}
