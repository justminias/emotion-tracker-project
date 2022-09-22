package com.emotion.emotiontracker.dto;

import com.emotion.emotiontracker.converter.UserConverter;
import com.emotion.emotiontracker.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class UserConverterTest {

    UserConverter userConverter = new UserConverter();
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2000, Month.SEPTEMBER, 12, 11, 11, 11, 11);

    @Test
    void shouldConvertUserDtoToUserEntity() {
        //given
        UserDto userDto = new UserDto("1", "J", "M", 25, LOCAL_DATE_TIME);

        //when
        UserEntity result = userConverter.convert(userDto);

        //then
        Assertions.assertEquals(UserEntity.class, result.getClass());
        Assertions.assertEquals(userDto.getId(), result.getId());
        Assertions.assertEquals(userDto.getFirstName(), result.getFirstName());
        Assertions.assertEquals(userDto.getLastName(), result.getLastName());
        Assertions.assertEquals(userDto.getAge(), result.getAge());
        Assertions.assertEquals(userDto.getRegistrationDate(), result.getRegistrationDate());
    }
}