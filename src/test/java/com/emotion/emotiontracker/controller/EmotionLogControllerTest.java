package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.EmotionTrackerIntegrationTest;
import com.emotion.emotiontracker.dto.EmotionLogDto;
import com.emotion.emotiontracker.dto.EmotionLogResponse;
import com.emotion.emotiontracker.dto.EmotionLogsRequest;
import com.emotion.emotiontracker.service.EmotionLogService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
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

@EmotionTrackerIntegrationTest
public class EmotionLogControllerTest {

    @Autowired
    EmotionLogService emotionLogService;

    @Autowired
    TestCleaner testCleaner;

    @Autowired
    EmotionLogControllerTestInitializer emotionLogControllerTestInitializer;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    public void tearDown() {
        testCleaner.cleanAllRepositories();
    }

    @Test
    public void shouldAddEmotionLogToDatabase() {
        //given
        emotionLogControllerTestInitializer.shouldAddEmotionLogToDatabaseInitializeData();

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
        emotionLogControllerTestInitializer.shouldUpdateEmotionLogToDatabaseInitializeData();

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
        emotionLogControllerTestInitializer.shouldDeleteEmotionLogFromDatabaseInitializeData();

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
        emotionLogControllerTestInitializer.shouldReturnEmotionLogsForUserInitializeData();

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
