package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.EmotionTrackerIntegrationTest;
import com.emotion.emotiontracker.dto.SolutionDto;
import com.emotion.emotiontracker.service.SolutionService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

@EmotionTrackerIntegrationTest
public class SolutionControllerTest {

    @Autowired
    SolutionService solutionService;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldAddSolutionToDatabase() {
        //given when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(SolutionDto.builder().id("1").name("Solution1").build())
                .when()
                .post("api/solution/add")
                .then()
                .statusCode(200);

        //then
        List<String> result = solutionService.selectSolutions();
        Assertions.assertEquals(List.of("Solution1"), result);
    }

    @Test
    public void shouldReadSolutionFromDatabase() {
        //given
        solutionService.createSolution(SolutionDto.builder()
                .id("2")
                .name("Solution2")
                .build());

        //when then
        List<String> result = RestAssured
                .given()
                .when()
                .get("api/solution/select")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("names", String.class);

        Assertions.assertTrue(result.contains("Solution2"));
    }

    @Test
    public void shouldUpdateSolutionToDatabase() {
        //given when
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(SolutionDto.builder().id("3").name("Solution3").build())
                .when()
                .post("api/solution/update")
                .then()
                .statusCode(200);

        //then
        List<String> result = solutionService.selectSolutions();
        Assertions.assertEquals(List.of("Solution3"), result);
    }

    @Test
    public void shouldDeleteSolutionFromDatabase() {
        //given
        solutionService.createSolution(SolutionDto.builder()
                .id("4")
                .name("Solution4")
                .build());

        //when
        RestAssured
                .given()
                .when()
                .delete("api/solution/remove/4")
                .then()
                .statusCode(200);

        //then
        List<String> result = solutionService.selectSolutions();
        Assertions.assertEquals(List.of(), result);

    }
}
