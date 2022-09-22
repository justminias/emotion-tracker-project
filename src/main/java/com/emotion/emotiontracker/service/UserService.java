package com.emotion.emotiontracker.service;

import com.emotion.emotiontracker.converter.UserConverter;
import com.emotion.emotiontracker.dto.UserDto;
import com.emotion.emotiontracker.entity.UserEntity;
import com.emotion.emotiontracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public void createUser(UserDto userDto) {
        UserEntity convertedUser = userConverter.convert(userDto);
        convertedUser.setRegistrationDate(LocalDateTime.now());
        userRepository.save(convertedUser);
    }

    public UserDto readUser(String id) {
        Optional<UserEntity> result = userRepository.findById(id);
        UserEntity user = result.orElseThrow(NullPointerException::new);
        UserDto convertedUser = userConverter.convertReverse(user);
        return convertedUser;
    }

    public void modifyUser(UserDto userDto) {
        UserEntity convertedUser = userConverter.convert(userDto);
        String tempId = convertedUser.getId();
        LocalDateTime tempRegistrationDate = userRepository.findById(tempId).get().getRegistrationDate();
        convertedUser.setRegistrationDate(tempRegistrationDate);
        userRepository.save(convertedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
