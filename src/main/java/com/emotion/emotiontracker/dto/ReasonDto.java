package com.emotion.emotiontracker.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReasonDto implements Dto {

    private String id;
    private String name;
    private LocalDateTime updateDate;

    public ReasonDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
