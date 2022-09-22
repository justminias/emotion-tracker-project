package com.emotion.emotiontracker.repository;

import com.emotion.emotiontracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
