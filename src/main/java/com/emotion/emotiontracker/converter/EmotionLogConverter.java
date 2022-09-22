package com.emotion.emotiontracker.converter;

import com.emotion.emotiontracker.dto.EmotionLogDto;
import com.emotion.emotiontracker.dto.EmotionLogResponse;
import com.emotion.emotiontracker.entity.EmotionLogEntity;
import com.emotion.emotiontracker.repository.EmotionRepository;
import com.emotion.emotiontracker.repository.ReasonRepository;
import com.emotion.emotiontracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmotionLogConverter implements Converter<EmotionLogDto, EmotionLogEntity> {

    private final ReasonRepository reasonRepository;
    private final UserRepository userRepository;
    private final EmotionRepository emotionRepository;

    @Override
    public EmotionLogEntity convert(EmotionLogDto emotionLogDto) {

        return EmotionLogEntity.builder()
                .id(emotionLogDto.getId() == null ? UUID.randomUUID().toString() : emotionLogDto.getId())
                .user(userRepository.getReferenceById(emotionLogDto.getUserId()))
                .emotion(emotionRepository.getReferenceById(emotionLogDto.getEmotionId()))
                .reason(reasonRepository.getReferenceById(emotionLogDto.getReasonId()))
                .startTime(emotionLogDto.getStartTime())
                .endTime(emotionLogDto.getEndTime())
                .date(emotionLogDto.getDate())
                .description(emotionLogDto.getDescription())
                .build();
    }

    public EmotionLogDto convertReverse(EmotionLogEntity emotionLogEntity) {
        return EmotionLogDto.builder()
                .id(emotionLogEntity.getId())
                .userId(emotionLogEntity.getUser().getId())
                .emotionId(emotionLogEntity.getEmotion().getId())
                .reasonId(emotionLogEntity.getReason().getId())
                .startTime(emotionLogEntity.getStartTime())
                .endTime(emotionLogEntity.getEndTime())
                .date(emotionLogEntity.getDate())
                .description(emotionLogEntity.getDescription())
                .build();
    }
}
