package com.emotion.emotiontracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "SOLUTIONS")
public class SolutionEntity {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "solutions")
    @JsonBackReference
    private List<EmotionLogEntity> emotionLogs;

    private LocalDateTime updateDate;

    public SolutionEntity(String name) {
        this.name = name;
    }
}
