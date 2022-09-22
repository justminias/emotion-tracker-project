package com.emotion.emotiontracker.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "REASONS")
public class ReasonEntity {

    @Id
    private String id;

    @Column(unique = true)
    private String name;
    private LocalDateTime updateDate;

    public ReasonEntity(String name) {
        this.name = name;
    }
}
