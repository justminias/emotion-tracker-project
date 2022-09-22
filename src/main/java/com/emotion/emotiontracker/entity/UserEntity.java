package com.emotion.emotiontracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<EmotionLogEntity> emotionLogs = new ArrayList<>();

    public UserEntity(String id, String firstName, String lastName, Integer age) {
        this(firstName, lastName, age);
        this.id = id;
    }

    public UserEntity(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.registrationDate = LocalDateTime.now();
    }
}
