package com.emotion.emotiontracker.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SolutionDto implements Dto {

    private String id;
    private String name;
    private LocalDateTime updateDate;

    public SolutionDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
