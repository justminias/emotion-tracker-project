package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.dto.UserDto;
import com.emotion.emotiontracker.entity.UserEntity;
import com.emotion.emotiontracker.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @GetMapping("/select/{id}")
    public UserDto selectUser(@PathVariable String id) {
        return userService.readUser(id);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.modifyUser(userDto);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
