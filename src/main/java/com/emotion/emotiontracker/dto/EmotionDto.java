package com.emotion.emotiontracker.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmotionDto implements Dto {

    private String id;
    private String name;
}
