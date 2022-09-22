package com.emotion.emotiontracker.controller;

import com.emotion.emotiontracker.dto.GetReasonsResponse;
import com.emotion.emotiontracker.dto.ReasonDto;
import com.emotion.emotiontracker.service.ReasonService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reason")
public class ReasonController {

    private final ReasonService reasonService;

    @PostMapping("/add")
    public void addReason(@RequestBody ReasonDto reasonDto) {
        reasonService.createReason(reasonDto);
    }

    @GetMapping("/select")
    public GetReasonsResponse selectReasons() {
        List<ReasonDto> reasons = reasonService.selectReasons();
        GetReasonsResponse getReasonsResponse = new GetReasonsResponse(reasons);
        return getReasonsResponse;
    }

    @PostMapping("/update")
    public void updateReason(@RequestBody ReasonDto reasonDto) {
        reasonService.modifyReason(reasonDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeReason(@PathVariable String id) {
        reasonService.deleteReason(id);
    }
}
