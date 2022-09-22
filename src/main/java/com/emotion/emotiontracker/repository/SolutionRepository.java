package com.emotion.emotiontracker.repository;

import com.emotion.emotiontracker.entity.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<SolutionEntity, String> {
}
