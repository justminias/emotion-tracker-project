package com.emotion.emotiontracker.converter;

import com.emotion.emotiontracker.converter.Converter;
import com.emotion.emotiontracker.dto.ReasonDto;
import com.emotion.emotiontracker.entity.ReasonEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
public class ReasonConverter implements Converter<ReasonDto, ReasonEntity> {

    @Override
    public ReasonEntity convert(ReasonDto reasonDto) {
        return ReasonEntity.builder()
                .id(reasonDto.getId() == null ? UUID.randomUUID().toString() : reasonDto.getId())
                .name(reasonDto.getName())
                .build();
    }
}
