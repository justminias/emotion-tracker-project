package com.emotion.emotiontracker.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserDto implements Dto {

    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDateTime registrationDate;

    public UserDto(String id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
