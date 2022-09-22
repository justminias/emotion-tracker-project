package com.emotion.emotiontracker.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "EMOTIONS")
@Builder
public class EmotionEntity {

    @Id
    private String id;

    @Column(unique = true)
    private String name;
}
