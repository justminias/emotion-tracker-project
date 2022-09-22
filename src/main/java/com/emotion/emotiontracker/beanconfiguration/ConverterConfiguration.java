package com.emotion.emotiontracker.beanconfiguration;

import com.emotion.emotiontracker.converter.*;
import com.emotion.emotiontracker.repository.EmotionRepository;
import com.emotion.emotiontracker.repository.ReasonRepository;
import com.emotion.emotiontracker.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public EmotionLogConverter emotionLogConverter(ReasonRepository reasonRepository, UserRepository userRepository, EmotionRepository emotionRepository) {
        return new EmotionLogConverter(reasonRepository, userRepository, emotionRepository);
    }

    @Bean
    public ReasonConverter reasonConverter() {
        return new ReasonConverter();
    }

    @Bean
    public EmotionConverter emotionConverter() {
        return new EmotionConverter();
    }

    @Bean
    public SolutionConverter solutionConverter() {
        return new SolutionConverter();
    }

    @Bean
    public UserConverter userConverter() {
        return new UserConverter();
    }

    @Bean
    public EmotionLogEntityToEmotionLogResponseConverter emotionLogEntityToEmotionLogResponseConverter() {
        return new EmotionLogEntityToEmotionLogResponseConverter();
    }
}
