package com.emotion.emotiontracker.converter;

import com.emotion.emotiontracker.converter.Converter;
import com.emotion.emotiontracker.dto.UserDto;
import com.emotion.emotiontracker.entity.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
public class UserConverter implements Converter<UserDto, UserEntity> {

    @Override
    public UserEntity convert(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId() == null ? UUID.randomUUID().toString() : userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .registrationDate(userDto.getRegistrationDate())
                .build();
    }

    @Override
    public UserDto convertReverse(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .age(userEntity.getAge())
                .build();
    }
}
