package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.EmotionTrackerIntegrationTest;
import com.emotion.emotiontracker.dto.*;
import com.emotion.emotiontracker.service.EmotionService;
import com.emotion.emotiontracker.service.EmotionLogService;
import com.emotion.emotiontracker.service.ReasonService;
import com.emotion.emotiontracker.service.UserService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@EmotionTrackerIntegrationTest
public class EmotionLogControllerTest {

    @Autowired
    EmotionLogService emotionLogService;

    @Autowired
    EmotionService emotionService;

    @Autowired
    ReasonService reasonService;

    @Autowired
    UserService userService;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldAddEmotionLogToDatabase() {
        //given
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

        //when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(EmotionLogDto.builder()
                        .id("1")
                        .userId("1")
                        .emotionId("1")
                        .reasonId("1")
                        .startTime(LocalTime.of(12, 30, 33))
                        .endTime(LocalTime.of(13, 12, 33))
                        .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                        .description("Description1")
                        .build())
                .when()
                .post("api/emotion-log/add")
                .then()
                .statusCode(200);

        //then
        EmotionLogDto result = emotionLogService.readEmotionLog("1");
        Assertions.assertEquals(EmotionLogDto.builder()
                .id("1")
                .userId("1")
                .emotionId("1")
                .reasonId("1")
                .startTime(LocalTime.of(12, 30, 33))
                .endTime(LocalTime.of(13, 12, 33))
                .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                .description("Description1")
                .build(), result);
    }

    @Test
    public void shouldUpdateEmotionLogToDatabase() {
        //given
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

        //when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(EmotionLogDto.builder()
                        .id("3")
                        .userId("3")
                        .emotionId("3")
                        .reasonId("3")
                        .startTime(LocalTime.of(12, 30, 33))
                        .endTime(LocalTime.of(13, 12, 33))
                        .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                        .description("Description3")
                        .build())
                .when()
                .post("api/emotion-log/update")
                .then()
                .statusCode(200);

        //then
        EmotionLogDto result = emotionLogService.readEmotionLog("3");
        Assertions.assertEquals(EmotionLogDto.builder()
                .id("3")
                .userId("3")
                .emotionId("3")
                .reasonId("3")
                .startTime(LocalTime.of(12, 30, 33))
                .endTime(LocalTime.of(13, 12, 33))
                .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                .description("Description3")
                .build(), result);
    }

    @Test
    public void shouldDeleteEmotionLogFromDatabase() {
        //given
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

        //when
        RestAssured
                .given()
                .when()
                .delete("api/emotion-log/remove/4")
                .then()
                .statusCode(200);

        //then
        Assertions.assertThrows(NullPointerException.class, () -> emotionLogService.readEmotionLog("4"));
    }

    @Test
    public void shouldReturnEmotionLogsForUser() {
        //given
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

        //when
        List<EmotionLogResponse> result = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(EmotionLogsRequest.builder()
                        .userId("5")
                        .build())
                .when()
                .post("api/emotion-log")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("emotionLogs", EmotionLogResponse.class);

        //then
        Assertions.assertTrue(result.size() == 2);
        Assertions.assertEquals(EmotionLogResponse.builder()
                .id("5")
                .emotionName("Emotion5")
                .startTime(LocalTime.of(12, 30, 12))
                .endTime(LocalTime.of(13, 12, 12))
                .date(LocalDate.of(2020, Month.SEPTEMBER, 12))
                .description("Description5")
                .reason("Reason5")
                .solutions(new ArrayList<>())
                .build(), result.get(0));

        Assertions.assertEquals(EmotionLogResponse.builder()
                .id("6")
                .emotionName("Emotion5")
                .startTime(LocalTime.of(13, 13, 13))
                .endTime(LocalTime.of(14, 13, 13))
                .date(LocalDate.of(2021, Month.OCTOBER, 13))
                .description("Description6")
                .reason("Reason5")
                .solutions(new ArrayList<>())
                .build(), result.get(1));
    }
}
