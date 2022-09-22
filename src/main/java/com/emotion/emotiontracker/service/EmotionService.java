package com.emotion.emotiontracker.service;

import com.emotion.emotiontracker.converter.Converter;
import com.emotion.emotiontracker.converter.EmotionConverter;
import com.emotion.emotiontracker.dto.EmotionDto;
import com.emotion.emotiontracker.entity.EmotionEntity;
import com.emotion.emotiontracker.repository.EmotionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;
    private final EmotionConverter emotionConverter;

    public void createEmotion(EmotionDto emotionDto) {
        EmotionEntity convertedEmotion = emotionConverter.convert(emotionDto);
        emotionRepository.save(convertedEmotion);
    }

    public EmotionDto readEmotion(String id) {
        Optional<EmotionEntity> result = emotionRepository.findById(id);
        EmotionEntity emotion = result.orElseThrow(NullPointerException::new);
        EmotionDto convertedEmotion = emotionConverter.convertReverse(emotion);
        return convertedEmotion;
    }

    public void modifyEmotion(EmotionDto emotionDto) {
        EmotionEntity convertedEmotion = emotionConverter.convert(emotionDto);
        emotionRepository.save(convertedEmotion);
    }

    public void deleteEmotion(String id) {
        emotionRepository.deleteById(id);
    }
}
