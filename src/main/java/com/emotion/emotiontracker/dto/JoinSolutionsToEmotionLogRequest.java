package com.emotion.emotiontracker.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JoinSolutionsToEmotionLogRequest {

    private String emotionLogId;
    private List<String> solutionIds;

}
