package com.emotion.emotiontracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class EmotionLogsResponse {

    private List<EmotionLogResponse> emotionLogs = new ArrayList<>();
}
