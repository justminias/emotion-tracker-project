package com.emotion.emotiontracker.dto;

import com.emotion.emotiontracker.converter.SolutionConverter;
import com.emotion.emotiontracker.entity.SolutionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class SolutionConverterTest {

    SolutionConverter solutionConverter = new SolutionConverter();
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2000, Month.SEPTEMBER, 12, 11, 11, 11, 11);

    @Test
    void shouldConvertSolutionDtoToSolutionEntity() {
        //given
        SolutionDto solutionDto = new SolutionDto("1", "J", LOCAL_DATE_TIME);

        //when
        SolutionEntity result = solutionConverter.convert(solutionDto);

        //then
        Assertions.assertEquals(SolutionEntity.class, result.getClass());
        Assertions.assertEquals(solutionDto.getId(), result.getId());
        Assertions.assertEquals(solutionDto.getName(), result.getName());
    }
}