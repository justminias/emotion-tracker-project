package com.emotion.emotiontracker.converter;

import com.emotion.emotiontracker.dto.Dto;

public interface Converter<T1, T2> {

    T2 convert(T1 type1);
}
