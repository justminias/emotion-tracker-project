package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.EmotionTrackerIntegrationTest;
import com.emotion.emotiontracker.dto.ReasonDto;
import com.emotion.emotiontracker.service.ReasonService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

@EmotionTrackerIntegrationTest
public class ReasonControllerTest {

    @Autowired
    ReasonService reasonService;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldAddReasonToDatabase() {
        //given when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(ReasonDto.builder().id("1").name("Reason1").build())
                .when()
                .post("api/reason/add")
                .then()
                .statusCode(200);

        //then
        List<ReasonDto> result = reasonService.selectReasons();
        Assertions.assertEquals(List.of(ReasonDto.builder().id("1").name("Reason1").build()), result);
    }

    @Test
    public void shouldReadReasonFromDatabase() {
        //given
        ReasonDto reasonDto = new ReasonDto("2", "Reason2");
        reasonService.createReason(reasonDto);

        //when then
        List<String> result = RestAssured
                .given()
                .when()
                .get("api/reason/select")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("Reason2", String.class);
    }

    @Test
    public void shouldUpdateReasonToDatabase() {
        //given when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(ReasonDto.builder().id("3").name("Reason3").build())
                .when()
                .post("api/reason/update")
                .then()
                .statusCode(200);

        //then
        String result = reasonService.selectReasons().stream()
                .filter(dto -> "3".equals(dto.getId()))
                .map(ReasonDto::getName)
                .findFirst()
                .get();
        Assertions.assertEquals("Reason3", result);
    }

    @Test
    public void shouldDeleteReasonFromDatabase() {
        //given
        ReasonDto reasonDto = new ReasonDto("4", "Reason4");
        reasonService.createReason(reasonDto);

        //when
        RestAssured
                .given()
                .when()
                .delete("api/reason/remove/4")
                .then()
                .statusCode(200);

        //then
        List<ReasonDto> result = reasonService.selectReasons();
        Assertions.assertEquals(List.of(), result);
    }
}
