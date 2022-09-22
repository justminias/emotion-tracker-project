package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.EmotionTrackerIntegrationTest;
import com.emotion.emotiontracker.dto.EmotionDto;
import com.emotion.emotiontracker.service.EmotionService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;

@EmotionTrackerIntegrationTest
public class EmotionControllerTest {

    @Autowired
    EmotionService emotionService;

    @LocalServerPort // todo: add some config to not init it any time
    private int port;

    @BeforeEach // todo: add some config to not init it any time, maybe should change RANDOM_PORT
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldAddEmotionToDatabase() {
        //given when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(EmotionDto.builder().id("7").name("Euphoria").build())
                .when()
                .post("api/emotion/add")
                .then()
                .statusCode(200);

        //then
        EmotionDto result = emotionService.readEmotion("7");
        Assertions.assertEquals(EmotionDto.builder()
                .id("7")
                .name("Euphoria")
                .build(), result);
    }

    @Test
    public void shouldReadEmotionFromDatabase() {
        //given
        EmotionDto emotionDto = new EmotionDto("5", "Anger");
        emotionService.createEmotion(emotionDto);

        //when
        EmotionDto result = RestAssured
                .given()
                .get("api/emotion/select/5")
                .then()
                .statusCode(200)
                .extract().as(EmotionDto.class);

        //then
        Assertions.assertEquals("5", result.getId());
        Assertions.assertEquals("Anger", result.getName());
        Assertions.assertEquals(new EmotionDto("5", "Anger"), result);
        Assertions.assertEquals(EmotionDto.builder()
                .id("5")
                .name("Anger")
                .build(), result);
    }

    @Test
    public void shouldReadEmotionFromDatabase2() {
        //given
        EmotionDto emotionDto = new EmotionDto("6", "Sadness");
        emotionService.createEmotion(emotionDto);

        //when then
        RestAssured
                .given()
                .when()
                .get("api/emotion/select/6")
                .then()
                .statusCode(200)
                .body("id", equalTo("6"), "name", equalTo("Sadness"));
    }

    @Test
    public void shouldUpdateEmotionToDatabase() {
        //given when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(EmotionDto.builder().id("9").name("Anxiety").build())
                .when()
                .post("api/emotion/update")
                .then()
                .statusCode(200);

        //then
        EmotionDto result = emotionService.readEmotion("9");
        Assertions.assertEquals(EmotionDto.builder().id("9").name("Anxiety").build(), result);
    }

    @Test
    public void shouldDeleteEmotionFromDatabase() {
        //given
        EmotionDto emotionDto = new EmotionDto("4", "EmotionToDelete");
        emotionService.createEmotion(emotionDto);

        //when
        RestAssured
                .given()
                .when()
                .delete("api/emotion/remove/4")
                .then()
                .statusCode(200);

        //then
        Assertions.assertThrows(NullPointerException.class, () -> emotionService.readEmotion("4"));
    }
}