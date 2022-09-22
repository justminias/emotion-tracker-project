package com.emotion.emotiontracker.repository;

import com.emotion.emotiontracker.entity.ReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository extends JpaRepository<ReasonEntity, String> {
}
