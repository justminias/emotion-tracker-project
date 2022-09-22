package com.emotion.emotiontracker.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmotionLogResponse implements Dto {

    private String id;
    private String emotionName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String description;
    private String reason;
    private List<String> solutions;
}
