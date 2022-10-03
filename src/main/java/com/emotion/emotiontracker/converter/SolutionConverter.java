package com.emotion.emotiontracker.converter;

import com.emotion.emotiontracker.converter.Converter;
import com.emotion.emotiontracker.dto.SolutionDto;
import com.emotion.emotiontracker.entity.SolutionEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
public class SolutionConverter implements Converter<SolutionDto, SolutionEntity> {

    @Override
    public SolutionEntity convert(SolutionDto solutionDto) {
        return SolutionEntity.builder()
                .id(solutionDto.getId() == null ? UUID.randomUUID().toString() : solutionDto.getId())
                .name(solutionDto.getName())
                .build();
    }

    @Override
    public SolutionDto convertReverse(SolutionEntity entity) {
        return SolutionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
