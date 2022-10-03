package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.dto.EmotionDto;
import com.emotion.emotiontracker.dto.EmotionLogDto;
import com.emotion.emotiontracker.dto.ReasonDto;
import com.emotion.emotiontracker.dto.UserDto;
import com.emotion.emotiontracker.service.EmotionLogService;
import com.emotion.emotiontracker.service.EmotionService;
import com.emotion.emotiontracker.service.ReasonService;
import com.emotion.emotiontracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

@Component
@AllArgsConstructor
public class EmotionLogControllerTestInitializer {

    @Autowired
    EmotionLogService emotionLogService;

    @Autowired
    EmotionService emotionService;

    @Autowired
    ReasonService reasonService;

    @Autowired
    UserService userService;

    public void shouldAddEmotionLogToDatabaseInitializeData() {
        EmotionDto emotionDto = EmotionDto.builder()
                .id("1")
                .name("Emotion1")
                .build();
        emotionService.createEmotion(emotionDto);

        ReasonDto reasonDto = ReasonDto.builder()
                .id("1")
                .name("Reason1")
                .build();
        reasonService.createReason(reasonDto);

        UserDto userDto = UserDto.builder()
                .id("1")
                .firstName("Fname1")
                .lastName("Lname1")
                .age(20)
                .build();
        userService.createUser(userDto);
    }

    public void shouldUpdateEmotionLogToDatabaseInitializeData() {
        EmotionDto emotionDto = EmotionDto.builder()
                .id("3")
                .name("Emotion3")
                .build();
        emotionService.createEmotion(emotionDto);

        ReasonDto reasonDto = ReasonDto.builder()
                .id("3")
                .name("Reason3")
                .build();
        reasonService.createReason(reasonDto);

        UserDto userDto = UserDto.builder()
                .id("3")
                .firstName("Fname3")
                .lastName("Lname3")
                .age(20)
                .build();
        userService.createUser(userDto);

        EmotionLogDto emotionLogDto = EmotionLogDto.builder()
                .id("3")
                .userId("3")
                .emotionId("3")
                .reasonId("3")
                .startTime(LocalTime.of(12, 30, 12))
                .endTime(LocalTime.of(13, 12, 12))
                .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                .description("Description3")
                .build();
        emotionLogService.createEmotionLog(emotionLogDto);
    }

    public void shouldDeleteEmotionLogFromDatabaseInitializeData() {
        EmotionDto emotionDto = EmotionDto.builder()
                .id("4")
                .name("Emotion4")
                .build();
        emotionService.createEmotion(emotionDto);

        ReasonDto reasonDto = ReasonDto.builder()
                .id("4")
                .name("Reason4")
                .build();
        reasonService.createReason(reasonDto);

        UserDto userDto = UserDto.builder()
                .id("4")
                .firstName("Fname4")
                .lastName("Lname4")
                .age(20)
                .build();
        userService.createUser(userDto);

        EmotionLogDto emotionLogDto = EmotionLogDto.builder()
                .id("4")
                .userId("4")
                .emotionId("4")
                .reasonId("4")
                .startTime(LocalTime.of(12, 30, 12))
                .endTime(LocalTime.of(13, 12, 12))
                .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                .description("Description4")
                .build();
        emotionLogService.createEmotionLog(emotionLogDto);
    }

    public void shouldReturnEmotionLogsForUserInitializeData() {
        EmotionDto emotionDto = EmotionDto.builder()
                .id("5")
                .name("Emotion5")
                .build();
        emotionService.createEmotion(emotionDto);

        ReasonDto reasonDto = ReasonDto.builder()
                .id("5")
                .name("Reason5")
                .build();
        reasonService.createReason(reasonDto);

        UserDto userDto = UserDto.builder()
                .id("5")
                .firstName("Fname5")
                .lastName("Lname5")
                .age(20)
                .build();
        userService.createUser(userDto);

        EmotionLogDto emotionLogDto1 = EmotionLogDto.builder()
                .id("5")
                .userId("5")
                .emotionId("5")
                .reasonId("5")
                .startTime(LocalTime.of(12, 30, 12))
                .endTime(LocalTime.of(13, 12, 12))
                .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                .description("Description5")
                .build();
        emotionLogService.createEmotionLog(emotionLogDto1);

        EmotionLogDto emotionLogDto2 = EmotionLogDto.builder()
                .id("6")
                .userId("5")
                .emotionId("5")
                .reasonId("5")
                .startTime(LocalTime.of(13, 13, 13))
                .endTime(LocalTime.of(14, 13, 13))
                .date(LocalDate.of(2021, Month.OCTOBER, 13))
                .description("Description6")
                .build();
        emotionLogService.createEmotionLog(emotionLogDto2);
    }
}
