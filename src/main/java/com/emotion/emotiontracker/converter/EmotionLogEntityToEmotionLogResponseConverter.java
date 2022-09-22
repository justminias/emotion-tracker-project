package com.emotion.emotiontracker.converter;

import com.emotion.emotiontracker.dto.EmotionLogResponse;
import com.emotion.emotiontracker.entity.EmotionLogEntity;

import java.util.stream.Collectors;

public class EmotionLogEntityToEmotionLogResponseConverter implements Converter<EmotionLogEntity, EmotionLogResponse> {

    @Override
    public EmotionLogResponse convert(EmotionLogEntity emotionLogEntity) {
        return EmotionLogResponse.builder()
                .id(emotionLogEntity.getId())
                .emotionName(emotionLogEntity.getEmotion().getName())
                .startTime(emotionLogEntity.getStartTime())
                .endTime(emotionLogEntity.getEndTime())
                .date(emotionLogEntity.getDate())
                .description(emotionLogEntity.getDescription())
                .reason(emotionLogEntity.getReason().getName())
                .solutions(emotionLogEntity.getSolutions().stream()
                        .map(solutionEntity -> solutionEntity.getName())
                        .collect(Collectors.toList()))
                .build();
    }
}
