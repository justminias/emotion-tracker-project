package com.emotion.emotiontracker.dto;

import com.emotion.emotiontracker.converter.ReasonConverter;
import com.emotion.emotiontracker.entity.ReasonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class ReasonConverterTest {

    ReasonConverter reasonConverter = new ReasonConverter();
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2000, Month.SEPTEMBER, 12, 11, 11, 11, 11);

    @Test
    void shouldConvertReasonDtoToReasonEntity() {
        //given
        ReasonDto reasonDto = new ReasonDto("1", "J", LOCAL_DATE_TIME);

        //when
        ReasonEntity result = reasonConverter.convert(reasonDto);

        //then
        Assertions.assertEquals(ReasonEntity.class, result.getClass());
        Assertions.assertEquals(reasonDto.getId(), result.getId());
        Assertions.assertEquals(reasonDto.getName(), result.getName());
    }
}