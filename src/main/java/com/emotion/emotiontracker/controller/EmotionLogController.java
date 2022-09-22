package com.emotion.emotiontracker.controller;


import com.emotion.emotiontracker.dto.EmotionLogDto;
import com.emotion.emotiontracker.dto.EmotionLogsRequest;
import com.emotion.emotiontracker.dto.EmotionLogsResponse;
import com.emotion.emotiontracker.dto.JoinSolutionsToEmotionLogRequest;
import com.emotion.emotiontracker.service.EmotionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/emotion-log")
public class EmotionLogController {

    private final EmotionLogService emotionLogService;

    @PostMapping("/add")
    public void addEmotionLog(@RequestBody EmotionLogDto emotionLogDto) {
        emotionLogService.createEmotionLog(emotionLogDto);
    }

    @PostMapping("/update")
    public void updateEmotionLog(@RequestBody EmotionLogDto emotionLogDto) {
        emotionLogService.modifyEmotionLog(emotionLogDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeEmotionLog(@PathVariable String id) {
        emotionLogService.deleteEmotionLog(id);
    }

    @PostMapping("/join-solutions")
    public void joinSolutions(@RequestBody JoinSolutionsToEmotionLogRequest joinSolutionsToEmotionLogRequest) {
        emotionLogService.joinSolutions(joinSolutionsToEmotionLogRequest);
    }

    @PostMapping
    public EmotionLogsResponse getEmotionLogsByUserId(@RequestBody EmotionLogsRequest emotionLogsRequest) {
        return emotionLogService.getEmotionLogsByUser(emotionLogsRequest);
    }
}
