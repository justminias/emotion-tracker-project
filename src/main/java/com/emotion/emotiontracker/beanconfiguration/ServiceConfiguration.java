package com.emotion.emotiontracker.beanconfiguration;

import com.emotion.emotiontracker.converter.*;
import com.emotion.emotiontracker.repository.*;
import com.emotion.emotiontracker.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public EmotionLogService emotionLogService(EmotionLogRepository emotionLogRepository, SolutionRepository solutionRepository,
                                               EmotionLogConverter emotionLogConverter,
                                               EmotionLogEntityToEmotionLogResponseConverter emotionLogEntityToEmotionLogResponseConverter) {
        return new EmotionLogService(emotionLogRepository, solutionRepository, emotionLogConverter, emotionLogEntityToEmotionLogResponseConverter);
    }

    @Bean
    public EmotionService emotionService(EmotionRepository emotionRepository, EmotionConverter emotionConverter) {
        return new EmotionService(emotionRepository, emotionConverter);
    }

    @Bean
    public ReasonService reasonService(ReasonRepository reasonRepository, ReasonConverter reasonConverter) {
        return new ReasonService(reasonRepository, reasonConverter);
    }

    @Bean
    public SolutionService solutionService(SolutionRepository solutionRepository, SolutionConverter solutionConverter) {
        return new SolutionService(solutionRepository, solutionConverter);
    }

    @Bean
    public UserService userService(UserRepository userRepository, UserConverter userConverter) {
        return new UserService(userRepository, userConverter);
    }
}
